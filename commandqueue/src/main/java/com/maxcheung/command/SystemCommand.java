package com.maxcheung.command;

import com.maxcheung.spring.CommandUtil;

public class SystemCommand implements Command {

	private final CommandParameters commandParameters;
	
	public SystemCommand(CommandParameters commandParameters) {
		super();
		this.commandParameters = commandParameters;
	}

	@Override
	public void execute() {
		CommandExecutor execCmd = CommandUtil.getCommandExecutor(commandParameters);
		CommandPersister persistCmd = CommandUtil.getCommandPersister(commandParameters);
		CommandResult commandResult =  execCmd.execute(commandParameters);
		persistCmd.execute(commandResult);
	}

	
}