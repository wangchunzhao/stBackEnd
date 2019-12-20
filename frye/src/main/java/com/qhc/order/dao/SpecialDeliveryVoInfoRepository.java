package com.qhc.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.qhc.order.domain.SpecialDeliveryVoInfo;
@Repository
public interface SpecialDeliveryVoInfoRepository extends JpaRepository<SpecialDeliveryVoInfo, String> ,JpaSpecificationExecutor<SpecialDeliveryVoInfo>{

}
