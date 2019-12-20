/**
 * 
 */
package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.Currency;
import com.qhc.system.entity.UserRole;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {

}
