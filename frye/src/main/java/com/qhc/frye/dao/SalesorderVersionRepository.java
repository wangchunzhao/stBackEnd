/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.DOrder;
import com.qhc.frye.domain.KOrderVersion;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface SalesorderVersionRepository extends JpaRepository<KOrderVersion, Integer> {

}
