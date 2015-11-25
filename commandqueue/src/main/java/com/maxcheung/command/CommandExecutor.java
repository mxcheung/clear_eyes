package com.maxcheung.command;

public interface CommandExecutor {
	CommandResult execute(CommandParameters commandParameters);
}
