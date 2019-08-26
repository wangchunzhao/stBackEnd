/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.service.ConfirugrableService;

import io.swagger.annotations.Api;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@RequestMapping("material")
@Api(value = "Material Management in Frye")
public class MaterialController {
	
	@Autowired
    private ConfirugrableService configService;

}
