package com.ws.main;

public class Main {

	public static void main(String[] args) throws Exception {
		MainServer srv = new MainServer();
		
		Thread.sleep(60000);
		
		srv.stop();
	}

}
