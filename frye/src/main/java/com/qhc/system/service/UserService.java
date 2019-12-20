package com.qhc.system.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qhc.system.domain.OperationDto;
import com.qhc.system.domain.RoleDto;
import com.qhc.system.domain.UserDto;
import com.qhc.system.entity.Role;
import com.qhc.system.entity.User;
import com.qhc.system.entity.UserRole;
import com.qhc.system.mapper.RoleMapper;
import com.qhc.system.mapper.UserMapper;
import com.qhc.system.mapper.UserRoleMapper;

/**
 * @author lizuoshan
 *
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private OperationService operationService;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	public User getUser(String mail) throws NoSuchElementException{
		User u = new User();
		return u;
		
	}
	
	/**
	 * 新增修改用户
	 * @param user
	 * @return
	 */
	public UserDto createOrUpdateUser(UserDto userDto) {
		User user = new User();
		try {
			BeanUtils.copyProperties(user, userDto);
			if (user.getId() == null) {
				userMapper.insert(user);
			} else {
				userMapper.update(user);
			}
			
			userRoleMapper.deleteByUserId(user.getId());
			
			List<RoleDto> roles = userDto.getRoles();
			
			for (RoleDto roleDto : roles) {
				UserRole userRole = new UserRole();
				userRole.setUserId(user.getId());
				userRole.setRoleId(roleDto.getId());
				
				userRoleMapper.insert(userRole);
			}
			
			this.findByUserIdentity(user.getUserIdentity());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return userDto;
	}

	/**
	  *  查询所有用户
	 * @param user
	 * @return
	 */
	public List<User> findAll() {
		return userMapper.findByParams(new HashMap());
	}
	
	/**
	  *  查询所有用户
	 * @param user
	 * @return
	 */
	public List<User> findByMultipleConditions(int isActice,String userName,String userMail,String userIdentity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userIdentity", userIdentity);
		params.put("isActice", isActice);
		params.put("name", userName);
		params.put("userMail", userMail);
		List<User> list = userMapper.findByParams(params);
		return list;
	}

	/**
	  * 通过id查询用户
	 * @param user
	 * @return
	 */
	public UserDto findById(Integer id) {
		User user = userMapper.findById(id);
		UserDto dto = new UserDto();
		try {
			BeanUtils.copyProperties(dto, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	public void delete(Integer id) {
		userMapper.deleteById(id);
	}

	/**
	 * 使用户不可用
	 * @param id
	 * @return
	 */
	public User notAvailable(Integer id) {
		User user = userMapper.findById(id);
		user.setIsActive(1);
		
		userMapper.update(user);
		
		return user;
	}

//	public List<User> findByUserMailAndIsActive(String userMail, Integer isActive) {
//		return userMapper.findByUserMailLikeAndIsActive(userMail,isActive);
//	}
//
//	public List<User> findByUserMail(String userMail) {
//		return userMapper.findByUserMailLike(userMail);
//	}
//
//	public List<User> findByIsActive(Integer isActive) {
//		return userMapper.findByIsActive(isActive);
//	}

//	public User findByUserName(String userName) {
//		return userMapper.findByUserName(userName);
//	}

	/**
	 * find by identity
	 * @param userIdentity
	 * @return
	 */
	public UserDto findByUserIdentity(String userIdentity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userIdentity", userIdentity);
		List<User> list = userMapper.findByParams(params);
		
		UserDto dto = new UserDto();
		if (list.size() > 0) {
			User user = list.get(0);
			try {
				BeanUtils.copyProperties(dto, user);
				List<RoleDto> roles = roleService.findByUserIdentity(userIdentity);
				dto.setRoles(roles);
				
				List<OperationDto> operations = operationService.findByUser(userIdentity);
				dto.setOperations(operations);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		
		return dto;
	}

	public PageInfo<User> getByConditions(User user, Pageable pageable) {
		PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
		Map<String, Object> params = new HashMap<String, Object>();
		//增加筛选条件
		params.put("isActive", user.getIsActive());
		params.put("userIdentity", user.getUserIdentity());
		params.put("name", "%" + user.getName() + "%");
		params.put("userMail", "%" + user.getUserMail() + "%");

		return new PageInfo(userMapper.findByParams(params));
	}

}
