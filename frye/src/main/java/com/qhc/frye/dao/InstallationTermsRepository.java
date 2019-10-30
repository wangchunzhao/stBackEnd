/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.DInstallationTerms;

/**
 * @author 
 *
 */
@Repository
public interface InstallationTermsRepository extends JpaRepository<DInstallationTerms, String> {

}
