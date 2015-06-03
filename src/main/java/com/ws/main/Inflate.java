package com.ws.main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.filter.function.regexMatchFunction;

public class Inflate implements SOAPHandler<SOAPMessageContext> {

	private final static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyMMddHHmmssSSS");
	private final static String filepath = "income/";
	private final static String fileext = ".xml";

	public boolean handleMessage(SOAPMessageContext mc) {

		FileOutputStream output = null;

		try {

			Boolean outboundProperty = (Boolean) mc
					.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

			if (outboundProperty.booleanValue())
				return false;

			final SOAPMessage message = mc.getMessage();

			System.out.println(message.toString());

			File f = new File(filepath + sdf.format(new Date()) + fileext);

			f.getParentFile().mkdirs();
			f.createNewFile();

			output = new FileOutputStream(f);

			message.writeTo(output);
			
			sendToAmq(message, f.getName());

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (output != null)
					output.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}

		}

	}

	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

	public void close(MessageContext mc) {
	}

	public boolean handleFault(SOAPMessageContext mc) {
		return true;
	}

	private void sendToAmq(SOAPMessage msg, String file_name) throws SOAPException, JAXBException, IOException {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"vm://localhost");

		Connection connection;
		try {
			
			String header = null;
			
			connection = connectionFactory.createConnection();

			connection.start();

			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			Queue destination = session.createQueue("Income");
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			Iterator<SOAPHeaderElement> iterator = (Iterator<SOAPHeaderElement>) msg
					.getSOAPHeader().extractAllHeaderElements();
			while (iterator.hasNext()) {
				SOAPHeaderElement she = (SOAPHeaderElement) iterator.next();
				System.out.println("Inflanter header:" + she.getTextContent());
				header = she.getTextContent();
			}

			BytesMessage message = session.createBytesMessage();
			
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); 
			
			msg.writeTo(byteArrayOutputStream);
			
			message.setStringProperty("head", header);
			message.setStringProperty("filename", file_name);
			
			message.writeBytes(byteArrayOutputStream.toByteArray());

			producer.send(message);

			session.close();
			connection.close();

		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}