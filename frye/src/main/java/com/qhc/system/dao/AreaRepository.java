package com.qhc.system.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.system.entity.Area;
@Repository
public interface AreaRepository extends JpaRepository<Area, String> ,JpaSpecificationExecutor<Area>{
	
	@Query(value = "select p.name as pname,b.name as cname,a.name,a.price,a.price1,a.price2,a.price3,a.price4,a.price5,a.price6,a.price7,a.price8,a.price9,a.price10,a.price11  from b_area a LEFT JOIN b_city b ON a.city_code = b.CODE LEFT JOIN b_province p ON p.CODE = b.province_code where 1=1  ",
		    countQuery = "SELECT count(*) FROM b_area a LEFT JOIN b_city b ON a.city_code = b.CODE LEFT JOIN b_province p ON p.CODE = b.province_code where 1=1  ",
		    nativeQuery = true)
	Page<List<Object>> findAllList(Pageable pageable);

}
