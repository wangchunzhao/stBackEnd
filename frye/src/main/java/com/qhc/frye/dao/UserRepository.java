package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByUserMailLikeAndIsActive(String userMail, Integer isActive);

	List<User> findByUserMailLike(String userMail);

	/**
	 * find by isActive
	 * @param isActive
	 * @return
	 */
	List<User> findByIsActive(Integer isActive);

	/**
	 * find by userName
	 */
	User findByUserName(String userName);

	/**
	 * find by identity
	 * @param userIdentity
	 * @return
	 */
	User findByUserIdentity(String userIdentity);



}
