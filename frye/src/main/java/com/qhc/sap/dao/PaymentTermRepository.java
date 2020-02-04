/**
 * 
 */
package com.qhc.sap.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.PaymentTerm;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface PaymentTermRepository extends JpaRepository<PaymentTerm, String>{

	List<PaymentTerm> findByCodeLike(String code, Sort by);
	
}
