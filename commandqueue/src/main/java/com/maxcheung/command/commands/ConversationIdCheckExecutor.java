package com.maxcheung.command.commands;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.maxcheung.command.CommandExecutor;
import com.maxcheung.command.CommandParameters;
import com.maxcheung.command.CommandResult;
import com.maxcheung.command.CommandResultStatus;

@Component
public class ConversationIdCheckExecutor implements CommandExecutor {
	public CommandResult execute(CommandParameters commandParameters) {
		List<String> convList = conversationIdList();
		List<String> retList = new ArrayList<String>();
		String conv = (String) commandParameters.getParams();
		retList.add("checking : " + conv );

		CommandResult result = new CommandResult(commandParameters);
		CommandResultStatus status;
		status =  convList.contains(conv)  ? CommandResultStatus.SUCCESS : CommandResultStatus.FAILURE;
		result.setCommandResultStatus(status);
		result.setResult(retList);
		return result;
	}

	
	
	private List<String> conversationIdList() {
		List<String> listA = new ArrayList<String>();
		listA.add("element 1");
		listA.add("element 2");
		listA.add("element 3");
		listA.add("conv1");
		return listA;
	}



	
}