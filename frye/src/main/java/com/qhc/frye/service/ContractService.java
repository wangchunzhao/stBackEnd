/**
 * 
 */
package com.qhc.frye.service;

import java.math.BigInteger;
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

	public Contract findById(Integer id) {
		return contractRepository.findById(id).get();
	}

	public Contract save(Contract contract) {
		return contractRepository.saveAndFlush(contract);
	}

	public PageHelper<ContractView> find(Map<String, Object> params) {
		String sql = "select "
				+ "support.contract_number                as contractNumber,"
				// 支持经理，签约人
				+ "support.opterator_domain_id                as opteratorDomainId,"
				+ "orders.id                      as orderId,"
				// 签约单位 Contract Unit
				+ "orders.contractor_code                as contractorCode,"
				+ "orders.contractor_name                as contractorName,"
				// 性质分类 Classification
				+ "orders.contractor_class_code                as contractorClassCode,"
				+ "orders.contractor_class_name                as contractorClassName,"
				// 版本号 Version Number
				+ "version.version                as version,"
				+ "version.id                as versionId,"
				+ "version.create_time                as createTime,"
				+ "contract.id                            as id," + 
				"contract.sequence_number               as sequenceNumber," + 
				"contract.PartyA_code                   as partyaCode," + 
				"contract.PartyA_name                   as partyaName," + 
				"contract.partyA_mail                   as partyaMail," + 
				"contract.amount_on_contract            as amountOnContract," + 
				"contract.delivery_days_after_prepay    as deliveryDaysAfterPrepay," + 
				"contract.client_name                   as clientName," + 
				"contract.install_location              as installLocation," + 
				"contract.quality_stand                 as qualityStand," + 
				"contract.settlement                    as settlement," + 
				"contract.paryA_address                 as paryaAddress," + 
				"contract.invoice_address               as invoiceAddress," + 
				"contract.broker                        as broker," + 
				"contract.invoice_receiver              as invoiceReceiver," + 
				"contract.invoice_tel                   as invoiceTel," + 
				"contract.invoice_post_code             as invoicePostCode," + 
				"contract.company_tel                   as companyTel," + 
				"contract.bank_name                     as bankName," + 
				"contract.account_number                as accountNumber," + 
				"contract.k_order_version_id            as orderVersionId," + 
				"contract.receive_terms_code            as receiveTermsCode," + 
				"contract.receive_terms_name            as receiveTermsName," + 
				"contract.k_acceptance_criteria_code    as acceptanceCriteriaCode," + 
				"contract.mail                          as mail," + 
				"contract.contractor_1_id               as contractor1Id," + 
				"contract.contractor_1_tel              as contractor1Tel," + 
				"contract.contractor_2_id               as contractor2Id," + 
				"contract.contractor_2_tel              as contractor2Tel," + 
				"contract.contractor_3_id               as contractor3Id," + 
				"contract.contractor_3_tel              as contractor3Tel," + 
				"contract.status              as status," + 
				"contract.production_time              as productionTime," + 
				"contract.send_time              as sendTime"
				+ " from k_orders as orders"
				+ " left join k_order_support_info as support on support.k_orders_id = orders.id"
				+ " left join k_order_version as version on version.k_orders_id = orders.id"
				+ " left join k_contract as contract on contract.k_order_version_id = version.id";
		
		Query query = entityManager.createNativeQuery(sql);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		// 设置分页信息
		int pageNo = isEmpty(toString(params.get("pageNo"))) ? 0 : Integer.parseInt(params.get("pageNo").toString());
		int pageSize = isEmpty(toString(params.get("pageSize"))) ? 10000 : Integer.parseInt(params.get("pageSize").toString());
		query.setFirstResult(pageNo * pageSize);
		query.setMaxResults(pageSize);

		List<ContractView> contracts = query.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(ContractView.class)).list();

		// 统计总记录数
		String countSql = sql;
		countSql = "select count(1) " + countSql.substring(countSql.indexOf("from"));
		Query countQuery = entityManager.createNativeQuery(countSql);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			countQuery.setParameter(entry.getKey(), entry.getValue());
		}
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
