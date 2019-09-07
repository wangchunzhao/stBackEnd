package com.qhc.frye.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qhc.frye.dao.ApplicationRepository;
import com.qhc.frye.domain.ApplicationOfRolechange;
import com.qhc.frye.domain.User;

/**
 */
@Service
public class ApplicationOfRolechangeService {

	@Autowired
	private ApplicationRepository applicationRepository;



	public List<ApplicationOfRolechange> findByBUsersId(int userId) {
		return applicationRepository.findByBusersId(userId);
	}
	
	public ApplicationOfRolechange add(ApplicationOfRolechange applicationOfRolechange) {
		return applicationRepository.save(applicationOfRolechange);
	}
	
	public void deleteByBUsersId(Integer id) {
		applicationRepository.deleteByBusersId(id);;
	}

	public void addOrUpdateApp(User user) throws Exception{

		Set<ApplicationOfRolechange> set = user.getApps();
		ApplicationOfRolechange creatorContainer = set.iterator().next();
		String creator = creatorContainer.getCreator();
		//得到application信息
		ApplicationOfRolechange oldApp = null;
		String roles = "";
		List<ApplicationOfRolechange> apps = findByBUsersId(user.getId());
		if(apps!=null&&apps.size()>0) {
			oldApp = apps.get(0);
			for(ApplicationOfRolechange app : apps) {
				roles= roles+app.getbRolesId()+",";
			}
		}
		
		//角色发生变化的话，删除重加；否则，直接修改
		
		String rolesName = user.getRolesName();
		if(rolesName!=null&&!"".equals(rolesName)) {
			String[] roleArr = rolesName.split(",");
			if(rolesName.equals(roles.substring(0, roles.length()-1))) {
				//直接修改数据
				for(ApplicationOfRolechange app : apps) {
					app.setApprovalTime(new Date());
					app.setAttachedCode(user.getRegion().getCode());
					applicationRepository.save(app);
				}
			}else {
				deleteByBUsersId(user.getId());
				applicationRepository.flush();
				//得到角色id
				for(String roleId:roleArr) {
					ApplicationOfRolechange app = new ApplicationOfRolechange();
					app.setApprovalTime(new Date());
					app.setBusersId(user.getId());
					app.setIsactive(user.getIsActive());
					app.setCreateTime(new Date());
					app.setCreator(creator);
					app.setAttachedCode(user.getRegion().getCode());
					app.setbRolesId(Integer.valueOf(roleId));
					if(oldApp!=null) {
						app.setApproverFact(oldApp.getApproverFact());
						app.setApproverRequired(oldApp.getApproverRequired());
					}else {
						app.setApproverFact("none");
						app.setApproverRequired("none");
					}
					applicationRepository.save(app);
					
				}
			}
			
		}
		
	}

}
