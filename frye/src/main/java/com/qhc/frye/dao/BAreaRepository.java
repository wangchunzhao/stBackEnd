package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.frye.entity.BArea;
@Repository
public interface BAreaRepository extends JpaRepository<BArea, Integer> ,JpaSpecificationExecutor<BArea>{
	
	@Query(value = "select p.name as pname,b.name as cname,a.name,a.price,a.price1,a.price2,a.price3,a.price4,a.price5,a.price6,a.price7,a.price8,a.price9,a.price10,a.price11  from b_area a left join b_city b on a.b_city_code=b.code left join b_province p on p.code=b.b_province_code where 1=1  ",
		    countQuery = "SELECT count(*) FROM b_area a left join b_city b on a.b_city_code=b.code left join b_province p on p.code=b.b_province_code where 1=1  ",
		    nativeQuery = true)
	Page<List<Object>> findAllList(Pageable pageable);

}
