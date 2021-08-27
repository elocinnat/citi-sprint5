package com.hackathon.demo.controller;

import com.hackathon.demo.entity.HistoricalItem;
import com.hackathon.demo.entity.Stock;
import com.hackathon.demo.service.StockInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class StockInfoController {

    @Autowired
    private StockInfoServiceImpl stockInformation;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("model");
        return "home";
    }

    @GetMapping ("/search/{stock}")
    @ResponseBody
    public Stock handleStockPrice(@PathVariable String stock) throws IOException {
        stockInformation.getResponseBody(stock);
        Stock tmpStock = new Stock(stock, stockInformation.getName(), stockInformation.getPrice(), stockInformation.getDescription());
        return tmpStock;
    }

    @GetMapping ("/historical-prices/{stock}")
    @ResponseBody
    public List<HistoricalItem> handleHistoricalPrices(@PathVariable String stock) throws IOException {
        return stockInformation.getHistoricalData(stock);
    }



}
