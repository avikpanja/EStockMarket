package com.estock.company.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estock.company.entity.CompanyInfoEntity;
import com.estock.company.entity.StockPriceEntity;
import com.estock.company.exception.NoDataFoundException;
import com.estock.company.model.StockModel;
import com.estock.company.repo.StockRepository;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired CompanyService compService;
	@Autowired StockRepository stockRepo;
	
	public boolean updateCompanyStockDetails(StockModel stockPriceDetails) {
		Optional<CompanyInfoEntity> compDetails = compService.getCompanyInfo(stockPriceDetails.getCompanyCode());
		
		if(compDetails.isPresent()) {
			
			StockPriceEntity sp = new StockPriceEntity();
			sp.setStockPrice(stockPriceDetails.getStockPrice());
			
			compDetails.get().setStockPrice(stockPriceDetails.getStockPrice());
			compDetails.get().setStockPriceHistory(List.of(sp));
			
			sp.setCompInfoEntity(compDetails.get());
			
			if(stockRepo.save(sp)!=null) {
				return true;
			}
			
			return false; 
		}
		throw new NoDataFoundException();
	}
	
	public List<StockPriceEntity> getFullStockHistory(String companyCode) {
		return stockRepo.findByCompanyCode(companyCode);
	}

}
