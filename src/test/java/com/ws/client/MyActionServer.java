
package com.ws.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MyActionServer", targetNamespace = "http://main.ws.com/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MyActionServer {


    /**
     * 
     * @param parameters
     * @param header
     * @return
     *     returns com.ws.client.MyActionMethodResponse
     * @throws SOAPException_Exception
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(name = "myActionMethodResponse", targetNamespace = "http://main.ws.com/", partName = "result")
    @Action(input = "http://main.ws.com/MyActionServer/myActionMethodRequest", output = "http://main.ws.com/MyActionServer/myActionMethodResponse", fault = {
        @FaultAction(className = SOAPException_Exception.class, value = "http://main.ws.com/MyActionServer/myActionMethod/Fault/SOAPException"),
        @FaultAction(className = IOException_Exception.class, value = "http://main.ws.com/MyActionServer/myActionMethod/Fault/IOException")
    })
    public MyActionMethodResponse myActionMethod(
        @WebParam(name = "myActionMethod", targetNamespace = "http://main.ws.com/", partName = "parameters")
        MyActionMethod parameters,
        @WebParam(name = "header", targetNamespace = "http://main.ws.com/", header = true, partName = "header")
        MyActionHeader header)
        throws IOException_Exception, SOAPException_Exception
    ;

}