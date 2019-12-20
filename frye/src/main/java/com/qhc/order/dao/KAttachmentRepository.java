/**
 * 
 */
package com.qhc.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qhc.order.entity.Attachment;

/**
 * @author wang@dxc.com
 *
 */
public interface KAttachmentRepository extends JpaRepository<Attachment, Integer> {

}
