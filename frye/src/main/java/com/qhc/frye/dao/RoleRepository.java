package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {



}
