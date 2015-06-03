package com.ws.main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.ws.types.MyActionBody;
import com.ws.types.MyActionHeader;
import com.ws.types.MyActionResponse;
import com.ws.utils.ServiceUtil;

@WebService(targetNamespace="http://main.ws.com/")
@HandlerChain(file = "handlers.xml")
public class MyActionServer {
	
	/*private final static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyMMddHHmmssSSS");
	private final static String filepath = "income/";
	private final static String fileext = ".xml";*/

	@WebMethod//(action="MyWSAction", operat+ionName="MyWSAction")
	@WebResult(name="Response")
	public MyActionResponse myActionMethod(
			@WebParam(name="header", header=true) MyActionHeader header,
			@WebParam(name="body") MyActionBody body
	) throws SOAPException, IOException, JAXBException {
		
		/*File f = new File(filepath + sdf.format(new Date()) + fileext);

		f.getParentFile().mkdirs();
		f.createNewFile();
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); 
		
		byteArrayOutputStream.write(ServiceUtil.toXML(header).getBytes());
		byteArrayOutputStream.write(ServiceUtil.toXML(body).getBytes());
		
		byteArrayOutputStream.writeTo(new FileOutputStream(f));
		
		sendToAmq(header, body, f.getName());*/
		
		return new MyActionResponse("Message received and processed");
	}
	
	/*
	private void sendToAmq(MyActionHeader header, MyActionBody body, String file_name) throws SOAPException, JAXBException, IOException {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"vm://localhost");

		Connection connection;
		try {
			
			connection = connectionFactory.createConnection();

			connection.start();

			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			Queue destination = session.createQueue("Income");
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			TextMessage message = session.createTextMessage();
			
			message.setStringProperty("head", header.header);
			message.setStringProperty("filename", file_name);
			
			message.setText(ServiceUtil.toXML(body));
			
			System.out.println(ServiceUtil.toXML(body));
			System.out.println(ServiceUtil.toXML(header));

			producer.send(message);

			session.close();
			connection.close();

		} catch (JMSException e) {
			e.printStackTrace();
		}

	}*/

	
	
}
