package com.qhc.system.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qhc.system.dao.SettingsRepository;
import com.qhc.system.entity.Settings;


@Service
public class SettingsService {
	
	@Autowired
	private SettingsRepository parameterSettingsRepository;

	public List<Settings> findAll() {
		return parameterSettingsRepository.findAll();
	}

	@Transactional
	public Settings updateParameter(Settings p) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Integer id = p.getId();
		String code = p.getCode();
		Settings old = parameterSettingsRepository.getOne(id);
		String tdate = sdf.format(new Date());
		String sdate = sdf.format(p.getEnableDate());
		String osdate = sdf.format(old.getEnableDate());
		p.setOptTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		if (!sdate.equals(osdate)) {
			if (sdate.compareTo(tdate) > 0) {
				Settings after = parameterSettingsRepository.findAfterInfo(code);
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

	public Settings findById(Integer id) {
		return parameterSettingsRepository.findById(id).get();
	}

	public Settings findByCode(String code) {
		Settings result = null;
		List<Settings> list = findDistinctInfo();
		for (Settings settings : list) {
			if (settings.getCode().equals(code)) {
				result = settings;
				break;
			}
		}
		return result;
	}
	
	public List<Settings> findDistinctInfo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date());
		List<Settings> list = parameterSettingsRepository.findSettings();
		List<Settings> ps = new ArrayList<Settings>();
		Map<String, Settings> map = new LinkedHashMap<String, Settings>();
		for (Settings parameter : list) {
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
		
		for (Map.Entry<String, Settings> e : map.entrySet()) {
			String key = e.getKey();
			String code = key.substring(0, key.length() - 4);
			Settings parameter = e.getValue();
			if (key.endsWith("_pre")) {
				ps.add(parameter);
				Settings after = map.get(code + "_aft");
				if (after != null ) {
					parameter.setAfterValue(after.getsValue());
					parameter.setAfterEnableDate(after.getEnableDate());
				}
			}
			if (key.endsWith("_aft")) {
			  Settings pre = map.get(code + "_pre");
			  if (pre == null) {
			    ps.add(parameter);
                parameter.setAfterValue(parameter.getsValue());
                parameter.setAfterEnableDate(parameter.getEnableDate());
                parameter.setsValue(null);
                parameter.setEnableDate(null);
			  }
			}
		}
		 
		 return ps;
	}
	
}
