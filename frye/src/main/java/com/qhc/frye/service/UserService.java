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
		user.setIsActive(1);
		
		return userRepository.save(user);
	}

	public List<User> findByUserMailAndIsActive(String userMail, Integer isActive) {
		return userRepository.findByUserMailLikeAndIsActive(userMail,isActive);
	}

	public List<User> findByUserMail(String userMail) {
		return userRepository.findByUserMailLike(userMail);
	}

	public List<User> findByIsActive(Integer isActive) {
		return userRepository.findByIsActive(isActive);
	}

	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	/**
	 * find by identity
	 * @param userIdentity
	 * @return
	 */
	public User findByUserIdentity(String userIdentity) {
		
		return userRepository.findByUserIdentity(userIdentity);
	}

}
