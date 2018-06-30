package com.manage.project.process.person;

import com.manage.project.base.pojo.BaseEntity;

public class LogInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4083198105223423873L;
	
	 private String id;
	
	 private String serviceType;
	 
	 private String userId;
	 
	 private String serviceName;
	 
	 private String coinNum;
	 
	 private String time;
	 
	 private String pageSzie;
	 
	 private String page;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getCoinNum() {
		return coinNum;
	}

	public void setCoinNum(String coinNum) {
		this.coinNum = coinNum;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPageSzie() {
		return pageSzie;
	}

	public void setPageSzie(String pageSzie) {
		this.pageSzie = pageSzie;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	 
	 

}
