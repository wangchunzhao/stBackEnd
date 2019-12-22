package com.qhc.system.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;


import com.qhc.system.entity.Settings;

/**
 * 
 * Settings数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface SettingsMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param id id
   * @return Settings对象
   */
	Settings findById(Integer id);

  /**
   * 
   * 按参数统计记录数.
   * 
   * @param params 查询参数
   * @return 满足条件的记录数
   */
	int countByParams(Map<String, Object> params);

  /**
   * 
   * 按参数查询Settings信息.
   * 
   * @param params 查询参数
   * @return Settings列表
   */
	List<Settings> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增Settings.
   * 
   * @param settings 新增的Settings对象
   * @return 新增的记录数
   */
	int insert(Settings settings);

  /**
   * 
   * 修改Settings.
   * 
   * @param settings 修改的Settings对象
   * @return 修改的记录数
   */
	int update(Settings settings);

  /**
   * 
   * 按主键删除单条Settings.
   * 
   * @param id id
   * @return
   */
	int deleteById(Integer id);

	//当前生效信息
//	@Query(value="select * from b_settings where code=?1 and DATE_FORMAT(enable_date,'%Y%m-%d') <= DATE_FORMAT(curdate(),'%Y%m-%d') order by enable_date desc limit 1" ,nativeQuery=true)
	Settings findEnabledInfo(String code);
	
//	@Query(value="select * from b_settings where code=?1 and DATE_FORMAT(enable_date,'%Y%m-%d') > DATE_FORMAT(curdate(),'%Y%m-%d') order by enable_date asc limit 1" ,nativeQuery=true)
	Settings findAfterInfo(String code);
	
	// 指定日期生效信息
//	@Query(value="select * from b_settings where code=?1 and DATE_FORMAT(enable_date,'%Y%m-%d') = DATE_FORMAT(?2,'%Y%m-%d') order by enable_date desc limit 1" ,nativeQuery=true)
	Settings findInfo(String code, Date date);
	
	//所有setting数据，按code enable_date正向排序
//	@Query(value="select * from b_settings order by code asc, enable_date asc" ,nativeQuery=true)
	List<Settings> findSettings();

} 