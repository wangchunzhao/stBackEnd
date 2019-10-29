package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.KAttachment;

@Repository
public interface AttachementRepository extends JpaRepository<KAttachment, Integer> {
	public List<KAttachment> findByOrderInfo(String orderInfoId);
}
