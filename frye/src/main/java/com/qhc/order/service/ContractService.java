/**
 * 
 */
package com.qhc.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.pagehelper.PageInfo;
import com.qhc.order.domain.ContractDto;
import com.qhc.order.domain.ContractSignSys;
import com.qhc.order.entity.Contract;
import com.qhc.order.mapper.ContractMapper;
import com.qhc.system.domain.PageHelper;

/**
 * @author walker
 *
 */
@Service
public class ContractService {
	private static final Logger logger = LoggerFactory.getLogger(ContractService.class);
	@Autowired
	private ContractMapper contractMapper;
	@Autowired
	private BestsignService bestsignService;

	public ContractDto findById(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		PageHelper<ContractDto> pageHelper = find(params);
		List<ContractDto> contracts = pageHelper.getRows();
		return contracts.size() > 0 ? contracts.get(0) : null;
	}

	public Contract findOne(Integer id) {
		return contractMapper.findById(id);
	}

	@Transactional
	public Contract save(Contract contract) {
		if (contract.getId() == null) {
			contractMapper.insert(contract);
		} else {
			contractMapper.update(contract);
		}
		
		return contract;
	}

	public PageHelper<ContractDto> find(Map<String, Object> params) {
		// 设置分页信息
		int pageNo = isEmpty(toString(params.get("pageNo"))) ? 0 : Integer.parseInt(params.get("pageNo").toString());
		int pageSize = isEmpty(toString(params.get("pageSize"))) ? 10000
				: Integer.parseInt(params.get("pageSize").toString());
		
		com.github.pagehelper.PageHelper.startPage(pageNo, pageSize);

		List<ContractDto> contracts = contractMapper.findByParams(params);

		PageInfo<ContractDto> pageInfo = new PageInfo<ContractDto>(contracts);
		PageHelper<ContractDto> page = new PageHelper<ContractDto>(pageInfo);

		return page;
	}

	private boolean isEmpty(String v) {
		return v == null || v.trim().length() == 0;
	}

	private String toString(Object v) {
		return toString(v, "");
	}

	private String toString(Object v, String defaultValue) {
		if (v == null) {
			return defaultValue;
		}
		return v.toString();
	}

		/**
	 * 查询电子签约合同状态并更新本地合同状态及电子签约合同id
	 * 
	 * @return
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	@Transactional
	public boolean doRefreshContractState() throws JsonMappingException, JsonProcessingException {
		String states = "03,04,05,06";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("statusList", states.split(","));
		List<ContractDto> contractList = this.find(params).getRows();
		List<ContractSignSys> signList = bestsignService.syncContractSignSysData();
		if (signList == null || signList.size() <= 0) {
			logger.info(System.currentTimeMillis() + ":--update contracts' state--Failed");
			return false;
		}

		for (ContractDto contract : contractList) {
			String fileHashCode = contract.getFileHashcode();
			if (fileHashCode == null || fileHashCode.isEmpty())
				continue;

			ContractSignSys theOne = null;
			for (ContractSignSys one : signList) {
				if (one.getFileHashCode().equalsIgnoreCase(fileHashCode)) {
					if (theOne == null || theOne.getCreateDate().before(one.getCreateDate())) {
						theOne = one;
					}
				}
			}
			String signContractId = theOne != null ? theOne.getSignContractId() : null;
			
			String status = "03";
			if (signContractId != null) {
				status = bestsignService.getContractStatus(signContractId, contract.getCustomerName());
			}
			if (!contract.getStatus().equals(Integer.parseInt(status))) {
				// 更新合同状态及电子签约合同ID
				Contract c = new Contract();
				c.setId(contract.getId());
				c.setSignContractid(signContractId);
				c.setStatus(status);
				updateStatus(c);
			}
		}
		return true;
	}

	/**
	 * 
	 * 签署合同
	 * 
	 * @param contractId 合同ID
	 * @return
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	@Transactional
	public boolean doSignContract(int contractId) throws JsonMappingException, JsonProcessingException {
		ContractDto contract = this.findById(contractId);

		if (contract == null)
			return false;

		// 电子签约中合同Id
		String signContractId = contract.getSignContractid();
		boolean result = bestsignService.doSignContract(signContractId);
		String state = bestsignService.getContractStatus(signContractId, contract.getCustomerName());
		System.out.println("state:" + state);
		if (result || state.equals("06")) {
//			contract.setStatus("06");

			// 使用上上签电子合同状态更新数据库更新合同状态
//			this.updateStatus(contractId, 6);
			Contract c = new Contract();
			c.setId(contractId);
			c.setStatus("06");
			updateStatus(c);
		}

		return false;
	}

	/**
	 * 值更新合同状态及与状态变化相关的字段
	 * 
	 * @param contract
	 */
	@Transactional
	public void updateStatus(Contract contract) {
		contractMapper.updateStatus(contract);
	}
}
