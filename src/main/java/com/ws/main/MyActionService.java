package com.ws.main;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.ws.types.MyActionBody;
import com.ws.types.MyActionHeader;
import com.ws.types.MyActionResponse;

@WebService
public class MyActionService {

	@WebMethod(action = "ServiceName", operationName = "operation")
	public MyActionResponse MyActionMethod(
			@WebParam(mode = WebParam.Mode.IN, header = true, name = "MyAction") MyActionHeader header,
			@WebParam(mode = WebParam.Mode.IN, header = true, name = "MyBody") MyActionBody body) {

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"vm://localhost");

		Connection connection;
		try {
			connection = connectionFactory.createConnection();

		connection.start();

		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		Queue destination = session.createQueue("IncomeQueue");
		MessageProducer producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		Message message = session.createObjectMessage();

		message.setObjectProperty("header", header);
		message.setObjectProperty("body", body);

		producer.send(message);

		session.close();
		connection.close();
		
		} catch (JMSException e) {
			e.printStackTrace();
			return new MyActionResponse(e.getMessage());
		}

		return new MyActionResponse("Message received and processed");
	}

}
