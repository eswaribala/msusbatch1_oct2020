package com.virtusa.marketdatasink.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.virtusa.marketdatasink.models.MarketData;

public interface MarketDataRepository extends MongoRepository<MarketData, String> {

}
