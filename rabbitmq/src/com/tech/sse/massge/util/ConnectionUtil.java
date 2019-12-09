package com.tech.sse.massge.util;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class ConnectionUtil {
/*
 * Function: 用户名=guest,密码=guest.默认配置
 */
	public static Connection getConnection() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		//设置服务器IP
		factory.setHost("localhost");
		//获取与服务器的连接
		Connection connection = factory.newConnection();
		return connection; 
	}

}
