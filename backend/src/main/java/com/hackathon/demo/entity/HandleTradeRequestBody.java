package com.hackathon.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HandleTradeRequestBody {

    private String type;
    private String ticker;
    private int qty;

}

