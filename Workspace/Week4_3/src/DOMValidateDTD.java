import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DOMValidateDTD {
	public static void main(String args[]) {
		try {
			String port = "port";
			String portNumber = "80t80";
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document d = db.parse(new FileInputStream("server.xml"));
			
	        NodeList nodeList = d.getElementsByTagName("Connector");
	        for(int i = 0; i < nodeList.getLength(); i++){
	        	System.out.println("Before: "+nodeList.item(i).getAttributes().getNamedItem(port).getTextContent());
	        	nodeList.item(i).getAttributes().getNamedItem(port).setTextContent(portNumber);
	        	System.out.println("After: "+nodeList.item(i).getAttributes().getNamedItem(port).getTextContent());
	        }
	        
	        DOMSource source = new DOMSource(d);

	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        StreamResult result = new StreamResult("server.xml");
	        transformer.transform(source, result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}