package com;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {
	public static void main(String[] args) {
		// tcp url
		String brokerUrl = "tcp://127.0.0.1:61616";
		ConnectionFactory cf = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, brokerUrl);
		Connection con = null;
		Session session = null;
		Destination dest = null;
		MessageConsumer mc = null;
		try {
			// 工厂生产连接对象
			con = cf.createConnection();
			// 启动连接
			con.start();
			session = con.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			dest = session.createQueue("first queue");
//			dest = session.createTopic("first topic");
			mc = session.createConsumer(dest);
			while (true) {
//			for (int i = 0; i < 20; i++) {
				TextMessage mm = (TextMessage) mc.receive();
				session.commit();
				System.out.println("1 topic:" + mm.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
