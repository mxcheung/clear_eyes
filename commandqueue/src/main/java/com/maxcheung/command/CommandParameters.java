package com.maxcheung.command;

import com.maxcheung.domain.JobCommand;
import com.maxcheung.domain.JobCommandQueue;

public class CommandParameters {
	
	
	private CommandType commandType;
	
	private Object params;
	
	
	private JobCommandQueue jobCommandQueue;

	
	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	public JobCommandQueue getJobCommandQueue() {
		return jobCommandQueue;
	}

	public void setJobCommandQueue(JobCommandQueue jobCommandQueue) {
		this.jobCommandQueue = jobCommandQueue;
	}


	


	
}
