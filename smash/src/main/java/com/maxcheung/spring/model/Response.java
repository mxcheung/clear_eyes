package com.maxcheung.spring.model;

import java.math.BigDecimal;

public class Response {

	String id;
	String someData;
	String userAgent;
	String additionalInfo;
	
	BigDecimal totalAmt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSomeData() {
		return someData;
	}
	public void setSomeData(String someData) {
		this.someData = someData;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public BigDecimal getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	
	
}
