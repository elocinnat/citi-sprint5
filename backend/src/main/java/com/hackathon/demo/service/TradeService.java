package com.hackathon.demo.service;

import com.hackathon.demo.entity.Trade;
import com.hackathon.demo.entity.TradeType;

import java.io.IOException;

public interface TradeService {
    boolean handleUserCurrency(Trade trade);
    void createTrade(double price, String type, int qty, String ticker);
    void processTrades() throws IOException;
    void handleAsset(Trade trade) throws IOException;
}
