package com.estock.company.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estock.company.entity.CompanyInfoEntity;

@Repository
public interface CompanyInfoRepo extends JpaRepository<CompanyInfoEntity, String>{
	
}
