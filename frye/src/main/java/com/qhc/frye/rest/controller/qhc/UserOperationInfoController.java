package com.qhc.frye.rest.controller.qhc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.dao.UserOperationInfoRepository;
import com.qhc.frye.domain.UserOperationInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * @author lizuoshan
 *
 */
@RestController
@RequestMapping("userOperationInfo")
@Api(value = "UserOperationInfo", description = "用户角色权限视图")
public class UserOperationInfoController {
	
	@Autowired
	private UserOperationInfoRepository userOperationInfoRepository;
	
	@ApiOperation(value="查询整个视图", notes="查询整个视图")
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserOperationInfo> findAll() throws Exception
    {	
        return userOperationInfoRepository.findAll();
    }
	
	@ApiOperation(value="根据域账号查询视图", notes="根据域账号查询视图")
	@GetMapping(value="/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserOperationInfo> findByUserId(@PathVariable("userId") Integer userId) throws Exception
    {	
		return userOperationInfoRepository.findByUserId(userId);
    }
	
	


}
