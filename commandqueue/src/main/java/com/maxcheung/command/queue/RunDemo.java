package com.maxcheung.command.queue;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.maxcheung.command.Command;
import com.maxcheung.dao.CustomerRepository;
import com.maxcheung.dao.JobCommandQueueRepository;
import com.maxcheung.dao.JobCommandRepository;
import com.maxcheung.domain.Customer;
import com.maxcheung.domain.JobCommand;
import com.maxcheung.domain.JobCommandQueue;

@Component
public class RunDemo {

	@Autowired
	CommandProducer commandProducer;
	
	@Autowired
	CommandConsumer commandConsumer;
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	JobCommandRepository jobCommandRepository;

	@Autowired
	JobCommandQueueRepository jobCommandQueueRepository;
	
	
	private static ApplicationContext context;
	
	public static void main(String[] args) {
		
		  context = new ClassPathXmlApplicationContext(
			        "context.xml");

		  RunDemo runDemo = context.getBean(RunDemo.class);
		  runDemo.start(args);
		  
		  
	}
	
    private void start(String[] args) {
    
    
    
	    addJob("PROGRAMMER", "A1");
	    addJob("POLITICIAN","B1");
	    addJob("PROGRAMMER","B2");
		
    	
//		Queue<Command> queue = commandProducer.produceRequests();
		commandConsumer.consumeRequests();
		
//		queue = commandProducer.produceRequests();
//		commandConsumer.consumeRequests(queue);
		
		
		
    	System.out.println("my beans method: ");
    	
    	
		Customer customer = new Customer();
		customer.setLastname("Bloggs");
		System.out.println("xxxmy beans method: ");
		customerRepository.save(customer);

//		JobCommand jobCommand = new JobCommand();
//		jobCommand.setName("Demo");
//		jobCommandRepository.save(jobCommand);
//		
//		JobCommandQueue jobCommandQueue = new JobCommandQueue();
//		jobCommandQueue.setJobCommandId(jobCommand.getId());
//		jobCommandQueueRepository.save(jobCommandQueue);
    }

	private void addJob(String jobname, String sourceID) {
		JobCommand nextJobCommand = new JobCommand();
	    nextJobCommand.setName(jobname);
	    nextJobCommand.setSourceid(sourceID);
		jobCommandRepository.save(nextJobCommand);
		System.out.println("Saving next command to queue id:" + nextJobCommand.getId());
		JobCommandQueue jobCommandQueue = new JobCommandQueue();
		jobCommandQueue.setJobCommandId(nextJobCommand.getId());
		jobCommandQueue.setCommandType(nextJobCommand.getName());
		jobCommandQueueRepository.save(jobCommandQueue);
	}
}