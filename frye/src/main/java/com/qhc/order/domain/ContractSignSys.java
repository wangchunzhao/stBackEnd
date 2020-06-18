package com.qhc.order.domain;

import io.swagger.annotations.*;
import java.sql.Timestamp;
//import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@ApiModel(description = "签约系统合同")
public class ContractSignSys {
	private long id;
	@Size(max = 40)
	@ApiModelProperty("电子签约中合同Id")
	private String signContractId;
	@Size(max = 200)
	@ApiModelProperty("文档Hash值")
	private String fileHashCode;
	private String title;
	private String status;

	public void setId(long id) {
		this.id = id;
	}

	@Size(max = 2)
	@ApiModelProperty("是否删除标识")
	private String isDelete;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty("创建日期")
	private Timestamp createDate;
	private Boolean curHave;

	public void setSignContractId(String signContractId) {
		this.signContractId = signContractId;
	}

	public void setFileHashCode(String fileHashCode) {
		this.fileHashCode = fileHashCode;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public void setCurHave(Boolean curHave) {
		this.curHave = curHave;
	}

	public long getId() {
		return this.id;
	}

	public String getSignContractId() {
		return this.signContractId;
	}

	public String getFileHashCode() {
		return this.fileHashCode;
	}

	public String getIsDelete() {
		return this.isDelete;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public Boolean getCurHave() {
		return this.curHave;
	}

	public String getTitle() {
		return title;
	}

	public String getStatus() {
		return status;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
