package com.hackathon.demo.repository;

import com.hackathon.demo.entity.Asset;
import com.hackathon.demo.entity.Trade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssetRepository extends CrudRepository<Asset, String>{
    @Query(value="SELECT * FROM hackathon.asset where ticker=?1 limit 1;", nativeQuery = true)
    Asset findByThisTicker(String ticker);

    List<Asset> findByTicker(String ticker);
    boolean existsByTicker(String ticker);
    List<Asset> findAllByTicker(String ticker);
}