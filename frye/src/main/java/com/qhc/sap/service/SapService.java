package com.qhc.sap.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.qhc.sap.domain.Bom;
import com.qhc.sap.domain.BomBodyParam;
import com.qhc.sap.domain.BomHeadParam;
import com.qhc.utils.HttpUtil;

/**
 * SAP外部接口
 * 
 * @author Walker
 *
 */
@Service
public class SapService {
	@Value("${sap.material.addr}")
	String materialUrl;
	
	@Value("${sap.bomExplosion.addr}")
	String bomExplosionUrl;
	
	private ObjectMapper objectMapper = new ObjectMapper();

	// BOM
	public Map<String, List<Bom>> getBomExplosion(Map<String, String> mapParam) {
		Map<String, List<Bom>> map = new HashMap<String, List<Bom>>();

		try {
			List<Bom> standard = new ArrayList<Bom>();
			List<Bom> optional = new ArrayList<Bom>();
			// 请求参数
			BomHeadParam bomHeadParam = new BomHeadParam();
			bomHeadParam.setMatnr(mapParam.get("bom_code"));
			bomHeadParam.setWerks("0841");
			bomHeadParam.setStlan("1");
			// 去掉map中的物料号，只留特征，循环插入list
			mapParam.remove("bom_code");
			List list = new ArrayList<>();
			for (Map.Entry<String, String> entity : mapParam.entrySet()) {
				BomBodyParam atnam = new BomBodyParam();
				atnam.setAtnam(entity.getKey());
				atnam.setAtwrt(entity.getValue());
				list.add(atnam);
			}
			bomHeadParam.setCharac(list);
			// BOM接口的请求参数
			String bomParam = objectMapper.writeValueAsString(bomHeadParam);

			// 发送请求获取数据
			String jsonResult = HttpUtil.postbody(bomExplosionUrl, bomParam);
			JsonNode jsonNode = objectMapper.readTree(jsonResult);
			Object message = jsonNode.get("message");
			if (!message.equals("Success")) {
				throw new RuntimeException(String.format("Failed get bom from sap. response: %s", jsonNode));
			}
			// optional
			JsonNode data = jsonNode.get("data");
			// default standard
			JsonNode dataDef = jsonNode.get("data_def");
			
			standard = convert((ArrayNode)dataDef);
			optional = convert((ArrayNode)data);
			
			map.put("optional", optional);
			map.put("standard", standard);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}

	/**
	 * convert sap bom to Bom object
	 * 
	 * @param dataArray
	 * @return
	 */
	private List<Bom> convert(ArrayNode dataArray) {
		List<Bom> list = new ArrayList<Bom>();
		for (int i = 0; i < dataArray.size(); i++) {
			JsonNode obj = dataArray.get(i);
			//
			String code = StringUtils.trimToEmpty(obj.get("matnr_stpo").asText());
			String parentCode = StringUtils.trimToEmpty(obj.get("matnr").asText());
			String price = StringUtils.trimToEmpty(obj.get("stprs").asText());
			String quantity = StringUtils.trimToEmpty(obj.get("menge").asText());
			Boolean configurable = StringUtils.isNotEmpty(obj.get("kzkfg").asText()) ? true : false;
			Boolean marked = StringUtils.isNotEmpty(obj.get("mark").asText()) ? true : false;
			//
			Bom bom = new Bom();
			bom.setCode(code);
			bom.setParentCode(parentCode);
			bom.setPrice(NumberUtils.toDouble(price, 0));
			bom.setQuantity(NumberUtils.toDouble(quantity, 0));
			bom.setConfigurable(configurable);
			bom.setMarked(marked);
			
			list.add(bom);
		}
		
		return list;
	}
}
