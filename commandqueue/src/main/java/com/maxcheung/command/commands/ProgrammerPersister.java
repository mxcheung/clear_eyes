package com.maxcheung.command.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxcheung.command.CommandPersister;
import com.maxcheung.command.CommandResult;
import com.maxcheung.dao.JobCommandQueueRepository;
import com.maxcheung.dao.JobCommandRepository;
import com.maxcheung.domain.JobCommand;
import com.maxcheung.domain.JobCommandQueue;

@Service
public class ProgrammerPersister implements CommandPersister {

	@Autowired
	JobCommandRepository jobCommandRepository;

	@Autowired
	JobCommandQueueRepository jobCommandQueueRepository;

	public void execute(CommandResult commandResult) {
		System.out.println("Programmer Persist:" + commandResult.getResult());

		JobCommand nextJobCommand = commandResult.getNextCommand();
		JobCommandQueue currentJobCommandQueue = commandResult.getCommandParameters().getJobCommandQueue();

		if (nextJobCommand != null) {
			System.out.println("Saving next command:" + nextJobCommand.getName());
			jobCommandRepository.save(nextJobCommand);
			JobCommandQueue nextJobCommandQueue = new JobCommandQueue();
			nextJobCommandQueue.setJobCommandId(nextJobCommand.getId());
			nextJobCommandQueue.setCommandType(nextJobCommand.getName());

			System.out.println("Removing current command from queue id:" + currentJobCommandQueue.getId());
			jobCommandQueueRepository.delete(currentJobCommandQueue);
			System.out.println("Saving next command to queue id:" + nextJobCommand.getId());
			jobCommandQueueRepository.save(nextJobCommandQueue);

		}
	}

}