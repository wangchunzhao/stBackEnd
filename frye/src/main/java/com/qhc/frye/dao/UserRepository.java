package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {



}
