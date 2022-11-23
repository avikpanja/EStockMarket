package com.estock.company.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estock.company.entity.StockPriceEntity;

@Repository
public interface StockRepository extends JpaRepository<StockPriceEntity, Integer> {

	public List<StockPriceEntity> findByCompanyCode(String companyCode);
}
