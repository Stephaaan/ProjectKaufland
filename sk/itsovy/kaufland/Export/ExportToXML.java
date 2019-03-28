package sk.itsovy.kaufland.Export;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import sk.itsovy.kaufland.Bill;
import sk.itsovy.kaufland.Models.Item;

public class ExportToXML {
	/*
	 * datum, čas, položky, cena,  prepocet
	 * */
	public static void export(Bill b) {
		if(!b.isEnd()) {
			return;
		}
		try {
		    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		    Document doc = docBuilder.newDocument();
		    Element rootElement = doc.createElement("Bills");
		    for(Item i:b.getBill()) {
		    	Element person = doc.createElement("Person");
		    	Element fname = doc.createElement("Fname");
		    	fname.appendChild(doc.createTextNode(p.getFname()));
		    	person.appendChild(fname);
		    	Element lname = doc.createElement("Lname");
		    	lname.appendChild(doc.createTextNode(p.getLname()));
		    	person.appendChild(lname);
		    	Element dob = doc.createElement("DOB");
		    	dob.appendChild(doc.createTextNode(new SimpleDateFormat("yyyy-mm-dd").format(p.getDob())));
		    	person.appendChild(dob);
		    	Element PIN = doc.createElement("PIN");
		    	PIN.appendChild(doc.createTextNode(p.getPin())); 
		    	person.appendChild(PIN);
		    	rootElement.appendChild(person);
		    }
		    doc.appendChild(rootElement);
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    DOMSource source = new DOMSource(doc);
		    StreamResult result = new StreamResult(new File(name));
		    transformer.transform(source, result);
		    System.out.println("File saved!");
		  } catch (ParserConfigurationException pce) {
		    pce.printStackTrace();
		  } catch (TransformerException tfe) {
		    tfe.printStackTrace();
		  }
	}
}
