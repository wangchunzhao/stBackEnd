package com.qhc.system.service;

import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.qhc.system.dao.UserRepository;
import com.qhc.system.entity.User;

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
	  *  查询所有用户
	 * @param user
	 * @return
	 */
	public List<User> findByMultipleConditions(int isActice,String userName,String userMail,String userIdentity) {
		return userRepository.findByMultipleConditions(isActice, userName, userMail, userIdentity);
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

	public Page<User> getByConditions(User user, Pageable pageable) {
		 Specification<User> specification = new Specification<User>() {
	            @Override
	            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
	                //增加筛选条件
	                Predicate predicate = cb.conjunction();
	                if(user.getIsActive()==2) {
	                	
	                	predicate.getExpressions().add(cb.notEqual(root.get("isActive").as(Integer.class), user.getIsActive()));
	                }else {
	                	predicate.getExpressions().add(cb.equal(root.get("isActive").as(Integer.class), user.getIsActive()));
	                }
	                 //模糊查找
	                if(!"".equals(user.getUserIdentity())&&user.getUserIdentity()!=null) {
	                	
	                	predicate.getExpressions().add(cb.like(root.get("userIdentity").as(String.class), "%" + user.getUserIdentity() + "%"));
	                }
                    if(!"".equals(user.getUserName())&&user.getUserName()!=null) {
                    	
                    	predicate.getExpressions().add(cb.like(root.get("name").as(String.class), "%" + user.getUserName() + "%"));
                    }
                    if(!"".equals(user.getUserMail())&&user.getUserMail()!=null) {
                    	
                    	predicate.getExpressions().add(cb.like(root.get("userMail").as(String.class), "%" + user.getUserMail() + "%"));
                    }

//	    		   //起始日期
//	                if (startTime != null && !startTime.trim().equals("")) {
//	                    predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class), startTime));
//	                }
//	                //结束日期
//	                if (endTime != null && !endTime.trim().equals("")) {
//	                    predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class), endTime));
//	                }
	                return predicate;
	            }
	       };

		return userRepository.findAll(specification,pageable);
	}

}
