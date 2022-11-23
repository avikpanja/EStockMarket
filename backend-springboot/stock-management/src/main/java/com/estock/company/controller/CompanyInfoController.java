package com.estock.company.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estock.company.entity.CompanyInfoEntity;
import com.estock.company.model.CompanyInfo;
import com.estock.company.service.CompanyService;
import com.estock.company.util.ResponseHandler;

//@CrossOrigin("*")
@RestController
@RequestMapping("api/v1.0/market/company")
public class CompanyInfoController {

	@Autowired
	CompanyService companyService;
	
	/**
	 * Register a new company
	 * 
	 * @param company basic details
	 * @return on successful registration company details will be returned
	 * @throws Exception 
	 */
	@PostMapping(value = "/register", consumes="application/json")
	public ResponseEntity<?> registerNewCompany(@RequestBody CompanyInfo info) throws Exception {
		CompanyInfoEntity result = companyService.registerNewComapny(info);
		return ResponseEntity.ok().body( 
				ResponseHandler.generateResponse("Company registered successfully", HttpStatus.CREATED, result)
		);
	}
	
	/**
	 * This API returns all company details
	 * 
	 * @return all company details will be returned
	 */
	@GetMapping("/getall")
	public ResponseEntity<?> getAllCompaniesInfo() {
		System.out.println("getAllCompaniesInfo called");
		List<CompanyInfoEntity> companylist = companyService.getAllCompanyInfo();
		if(!companylist.isEmpty()) {
			CacheControl cacheControl = CacheControl.maxAge(10, TimeUnit.MINUTES);
			
			return ResponseEntity.ok()
					//.cacheControl(cacheControl)
					.body(ResponseHandler.generateResponse("Company records fetched successfully", HttpStatus.OK, companylist));
		}
		return ResponseHandler.generateResponse("No data found", HttpStatus.NO_CONTENT, null);
	}
	
	@GetMapping("/info/{companycode}")
	public ResponseEntity<?> getCompanyDetails(@PathVariable("companycode") String companyCode) {
		Optional<CompanyInfoEntity> infoObj = companyService.getCompanyInfo(companyCode);
		return ResponseEntity.ok().body(
				ResponseHandler.generateResponse("Company record fetched successfully", HttpStatus.OK, infoObj));
	}
	
	@DeleteMapping("/delete/{companycode}")
	public ResponseEntity<?> deleteOneCompany(@PathVariable("companycode") String companyCode) {
		Optional<CompanyInfoEntity> oldObj = companyService.removeOneCompany(companyCode);
		return ResponseHandler.generateResponse("Company record deleted successfully", HttpStatus.OK, oldObj);
	}
	
	@PutMapping(value = "/put", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCompanyInfo(@RequestBody CompanyInfo newObj) throws Exception {
		Optional<CompanyInfoEntity> oldObj = companyService.updateCompanyInfo(newObj);
		return ResponseHandler.generateResponse("Company record updated successfully", HttpStatus.CREATED, oldObj.get());
	}
}
