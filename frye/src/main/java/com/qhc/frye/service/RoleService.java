package com.qhc.frye.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.qhc.frye.dao.RoleRepository;
import com.qhc.frye.domain.Operation2role;
import com.qhc.frye.domain.Operations;
import com.qhc.frye.domain.Role;

/**
 * query role info
 * 查询角色信息
 */
@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private OperationService operationService;
	@Autowired
	private RelationService relationService;

	/**
	 * 通过id查询角色
	 * @param id
	 * @return
	 * @throws NoSuchElementException
	 */
	public Role findById(int id) throws NoSuchElementException{
		Role role = roleRepository.findById(id).get();
		List<Operation2role> relationList= relationService.findByRoleId(id,0);
		Set<Operations> operations = new HashSet<Operations>();
		if(relationList!=null&&relationList.size()>0) {
			for(Operation2role or:relationList) {
				operations.add(operationService.findById(or.getOperationId()));
			}
		}
			role.setOperations(operations);
		return role;	
		
	}

	/**
	 * 查询角色列表
	 * @return
	 */
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
	/**
	 * 分页查询角色列表
	 * @return
	 */
	public Page<Role> getPageRoleList(Pageable pageable) {
		
		return roleRepository.findAll(pageable);
	}
	
	
	/**
	 * 新增，修改角色
	 * @param role
	 */
	public Role createOrUpdateRole(Role role) {
		
		return roleRepository.save(role);
	}

	/**
	 * 删除角色
	 * @param role
	 */
	public void remove(int id) {
		
		roleRepository.deleteById(id);
		roleRepository.flush();
	}


	public List<Role> findByNameLike(String name) {
		return roleRepository.findByNameLike("%"+name+"%");
	}

	public Page<Role> getByConditions(Role role, Pageable pageRequest) {	
		if(role.getIsActive()==2) {
			//查询全部
			return roleRepository.findAll(pageRequest);
		
		}else {
			//查询启用或禁用的
//			return roleRepository.findByIsActive(role.getIsActive(),pageRequest);
			
			List<Role> roleList = roleRepository.findByIsActive(role.getIsActive());
			return new PageImpl<Role>(roleList,pageRequest,roleList.size());
			
		}
    }

	
}
