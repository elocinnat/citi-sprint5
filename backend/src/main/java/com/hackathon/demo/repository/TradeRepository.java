package com.hackathon.demo.repository;

import com.hackathon.demo.entity.TradeType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.hackathon.demo.entity.Trade;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TradeRepository extends CrudRepository<Trade,Integer>{
    @Query(value = "SELECT * FROM trade WHERE state=:mystate", nativeQuery = true)
    List<Trade> findByState(@Param("mystate") String state);
}
