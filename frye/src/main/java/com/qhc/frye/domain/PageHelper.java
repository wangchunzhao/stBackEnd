package com.qhc.frye.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageHelper <T>{
	//实体类集合
    private List<T> rows = new ArrayList<T>();
    //数据总条数
    private int total;
 
    public PageHelper() {
        super();
    }
    
    public PageHelper(Page page) {
    	
		this.setTotal(Integer.parseInt(String.valueOf(page.getTotalElements())));
		this.setRows(page.getContent());
    }
 
    public List<T> getRows() {
        return rows;
    }
 
    public void setRows(List<T> rows) {
        this.rows = rows;
    }
 
    public int getTotal() {
        return total;
    }
 
    public void setTotal(int total) {
        this.total = total;
    }

}
