package com.hackathon.demo.controller;

import com.hackathon.demo.entity.*;
import com.hackathon.demo.repository.AssetRepository;
import com.hackathon.demo.repository.TradeRepository;
import com.hackathon.demo.repository.UserRepository;
import com.hackathon.demo.service.TradeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value="/user/wallet-transaction", consumes="application/json", produces="application/json")
    @ResponseStatus
    public ResponseEntity handleWallet(@RequestBody WalletRequest requestBody) {

        String type = requestBody.getType();
        double quantity = requestBody.getQty();

        try {
            User user = userRepository.findThisUserById("demo");
            if (type.toLowerCase().equals("deposit")){
                user.setCurrency(user.getCurrency()+quantity);
            }
            else{
                user.setCurrency(user.getCurrency()-quantity);
            }
            userRepository.save(user);
            return new ResponseEntity(200, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity(400, HttpStatus.BAD_REQUEST);
        }
    }
}
