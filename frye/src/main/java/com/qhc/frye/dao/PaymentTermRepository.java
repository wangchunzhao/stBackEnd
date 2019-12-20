/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.entity.PaymentTerm;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface PaymentTermRepository extends JpaRepository<PaymentTerm, String>{
	
}
