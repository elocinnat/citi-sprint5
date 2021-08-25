package com.hackathon.demo.controller;

import com.hackathon.demo.entity.Trade;
import com.hackathon.demo.entity.TradeType;
import com.hackathon.demo.repository.TradeRepository;
import com.hackathon.demo.service.StockInfoServiceImpl;
import com.hackathon.demo.service.TradeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @PostMapping("/trade")
    @ResponseBody
    public void handleTrade(@RequestParam("type") String type,
                            @RequestParam("qty") Integer quantity,
                            @RequestParam("ticker") String ticker) throws IOException {
        stockInformation.getResponseBody(ticker);
        double price = stockInformation.getPrice();
        tradeService.createTrade(price, type, quantity, ticker);
    }

}
