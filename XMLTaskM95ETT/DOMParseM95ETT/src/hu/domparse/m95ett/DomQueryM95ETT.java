package hu.domparse.m95ett;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryM95ETT {

	// class level static variables
	static StringBuilder readLines = new StringBuilder();
	static Document readResult;

	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		// get the file we need to work on
		File xmlName = new File("XMLM95ETT.xml");

		// call the method that parses the file
		readResult = readDoc(xmlName);

		// All queries - please select one, then run the program
		// *****************************************************
		// printNote(readResult, "/Ablakgyar/Uvegek/Uveg");
		// printNote(readResult, "/Ablakgyar/Uvegek/Uveg[@uvegID=146]");
		// printNote(readResult, "/Ablakgyar/Rendelesek/Rendeles[last()]");
		// printNote(readResult, "/Ablakgyar/Rendelesek/Rendeles[@rendelesszam > 183]");
		printNote(readResult, "/Ablakgyar/Profilok/Profil/Anyag[Fa > 1]");

		// write the query result to console
		System.out.println(readLines);

	}

	public static Document readDoc(File xmlName) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		File toParse = xmlName;
		DocumentBuilder db = dbf.newDocumentBuilder();

		// parse XML file
		Document doc = db.parse(toParse);

		// get the root element, then display it
		Node first = doc.getDocumentElement();

		readLines.append("Root Element: " + first.getNodeName());
		readLines.append("\n-------------------------");

		return (doc);
	}

	private static void printNote(Document doc, String queryName) throws XPathExpressionException {

		XPath xPath = XPathFactory.newInstance().newXPath();

		// get the nodelist based on the query
		NodeList elementsToRead = (NodeList) xPath.compile(queryName).evaluate(doc, XPathConstants.NODESET);

		// iterate trough the list, then write out the element nodes (if no child nodes
		// present, write out the text value as well
		for (int i = 0; i < elementsToRead.getLength(); i++) {

			Node tempNode = elementsToRead.item(i);

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				readLines.append("\n\n  " + tempNode.getNodeName());
				readLines.append("\n  -----------------------");

				if (tempNode.hasAttributes()) {

					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();
					for (int j = 0; j < nodeMap.getLength(); j++) {
						Node node = nodeMap.item(j);
						readLines.append("\n    " + node.getNodeName() + ": " + node.getNodeValue());
					}
				}

				if (tempNode.getChildNodes().getLength() != 1) {
					NodeList childs = tempNode.getChildNodes();

					for (int k = 0; k < childs.getLength(); k++) {

						Node asd = childs.item(k);

						if (asd.getNodeType() == Node.ELEMENT_NODE) {

							handleChilds(asd);
						}
					}

				} else
					readLines.append("\n" + tempNode.getNodeName() + ": " + tempNode.getTextContent());
			}
		}
	}

	private static void handleChilds(Node asd) {

		if (asd.getChildNodes().getLength() != 1) {
			NodeList secondarychilds = asd.getChildNodes();
			readLines.append("\n    " + asd.getNodeName());

			for (int l = 0; l < secondarychilds.getLength(); l++) {

				Node qwe = secondarychilds.item(l);

				if (qwe.getNodeType() == Node.ELEMENT_NODE) {

					readLines.append("\n      " + qwe.getNodeName() + ": " + qwe.getTextContent());
				}
			}

		} else
			readLines.append("\n    " + asd.getNodeName() + ": " + asd.getTextContent());
	}
}