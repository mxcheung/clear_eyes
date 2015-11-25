package com.maxcheung.command.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxcheung.command.CommandExecutor;
import com.maxcheung.command.CommandParameters;
import com.maxcheung.command.CommandResult;
import com.maxcheung.dao.JobCommandRepository;
import com.maxcheung.domain.JobCommand;
import com.maxcheung.domain.JobCommandQueue;
import com.maxcheung.spring.JobCommandUtil;

@Service
public class PoliticianExecutor implements CommandExecutor {
	
	@Autowired
	JobCommandRepository jobCommandRepository;
	
	@Override
	public CommandResult execute(CommandParameters commandParameters) {
		CommandResult result = new CommandResult(commandParameters);
		result.setResult("take money from the rich, take votes from the poor");
	    System.out.println(result.getResult());
	    
	    
	    	    
	    JobCommandQueue jobCommandQueue = commandParameters.getJobCommandQueue();
	    JobCommand currentJobCommand = jobCommandRepository.findOne(jobCommandQueue.getJobCommandId());
	    result.setCurrentCommand(currentJobCommand);
	    

		JobCommand nextCommand = JobCommandUtil.createNextJob(currentJobCommand, "DOMESTIC_ENGINEER");
		result.setNextCommand(nextCommand);

	    
		return result;
	}
}