package com.qhc.sap.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SapViewMapperTest {
	
	@Autowired
	SapViewMapper mapper;

	@Test
	void testFindMaterialInfoByMaterialId() {
		mapper.findMaterialInfoByMaterialId("");
	}

	@Test
	void testFindCharacteristicValueByClazzCode() {
		mapper.findCharacteristicValueByClazzCode("");
	}

	@Test
	void testFindDefaultCharacterValueByMaterial() {
		mapper.findDefaultCharacterValueByMaterial("");
	}

}
