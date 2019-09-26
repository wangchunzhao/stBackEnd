package com.qhc.frye.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.BArea;
@Repository
public interface BAreaRepository extends JpaRepository<BArea, Integer> ,JpaSpecificationExecutor<BArea>{
	
	@Query(value = "select * from b_area a left join b_city b on a.b_city_code=b.code left join b_province p on p.code=b.b_province_code where a.name=?1 ",
		    countQuery = "SELECT count(*) FROM b_area a left join b_city b on a.b_city_code=b.code left join b_province p on p.code=b.b_province_code where a.name=?1 ",
		    nativeQuery = true)
	Page<Object[]> findByName(String name, Pageable pageable);

}
