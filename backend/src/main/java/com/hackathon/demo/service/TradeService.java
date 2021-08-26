package com.hackathon.demo.service;

import com.hackathon.demo.entity.Trade;

public interface TradeService {
    boolean checkUserAsset(Trade trade);
    void createTrade(double price, String type, int qty, String ticker);
    void handleBuyAsset(Trade trade);
    void handleSellAsset(Trade trade);
    void processTrades();
}
