/**
 * 
 */
package com.qhc.frye.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.CharacteristicDefaultRepository;
import com.qhc.frye.dao.CharacteristicRepository;
import com.qhc.frye.dao.CharacteristicValueRepository;
import com.qhc.frye.dao.ClassAndCharacterRepository;
import com.qhc.frye.dao.ClazzOfMaterialRepository;
import com.qhc.frye.dao.ContractRepository;
import com.qhc.frye.domain.Contract;
import com.qhc.frye.domain.DCharacteristic;
import com.qhc.frye.domain.DCharacteristicDefault;
import com.qhc.frye.domain.DCharacteristicValue;
import com.qhc.frye.domain.DClassAndCharacter;
import com.qhc.frye.domain.DClazzOfMaterial;
import com.qhc.frye.domain.KOrderView;
import com.qhc.frye.rest.controller.entity.CharacteristicValue;
import com.qhc.frye.rest.controller.entity.Clazz;
import com.qhc.frye.rest.controller.entity.ContractView;
import com.qhc.frye.rest.controller.entity.DefaultCharacteristics;
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.rest.controller.entity.form.AbsOrder;

/**
 * @author walker
 *
 */
@Service
public class ContractService {
	@Autowired
	private ConstantService constantService;

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
		sql.append("contract.mail                          as mail,");
		sql.append("contract.contractor_1_id               as contractor1Id,");
		sql.append("contract.contractor_1_tel              as contractor1Tel,");
		sql.append("contract.contractor_2_id               as contractor2Id,");
		sql.append("contract.contractor_2_tel              as contractor2Tel,");
		sql.append("contract.contractor_3_id               as contractor3Id,");
		sql.append("contract.contractor_3_tel              as contractor3Tel,");
		sql.append("contract.contract_status              as status,");
		sql.append("contract.production_time              as productionTime,");
		sql.append("contract.send_time              as sendTime" + " from k_orders as orders");
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
		if (!isEmpty(id)) {
			sql.append(" and contract.id = " + id);
		}
		if (!isEmpty(status)) {
			sql.append(" and contract.contract_status = " + status + "");
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
		return v == null || v.length() == 0;
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
}
