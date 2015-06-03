
package com.ws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ws.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _IOException_QNAME = new QName("http://main.ws.com/", "IOException");
    private final static QName _SOAPException_QNAME = new QName("http://main.ws.com/", "SOAPException");
    private final static QName _MyActionResponse_QNAME = new QName("http://main.ws.com/", "myActionResponse");
    private final static QName _MyActionMethod_QNAME = new QName("http://main.ws.com/", "myActionMethod");
    private final static QName _MyActionHeader_QNAME = new QName("http://main.ws.com/", "myActionHeader");
    private final static QName _MyActionMethodResponse_QNAME = new QName("http://main.ws.com/", "myActionMethodResponse");
    private final static QName _MyActionBody_QNAME = new QName("http://main.ws.com/", "myActionBody");
    private final static QName _Header_QNAME = new QName("http://main.ws.com/", "header");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ws.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link MyActionResponse }
     * 
     */
    public MyActionResponse createMyActionResponse() {
        return new MyActionResponse();
    }

    /**
     * Create an instance of {@link SOAPException }
     * 
     */
    public SOAPException createSOAPException() {
        return new SOAPException();
    }

    /**
     * Create an instance of {@link MyActionMethodResponse }
     * 
     */
    public MyActionMethodResponse createMyActionMethodResponse() {
        return new MyActionMethodResponse();
    }

    /**
     * Create an instance of {@link MyActionHeader }
     * 
     */
    public MyActionHeader createMyActionHeader() {
        return new MyActionHeader();
    }

    /**
     * Create an instance of {@link MyActionMethod }
     * 
     */
    public MyActionMethod createMyActionMethod() {
        return new MyActionMethod();
    }

    /**
     * Create an instance of {@link MyActionBody }
     * 
     */
    public MyActionBody createMyActionBody() {
        return new MyActionBody();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.ws.com/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SOAPException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.ws.com/", name = "SOAPException")
    public JAXBElement<SOAPException> createSOAPException(SOAPException value) {
        return new JAXBElement<SOAPException>(_SOAPException_QNAME, SOAPException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MyActionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.ws.com/", name = "myActionResponse")
    public JAXBElement<MyActionResponse> createMyActionResponse(MyActionResponse value) {
        return new JAXBElement<MyActionResponse>(_MyActionResponse_QNAME, MyActionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MyActionMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.ws.com/", name = "myActionMethod")
    public JAXBElement<MyActionMethod> createMyActionMethod(MyActionMethod value) {
        return new JAXBElement<MyActionMethod>(_MyActionMethod_QNAME, MyActionMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MyActionHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.ws.com/", name = "myActionHeader")
    public JAXBElement<MyActionHeader> createMyActionHeader(MyActionHeader value) {
        return new JAXBElement<MyActionHeader>(_MyActionHeader_QNAME, MyActionHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MyActionMethodResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.ws.com/", name = "myActionMethodResponse")
    public JAXBElement<MyActionMethodResponse> createMyActionMethodResponse(MyActionMethodResponse value) {
        return new JAXBElement<MyActionMethodResponse>(_MyActionMethodResponse_QNAME, MyActionMethodResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MyActionBody }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.ws.com/", name = "myActionBody")
    public JAXBElement<MyActionBody> createMyActionBody(MyActionBody value) {
        return new JAXBElement<MyActionBody>(_MyActionBody_QNAME, MyActionBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MyActionHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.ws.com/", name = "header")
    public JAXBElement<MyActionHeader> createHeader(MyActionHeader value) {
        return new JAXBElement<MyActionHeader>(_Header_QNAME, MyActionHeader.class, null, value);
    }

}
