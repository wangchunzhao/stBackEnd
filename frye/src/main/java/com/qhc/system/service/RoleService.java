package com.qhc.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.qhc.system.mapper.RoleOperationMapper;
import com.qhc.system.mapper.UserRoleMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qhc.system.domain.OperationDto;
import com.qhc.system.domain.RoleDto;
import com.qhc.system.entity.Role;
import com.qhc.system.mapper.RoleMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * query role info 查询角色信息
 */
@Service
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private OperationService operationService;
	@Autowired
	private RelationService relationService;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleOperationMapper roleOperationMapper;

	/**
	 * 通过id查询角色
	 * 
	 * @param id
	 * @return
	 * @throws NoSuchElementException
	 */
	public RoleDto findById(int id) throws NoSuchElementException {
		Role role = roleMapper.findById(id);
		RoleDto roleDto = new RoleDto();
		try {
			BeanUtils.copyProperties(roleDto, role);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Set<UserRole> sets = role.getApps();
		List<OperationDto> operations = relationService.findByRoleId(id);
		roleDto.setOperations(operations);
		return roleDto;

	}

	/**
	 * 查询角色列表
	 * 
	 * @return
	 */
	public List<RoleDto> findAll() {
		List<Role> list = roleMapper.findByParams(new HashMap<String, Object>());
		List<RoleDto> roles = new ArrayList();
		toDtoList(list, roles);
		
		return roles;
	}

	private void toDtoList(List<Role> list, List<RoleDto> roles) {
		for (Role role : list) {
			RoleDto roleDto = new RoleDto();
			roles.add(roleDto);

			try {
				BeanUtils.copyProperties(roleDto, role);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 分页查询角色列表
	 * 
	 * @return
	 */
	public PageInfo<RoleDto> getPageRoleList(Pageable pageable) {
		PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
		PageInfo p = new PageInfo(roleMapper.findByParams(new HashMap<String, Object>()));

		List<RoleDto> roles = new ArrayList();
		toDtoList(p.getList(), roles);
		p.setList(roles);
		
		return p;
	}

	/**
	 * 新增，修改角色
	 * 
	 * @param role
	 */
	@Transactional
	public RoleDto createOrUpdateRole(RoleDto role) {
		Role r = new Role();
		try {
			BeanUtils.copyProperties(r, role);
			if (role.getId() == null || role.getId() == 0) {
				roleMapper.insert(r);
			} else {
				roleMapper.update(r);
			}
			role.setId(r.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return role;
	}

	/**
	 * 删除角色
	 * @param role
	 * @return
	 */
	@Transactional
	public Role deleteRole(Role role) {
		int roleId = role.getId();
		//删除角色用户关系
		userRoleMapper.deleteByRoleId(roleId);
		//删除角色权限关系
		roleOperationMapper.deleteByRoleId(roleId);
		//删除角色
		roleMapper.deleteById(role.getId());
		return role;
	}

	public List<Role> findByNameLike(String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "%" + name + "%");
		return roleMapper.findByParams(params);
	}

	public Role findByName(String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		List<Role> list = roleMapper.findByParams(params);
		
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 查询用户所有的角色
	 * 
	 * @param userIdentity
	 * @return
	 */
	public List<RoleDto> findByUserIdentity(String userIdentity) {
		List<Role> list = roleMapper.findByUserIdentity(userIdentity);
		List<RoleDto> roles = new ArrayList<>();
		
		this.toDtoList(list, roles);
		
		return roles;
	}

	public PageInfo<Role> getByConditions(Role role, Pageable pageable) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Role> list = roleMapper.findByParams(params);
		
		return new PageInfo<Role>(list);
	}

}
