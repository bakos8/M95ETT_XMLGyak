package hu.domparse.m95ett;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomModifyM95ETT {

	static Document readResult;

	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, IOException, TransformerException, XPathExpressionException {

		//get the file for parsing 
		File xmlName = new File("XMLM95ETT.xml");

		// call the method that parses the file
		readResult = readDoc(xmlName);

		//methods to modify the file - select ones you want to run
		//********************************************************
		//modifyAttribute(readResult, "Profil", "profilID", "003", "321");
		//modifyAttribute(readResult, "Beepites", "beepID", "034", "340");
		//modifyElement(readResult, "/Ablakgyar/Uvegek/Uveg[@uvegID=147]/Hofolia", "3", "5");
		//modifyElement(readResult, "/Ablakgyar/Rendelesek/Rendeles[@rendelesszam=185]/Megrendelo/Telepules", "Miskolc", "Kazincbarcika");
		modifyElement(readResult, "/Ablakgyar/Anyagok/Anyag/profilID_fk", "012", "004");

		// write the result to a new file, and display it on console
		File myFile = new File("XMLM95ETTmod.xml");
		writeXml(readResult, myFile);
	}

	//parsing the input file
	public static Document readDoc(File xmlName) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		File toParse = xmlName;
		DocumentBuilder db = dbf.newDocumentBuilder();

		// parse XML file
		Document doc = db.parse(toParse);

		return (doc);
	}

	//modifies the selected attributes (as ID-s are unique, no type checking needed)
	public static void modifyAttribute(Document doc, String tagName, String attrName, String oldData, String newData) {

		NodeList nodes = doc.getElementsByTagName(tagName);

		for (int i = 0; i < nodes.getLength(); i++) {

			Node node = nodes.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				if (node.getAttributes().getNamedItem(attrName).getTextContent().equals(oldData)) {

					node.getAttributes().getNamedItem(attrName).setTextContent(newData);

				}
			}
		}
	}

	//modifies the selected element (using xPhath to avoid problems when two elements have the same attribute
	public static void modifyElement(Document doc, String accesPath, String oldData, String newData) throws XPathExpressionException {

		XPath xPath = XPathFactory.newInstance().newXPath();

		// get the nodelist based on the query
		NodeList nodes = (NodeList) xPath.compile(accesPath).evaluate(doc, XPathConstants.NODESET);

		for (int i = 0; i < nodes.getLength(); i++) {

			Node node = nodes.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				if (node.getTextContent().equals(oldData)) {

					node.setTextContent(newData);
				}
			}
		}
	}
	
	//writes the modified file to console, and to the specified file path
	// https://mkyong.com/java/how-to-modify-xml-file-in-java-dom-parser/
	private static void writeXml(Document doc, File output) throws TransformerException, UnsupportedEncodingException {

		TransformerFactory transformerFactory = TransformerFactory.newInstance();

		Transformer transformer = transformerFactory.newTransformer();

		// pretty print
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		DOMSource source = new DOMSource(doc);

		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(output);

		transformer.transform(source, console);
		transformer.transform(source, file);

	}
}