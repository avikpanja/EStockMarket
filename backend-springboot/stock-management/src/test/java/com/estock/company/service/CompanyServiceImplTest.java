package com.estock.company.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.estock.company.entity.CompanyInfoEntity;
import com.estock.company.model.CompanyInfo;
import com.estock.company.repo.CompanyInfoRepo;
//import com.google.inject.Inject;

@SpringBootTest
@AutoConfigureMockMvc
class CompanyServiceImplTest {
	
//	@Mock
//	CompanyInfoRepo repo;
//	
//	@InjectMocks
//	CompanyServiceImpl companyService;
//
////	@Autowired
////	CompanyService companyService;
//	
//	CompanyInfo companyModel;
//	
//	@BeforeEach
//	public void init() {
//		companyModel = new CompanyInfo();
//		companyModel.setCompanyCode("cts");
//		companyModel.setCompanyName("Cognizant");
//		companyModel.setCeo("Brian");
//		companyModel.setTurnover(100000.00);
//		companyModel.setWebsite("abc");
//		companyModel.setStockExchange("BSE");
//	}
//
//	@Test
//	void testRegisterNewComapny() throws Exception {
////		when(repo.saveAndFlush(any())).thenReturn(companyModel);
////		CompanyInfoEntity savedObj = companyService.registerNewComapny(companyModel);
////		assertEquals(companyModel.getCompanyName(), savedObj.getCompanyName());
//	}
//
//	@Test
//	void testGetAllCompanyInfo() {
//		//fail("Not yet implemented");
//		
//	}
//
//	@Test
//	void testGetCompanyInfo() {
//		//fail("Not yet implemented");
//	}
//
//	@Test
//	void testRemoveOneCompany() {
//		//fail("Not yet implemented");
//	}
//
//	@Test
//	void testUpdateCompanyInfo() {
//		//fail("Not yet implemented");
//	}
//
//	@Test
//	void testUpdateCompanyStockDetails() {
//		//fail("Not yet implemented");
//	}

}
