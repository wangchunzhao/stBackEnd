package com.qhc.frye.service;

import java.util.List;
import java.util.concurrent.locks.LockSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConfirugrableService {
	

	
	public String findMaterial(List<String> config) {
		
		Thread thread = Thread.currentThread();
	    
	    //LockSupport.unpark(thread);
		return null;
	}

}
