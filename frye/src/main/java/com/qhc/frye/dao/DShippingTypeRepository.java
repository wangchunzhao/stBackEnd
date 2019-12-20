package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.entity.DShippingType;

@Repository
public interface DShippingTypeRepository extends JpaRepository<DShippingType, String> {
}
