package com.qhc.frye.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.Parameter;


@Repository
public interface ParameterSettingsRepository extends JpaRepository<Parameter, Integer>{

	//当前生效信息
	@Query(value="select * from b_settings where code=?1 and enable_date<= curdate() order by enable_date desc limit 1" ,nativeQuery=true)
	Parameter findEnabledInfo(String code);
	
	@Query(value="select * from b_settings where code=?1 and enable_date> curdate() order by enable_date asc limit 1" ,nativeQuery=true)
	Parameter findAfterInfo(String code);
	
	//失效信息
	@Query(value="select * from b_settings where code=?1 and enable_date< ?2 order by enable_date desc limit 1" ,nativeQuery=true)
	Parameter findPreInfo(String code,Date date);

}
