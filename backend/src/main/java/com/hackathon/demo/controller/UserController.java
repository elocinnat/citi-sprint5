package com.hackathon.demo.controller;

import com.hackathon.demo.entity.Asset;
import com.hackathon.demo.entity.Trade;
import com.hackathon.demo.entity.User;
import com.hackathon.demo.repository.AssetRepository;
import com.hackathon.demo.repository.TradeRepository;
import com.hackathon.demo.service.TradeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private User user;

    @Autowired
    private TradeServiceImpl tradeService;

    @GetMapping("/user/history")
    @ResponseBody
    public Iterable<Trade> getUserAllTrades(){
        return tradeRepository.findAll();
    }

    @GetMapping("/user/asset")
    @ResponseBody
    public Iterable<Asset> getUserAssets(){
        return assetRepository.findAll();
    }

    @GetMapping("/user")
    @ResponseBody
    public String getUserCurrency(){
        return user.toString();
    }
}
