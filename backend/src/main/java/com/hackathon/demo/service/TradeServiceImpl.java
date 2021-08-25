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
    public void createTrade(double price, String type, int qty, String ticker){
        Trade tmpTrade = new Trade();
        tmpTrade.setPrice(price);
        tmpTrade.setQuantity(qty);
        tmpTrade.setTicker(ticker.toUpperCase());
        tmpTrade.setType(type.toLowerCase().equals("buy")? TradeType.BUY:TradeType.SELL);
        tradeRepository.save(tmpTrade);
    }

    @Override
    public boolean handleUserCurrency(Trade trade){
        double amount = trade.getPrice()*trade.getQuantity();
        if (trade.getType().equals(TradeType.BUY)&& user.currency>amount){
            user.currency = user.currency-amount;
            return true;
        }
        else if (trade.getType().equals(TradeType.SELL)){
            user.currency = user.currency+amount;
            return true;
        }
        return false;
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
            if(handleUserCurrency(thisTrade)) {
                thisTrade.setState(TradeState.FILLED);
                handleAsset(thisTrade);
            }
            else {
                thisTrade.setState(TradeState.REJECTED);
            }
            tradeRepository.save(thisTrade);
        }
    }

    @Scheduled(fixedRateString = "${scheduleRateMs:10000}")
    @Override
    public void processTrades() throws IOException {
        findTradesForFilling();
        findTradesForProcessing();
    }

    @Override
    public void handleAsset(Trade trade) throws IOException {

        // Check if asset alr exists
        Asset thisAsset = new Asset();
        String ticker = trade.getTicker();
        stockInfoService.getResponseBody(ticker);
        thisAsset.setTicker(ticker);
        thisAsset.setName(stockInfoService.getName());
        thisAsset.setPnl(getProfitLoss(stockInfoService.getPrice(),trade.getPrice()));
        thisAsset.setTradedPrice(trade.getPrice());
        thisAsset.setQty(trade.getQuantity());
        thisAsset.setValuation(getValuation(stockInfoService.getPrice(),trade.getQuantity()));
        assetRepository.save(thisAsset);
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
}
