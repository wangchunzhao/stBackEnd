package com.qhc.frye.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qhc.order.domain.ContractDto;
import com.qhc.order.service.ContractService;

@SpringBootTest
class ContractServiceTest {
	
	@Autowired
	ContractService contractService;

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testFind() {
		List<ContractDto> list = contractService.find(new HashMap<String, Object>()).getRows();
		System.out.println(list);
	}

}
