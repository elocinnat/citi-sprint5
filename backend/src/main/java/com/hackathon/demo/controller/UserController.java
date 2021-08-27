package com.hackathon.demo.controller;

import com.hackathon.demo.entity.Asset;
import com.hackathon.demo.entity.Trade;
import com.hackathon.demo.entity.User;
import com.hackathon.demo.repository.AssetRepository;
import com.hackathon.demo.repository.TradeRepository;
import com.hackathon.demo.repository.UserRepository;
import com.hackathon.demo.service.TradeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class UserController {
    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TradeServiceImpl tradeService;

    @GetMapping("/user/history")
    @ResponseBody
    public Iterable<Trade> getUserAllTrades(){
        return tradeRepository.findAllInDesc();
    }

    @GetMapping("/user/asset")
    @ResponseBody
    public Iterable<Asset> getUserAssets(){
        return assetRepository.findAll();
    }

    @GetMapping("/user")
    @ResponseBody
    public double getUserCurrency(){
        return userRepository.findThisUserById("demo").getCurrency();
    }
}
