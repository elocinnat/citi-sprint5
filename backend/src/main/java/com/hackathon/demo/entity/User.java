package com.hackathon.demo.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    public String userName;
    public double currency;
    @Override
    public String toString() {
        return String.format("Total currency: %,.2f USD", currency);
    }
}
