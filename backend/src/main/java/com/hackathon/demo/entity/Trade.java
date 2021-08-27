package com.hackathon.demo.entity;
import java.util.Date;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Trade {

    @Id
    @GeneratedValue
    private int _id;

    private Date created = new Date(System.currentTimeMillis());

    @Enumerated(EnumType.STRING)
    private TradeState state = TradeState.CREATED;
    @Enumerated(EnumType.STRING)
    private TradeType type = TradeType.BUY;
    private String ticker;
    private String name;
    private int quantity;
    private double price;

    @Override
    public String toString() {
        return "Trade{" +
                "_id=" + _id +
                ", created=" + created +
                ", state=" + state +
                ", type=" + type +
                ", ticker='" + ticker + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
