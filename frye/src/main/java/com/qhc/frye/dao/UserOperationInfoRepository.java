package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qhc.frye.domain.UserOperationInfo;


@Repository
public interface UserOperationInfoRepository extends JpaRepository<UserOperationInfo, Integer>{

	List<UserOperationInfo> findAll();

	List<UserOperationInfo> findByUserId(Integer userId);

}
