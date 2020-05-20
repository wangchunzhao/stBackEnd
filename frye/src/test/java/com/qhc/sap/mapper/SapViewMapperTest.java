package com.qhc.sap.mapper;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
      Map<String, Object> params = new HashMap<>();
      params.put("code", "000000000004077722");
      params.put("name", "金");
      mapper.findMaterialInfo(params);
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
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("clazzCode", "0000001004");
      params.put("name", "01");
		mapper.findCustomer(params);
	}

}
