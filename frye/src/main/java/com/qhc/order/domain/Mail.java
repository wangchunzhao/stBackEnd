package com.qhc.order.domain;

import java.io.File;
import java.util.LinkedHashMap;

public class Mail {
	private String id;
	private String to;
	private String cc;
	private String bcc;
	private String from;

	private String subject;
	private String body;
	private String head;
	private LinkedHashMap<String, File> attachments;

	public void setId(String id) {
		this.id = id;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public void setAttachments(LinkedHashMap<String, File> attachments) {
		this.attachments = attachments;
	}

	public String getId() {
		return this.id;
	}

	public String getTo() {
		return this.to;
	}

	public String getCc() {
		return this.cc;
	}

	public String getBcc() {
		return this.bcc;
	}

	public String getFrom() {
		return this.from;
	}

	public String getSubject() {
		return this.subject;
	}

	public String getBody() {
		return this.body;
	}

	public String getHead() {
		return this.head;
	}

	public LinkedHashMap<String, File> getAttachments() {
		return this.attachments;
	}

    @Override
  public String toString() {
    return "Mail [id=" + id + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", from=" + from
        + ", subject=" + subject + ", body=" + body + ", head=" + head + ", attachments="
        + attachments + "]";
  }
}