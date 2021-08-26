package com.hackathon.demo;

import com.hackathon.demo.service.StockInfoService;
import com.hackathon.demo.service.StockInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class StockMarketTradingBackentApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(StockMarketTradingBackentApplication.class, args);

    }

}
