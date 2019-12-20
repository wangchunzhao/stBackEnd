/**
 * 
 */
package com.qhc.sap.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qhc.sap.domain.CustomerDto;
import com.qhc.sap.entity.Customer;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

	// @Query(value = "SELECT * FROM bohemian.sap_customer where name like
	// %?1%",nativeQuery=true)
	// public Page<Customer> findByName(String name,int pageNo, int number);
	@Query(value = "SELECT * FROM sap_customer where name like %:name%", countQuery = "SELECT count(*)  FROM bohemian.sap_customer where  name like %:name%", nativeQuery = true)
	public Page<Customer> findByName(@Param("name") String name,Pageable pageable);

	@Query(value = "SELECT * FROM sap_customer where sap_customer_class_code = :code and name like %:name%", countQuery = "SELECT count(*)  FROM bohemian.sap_customer where sap_customer_class_code = :code and name like %:name%", nativeQuery = true)
	public Page<Customer> findByCodeAndName(@Param("name") String name, @Param("code") String code, Pageable pageable);

}
