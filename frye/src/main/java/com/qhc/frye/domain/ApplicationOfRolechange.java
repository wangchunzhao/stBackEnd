package com.qhc.frye.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "b_application_of_rolechange")
public class ApplicationOfRolechange implements Serializable{
	
	@Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

	@NotNull
	@Column(name="creator",columnDefinition="TEXT")
	private String creator;
	
	@NotNull
	@Column(name="create_time",columnDefinition="datetime")
    private Date createTime;

	@Column(name="approval_time",columnDefinition="datetime")
    private Date approvalTime;

	@NotNull
	@Column(name="approver_required",columnDefinition="TEXT")
	private String approverRequired;
	
	@Column(name="approver_fact",columnDefinition="TEXT")
	private String approverFact;
	
	@NotNull
	@Column(name="b_users_id",length = 10)
    private Integer bUsersId;

	@NotNull
	@Column(name="isactive",columnDefinition="BIT")
    private int isactive;

	@Column(name="attached_code",columnDefinition="CHAR",length = 32)
    private String attachedCode;//区域编码

	@NotNull
	@Column(name="b_roles_id",length = 10)
    private Integer bRolesId;
	

    
	 @JoinColumn(name = "b_users_id")   // 外键关联
	 private User user = new User();
	 
	 @JoinColumn(name = "b_roles_id")   // 外键关联
	 private Role role = new Role();
	 
	 @JoinColumn(name = "attached_code")   // 外键关联
	 private SapSalesOffice sapSales = new SapSalesOffice();
    

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getApproverRequired() {
        return approverRequired;
    }

    public void setApproverRequired(String approverRequired) {
        this.approverRequired = approverRequired == null ? null : approverRequired.trim();
    }

    public String getApproverFact() {
        return approverFact;
    }

    public void setApproverFact(String approverFact) {
        this.approverFact = approverFact == null ? null : approverFact.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public Integer getbUsersId() {
        return bUsersId;
    }

    public void setbUsersId(Integer bUsersId) {
        this.bUsersId = bUsersId;
    }

    public String getAttachedCode() {
        return attachedCode;
    }

    public void setAttachedCode(String attachedCode) {
        this.attachedCode = attachedCode == null ? null : attachedCode.trim();
    }

    public Integer getbRolesId() {
        return bRolesId;
    }

    public void setbRolesId(Integer bRolesId) {
        this.bRolesId = bRolesId;
    }

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public SapSalesOffice getSapSales() {
		return sapSales;
	}

	public void setSapSales(SapSalesOffice sapSales) {
		this.sapSales = sapSales;
	}
    
    
}