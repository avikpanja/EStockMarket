package com.estock.company.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estock.company.entity.CompanyInfoEntity;
import com.estock.company.entity.StockPriceEntity;
import com.estock.company.exception.DataAlreadyExistsException;
import com.estock.company.exception.NoDataFoundException;
import com.estock.company.model.CompanyInfo;
import com.estock.company.repo.CompanyInfoRepo;
import com.estock.company.repo.StockRepository;
import com.estock.company.util.Util;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	CompanyInfoRepo infoRepo;
	
	@Autowired
	StockRepository stockRepo;

	@Override
	public CompanyInfoEntity registerNewComapny(CompanyInfo newDetails) throws Exception {
		System.out.println("*********** registerNewComapny called ******* " + this);
		Optional<CompanyInfoEntity> oldDetails = getOneCompanyInfo(newDetails.getCompanyCode());
		if(oldDetails.isEmpty()) {
			CompanyInfoEntity infoEntity = (CompanyInfoEntity) Util.convertObject(newDetails, CompanyInfoEntity.class);
			return infoRepo.saveAndFlush(infoEntity);
		}
		throw new DataAlreadyExistsException();
	}

	@Override
	public List<CompanyInfoEntity> getAllCompanyInfo() {
		return infoRepo.findAll();
	}

	@Override
	public Optional<CompanyInfoEntity> getCompanyInfo(String companyCode) {
		Optional<CompanyInfoEntity> infoObj = getOneCompanyInfo(companyCode);
		if(infoObj.isPresent()) {
			return infoObj; 
		}
		throw new NoDataFoundException();
	}

	@Override
	public Optional<CompanyInfoEntity> removeOneCompany(String companyCode) {
		Optional<CompanyInfoEntity> infoObj = getOneCompanyInfo(companyCode);
		if(infoObj.isPresent()) {
			infoRepo.deleteById(companyCode);
			return infoObj; 
		}
		throw new NoDataFoundException();
	}

	@Override
	public Optional<CompanyInfoEntity> updateCompanyInfo(CompanyInfo newDetails) throws Exception {
		Optional<CompanyInfoEntity> oldDetails = getOneCompanyInfo(newDetails.getCompanyCode());
		if(oldDetails.isPresent()) {
			CompanyInfoEntity infoEntity = (CompanyInfoEntity) Util.convertObject(newDetails, CompanyInfoEntity.class);
			infoRepo.saveAndFlush(infoEntity);
			return oldDetails; 
		}
		throw new NoDataFoundException();
	}

	private Optional<CompanyInfoEntity> getOneCompanyInfo(String companyCode) {
		return infoRepo.findById(companyCode);
	}
}
