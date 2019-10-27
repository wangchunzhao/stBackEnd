/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qhc.frye.domain.ItemsForm;

/**
 * @author wang@dxc.com
 *
 */
public interface KOrderFormRepository  extends JpaRepository<ItemsForm, String>{

}
