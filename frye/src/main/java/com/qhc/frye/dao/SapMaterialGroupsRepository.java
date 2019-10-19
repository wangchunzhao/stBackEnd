package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.DMaterialGroups;

@Repository
public interface SapMaterialGroupsRepository extends JpaRepository<DMaterialGroups, String> {
}
