package com.rabbitMQ;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageProducer {
	private Logger logger = Logger.getLogger(MessageProducer.class);

	@Resource
	private AmqpTemplate amqpTemplate;

	public void sendMessageToQueueOne(Object message){
	  amqpTemplate.convertAndSend("queueOneKey",message);
	}
	
	public void sendMessageToQueueTwo(Object message){
		  amqpTemplate.convertAndSend("queueTwoKey",message);
		}

	public void sendMessageToQueue(String queueName , Object Message){
		amqpTemplate.convertAndSend(queueName,Message);
	}
}
