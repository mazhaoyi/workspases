package com;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {
	public static void main(String[] args) {
		// tcp url
		String brokerUrl = "tcp://localhost:61616";
		// connectionFactory JMS用他来创建连接
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, brokerUrl);
		// JSM Provider的连接
		Connection connection = null;
		// 一个发送或接受消息的线程
		Session session = null;
		// 消息的目的地
		Destination destination = null;
		// 消息发送者
		MessageProducer messageProducer = null;
		try {
			// 工厂生产连接对象
			connection = activeMQConnectionFactory.createConnection();
			// 启动连接
			connection.start();
			// 创建线程
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			// 消息目的地
			destination = session.createQueue("first queue");
//			destination = session.createTopic("first topic");
			// 消息发送者
			messageProducer = session.createProducer(destination);
			// 持久化消息
			messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			for (int i = 0; i < 1; i++) {
				TextMessage tm = session.createTextMessage("mq 发送的消息" + i);
				messageProducer.send(tm);
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
			
		}
	}
}
