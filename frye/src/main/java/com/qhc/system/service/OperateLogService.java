package com.qhc.system.service;

import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageInfo;
import com.qhc.system.entity.OperateLog;
import com.qhc.system.mapper.OperateLogMapper;

@Service
public class OperateLogService {
	@Autowired
	private OperateLogMapper operateLogMapper;

	@Transactional
	public int save(OperateLog log) {
		return operateLogMapper.insert(log);
	}
    
    public PageInfo<OperateLog> findLogs(Map<String, Object> params) throws Exception {
      return this.findLogs(params, 0, 10);
    }
    
    public PageInfo<OperateLog> findLogs(Map<String, Object> params, int pageNo, int pageSize) throws Exception {
	    // 设置分页信息
	    pageNo = pageNo < 0 ? 0 : pageNo;
	    pageSize = pageSize < 0 ? 10 : pageSize;

	    com.github.pagehelper.PageHelper.startPage(pageNo, pageSize);
	    List<OperateLog> logs = operateLogMapper.findByParams(params);

	    return new PageInfo<>(logs);
	}

}
