/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qhc.frye.rest.controller.entity.Clazz;

/**
 * @author wang@dxc.com
 *
 */
public interface ClassAndCharacter extends JpaRepository<Clazz, String>{

}
