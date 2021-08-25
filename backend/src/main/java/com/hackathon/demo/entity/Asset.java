package com.hackathon.demo.entity;


import com.hackathon.demo.service.StockInfoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    @Id
    private String ticker;
    private String name;
    private int qty;
    private double tradedPrice;
    private double valuation;
    private String pnl;
}
