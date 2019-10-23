package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.KCharacteristics;

@Repository
public interface KCharacteristicsRepository extends JpaRepository<KCharacteristics, String> {
	
	List<KCharacteristics> findByKItemDetailsId(String itemId);
}
