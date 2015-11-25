package com.maxcheung.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JobCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    /** ID of the customer */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    private String sourceid;

    
    @Column
    private String name;

    @Column
    private String result;

    @Column
    private long parentid;

    public void JCommand() {

    }


    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public long getParentid() {
		return parentid;
	}


	public void setParentid(long parentid) {
		this.parentid = parentid;
	}


	public String getSourceid() {
		return sourceid;
	}


	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}
	
	

 
}