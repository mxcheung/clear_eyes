package com.maxcheung.command;


import static org.junit.Assert.assertNotNull;

import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maxcheung.domain.JobCommandQueue;
import com.maxcheung.domain.JobItem;
import com.maxcheung.spring.CommandUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-config.xml"})
public class CommandUtilTest {

	
	
    @Test
    public void test() throws ExecutionException {
    	Long id = 1L;
//    	JobItem jobItem = createJobItem("PROGRAMMER",id++, null);
    	JobCommandQueue jobCommandQueue = createJobCommandQueue("PROGRAMMER");
		Command command = CommandUtil.getSystemCommand(jobCommandQueue);
    	assertNotNull(command);
    }
    
	private JobItem createJobItem(String status, Long id, Object params) {
		JobItem jobitem = new JobItem();
		jobitem.setId(id);
		jobitem.setStatus(status);
		jobitem.setParams(params);
		return jobitem;
	}


	private JobCommandQueue createJobCommandQueue(String commandType) {
		JobCommandQueue jobCommandQueue = new JobCommandQueue();
		jobCommandQueue.setCommandType(commandType);
		
		return jobCommandQueue;
	}

	
    @Test
    public void testGetBeans() throws ExecutionException {
    	
		CommandParameters commandParameters = new CommandParameters();
		commandParameters.setCommandType(CommandType.CONVERSATIONIDCHECK);

    	CommandExecutor commandExecutor = CommandUtil.getCommandExecutor(commandParameters);
    	assertNotNull(commandExecutor);

    	CommandPersister commandPersister = CommandUtil.getCommandPersister(commandParameters);
    	assertNotNull(commandPersister);

    
    }
    
	
}
