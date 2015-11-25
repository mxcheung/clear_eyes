package com.maxcheung.command.commands;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maxcheung.command.CommandPersister;
import com.maxcheung.command.CommandResult;
import com.maxcheung.command.CommandResultStatus;

@Service
public class ConversationIdCheckPersister implements CommandPersister {

	public void execute(CommandResult commandResult) {
		Object result = commandResult.getResult();
		String conv = (String) commandResult.getCommandParameters().getParams();
		List<String> convList = (List<String>) result;
		for (String convData : convList) {

			System.out.println("Persist:" + convData);
		}
		if (commandResult.getCommandResultStatus() == CommandResultStatus.SUCCESS) {
			System.out.println("Persist: " + conv + ": true");
		} else if (commandResult.getCommandResultStatus() == CommandResultStatus.FAILURE) {
			System.out.println("Persist: " + conv + ": false");
		}

	}

}