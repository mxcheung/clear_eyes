package com.maxcheung.command;

import com.maxcheung.domain.JobCommand;

public class CommandResult {
	
	
	private CommandParameters commandParameters;
	
	private CommandResultStatus commandResultStatus;
		
	private Object result;
	

	private JobCommand currentCommand;

	private JobCommand nextCommand;
	
	public CommandResult(CommandParameters commandParameters)
	{
		this.commandParameters = commandParameters;
	}
	
	public CommandParameters getCommandParameters() {
		return commandParameters;
	}

	public void setCommandParameters(CommandParameters commandParameters) {
		this.commandParameters = commandParameters;
	}

	public CommandResultStatus getCommandResultStatus() {
		return commandResultStatus;
	}

	public void setCommandResultStatus(CommandResultStatus commandResultStatus) {
		this.commandResultStatus = commandResultStatus;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

		
	public JobCommand getCurrentCommand() {
		return currentCommand;
	}

	public void setCurrentCommand(JobCommand currentCommand) {
		this.currentCommand = currentCommand;
	}

	public JobCommand getNextCommand() {
		return nextCommand;
	}

	public void setNextCommand(JobCommand nextCommand) {
		this.nextCommand = nextCommand;
	}
	
	
	
	
}
