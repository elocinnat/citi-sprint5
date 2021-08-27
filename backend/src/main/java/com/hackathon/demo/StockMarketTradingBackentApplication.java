package com.hackathon.demo;

import com.hackathon.demo.entity.User;
import com.hackathon.demo.repository.UserRepository;
import com.hackathon.demo.service.StockInfoService;
import com.hackathon.demo.service.StockInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class StockMarketTradingBackentApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StockMarketTradingBackentApplication.class, args);
        UserRepository users = context.getBean(UserRepository.class);
        User user = new User("demo", 100000);
        users.save(user);
        log.info(users.findThisUserById("demo").toString());
    }

}
