import javax.xml.parsers.DocumentBuilderFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

class CreateDomXml {
	public static void main(String[] args) {
		String filepath = "C:/Users/koen/Documents/Git/Distributed/Workspace/Week4_1/src";
		String nameFromFileName = "";
		String verlanglijstje = "";
		
		try {
			File dir = new File(filepath);
	        
			 FilenameFilter nameFilter = new FilenameFilter() {
			        public boolean accept(File dir, String name) {
			            return name.toLowerCase().endsWith(".txt");
			        }
			    };
			
			File[] files = dir.listFiles(nameFilter);
	        
	        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.newDocument();

				Element root = doc.createElement("Sinterklaas");
				root.setAttribute("jaar", "2012");
				doc.appendChild(root);
				
				Element lijstje = doc.createElement("lijstje");
				root.appendChild(lijstje);
				
				for(File f : files){
					//get name from filename
					String filename = f.getName();
					String[] parts = filename.split("_");
					nameFromFileName = parts[0];
					
					//create child element for name
					Element child = doc.createElement("naam");
					Text tn = doc.createTextNode(""+nameFromFileName);
					child.appendChild(tn);
					
					//add to lijstje
					lijstje.appendChild(child);
					
					//read file
					InputStream fis = new FileInputStream(f);
					InputStreamReader isr = new InputStreamReader(fis);
					BufferedReader br = new BufferedReader(isr);
					
			 	    while ((verlanglijstje = br.readLine()) != null) {
						child = doc.createElement("kado");
						tn = doc.createTextNode(verlanglijstje);
						child.appendChild(tn);
						lijstje.appendChild(child);
			 	    }
				}

			TransformerFactory tf = TransformerFactory.newInstance();

			Transformer t = tf.newTransformer();
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
					"3");

			DOMSource ds = new DOMSource(doc);
			Result dest = new StreamResult(System.out);
			Result dest1 = new StreamResult(new File("verlanglijstjes.xml"));
			t.transform(ds, dest);
			t.transform(ds, dest1);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}