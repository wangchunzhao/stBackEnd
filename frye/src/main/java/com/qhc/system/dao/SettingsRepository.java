package com.qhc.system.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.system.entity.Settings;


@Repository
public interface SettingsRepository extends JpaRepository<Settings, Integer>{

	//当前生效信息
	@Query(value="select * from b_settings where code=?1 and DATE_FORMAT(enable_date,'%Y%m-%d') <= DATE_FORMAT(curdate(),'%Y%m-%d') order by enable_date desc limit 1" ,nativeQuery=true)
	Settings findEnabledInfo(String code);
	
	@Query(value="select * from b_settings where code=?1 and DATE_FORMAT(enable_date,'%Y%m-%d') > DATE_FORMAT(curdate(),'%Y%m-%d') order by enable_date asc limit 1" ,nativeQuery=true)
	Settings findAfterInfo(String code);
	
	// 指定日期生效信息
	@Query(value="select * from b_settings where code=?1 and DATE_FORMAT(enable_date,'%Y%m-%d') = DATE_FORMAT(?2,'%Y%m-%d') order by enable_date desc limit 1" ,nativeQuery=true)
	Settings findInfo(String code,Date date);
	
	//所有setting数据，按code enable_date正向排序
	@Query(value="select * from b_settings order by code asc, enable_date asc" ,nativeQuery=true)
	List<Settings> findSettings();

}
