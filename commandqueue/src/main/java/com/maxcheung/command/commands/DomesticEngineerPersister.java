package com.maxcheung.command.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxcheung.command.CommandPersister;
import com.maxcheung.command.CommandResult;
import com.maxcheung.dao.JobCommandQueueRepository;
import com.maxcheung.dao.JobCommandRepository;
import com.maxcheung.domain.JobCommandQueue;

@Service
public class DomesticEngineerPersister implements CommandPersister {

	@Autowired
	JobCommandRepository jobCommandRepository;

	@Autowired
	JobCommandQueueRepository jobCommandQueueRepository;

	public void execute(CommandResult commandResult) {
		System.out.println("Dom engineer Persist:" + commandResult.getResult());

		JobCommandQueue currentJobCommandQueue = commandResult.getCommandParameters().getJobCommandQueue();
		System.out.println("Removing current command from queue id:" + currentJobCommandQueue.getId());
		jobCommandQueueRepository.delete(currentJobCommandQueue);

  	    System.out.println("Current command finished:" + currentJobCommandQueue.getId() );

	}

}