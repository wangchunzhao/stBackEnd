package com.qhc.system.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qhc.system.dao.AreaRepository;
import com.qhc.system.dao.CityRepository;
import com.qhc.system.dao.ProvinceRepository;
import com.qhc.system.entity.Area;
import com.qhc.system.entity.City;
import com.qhc.system.entity.Province;

@Service
public class AreaService {
	
	@Autowired
	private ProvinceRepository bProvinceRepository;
	
	@Autowired
	private CityRepository bCityRepository;
	
	@Autowired
	private AreaRepository bAreaRepository;

	@Transactional
	public Area add(Area bArea) {
		return bAreaRepository.save(bArea);
	}
	
	public Page<List<Object>> getList(Pageable pageable){
		return bAreaRepository.findAllList(pageable);
	}

	@Transactional
	public void saveFreight(List<List<String>> freight) {
		Set<Province> bProvinceList = new HashSet<Province>();
		Set<City> bCityList = new HashSet<City>();
		Set<Area> bAreaList = new HashSet<Area>();
		
		 for (int i = 0; i < freight.size(); i++) {
			 Province bProvince = new Province();
			 bProvince.setName(freight.get(i).get(0));
			 bProvince.setCode(freight.get(i).get(1));
			 bProvinceList.add(bProvince);
			 
			 City bCity = new City();
			 bCity.setProvinceCode(freight.get(i).get(1));
			 bCity.setName(freight.get(i).get(2));
			 bCity.setCode(freight.get(i).get(3));
			 bCityList.add(bCity);
			 
			 Area bArea = new Area();
			 bArea.setbCityCode(freight.get(i).get(3));
			 bArea.setName(freight.get(i).get(4));
			 bArea.setCode(freight.get(i).get(5));
			 bArea.setPrice(Double.valueOf(freight.get(i).get(6)));
			 bArea.setPrice1(Double.valueOf(freight.get(i).get(7)));
			 bArea.setPrice2(Double.valueOf(freight.get(i).get(8)));
			 bArea.setPrice3(Double.valueOf(freight.get(i).get(9)));
			 bArea.setPrice4(Double.valueOf(freight.get(i).get(10)));
			 bArea.setPrice5(Double.valueOf(freight.get(i).get(11)));
			 bArea.setPrice6(Double.valueOf(freight.get(i).get(12)));
			 bArea.setPrice7(Double.valueOf(freight.get(i).get(13)));
			 bArea.setPrice8(Double.valueOf(freight.get(i).get(14)));
			 bArea.setPrice9(Double.valueOf(freight.get(i).get(15)));
			 bArea.setPrice10(Double.valueOf(freight.get(i).get(16)));
			 bArea.setPrice11(Double.valueOf(freight.get(i).get(17)));
			 bAreaList.add(bArea);
		 }
		bProvinceRepository.saveAll(bProvinceList);
		bCityRepository.saveAll(bCityList);
		bAreaRepository.saveAll(bAreaList);
	}

}
