package com.estock.company.service;

import java.util.List;
import java.util.Optional;

import com.estock.company.entity.CompanyInfoEntity;
import com.estock.company.entity.StockPriceEntity;
import com.estock.company.model.CompanyInfo;

public interface CompanyService {

	public CompanyInfoEntity registerNewComapny(CompanyInfo info) throws Exception;
	public List<CompanyInfoEntity> getAllCompanyInfo();
	public Optional<CompanyInfoEntity> getCompanyInfo(String companyCode);
	public Optional<CompanyInfoEntity> removeOneCompany(String companyCode);
	public Optional<CompanyInfoEntity> updateCompanyInfo(CompanyInfo info) throws Exception;
	
}
