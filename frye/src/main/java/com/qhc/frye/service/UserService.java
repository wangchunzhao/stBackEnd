/**
 * 
 */
package com.qhc.frye.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.UserRepository;
import com.qhc.frye.domain.User;

/**
 * @author lizuoshan
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUser(String mail) throws NoSuchElementException{
		User u = new User();
		return u;
		
	}
	
	public User getUser(int id) throws NoSuchElementException{
		
		return userRepository.findById(id).get();	
		
	}


	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> getUserList() {
		return userRepository.findAll();
	}
	
	/**
	 * 新增修改用户
	 * @param user
	 * @return
	 */
	public User createOrUpdateRole(User user) {
		return userRepository.save(user);
	}

}
