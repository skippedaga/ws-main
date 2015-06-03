
package com.ws.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MyActionServerService", targetNamespace = "http://main.ws.com/", wsdlLocation = "http://localhost:9000/MyService?wsdl")
public class MyActionServerService
    extends Service
{

    private final static URL MYACTIONSERVERSERVICE_WSDL_LOCATION;
    private final static WebServiceException MYACTIONSERVERSERVICE_EXCEPTION;
    private final static QName MYACTIONSERVERSERVICE_QNAME = new QName("http://main.ws.com/", "MyActionServerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9000/MyService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MYACTIONSERVERSERVICE_WSDL_LOCATION = url;
        MYACTIONSERVERSERVICE_EXCEPTION = e;
    }

    public MyActionServerService() {
        super(__getWsdlLocation(), MYACTIONSERVERSERVICE_QNAME);
    }

    public MyActionServerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MYACTIONSERVERSERVICE_QNAME, features);
    }

    public MyActionServerService(URL wsdlLocation) {
        super(wsdlLocation, MYACTIONSERVERSERVICE_QNAME);
    }

    public MyActionServerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MYACTIONSERVERSERVICE_QNAME, features);
    }

    public MyActionServerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MyActionServerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MyActionServer
     */
    @WebEndpoint(name = "MyActionServerPort")
    public MyActionServer getMyActionServerPort() {
        return super.getPort(new QName("http://main.ws.com/", "MyActionServerPort"), MyActionServer.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MyActionServer
     */
    @WebEndpoint(name = "MyActionServerPort")
    public MyActionServer getMyActionServerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://main.ws.com/", "MyActionServerPort"), MyActionServer.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MYACTIONSERVERSERVICE_EXCEPTION!= null) {
            throw MYACTIONSERVERSERVICE_EXCEPTION;
        }
        return MYACTIONSERVERSERVICE_WSDL_LOCATION;
    }

}
