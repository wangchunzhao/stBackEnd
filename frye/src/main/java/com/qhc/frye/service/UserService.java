/**
 * 
 */
package com.qhc.frye.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.UserRepository;
import com.qhc.frye.domain.Role;
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
	
	/**
	 * 新增修改用户
	 * @param user
	 * @return
	 */
	public User createOrUpdateUser(User user) {
		return userRepository.save(user);
	}

	/**
	  *  查询所有用户
	 * @param user
	 * @return
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/**
	  * 通过id查询用户
	 * @param user
	 * @return
	 */
	public User findById(Integer id) {
		return userRepository.findById(id).get();
	}

	public void delete(Integer id) {
		User user = userRepository.findById(id).get();
		userRepository.delete(user);
	}

	/**
	 * 使用户不可用
	 * @param id
	 * @return
	 */
	public User notAvailable(Integer id) {
		User user = userRepository.findById(id).get();
		user.setIsActive(0);;
		
		return userRepository.save(user);
	}

}
