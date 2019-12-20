package com.qhc.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.system.entity.UserRole;

@Repository
public interface ApplicationRepository extends JpaRepository<UserRole, Integer> {

	List<UserRole> findByBusersId(int userId);

	void deleteByBusersId(Integer id);


}
