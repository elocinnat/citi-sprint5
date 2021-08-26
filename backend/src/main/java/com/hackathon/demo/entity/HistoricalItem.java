package com.hackathon.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Hashtable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalItem {
    private Date date;
    private HistoricalPrices prices;

}
