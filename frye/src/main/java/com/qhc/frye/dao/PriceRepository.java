package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.entity.DPrice;

/**
 * 
 * @author wang@dxc.com
 *
 */
@Repository
public interface PriceRepository extends JpaRepository<DPrice, String>{

}
