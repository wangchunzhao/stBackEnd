package com.qhc.sap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.MaterialGroups;

@Repository
public interface SapMaterialGroupsRepository extends JpaRepository<MaterialGroups, String> {
	public List<MaterialGroups> findByIsenableNotOrderByCode(int i);
}
