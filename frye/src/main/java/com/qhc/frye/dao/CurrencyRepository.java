/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.entity.ApplicationOfRolechange;
import com.qhc.frye.entity.DCurrency;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface CurrencyRepository extends JpaRepository<DCurrency, String> {

}
