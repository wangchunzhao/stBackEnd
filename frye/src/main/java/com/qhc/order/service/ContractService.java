/**
 * 
 */
package com.qhc.order.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.qhc.order.dao.ContractRepository;
import com.qhc.order.domain.ContractView;
import com.qhc.order.entity.Contract;
import com.qhc.order.entity.ContractSignSys;
import com.qhc.system.domain.PageHelper;

/**
 * @author walker
 *
 */
@Service
public class ContractService {
	private static final Logger logger = LoggerFactory.getLogger(ContractService.class);
	@Autowired
	private ConstantService constantService;
	@Autowired
	private BestsignService bestsignService;

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private EntityManager entityManager;

	public ContractView findById(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		PageHelper<ContractView> pageHelper = find(params);
		List<ContractView> contracts = pageHelper.getRows();
		return contracts.size() > 0 ? contracts.get(0) : null;
	}

	public Contract findOne(Integer id) {
		return contractRepository.findById(id).get();
	}

	public Contract save(Contract contract) {
		return contractRepository.saveAndFlush(contract);
	}

	public PageHelper<ContractView> find(Map<String, Object> params) {
		StringBuilder sql = new StringBuilder("select ");
		// 合同号
		sql.append("support.contract_number                as contractNumber,");
		// 支持经理，签约人
		sql.append("support.opterator_domain_id                as opteratorDomainId,");
		sql.append("orders.id                      as orderId,");
		sql.append("orders.sequence_number               as sequenceNumber,");
		// 签约单位 Contract Unit
		sql.append("orders.contractor_code                as contractorCode,");
		sql.append("orders.contractor_name                as contractorName,");
		// 性质分类 Classificationsql.append("orders.contractor_class_code as
		// contractorClassCode,");
		sql.append("orders.contractor_class_name                as contractorClassName,");
		// 版本号 Version Number
		sql.append("version.version                as version,");
		sql.append("version.id                as versionId,");
		sql.append("version.create_time                as createTime,");
		sql.append("contract.id                            as id,");
//		sql.append("contract.sequence_number               as sequenceNumber,");
		sql.append("contract.PartyA_code                   as partyaCode,");
		sql.append("contract.PartyA_name                   as partyaName,");
		sql.append("contract.partyA_mail                   as partyaMail,");
//		sql.append("contract.amount_on_contract            as amountOnContract,");
		sql.append("info.contract_rmb_amount            as amountOnContract,");
		sql.append("contract.delivery_days_after_prepay    as deliveryDaysAfterPrepay,");
		sql.append("contract.client_name                   as clientName,");
		sql.append("contract.install_location              as installLocation,");
		sql.append("contract.quality_stand                 as qualityStand,");
		sql.append("contract.settlement                    as settlement,");
		sql.append("contract.partyA_address                 as partyaAddress,");
		sql.append("contract.invoice_address               as invoiceAddress,");
		sql.append("contract.broker                        as broker,");
		sql.append("contract.invoice_receiver              as invoiceReceiver,");
		sql.append("contract.invoice_tel                   as invoiceTel,");
		sql.append("contract.invoice_post_code             as invoicePostCode,");
		sql.append("contract.company_tel                   as companyTel,");
		sql.append("contract.bank_name                     as bankName,");
		sql.append("contract.account_number                as accountNumber,");
		sql.append("contract.k_order_version_id            as orderVersionId,");
		sql.append("contract.receive_terms_code            as receiveTermsCode,");
		sql.append("contract.receive_terms_name            as receiveTermsName,");
		sql.append("contract.k_acceptance_criteria_code    as acceptanceCriteriaCode,");
//		sql.append("contract.mail                          as mail,");
		sql.append("contract.contractor_1_id               as contractor1Id,");
		sql.append("contract.contractor_1_tel              as contractor1Tel,");
		sql.append("contract.contractor_2_id               as contractor2Id,");
		sql.append("contract.contractor_2_tel              as contractor2Tel,");
		sql.append("contract.contractor_3_id               as contractor3Id,");
		sql.append("contract.contractor_3_tel              as contractor3Tel,");
		sql.append("contract.contract_status              as status,");
		sql.append("contract.production_time              as productionTime,");
		sql.append("contract.send_time              as sendTime, ");
		sql.append("contract.file_hashcode              as fileHashCode, ");
		sql.append("contract.sign_contractid              as signContractId ");
		sql.append(" from k_orders as orders");
		sql.append(" left join k_order_support_info as support on support.k_orders_id = orders.id");
		sql.append(" left join k_order_version as version on version.k_orders_id = orders.id");
		sql.append(" left join k_order_info as info on info.id = version.k_order_info_id");
		sql.append(" left join k_contract as contract on contract.k_order_version_id = version.id");
		sql.append(" where (version.status = 5 or version.status = 6)");
		
		String id = toString(params.get("id"));
		String sequenceNumber = toString(params.get("sequenceNumber"));
		String contractNumber = toString(params.get("contractNumber"));
		String contractManager = toString(params.get("contractManager"));
		String contractorName = toString(params.get("contractorName"));
		String createTime = toString(params.get("createTime"));
		String productionTime = toString(params.get("productionTime"));
		String status = toString(params.get("status"));
		String statusList = toString(params.get("statusList"));
		if (!isEmpty(id)) {
			sql.append(" and contract.id = " + id);
		}
		if (!isEmpty(status)) {
			sql.append(" and contract.contract_status = " + status + "");
		}
		if (!isEmpty(statusList)) {
			sql.append(" and contract.contract_status in (" + statusList + ")");
		}
		if (!isEmpty(sequenceNumber)) {
			sql.append(" and UPPER(orders.sequence_number) like '%" + sequenceNumber.toUpperCase() + "%'");
		}
		if (!isEmpty(contractNumber)) {
			sql.append(" and UPPER(support.contract_number) like '%" + contractNumber.toUpperCase() + "%'");
		}
//		if (!isEmpty(contractManager)) {
//			sql.append(" and support.opterator_domain_id = '" + contractManager + "'");
//		}
		if (!isEmpty(contractorName)) {
			sql.append(" and UPPER(orders.contractor_name) like '%" + contractorName.toUpperCase() + "%'");
		}
		if (!isEmpty(createTime)) {
			//2019-04-07 ~ 2019-11-07
			String[] strtimes = createTime.split("~");
			String start = strtimes[0].trim();
			String end = strtimes[1].trim();
			params.put("start", start);
			params.put("end", end);
			sql.append(" and DATE_FORMAT(version.create_time, '%Y-%m-%d') >= '" + start + "'");
			sql.append(" and DATE_FORMAT(version.create_time, '%Y-%m-%d') <= '" + end + "'");
		}
		if (!isEmpty(productionTime)) {
			//2019-04-07 ~ 2019-11-07
			String[] strtimes = productionTime.split("~");
			String start = strtimes[0].trim();
			String end = strtimes[1].trim();
			params.put("start", start);
			params.put("end", end);
			sql.append(" and DATE_FORMAT(contract.production_time, '%Y-%m-%d') >= '" + start + "'");
			sql.append(" and DATE_FORMAT(contract.production_time, '%Y-%m-%d') <= '" + end + "'");
		}

		Query query = entityManager.createNativeQuery(sql.toString() + " order by version.create_time desc");

		// 设置分页信息
		int pageNo = isEmpty(toString(params.get("pageNo"))) ? 0 : Integer.parseInt(params.get("pageNo").toString());
		int pageSize = isEmpty(toString(params.get("pageSize"))) ? 10000
				: Integer.parseInt(params.get("pageSize").toString());
		query.setFirstResult(pageNo * pageSize);
		query.setMaxResults(pageSize);

		List<ContractView> contracts = query.unwrap(NativeQuery.class)
				.setResultTransformer(Transformers.aliasToBean(ContractView.class)).list();

		// 统计总记录数
		String countSql = sql.toString();
		countSql = "select count(1) " + countSql.substring(countSql.indexOf("from"));
		Query countQuery = entityManager.createNativeQuery(countSql);
		BigInteger totalCount = (BigInteger) countQuery.getSingleResult();
		PageHelper<ContractView> page = new PageHelper<ContractView>();
		page.setRows(contracts);
		page.setTotal(totalCount.intValue());

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
	public boolean doRefreshContractState() throws JsonMappingException, JsonProcessingException {
		String states = "03,04,05,06";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("statusList", "3,4,5,6");
		List<ContractView> contractList = this.find(params).getRows();
		List<ContractSignSys> signList = bestsignService.syncContractSignSysData();
		if (signList == null || signList.size() <= 0) {
			logger.info(System.currentTimeMillis() + ":--update contracts' state--Failed");
			return false;
		}

		for (ContractView contract : contractList) {
			String fileHashCode = contract.getFileHashCode();
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
			
			String state = "03";
			if (signContractId != null) {
				state = bestsignService.getContractStatus(signContractId, contract.getContractorName());
			}
			if (!contract.getStatus().equals(Integer.parseInt(state))) {
				contract.setSignContractId(signContractId);
				contract.setStatus(Integer.parseInt(state));

				// 更新合同状态及电子签约合同ID
				Contract c = findOne(contract.getId());
				c.setSignContractId(signContractId);
				c.setStatus(Integer.parseInt(state));
				save(c);
//				this.updateSignId(contract.getId(), signContractId, Integer.parseInt(state));
////				this.updateStatus(contract.getId(), Integer.parseInt(state));
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
	public boolean doSignContract(int contractId) throws JsonMappingException, JsonProcessingException {
		ContractView contract = this.findById(contractId);

		if (contract == null)
			return false;

		// 电子签约中合同Id
		String signContractId = contract.getSignContractId();
		boolean result = bestsignService.doSignContract(signContractId);
		String state = bestsignService.getContractStatus(signContractId, contract.getContractorName());
		System.out.println("state:" + state);
		if (result || state.equals("06")) {
//			contract.setState("06");
			contract.setStatus(6);

			// 使用上上签电子合同状态更新数据库更新合同状态
//			this.updateStatus(contractId, 6);
			Contract c = findOne(contract.getId());
			c.setStatus(6);
			save(c);
		}

		return false;
	}
}
