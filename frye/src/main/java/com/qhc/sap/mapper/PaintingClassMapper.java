package com.qhc.sap.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.sap.entity.PaintingClass;

/**
 * 
 * PaintingClass数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface PaintingClassMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param paintingClass paintingClass
   * @return PaintingClass对象
   */
	PaintingClass findById(String paintingClass);

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
   * 按参数查询PaintingClass信息.
   * 
   * @param params 查询参数
   * @return PaintingClass列表
   */
	List<PaintingClass> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增PaintingClass.
   * 
   * @param paintingClass 新增的PaintingClass对象
   * @return 新增的记录数
   */
	int insert(PaintingClass paintingClass);

  /**
   * 
   * 修改PaintingClass.
   * 
   * @param paintingClass 修改的PaintingClass对象
   * @return 修改的记录数
   */
	int update(PaintingClass paintingClass);

  /**
   * 
   * 按主键删除单条PaintingClass.
   * 
   * @param paintingClass paintingClass
   * @return
   */
	int deleteById(String paintingClass);

} 