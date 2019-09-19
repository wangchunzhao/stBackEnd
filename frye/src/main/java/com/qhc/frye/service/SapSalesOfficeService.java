package com.qhc.frye.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qhc.frye.dao.SalesOfficeRepository;
import com.qhc.frye.domain.SapSalesOffice;

/**
 */
@Service
public class SapSalesOfficeService {

	@Autowired
	private SalesOfficeRepository salesOfficeRepository;


	/**
	 * 查询区域列表
	 * @return
	 */
	public List<SapSalesOffice> findAll() {
		return salesOfficeRepository.findAll();
	}
	

	public SapSalesOffice findByCode(String code) {
		return salesOfficeRepository.findByCode(code);
	}

}
