package com.estock.company.repo;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.estock.company.entity.CompanyInfoEntity;
import com.estock.company.model.CompanyInfo;

@DataJpaTest
@AutoConfigureMockMvc
class CompanyInfoRepoTest {

//	//@Autowired
//	@Mock
//	CompanyInfoRepo infoRepo;
//	
//	CompanyInfoEntity infoEntity;
//	
//	@BeforeEach
//	public void init() {
//		//infoEntity = Mockito.mock(CompanyInfoEntity.class);
//		infoEntity = new CompanyInfoEntity();
//		infoEntity.setCompanyCode("cts");
//		infoEntity.setCompanyName("Cognizant");
//		infoEntity.setCeo("Brian");
//		infoEntity.setTurnover(100000.00);
//		infoEntity.setWebsite("abc");
//		infoEntity.setStockExchange("BSE");
//	}
//	
//	@Test
//	public void findAll_Success() {
//		
//		when(infoRepo.findAll()).thenReturn(List.of(infoEntity));
//		
//		List<CompanyInfoEntity> list = infoRepo.findAll();
//		assertNotNull(list);
//		assertEquals(1,list.size());
//	}
//	
//	@Test
//	public void findAll_Failure() {
//		when(infoRepo.findAll()).thenReturn(null);	
//		List<CompanyInfoEntity> list = infoRepo.findAll();
//		assertNull(list);
//	}
//
//	@Test
//	public void addCompanyInfo_Success() {
//		when(infoRepo.save(any())).thenReturn(infoEntity);
//		CompanyInfoEntity savedObj =  infoRepo.save(infoEntity);
//		System.out.println("************************************** "+infoEntity.hashCode() + " " + savedObj.hashCode());
//		assertEquals(infoEntity.getCompanyName(), savedObj.getCompanyName());
//	}
}
