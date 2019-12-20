package com.qhc.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.system.entity.UserOperationView;


@Repository
public interface UserOperationInfoRepository extends JpaRepository<UserOperationView, Integer>{

	List<UserOperationView> findAll();

	List<UserOperationView> findByUserId(Integer userId);

}
