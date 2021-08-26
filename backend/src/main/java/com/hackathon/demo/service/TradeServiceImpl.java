package com.hackathon.demo.service;

import com.hackathon.demo.entity.*;
import com.hackathon.demo.repository.AssetRepository;
import com.hackathon.demo.repository.TradeRepository;
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

    private User user = new User();


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
    public boolean checkUserAsset(Trade trade) throws IOException {
        double amount = trade.getPrice()*trade.getQuantity();
        if (trade.getType().equals(TradeType.BUY)){
            if (Double.compare(user.currency, amount)>= 0){
                log.info("User has enough currency to buy the stock.");
                return true;
            }
            log.info("User does not have enough currency to buy the stock.");
            return false;
        }
        else{
            if (assetRepository.existsByTicker(trade.getTicker())){
                if (checkAssetQty(trade)) {
                    log.info("User has enough asset to sell the stock.");
                    return true;
                }

                else {
                    log.info("User does not have enough asset to sell the stock.");
                    return false;
                }
            }
            else {return false;}
        }
    }

    public boolean checkAssetQty(Trade trade){
        Asset thisAsset = new Asset();
        thisAsset = assetRepository.findByTicker(trade.getTicker()).get(0);
        if (Integer.compare(thisAsset.getQty(),trade.getQuantity())>=0){
            return true;
        }
        else {return false;}
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
    public void findTradesForFilling() throws IOException {
        List<Trade> foundTrades = tradeRepository.findByState("PROCESSING");

        for(Trade thisTrade: foundTrades) {
            if(checkUserAsset(thisTrade)) {
                double amount = thisTrade.getPrice()*thisTrade.getQuantity();
                if (thisTrade.getType().equals(TradeType.BUY)){
                    log.info(thisTrade.toString());
                    handleBuyAsset(thisTrade);
                    user.currency = user.currency - amount;
                }
                else{
                    handleSellAsset(thisTrade);
                    user.currency = user.currency + amount;
                }

                thisTrade.setState(TradeState.FILLED);
            }
            else {
                thisTrade.setState(TradeState.REJECTED);
            }
            tradeRepository.save(thisTrade);
        }
    }


    @Override
    public void handleBuyAsset(Trade trade) {

        String ticker = trade.getTicker();
        stockInfoService.getResponseBody(ticker);
        if (assetRepository.existsByTicker(ticker)){
            log.info("Asset already exists.");
            Asset existedAsset = assetRepository.findByTicker(ticker).get(0);
            int newQty = Integer.sum(existedAsset.getQty(), trade.getQuantity());
            existedAsset.setTradedPrice(getAvgPrice(existedAsset, trade, stockInfoService.getPrice(), newQty));
            existedAsset.setQty(newQty);
            existedAsset.setRealTimePrice(stockInfoService.getPrice());
            existedAsset.setValuation(getValuation(stockInfoService.getPrice(), newQty));
            saveAsset(existedAsset);
        }
        else {
            log.info("Asset does not exist. Adding into assets.");
            Asset thisAsset = new Asset();
            thisAsset.setTicker(ticker);
            thisAsset.setName(stockInfoService.getName());
            thisAsset.setPnl(getProfitLoss(stockInfoService.getPrice(), trade.getPrice()));
            thisAsset.setTradedPrice(trade.getPrice());
            log.info(Integer.toString(trade.getQuantity()));
            thisAsset.setQty(trade.getQuantity());
            thisAsset.setValuation(getValuation(stockInfoService.getPrice(), trade.getQuantity()));
            saveAsset(thisAsset);
        }
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
    public void handleSellAsset(Trade trade) throws IOException {
        int tradeQty = trade.getQuantity();
        Asset thisAsset = assetRepository.findByTicker(trade.getTicker()).get(0);
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

    @Scheduled(fixedRateString = "${scheduleRateMs:3000}")
    @Override
    public void processTrades() throws IOException {
        findTradesForFilling();
        findTradesForProcessing();
    }

}
