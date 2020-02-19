package com.qhc.system.service;

import java.io.Serializable;
import java.util.*;

import javax.validation.Valid;

import com.qhc.system.domain.MenusDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qhc.system.domain.OperationDto;
import com.qhc.system.entity.Operation;
import com.qhc.system.mapper.OperationMapper;

/**
 * query operation info
 * 查询权限信息
 */
@Service
public class OperationService implements Serializable{

	@Autowired
	private OperationMapper operationMapper;


	/**
	 * 查询权限列表
	 * @return
	 */
	public List<Operation> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("operationType", "menu");
		return operationMapper.findByParams(params);
	}
	/**
	 * 通过id查询权限
	 * @param id
	 * @return
	 * @throws NoSuchElementException
	 */
	public Operation findById(String id) {
		return operationMapper.findById(id);
	}

	@Transactional
	public Operation createOrUpdateOperations(@Valid Operation operations) {
		return null;
	}
	
	
	/**
	 * 查询用过的所有权限
	 * 
	 * @param userIdentity
	 */
	public List<OperationDto> findByUser(String userIdentity) {
		return operationMapper.findByUserIdentity(userIdentity);
	}

	/**
	 * 查询用户所有菜单
	 * @param userId
	 * @return
	 */
	public  Map<String,MenusDto> findMenus(String userId){
		//查询用户所有菜单
		List<OperationDto> list = operationMapper.findByUserIdentity(userId);
		//查询所有权限
		List<Operation> all = operationMapper.findByParams(null);
		Map<String,MenusDto> map = new HashMap<>();
        assert list != null;
        if(null != list || list.isEmpty())
		for (OperationDto on:list) {
			MenusDto mnd = new MenusDto();
			mnd.setCode(on.getId());
			mnd.setName(on.getName());
			map.put(mnd.getCode(), mnd);
			String parentId = on.getParentId();
			if(StringUtils.isNotEmpty(parentId)){
				MenusDto p = map.get(parentId);
				if (p == null) {
					for (Operation od:all) {
						if(od.getId().equals(on.getParentId())){
							p = new MenusDto();
							map.put(od.getId(), p);
							p.setCode(od.getId());
							p.setName(od.getName());
							p.getChilds().add(mnd.getCode());
							
							break;
						}
					}
				}
			}
		}
		return map;
	}

}
