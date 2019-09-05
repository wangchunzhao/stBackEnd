package com.qhc.frye.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.ParameterSettingsRepository;
import com.qhc.frye.domain.Parameter;


@Service
public class ParameterSettingsService {
	
	@Autowired
	private ParameterSettingsRepository parameterSettingsRepository;

	public List<Parameter> findAll() {
		return parameterSettingsRepository.findAll();
	}

	public Parameter updateParameter(Parameter p) {
		return parameterSettingsRepository.save(p);
	}
	
	public List<Parameter> findDistinctInfo() {
		List<Parameter> list = parameterSettingsRepository.findAll();
		 Set<String> set = new HashSet<String>();
		 if(list!=null&&list.size()>0) {
			 for(Parameter p:list) {
				 set.add(p.getCode());
			 }
		 }
		 List<Parameter> ps = new ArrayList<Parameter>();
		 for(String code:set) {
			 //找到当前生效的
			 Parameter p = parameterSettingsRepository.findEnabledInfo(code);
			 if(p!=null) {
				 Parameter preInfo = parameterSettingsRepository.findPreInfo(code,p.getEnableDate());
				 
				 Parameter afterInfo = parameterSettingsRepository.findAfterInfo(code);
				 
				 if(preInfo!=null) {
					 p.setPreValue(preInfo.getsValue());
					 p.setPreEnableDate(preInfo.getEnableDate());
				 }
				 if(afterInfo!=null) {
					 p.setAfterValue(afterInfo.getsValue());
					 p.setAfterEnableDate(afterInfo.getEnableDate());
				 }
			 }
			 ps.add(p);
		 }
		 
		 return ps;
	}

}
