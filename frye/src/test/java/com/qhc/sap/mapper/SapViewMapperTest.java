package com.qhc.sap.mapper;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.qhc.sap.entity.ClazzCharacteristicValueView;

@SpringBootTest
class SapViewMapperTest {
	
	@Autowired
	SapViewMapper mapper;

	@Test
	void testFindMaterialInfoByMaterialId() {
		mapper.findMaterialInfo("000000000004077722", "金");
	}

	@Test
	void testFindCharacteristicValueByClazzCode() {
	  List<ClazzCharacteristicValueView> list = mapper.findCharacteristicValueByClazzCode("Z4", "BG1QCQ00000");
	  System.out.println(list);
	}

	@Test
	void testFindDefaultCharacterValueByMaterial() {
		mapper.findDefaultCharacterValueByMaterial("");
	}

	@Test
	void testFindCustomer() {
		mapper.findCustomer("0000001004", "01");
	}

}
