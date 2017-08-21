

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DOMValidateDTD {
	public static void main(String args[]) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document d = db.parse(new FileInputStream("tomcat-users.xml"));
			NodeList nodeList = d.getElementsByTagName("tomcat-users");
			
			for(int i = 0; i < nodeList.getLength(); i++){
				
				Element newUser = d.createElement("user");
				newUser.setAttribute("password", "password");
				newUser.setAttribute("role", "manager-gui");
				newUser.setAttribute("name", "newUserKees");
				
				nodeList.item(i).appendChild(newUser);
			}
			
			DOMSource source = new DOMSource(d);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			StreamResult sr = new StreamResult("tomcat-users.xml");
			t.transform(source, sr);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}