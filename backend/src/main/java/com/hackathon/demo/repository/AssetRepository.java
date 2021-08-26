package com.hackathon.demo.repository;

import com.hackathon.demo.entity.Asset;
import com.hackathon.demo.entity.Trade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssetRepository extends CrudRepository<Asset, String>{
    List<Asset> findByTicker(String ticker);
    boolean existsByTicker(String ticker);
    List<Asset> findAllByTicker(String ticker);
}