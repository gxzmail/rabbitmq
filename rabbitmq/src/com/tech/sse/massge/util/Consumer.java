package com.tech.sse.massge.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import org.apache.poi.hpbf.model.MainContents;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.Queue;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.GetResponse;


public class Consumer {
	private static final String EXCHANGE_NAME = "exchange_name";
	private static final String QUEUE_NAME = "queue_name";
	private static final String ROUTING_KEY = "routing_key";
	
	public static void main(String[] args) throws Exception {
		boolean autoAck = false;
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null); 
//		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);	
		 // 定义队列的消费者
		DefaultConsumer consumer = new DefaultConsumer(channel);
		// 监听队列
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
//        Thread.sleep(500);
        // 获取消息
        while (true) {
//        	Thread.sleep(500);
        	Queue.DeclareOk response1 = channel.queueDeclarePassive(QUEUE_NAME);
        	System.out.println("response1:" + response1.getConsumerCount());
        	GetResponse response = channel.basicGet(QUEUE_NAME, autoAck);
        	if (response == null) {
        	    // No message retrievedTODO
//        		System.out.println("response = " + response);
        	} else {
        	    AMQP.BasicProperties props = response.getProps();
        	    byte[] body = response.getBody();
        	    // to process body
        	    ByteArrayInputStream bis = new ByteArrayInputStream(body);
        	    ObjectInput in = new ObjectInputStream(bis);
        	    ArrayList<String> cellContext = (ArrayList<String>)in.readObject();
        	    System.out.println(" [x] Received '" + cellContext + "'");
        	    //long deliveryTag = response.getEnvelope().getDeliveryTag();
        	    // ...
        }
	}
  }
}
