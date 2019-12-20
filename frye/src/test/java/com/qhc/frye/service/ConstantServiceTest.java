package com.qhc.frye.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qhc.order.service.ConstantService;

@SpringBootTest
class ConstantServiceTest {
	@Autowired
	ConstantService constantService;

	@Test
	void testFindIndustryCodeByCode() {
		constantService.findIndustryCodeByCode("abc");
	}

	@Test
	void testFindAllIndustryCodeFordealer() {
		constantService.findFordealerIndustryCodes();
	}

}
