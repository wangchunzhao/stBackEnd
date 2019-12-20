package com.qhc.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.order.entity.Attachment;

@Repository
public interface AttachementRepository extends JpaRepository<Attachment, Integer> {
	public List<Attachment> findByOrderInfo(String orderInfoId);
}
