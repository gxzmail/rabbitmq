package com.tech.sse.massge.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY); 
/*
 * test code		
 */
//		ArrayList<String> cellContext = new ArrayList();
//		cellContext.add("aa1");
//		cellContext.add("aa2");
		String cellContext = "aaaaa" + ROUTING_KEY;
		byte[]bytes; 
//	    ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
//	    ObjectOutputStream oos = new ObjectOutputStream(baos); 
//	    oos.writeObject(cellContext);
//	    oos.flush();
//	    oos.reset();
//	    bytes = baos.toByteArray();
	    channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, cellContext.getBytes());
	    System.out.println(" [x] Sent '" + cellContext + "'");
	    channel.close();
	    connection.close();

	}


	

}
