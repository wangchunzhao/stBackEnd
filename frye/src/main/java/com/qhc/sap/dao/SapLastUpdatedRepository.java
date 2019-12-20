/**
 * 
 */
package com.qhc.sap.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.LastUpdated;
import com.qhc.sap.entity.SapSalesOffice;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface SapLastUpdatedRepository extends JpaRepository<LastUpdated, String>{


}
