package com.qhc.sap.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.sap.entity.ProductClass;

/**
 * 
 * ProductClass数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface ProductClassMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param productClass
   * @return ProductClass对象
   */
	ProductClass findById(ProductClass productClass);

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
   * 按参数查询ProductClass信息.
   * 
   * @param params 查询参数
   * @return ProductClass列表
   */
	List<ProductClass> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增ProductClass.
   * 
   * @param productClass 新增的ProductClass对象
   * @return 新增的记录数
   */
	int insert(ProductClass productClass);

  /**
   * 
   * 修改ProductClass.
   * 
   * @param productClass 修改的ProductClass对象
   * @return 修改的记录数
   */
	int update(ProductClass productClass);

  /**
   * 
   * 按主键删除单条ProductClass.
   * 
   * @param productClass
   * @return
   */
	int deleteById(ProductClass productClass);

} 