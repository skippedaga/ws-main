package com.ws.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class MyActionResponse {
	
	protected String returnMsg;
	
	public MyActionResponse() {
		super();
	}

	
	public MyActionResponse(String returnMsg) {
		super();
		this.returnMsg = returnMsg;
	}

}
