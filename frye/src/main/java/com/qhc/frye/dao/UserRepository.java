package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.frye.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User> {

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

	@Query(value="select * from b_users where if(?1 !=2,isActive=?1,1=1) and if(?2 !='',name=?2,1=1) and if(?3 !='',user_Mail=?3,1=1) and if(?4 !='',user_Identity=?4,1=1)" ,nativeQuery=true)
	List<User> findByMultipleConditions(Integer isActive,String userName,String userMail,String userIdentity);
//	List<User> findByMultipleConditions(@Param("userName") String userName,@Param("userMail") String userMail,@Param("userIdentity") String userIdentity,@Param("isActive") Integer isActive);
}
