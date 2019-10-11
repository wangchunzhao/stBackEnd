/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.DCustomer;
import com.qhc.frye.rest.controller.entity.Customer;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<DCustomer, Integer>, JpaSpecificationExecutor<DCustomer> {

	// @Query(value = "SELECT * FROM bohemian.sap_customer where name like
	// %?1%",nativeQuery=true)
	// public Page<Customer> findByName(String name,int pageNo, int number);
	@Query(value = "SELECT * FROM bohemian.sap_customer where name like %:name%", countQuery = "SELECT count(*)  FROM bohemian.sap_customer where  name like %:name%", nativeQuery = true)
	public Page<DCustomer> findByName(@Param("name") String name,Pageable pageable);

	@Query(value = "SELECT * FROM bohemian.sap_customer where sap_customer_class_code =%:code% and name like %:name%", countQuery = "SELECT count(*)  FROM bohemian.sap_customer where sap_customer_class_code =%:code% and name like %:name%", nativeQuery = true)
	public Page<DCustomer> findByCodeAndName(@Param("name") String name, @Param("name") String code, Pageable pageable);

}
