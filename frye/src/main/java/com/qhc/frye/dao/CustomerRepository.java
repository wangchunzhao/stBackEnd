/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.DCustomer;
import com.qhc.frye.domain.User;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<DCustomer, Integer>{

	@Query(value = "SELECT * FROM bohemian.sap_customer where name like %?1%",nativeQuery=true)
	 public List<DCustomer> findByName(String name);
}
