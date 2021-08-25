package com.hackathon.demo.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Stock {
    private String ticker;
    private String name;
    private double price;
    private String description;


    @Override
    public String toString() {
        return "{" +
                "ticker='" + ticker + '\'' +
                ", name='" + name + '\'' +
                ", Open price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
