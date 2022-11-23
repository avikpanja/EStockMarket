package com.estock.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estock.company.entity.StockPriceEntity;
import com.estock.company.model.StockModel;
import com.estock.company.service.StockServiceImpl;
import com.estock.company.util.ResponseHandler;

@RestController
@RequestMapping("/api/v1.0/market/stock")
//@CrossOrigin("*")
public class StockPriceController {

	@Autowired
	StockServiceImpl stockService;
	
	@PostMapping(value="/add")
	public ResponseEntity<?> updateStockPrice(@RequestBody StockModel info) {
		if(stockService.updateCompanyStockDetails(info)) {
			return ResponseHandler.generateResponse("Stock details updated successfully", HttpStatus.CREATED, null);
		}
		return ResponseHandler.generateResponse("Stock details updation unsuccessful", HttpStatus.CONFLICT, null);
	}
	
	@GetMapping(value="/stockHistory/{companycode}")
	public ResponseEntity<?> getFullStockHistory(@PathVariable("companycode") String companyCode) {
		List<StockPriceEntity> stockList = stockService.getFullStockHistory(companyCode);
		System.out.println(stockList);
		if(stockList!=null && !stockList.isEmpty()) {
			return ResponseHandler.generateResponse("Stock details fetched successful", HttpStatus.OK, stockList);
		}
		return ResponseHandler.generateResponse("No Data Found", HttpStatus.NO_CONTENT, null);
	}
}
