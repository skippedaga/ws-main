package com.ws.tests;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ws.client.IOException_Exception;
import com.ws.client.MyActionBody;
import com.ws.client.MyActionHeader;
import com.ws.client.MyActionMethod;
import com.ws.client.MyActionServer;
import com.ws.client.MyActionServerService;
import com.ws.client.SOAPException_Exception;
import com.ws.main.MainServer;

public class MainTest extends CamelTestSupport {

	@EndpointInject(uri = "mock:result")
	protected MockEndpoint resultEndpoint;

	@Before
	public void SetUp() throws Exception {
		new MainServer(); // Starting Jetty SOAP server
	}

	@Test
	public void firstStep() throws IOException_Exception,
			SOAPException_Exception {

		MyActionServerService srv = new MyActionServerService();
		MyActionServer serv = srv.getMyActionServerPort();

		// Put i msg to SOAP serv
		for (int i = 1; i <= 2; i++) {

			MyActionBody actionBody = new MyActionBody();
			actionBody.setBody("Body ¹" + i);

			MyActionHeader header = new MyActionHeader();
			header.setHeader("Header ¹" + i);

			MyActionMethod method = new MyActionMethod();
			method.setBody(actionBody);

			serv.myActionMethod(method, header);
		}

	}

	protected CamelContext createCamelContext() throws Exception {
		CamelContext camelContext = super.createCamelContext();
		camelContext.addComponent("activemq",
				activeMQComponent("vm://localhost"));
		return camelContext;
	}

	protected RouteBuilder createRouteBuilder() throws Exception {

		return new RouteBuilder() {
			public void configure() throws Exception {

				from("activemq:queue:Income")
						.unmarshal()
						.soapjaxb("com.ws.client")
						.split()
						.body()
						.convertBodyTo(MyActionMethod.class)
						.marshal()
						.soapjaxb("com.ws.client")
						.to("activemq:queue:Outcome")
						.log(LoggingLevel.INFO,
								"message N:"
									+ simple("${in.header.head}").getText()
									+ " file:"
									+ simple("${in.header.filename}")
											.getText() + " "
									+ simple("${in.body}").getText())
						.to("mock:result");

				from("activemq:queue:Outcome").to("mock:result");
			}
		};
	}

	@BeforeClass
	public static void init() {
		System.setProperty(
				"com.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize", "true");
	}

	@AfterClass
	public static void revert() {
		System.getProperties().remove(
				"com.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize");
	}

}
