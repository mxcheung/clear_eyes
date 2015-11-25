package com.maxcheung.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JobCommandQueue implements Serializable {

    private static final long serialVersionUID = 1L;

    /** ID of the customer */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    
    @Column
    private long jobCommandId;

    @Column
    private String commandType;
      
    

	public long getId() {
		return id;
	}


	public long getJobCommandId() {
		return jobCommandId;
	}


	public void setJobCommandId(long jobCommandId) {
		this.jobCommandId = jobCommandId;
	}


	public String getCommandType() {
		return commandType;
	}


	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}

	
	





    
	
	

 
}