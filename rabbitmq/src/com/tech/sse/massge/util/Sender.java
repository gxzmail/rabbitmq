package com.tech.sse.massge.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Sender {
	private static final String EXCHANGE_NAME = "exchange_name";
	private static final String QUEUE_NAME = "queue_name";
	private static final String ROUTING_KEY = "routing_key";

	public static void main(String[] args) throws IOException, TimeoutException {
		//TODO 获取excel中的数据		
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		channel.queueBind(QUEUE_NAME, QUEUE_NAME, ROUTING_KEY);
/*
 * test code		
 */
		String cellConten = "xzguo"; 
				
	}


	

}
