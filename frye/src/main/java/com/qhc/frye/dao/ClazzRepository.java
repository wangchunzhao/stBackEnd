/**
 * 
 */
package com.qhc.frye.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.DClazz;

/**
 * @author @wang@dxc.com
 *
 */
@Repository
public interface ClazzRepository extends JpaRepository<DClazz, String> {

}
