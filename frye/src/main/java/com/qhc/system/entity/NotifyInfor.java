package com.qhc.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "b_notify_infor")
public class NotifyInfor {

	@Id
    @NotNull
	private int id;
	
	@NotNull
	@Column(name="hasSend",columnDefinition ="TINYINT",length=1)
	private int hasSend;
	
	@Column(name="msg_to",columnDefinition="TEXT")
	private String msg_to;
	
	@Column(name="msg_from",columnDefinition="TEXT")
	private String msg_from;
	
	@Column(name="message",columnDefinition="TEXT")
	private String message;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHasSend() {
		return hasSend;
	}

	public void setHasSend(int hasSend) {
		this.hasSend = hasSend;
	}

	public String getMsg_to() {
		return msg_to;
	}

	public void setMsg_to(String msg_to) {
		this.msg_to = msg_to;
	}

	public String getMsg_from() {
		return msg_from;
	}

	public void setMsg_from(String msg_from) {
		this.msg_from = msg_from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NotifyInfor(@NotNull int id, @NotNull int hasSend, String msg_to, String msg_from, String message) {
		super();
		this.id = id;
		this.hasSend = hasSend;
		this.msg_to = msg_to;
		this.msg_from = msg_from;
		this.message = message;
	}

	public NotifyInfor() {
		super();
	}
	
	

	
}
