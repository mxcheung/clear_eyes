package com.maxcheung.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.maxcheung.command.Command;
import com.maxcheung.command.CommandExecutor;
import com.maxcheung.command.CommandParameters;
import com.maxcheung.command.CommandPersister;
import com.maxcheung.command.CommandType;
import com.maxcheung.command.SystemCommand;
import com.maxcheung.domain.JobCommand;
import com.maxcheung.domain.JobCommandQueue;

public class CommandUtil implements BeanFactoryAware {

	private static BeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory bf) throws BeansException {
		beanFactory = bf;

	}

	public static CommandExecutor getCommandExecutor(CommandParameters commandParameters) {
		return (CommandExecutor) beanFactory.getBean(commandParameters.getCommandType() + "Executor");
	}

	public static CommandPersister getCommandPersister(CommandParameters commandParameters) {
		return (CommandPersister) beanFactory.getBean(commandParameters.getCommandType() + "Persister");
	}

	private static CommandType getCommandType(String status) {
		CommandType commandType;
		switch (status) {
		case "CHECK_CONVERSATION_ID":
			commandType = CommandType.CONVERSATIONIDCHECK;
			break;
		case "DOMESTIC_ENGINEER":
			commandType = CommandType.DOMESTIC_ENGINEER;
			break;
		case "PROGRAMMER":
			commandType = CommandType.PROGRAMMER;
			break;
		case "POLITICIAN":
			commandType = CommandType.POLITICIAN;
			break;
		default:
			throw new IllegalArgumentException("Invalid status: " + status);
		}
		return commandType;
	}



	public static Command getSystemCommand(JobCommandQueue jobCommandQueue) {
		CommandParameters commandParameters = new CommandParameters();
		commandParameters.setCommandType(getCommandType(jobCommandQueue.getCommandType()));
		// commandParameters.setParams(params);
		commandParameters.setJobCommandQueue(jobCommandQueue);
		return new SystemCommand(commandParameters);
	}

}