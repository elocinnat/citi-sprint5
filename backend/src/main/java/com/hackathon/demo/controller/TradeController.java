package com.hackathon.demo.controller;

import com.hackathon.demo.entity.HandleTradeRequestBody;
import com.hackathon.demo.entity.Trade;
import com.hackathon.demo.entity.TradeType;
import com.hackathon.demo.entity.Asset;
import com.hackathon.demo.entity.Trade;
import com.hackathon.demo.entity.TradeRequestBody;
import com.hackathon.demo.entity.TradeType;
import com.hackathon.demo.repository.AssetRepository;
import com.hackathon.demo.repository.TradeRepository;
import com.hackathon.demo.service.StockInfoServiceImpl;
import com.hackathon.demo.service.TradeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@Controller
public class TradeController {
    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private StockInfoServiceImpl stockInformation;

    @Autowired
    private TradeServiceImpl tradeService;

    @Autowired
    private AssetRepository assetRepository;

    @PostMapping(value="/trade", consumes="application/json", produces="application/json")
    @ResponseStatus
    public ResponseEntity handleTrade(@RequestBody TradeRequestBody requestBody) {

        String ticker = requestBody.getTicker();
        String type = requestBody.getType();
        int quantity = requestBody.getQty();
        try {
            stockInformation.getResponseBody(ticker);
            double price = stockInformation.getPrice();
            tradeService.createTrade(price, type, quantity, ticker);
            return new ResponseEntity(200, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity(400, HttpStatus.BAD_REQUEST);
        }
    }
}
