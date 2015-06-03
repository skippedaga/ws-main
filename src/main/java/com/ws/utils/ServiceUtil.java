/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

public class ServiceUtil {

	public static String toXML(Object obj) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter writer = new StringWriter();

			jaxbMarshaller.marshal(obj, writer);

			writer.flush();
			return writer.toString();
		} catch (JAXBException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private static <T> JAXBElement<T> makeQName(Object obj) {
		Class c = obj.getClass();
		QName qName = new QName("com.ukrcard.xmlMock", obj.getClass().getName());
		return new JAXBElement<T>(qName, c, (T) obj);
	}

	public static Object fromXML(String str, Class clazz) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			StringReader reader = new StringReader(str);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return jaxbUnmarshaller.unmarshal(reader);
		} catch (JAXBException ex) {
			return null;
		}
	}

}
