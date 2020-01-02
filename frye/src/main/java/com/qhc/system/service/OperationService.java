package com.qhc.system.service;

import java.io.Serializable;
import java.util.*;

import javax.validation.Valid;

import com.qhc.system.domain.MenusDto;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return operationMapper.findByParams(null);
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

	public Operation createOrUpdateOperations(@Valid Operation operations) {
		// TODO Auto-generated method stub
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
	public  Map<String,MenusDto> findAllAserMenus(String userId){
		//查询用户所有菜单
		List<Operation> list = operationMapper.findAllAserMenus(userId);
		//查询所有权限
		List<Operation> list1 = operationMapper.findByParams(null);
		Map<String,MenusDto> map = new HashMap<>();
		List<String> List3=new ArrayList<>();
		String code = "";
		String code1 = "";
        assert list != null;
        if(null != list || list.isEmpty())
		for (Operation on:list) {
			MenusDto mnd = new MenusDto();
			if(StringUtils.isNoneBlank(on.getParentId())){
				for (Operation od:list1) {
					MenusDto md = new MenusDto();
					if(od.getId().equals(on.getParentId())){
						if(!od.getId().equals(code1)){
							List3.clear();
						}
						List3.add(on.getId());
						String[] nsz=new String[List3.size()];
						List3.toArray(nsz);
						code = od.getId();
						code1 = od.getId();
						md.setCode(od.getId());
						md.setName(od.getName());
						md.setChilds(nsz);
						map.put(code,md);
						break;
					}

				}
			}
			code = on.getId();
			mnd.setCode(on.getId());
			mnd.setName(on.getName());
			String[] nsz={""};
			mnd.setChilds(nsz);
			map.put(code,mnd);
		}
		return map;
	}


//	Map<String,List<T>> hashMap=new HashMap<String, List<T>>();
//	//查出商品的父级id
//	List<T> list=goodsService.getParentTypeList();
//	//查出商品的id
//	List<T> listGoods=goodsService.goodsTypeList();
// for (T gt:list){
//		List<T> typeList=new ArrayList<T>();
//		for (T g:listGoods){
//			if (gt.getId()==g.getParent_id()){
//				typeList.add(g);
//			}
//		}
//     `hashMap.put(gt.getName(),typeList) ;
//


}
