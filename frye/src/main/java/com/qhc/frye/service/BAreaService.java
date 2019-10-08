package com.qhc.frye.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.BAreaRepository;
import com.qhc.frye.dao.BCityRepository;
import com.qhc.frye.dao.BProvinceRepository;
import com.qhc.frye.domain.BArea;
import com.qhc.frye.domain.BCity;
import com.qhc.frye.domain.BProvince;

@Service
public class BAreaService {
	
	@Autowired
	private BProvinceRepository bProvinceRepository;
	
	@Autowired
	private BCityRepository bCityRepository;
	
	@Autowired
	private BAreaRepository bAreaRepository;
	
	public BArea add(BArea bArea) {
		return bAreaRepository.save(bArea);
	}
	
	public Page<List<Object>> getList(Pageable pageable){
		return bAreaRepository.findAllList(pageable);
	}
	
	public void saveFreight(List<List<String>> freight) {
		Set<BProvince> bProvinceList = new HashSet<BProvince>();
		Set<BCity> bCityList = new HashSet<BCity>();
		Set<BArea> bAreaList = new HashSet<BArea>();
		
		 for (int i = 0; i < freight.size(); i++) {
			 BProvince bProvince = new BProvince();
			 bProvince.setName(freight.get(i).get(0));
			 bProvince.setCode(freight.get(i).get(1));
			 bProvinceList.add(bProvince);
			 
			 BCity bCity = new BCity();
			 bCity.setbProvinceCode(freight.get(i).get(1));
			 bCity.setName(freight.get(i).get(2));
			 bCity.setCode(freight.get(i).get(3));
			 bCityList.add(bCity);
			 
			 BArea bArea = new BArea();
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
