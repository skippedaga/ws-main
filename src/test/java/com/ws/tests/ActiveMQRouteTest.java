package com.ws.tests;

import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.component.jms.JmsEndpoint;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;
import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

/**
 *
 */
public class ActiveMQRouteTest extends CamelTestSupport {
    protected MockEndpoint resultEndpoint;
    protected String startEndpointUri = "activemq:queue:test.a";

    @Test
    public void testJmsRouteWithTextMessage() throws Exception {
        String expectedBody = "Hello there!";

        resultEndpoint.expectedBodiesReceived(expectedBody);
        resultEndpoint.message(0).header("cheese").isEqualTo(123);

        sendExchange(expectedBody);

        resultEndpoint.assertIsSatisfied();
    }

    protected void sendExchange(final Object expectedBody) {
        template.sendBodyAndHeader(startEndpointUri, expectedBody, "cheese", 123);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();

        resultEndpoint = (MockEndpoint) context.getEndpoint("mock:result");
    }

    protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();

        // START SNIPPET: example
        camelContext.addComponent("activemq", activeMQComponent("vm://localhost?broker.persistent=false"));
        // END SNIPPET: example

        return camelContext;
    }

    @Test
    public void testInvalidDestinationOptionOnConsumer() throws Exception {
        getMockEndpoint("mock:result").expectedMessageCount(0);
        assertMockEndpointsSatisfied(1, TimeUnit.SECONDS);
        try {
            new RouteBuilder() {
                public void configure() throws Exception {
                    from("activemq:queue:foo?destination.consumer.exclusive=true&destination.consumer.unknown=foo")
                        .to("mock:result");
                }
            };
        } catch (Exception e) {
            fail("Should not have accepted bad destination options.");
        }
    }

    @Test
    public void testInvalidDestinationOptionOnProducer() throws Exception {
        try {
            new RouteBuilder() {
                public void configure() throws Exception {
                    from("activemq:queue:foo")
                        .to("activemq:queue:bar?destination.producer.exclusive=true");
                }
            };
        } catch (Exception e) {
            fail("Should not have accepted bad destination options.");
        }
    }

    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from(startEndpointUri).to("activemq:queue:test.b");
                from("activemq:queue:test.b").to("mock:result");

                JmsEndpoint endpoint1 = (JmsEndpoint) endpoint("activemq:topic:quote.IONA");
                endpoint1.getConfiguration().setTransacted(true);
                from(endpoint1).to("mock:transactedClient");

                JmsEndpoint endpoint2 = (JmsEndpoint) endpoint("activemq:topic:quote.IONA");
                endpoint2.getConfiguration().setTransacted(false);
                from(endpoint2).to("mock:nonTrasnactedClient");
            }
        };
    }
}