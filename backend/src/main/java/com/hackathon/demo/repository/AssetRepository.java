package com.hackathon.demo.repository;

import com.hackathon.demo.entity.Asset;
import com.hackathon.demo.entity.Trade;
import org.springframework.data.repository.CrudRepository;

public interface AssetRepository extends CrudRepository<Asset, String>{
}