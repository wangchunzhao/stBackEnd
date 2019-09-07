package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.ApplicationOfRolechange;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationOfRolechange, Integer> {

	List<ApplicationOfRolechange> findByBusersId(int userId);

	void deleteByBusersId(Integer id);


}
