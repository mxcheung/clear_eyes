package com.maxcheung.command.queue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxcheung.command.Command;
import com.maxcheung.dao.JobCommandQueueRepository;
import com.maxcheung.dao.JobCommandRepository;
import com.maxcheung.dao.JobItemDao;
import com.maxcheung.domain.JobCommand;
import com.maxcheung.domain.JobCommandQueue;
import com.maxcheung.domain.JobItem;
import com.maxcheung.spring.CommandUtil;

@Service
public class CommandProducer {
	

	
	@Autowired
	private JobItemDao jobItemDao;
	
	@Autowired
	JobCommandRepository jobCommandRepository;

	@Autowired
	JobCommandQueueRepository jobCommandQueueRepository;


	public Queue<Command> produceRequests() {
		
//		List<JobItem> jobitems = jobItemDao.fetchItems();
//		for (JobItem jobItem : jobitems)
//		{
//			queue.add(CommandUtil.getSystemCommand(jobItem.getStatus(), jobItem.getParams()));
//		}
		
		Queue<Command> queue = new LinkedList<Command>();
		List<JobCommandQueue> jobCommandQueueList = jobCommandQueueRepository.findAll();
		for (JobCommandQueue jobCommandQueue : jobCommandQueueList)
		{
			queue.add(CommandUtil.getSystemCommand( jobCommandQueue));
		}
		
		System.out.println("Producer fetched : " + queue.size() + " items.");
		

		return queue;
	}



	
	
	
}
