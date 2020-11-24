package com.virtusa.marketdatasink.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.marketdatasink.models.MarketData;
import com.virtusa.marketdatasink.repositories.MarketDataRepository;



@Service
public class MarketDataService {
	@Autowired
	private MarketDataRepository marketDataRepository;
	public void addMarketData(List marketDataList)
	{
		for(Object marketData : marketDataList)
		{
		  MarketData obj=(MarketData) marketData;	
		  this.marketDataRepository.save(obj);
		}
		
	}

}

