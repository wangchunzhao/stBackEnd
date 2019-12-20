/**
 * 
 */
package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.InstallationTerms;

/**
 * @author 
 *
 */
@Repository
public interface InstallationTermsRepository extends JpaRepository<InstallationTerms, String> {

}
