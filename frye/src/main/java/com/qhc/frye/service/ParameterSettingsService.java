package com.qhc.frye.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.ParameterSettingsRepository;
import com.qhc.frye.entity.Parameter;


@Service
public class ParameterSettingsService {
	
	@Autowired
	private ParameterSettingsRepository parameterSettingsRepository;

	public List<Parameter> findAll() {
		return parameterSettingsRepository.findAll();
	}

	public Parameter updateParameter(Parameter p) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Integer id = p.getId();
		String code = p.getCode();
		Parameter old = parameterSettingsRepository.getOne(id);
		String tdate = sdf.format(new Date());
		String sdate = sdf.format(p.getEnableDate());
		String osdate = sdf.format(old.getEnableDate());
		p.setOptTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		if (!sdate.equals(osdate)) {
			if (sdate.compareTo(tdate) > 0) {
				Parameter after = parameterSettingsRepository.findAfterInfo(code);
				if (after == null) {
					p.setId(null);
				} else {
					p.setId(after.getId());
				}
			} else if (sdate.compareTo(tdate) < 0) {
				throw new IllegalArgumentException("修改的生效时间必须大于当前时间");
			} else {
				//p.setEnableDate(new Date());
			}
		}
		
		p.getEnableDate().setHours(12);
		
		return parameterSettingsRepository.save(p);
	}

	public Parameter findById(Integer id) {
		return parameterSettingsRepository.findById(id).get();
	}
	
	public List<Parameter> findDistinctInfo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date());
		List<Parameter> list = parameterSettingsRepository.findSettings();
		List<Parameter> ps = new ArrayList<Parameter>();
		Map<String, Parameter> map = new LinkedHashMap<String, Parameter>();
		for (Parameter parameter : list) {
			String code = parameter.getCode();
			String date = sdf.format(parameter.getEnableDate());
			if (date.compareTo(today) <= 0) {
				// pre record only get last value
				map.put(code + "_pre", parameter);
			} else {
				// after record only get firt value
				String afterkey = code + "_aft";
				if (!map.containsKey(afterkey)) {
					map.put(code + "_aft", parameter);
				}
			}
		}
		
		for (Map.Entry<String, Parameter> e : map.entrySet()) {
			String key = e.getKey();
			String code = key.substring(0, key.length() - 4);
			Parameter parameter = e.getValue();
			if (key.endsWith("_pre")) {
				ps.add(parameter);
				Parameter after = map.get(code + "_aft");
				if (after != null ) {
					parameter.setAfterValue(after.getsValue());
					parameter.setAfterEnableDate(after.getEnableDate());
					after.setPreValue(parameter.getsValue());
					after.setPreEnableDate(parameter.getEnableDate());
				}
			}
		}
		 
		 return ps;
	}
	
}
