package com.maxcheung.command.queue;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxcheung.command.Command;

@Service
public class CommandConsumer {

	@Autowired
	CommandProducer commandProducer;

	public void consumeRequests() {

		Queue<Command> queue = commandProducer.produceRequests();
		while (!queue.isEmpty()) {
			Command command = queue.peek();
			command.execute();
			queue.remove(command);

			if (queue.isEmpty())
			{
				System.out.println("Queue empty fetch more items.");
				queue = commandProducer.produceRequests();
			}
		}

	}
}