package com.maxcheung.spring.model;

import java.math.BigDecimal;

public class Request {

	String someData;
	BigDecimal creditAmt;
	BigDecimal miscAmt;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	String id;

	public String getSomeData() {
		return someData;
	}

	public void setSomeData(String someData) {
		this.someData = someData;
	}

	public BigDecimal getCreditAmt() {
		return creditAmt;
	}

	public void setCreditAmt(BigDecimal creditAmt) {
		this.creditAmt = creditAmt;
	}

	public BigDecimal getMiscAmt() {
		return miscAmt;
	}

	public void setMiscAmt(BigDecimal miscAmt) {
		this.miscAmt = miscAmt;
	}
	
	

}
