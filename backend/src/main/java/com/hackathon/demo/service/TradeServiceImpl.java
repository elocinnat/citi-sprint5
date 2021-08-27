package com.hackathon.demo.service;

import com.hackathon.demo.entity.*;
import com.hackathon.demo.repository.AssetRepository;
import com.hackathon.demo.repository.TradeRepository;
import com.hackathon.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.field.FieldDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

import static java.lang.Double.compare;

@Slf4j
@Service
@Configuration
public class TradeServiceImpl implements TradeService{

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private StockInfoServiceImpl stockInfoService;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void createTrade(double price, String type, int qty, String ticker) {
        stockInfoService.getResponseBody(ticker);
        Trade tmpTrade = new Trade();
        tmpTrade.setPrice(price);
        tmpTrade.setQuantity(qty);
        tmpTrade.setTicker(ticker.toUpperCase());
        tmpTrade.setType(type.toLowerCase().equals("buy")? TradeType.BUY:TradeType.SELL);
        tmpTrade.setName(stockInfoService.getName());
        tradeRepository.save(tmpTrade);
    }

    @Override
    public boolean checkUserAsset(Trade trade){
        double amount = trade.getPrice()*trade.getQuantity();
        if (trade.getType().equals(TradeType.BUY)){
            if (Double.compare(userRepository.findThisUserById("demo").getCurrency(), amount)>= 0){
                log.info("User has enough currency to buy the stock.");
                return true;
            }
            else{
                log.info("User does not have enough currency to buy the stock.");
                return false;
            }
        }
        else{
            log.info("Checking assets for SELL Trade.");
            if (assetRepository.existsByTicker(trade.getTicker())){
                log.info("SELL Trade asset exists.");
                if (checkAssetQty(trade)) {
                    log.info("User has enough asset to sell the stock.");
                    return true;
                }
                else {
                    log.info("User does not have enough asset to sell the stock.");
                    return false;
                }
            }
            else {
                log.info("SELL Trade asset does not exist.");
                return false;}
        }
    }

    public boolean checkAssetQty(Trade trade){
        Asset thisAsset = assetRepository.findByThisTicker(trade.getTicker());
        int assetQty = thisAsset.getQty();
        int tradeQty = trade.getQuantity();
        if (assetQty >= tradeQty){
            log.info("Trade quantity is valid");
            return true;
        }
        else {
            log.info("Invalid trade quantity");
            return false;}
    }

    @Transactional
    public void findTradesForProcessing(){
        List<Trade> foundTrades = tradeRepository.findByState("CREATED");
        for(Trade thisTrade: foundTrades) {
            thisTrade.setState(TradeState.PROCESSING);
            tradeRepository.save(thisTrade);
        }
    }

    @Transactional
    public void findTradesForFilling() {
        List<Trade> foundTrades = tradeRepository.findByState("PROCESSING");

        for(Trade thisTrade: foundTrades) {
            if(checkUserAsset(thisTrade)) {
                thisTrade.setState(TradeState.FILLED);
                tradeRepository.save(thisTrade);
                if (thisTrade.getType().equals(TradeType.BUY)){
                    log.info(thisTrade.toString());
                    handleBuyAsset(thisTrade);
                }
                else{
                    log.info(thisTrade.toString());
                    handleSellAsset(thisTrade);
                }
            }
            else {
                thisTrade.setState(TradeState.REJECTED);
                tradeRepository.save(thisTrade);
            }
        }
    }


    @Override
    @Transactional
    public void handleBuyAsset(Trade trade) {
        String ticker = trade.getTicker();
        stockInfoService.getResponseBody(ticker);
        handleCurrency(trade);
        if (assetRepository.existsByTicker(ticker)){
            log.info("Asset already exists.");
            Asset existedAsset = assetRepository.findByThisTicker(ticker);
            int newQty = Integer.sum(existedAsset.getQty(), trade.getQuantity());
            existedAsset.setTradedPrice(getAvgPrice(existedAsset, trade, stockInfoService.getPrice(), newQty));
            existedAsset.setQty(newQty);
            existedAsset.setRealTimePrice(stockInfoService.getPrice());
            existedAsset.setValuation(getValuation(stockInfoService.getPrice(), newQty));
            existedAsset.setPnl(getProfitLoss(existedAsset.getRealTimePrice(), existedAsset.getTradedPrice()));
            saveAsset(existedAsset);
        }
        else {
            log.info("Asset does not exist. Adding into assets.");
            Asset thisAsset = new Asset();
            thisAsset.setTicker(ticker);
            thisAsset.setName(stockInfoService.getName());
            thisAsset.setPnl(getProfitLoss(stockInfoService.getPrice(), trade.getPrice()));
            thisAsset.setTradedPrice(trade.getPrice());
            thisAsset.setQty(trade.getQuantity());
            thisAsset.setRealTimePrice(stockInfoService.getPrice());
            thisAsset.setValuation(getValuation(stockInfoService.getPrice(), trade.getQuantity()));
            saveAsset(thisAsset);
        }
    }

    @Transactional
    public void handleCurrency(Trade trade){
        double amount = trade.getPrice()*trade.getQuantity();
        double newCurrency;
        if (trade.getType().equals(TradeType.BUY)){
            newCurrency = userRepository.findThisUserById("demo").getCurrency()-amount;
        }
        else{
            newCurrency = userRepository.findThisUserById("demo").getCurrency()+amount;
        }
        User thisUser = userRepository.findThisUserById("demo");
        userRepository.deleteAll();
        thisUser.setCurrency(newCurrency);
        userRepository.save(thisUser);
    }

    public double getAvgPrice(Asset asset, Trade trade, double realTimePrice, int newQty){
        double curTotal = asset.getQty()*asset.getTradedPrice();
        double newTotal = curTotal + trade.getQuantity()*realTimePrice;
        return newTotal/newQty;
    }

    @Transactional
    public void saveAsset(Asset asset){
        assetRepository.save(asset);
    }

    @Override
    @Transactional
    public void handleSellAsset(Trade trade) {
        int tradeQty = trade.getQuantity();
        Asset thisAsset = assetRepository.findByThisTicker(trade.getTicker());
        handleCurrency(trade);
        int assetQty = thisAsset.getQty();
        if (Integer.compare(assetQty,tradeQty)==0){
            assetRepository.delete(thisAsset);
        }
        else {
            thisAsset.setQty(thisAsset.getQty() - tradeQty);
            thisAsset.setRealTimePrice(stockInfoService.getPrice());
            thisAsset.setValuation(getValuation(stockInfoService.getPrice(), thisAsset.getQty()));
            saveAsset(thisAsset);
        }
    }

    public String getProfitLoss(double realTimePrice, double tradedPrice){
        double diff = realTimePrice-tradedPrice;
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMinimumFractionDigits(2);
        String prefix=(diff<0)?"-":"+";
        return prefix+format.format(diff/tradedPrice);
    }

    public double getValuation(double price, int qty){
        return price*qty;
    }

    @Override
    @Scheduled(fixedRateString = "${scheduleRateMs:5000}")
    public void processTrades(){
        findTradesForFilling();
        findTradesForProcessing();
    }

}
