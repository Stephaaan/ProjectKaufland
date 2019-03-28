package sk.itsovy.kaufland.Export;

import java.io.File;
import java.io.IOException;
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

import org.json.simple.parser.ParseException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import sk.itsovy.kaufland.Bill;
import sk.itsovy.kaufland.API.RequestCaller;
import sk.itsovy.kaufland.Models.Item;
import sk.itsovy.kaufland.Models.Food.Fruit;
import sk.itsovy.kaufland.interfaces.DraftInterface;
import sk.itsovy.kaufland.interfaces.PcsInterface;

public class ExportToXML {
	/*
	 * datum, čas, položky, cena,  prepocet
	 * */
	public static void export(Bill b) throws DOMException, IOException, ParseException {
		if(!b.isEnd()) {
			return;
		}
		try {
		    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		    Document doc = docBuilder.newDocument();
		    Element rootElement = doc.createElement("Bill");
		    rootElement.appendChild(doc.createElement("date").appendChild(doc.createTextNode(b.getDate().toGMTString())));
		    Element items = doc.createElement("Items");
		    for(Item i:b.getBill()) {
		    	Element item = doc.createElement("Item");
		    	Element name = doc.createElement("name");
		    	name.appendChild(doc.createTextNode(i.getName()));
		    	item.appendChild(name);
		    	String amount = "";
		    	String unit = "";
		    	if(i instanceof PcsInterface) {
					amount +=((PcsInterface) i).getAmount();
					unit = "pcs";
				}else if(i instanceof Fruit) {
					amount += ((Fruit) i).getWeight();
					unit = "kg";
				}else if(i instanceof DraftInterface) {
					amount += ((DraftInterface) i).getVolume();
					unit = "l";
				}
		    	Element count = doc.createElement("count");
		    	count.setAttribute("unit", unit);
		    	count.appendChild(doc.createTextNode(amount));
		    	item.appendChild(count);
		    	Element pricePerPiece = doc.createElement("PricePerPiece");
		    	pricePerPiece.appendChild(doc.createTextNode(i.getPrice() + ""));
		    	item.appendChild(pricePerPiece);
		    	items.appendChild(item);
		    }
		    rootElement.appendChild(items);
		    Element price = doc.createElement("Price");
		    price.appendChild(doc.createTextNode(b.getTotalPrice() + ""));
		    rootElement.appendChild(price);
		    Element priceInUSD = doc.createElement("PriceInUSD");
		    priceInUSD.appendChild(doc.createTextNode((new RequestCaller().getUdsRate() * b.getTotalPrice()) + ""));
		    rootElement.appendChild(priceInUSD);
		    doc.appendChild(rootElement);
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    DOMSource source = new DOMSource(doc);
		    StreamResult result = new StreamResult(new File("bill"+b.getDate().getTime() +".xml"));
		    transformer.transform(source, result);
		    System.out.println("File saved!");
		  } catch (ParserConfigurationException pce) {
		    pce.printStackTrace();
		  } catch (TransformerException tfe) {
		    tfe.printStackTrace();
		  }
	}
}
