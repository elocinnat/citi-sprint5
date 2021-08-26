package com.hackathon.demo.service;

import com.hackathon.demo.entity.*;
import com.hackathon.demo.repository.AssetRepository;
import com.hackathon.demo.repository.TradeRepository;
import lombok.extern.slf4j.Slf4j;
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
    public void createTrade(double price, String type, int qty, String ticker) throws IOException {
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
                System.out.println("User has enough asset to buy the stock.");
                handleBuyAsset(trade);
                user.currency = user.currency - amount;
                return true;
            }
            return false;
        }
        else{
            if (assetRepository.existsByTicker(trade.getTicker())){
                if (checkAssetQty(trade)) {
                    handleSellAsset(trade);
                    user.currency = user.currency + amount;
                    return true;
                }
                else return false;
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
                thisTrade.setState(TradeState.FILLED);
            }
            else {
                thisTrade.setState(TradeState.REJECTED);
            }
            tradeRepository.save(thisTrade);
        }
    }


    @Override
    public void handleBuyAsset(Trade trade) throws IOException {

        String ticker = trade.getTicker();
        stockInfoService.getResponseBody(ticker);
        if (assetRepository.existsByTicker(ticker)){
            System.out.println("Asset already exists.");
            Asset existedAsset = assetRepository.findByTicker(ticker).get(0);
            int newQty = existedAsset.getQty() + trade.getQuantity();
            existedAsset.setQty(newQty);
            existedAsset.setValuation(getValuation(stockInfoService.getPrice(), newQty));
            saveAsset(existedAsset);
        }
        else {
            System.out.println("Asset does not exist.");
            Asset thisAsset = new Asset();
            thisAsset.setTicker(ticker);
            thisAsset.setName(stockInfoService.getName());
            thisAsset.setPnl(getProfitLoss(stockInfoService.getPrice(), trade.getPrice()));
            thisAsset.setTradedPrice(trade.getPrice());
            thisAsset.setQty(trade.getQuantity());
            thisAsset.setValuation(getValuation(stockInfoService.getPrice(), trade.getQuantity()));
            saveAsset(thisAsset);
        }
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
            thisAsset.setValuation(getValuation(thisAsset.getTradedPrice(), thisAsset.getQty()));
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

    @Scheduled(fixedRateString = "${scheduleRateMs:10000}")
    @Override
    public void processTrades() throws IOException {
        findTradesForFilling();
        findTradesForProcessing();
    }

}
