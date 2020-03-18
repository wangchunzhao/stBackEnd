package com.qhc.order.domain.bpm;

public class OrderAttachment {
	/* FileName */
	private String name = null;

	/* FileUrl */
	private String url = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "OrderAtttachment [name=" + name + ", url=" + url + "]";
	}

}
