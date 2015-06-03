package com.ws.main;

import javax.xml.ws.Endpoint;

public class MainServer {

	Endpoint ep;

	public MainServer() throws Exception {
		System.out.println("Starting Server");
		Object implementor = new MyActionServer();
		String address = "http://localhost:9000/MyService";
		this.ep = Endpoint.publish(address, implementor);
	}

	public void stop() {
		System.out.println("Server exiting");
		this.ep.stop();
	}
}
