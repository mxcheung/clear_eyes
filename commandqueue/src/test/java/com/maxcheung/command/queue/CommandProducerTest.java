package com.maxcheung.command.queue;


import static org.junit.Assert.assertEquals;

import java.util.Queue;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maxcheung.command.Command;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-config.xml"})
public class CommandProducerTest {

	@Autowired
	private CommandProducer commandProducer;
	
	
    @Test
    public void test() throws ExecutionException {
    	Queue<Command> requests = commandProducer.produceRequests();
    	assertEquals(5,requests.size());
    }
}
