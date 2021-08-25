package com.hackathon.demo.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.persistence.Entity;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
public class User {
    public static double currency = 100000;

    @Override
    public String toString() {
        return String.format("Total currency: %,.2f USD", currency);
    }
}
