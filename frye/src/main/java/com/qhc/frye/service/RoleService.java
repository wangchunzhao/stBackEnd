package com.qhc.frye.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.RoleRepository;
import com.qhc.frye.domain.Role;

/**
 * query role info
 * 查询角色信息
 */
@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	/**
	 * 通过id查询角色
	 * @param id
	 * @return
	 * @throws NoSuchElementException
	 */
	public Role getRole(int id) throws NoSuchElementException{
		
		return roleRepository.findById(id).get();	
		
	}

	/**
	 * 查询角色列表
	 * @return
	 */
	public List<Role> getRoleList() {
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
	public void createOrUpdateRole(Role role) {
		
		roleRepository.save(role);
	}

}
