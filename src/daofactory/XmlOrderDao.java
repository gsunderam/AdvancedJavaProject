package daofactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static log.Logger.stdout;

public class XmlOrderDao implements OrderDao {

	//Do XML DB specific set up work here
	static {
		try {
			Files.deleteIfExists(Paths.get("./order.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insertOrder(String name, int id) {
		String order = "\n<item id=\"" + id + "\">\n" + name + "\n</item>\n";
		writeToFile(order);
		return id;
	}

	/**
	 * New Java 7  way to write to files using NIO channels
	 * @param order
	 */
	private void writeToFile(String order) {
		boolean isExist = Files.exists(Paths.get("./order.xml"));
		StringBuilder header = new StringBuilder("<?xml version=\"1.0\"?>\n<items>");

		if (!isExist) order = header.append(order).toString();

		try {
			Files.write(Paths.get("./order.xml"), order.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Order findOrder(int id) {
		try {
			//Just adding the end tag </items>
			Files.write(Paths.get("./order.xml"), "</items>".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			stdout("Found order with id " + id + " value: " + parseXML(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private String parseXML(int id) throws Exception {
		Document doc = getDOMDocument("./order.xml");
		stdout("Root element :" + doc.getDocumentElement().getNodeName());

    NodeList nList = doc.getElementsByTagName("item");
		String item = null;
		
		for (int i = 0; i < nList.getLength(); i++) {
        Node nNode = nList.item(i);
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element node = (Element) nNode;

					  if (id == Integer.valueOf(node.getAttribute("id"))) {
							item = node.getTextContent().replaceAll("\n ", "");
						}
        }
    }

		return item;
  }

	/**
	 * Boiler plate code to get the Document object from the file
	 * @param orderXML
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private Document getDOMDocument(String orderXML) throws ParserConfigurationException, SAXException, IOException {
		File orderXMLFile = new File(orderXML);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(orderXMLFile);

		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
		return doc;
	}
}
