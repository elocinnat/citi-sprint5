package com.hackathon.demo.service;

import com.hackathon.demo.entity.Trade;
import com.hackathon.demo.entity.TradeType;

import java.io.IOException;

public interface TradeService {
    boolean checkUserAsset(Trade trade) throws IOException;
    void createTrade(double price, String type, int qty, String ticker);
    void processTrades() throws IOException;
    void handleBuyAsset(Trade trade) throws IOException;
    void handleSellAsset(Trade trade) throws IOException;
}
