/**
 * 
 */
package com.qhc.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.order.entity.DOrder;
import com.qhc.order.entity.KOrderVersion;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface SalesorderVersionRepository extends JpaRepository<KOrderVersion, Integer> {

}
