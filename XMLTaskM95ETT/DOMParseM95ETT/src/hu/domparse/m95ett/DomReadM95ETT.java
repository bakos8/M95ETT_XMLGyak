package hu.domparse.m95ett;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReadM95ETT {

	static StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

		// Instantiate the Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		File toParse = new File("XMLM95ETT.xml");

		DocumentBuilder db = dbf.newDocumentBuilder();

		// parse XML file
		Document doc = db.parse(toParse);

		Node first = doc.getFirstChild();
		NodeList rest = first.getChildNodes();

		// write out the parent element's name
		result.append("Root Element: " + first.getNodeName());
		result.append("\n-------------------------");

		// call the method that reads out the file
		printNote(rest);

		// call the method that writes the result to a file
		writeTofile(result);

		System.out.println(result);

	}

	private static void printNote(NodeList nodeList) {
		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				// get node name and value
				result.append("\n\nKategoria: " + tempNode.getNodeName());
				result.append("\n-------------------------");

				if (tempNode.hasAttributes()) {
					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node node = nodeMap.item(i);
						result.append("\n\n" + node.getNodeName() + node.getNodeValue());
					}
				}

				//this means there are other elements in the node
				if (tempNode.getChildNodes().getLength() != 1) {
					NodeList childs = tempNode.getChildNodes();


					for (int i = 0; i < childs.getLength(); i++) {

						Node asd = childs.item(i);

						if (asd.getNodeType() == Node.ELEMENT_NODE) {
							if (asd.getChildNodes().getLength() != 1) {
								
								NodeList secondarychilds = asd.getChildNodes();
								
								result.append("\n\n  " + asd.getNodeName());
								result.append("\n-------------------------");
								
								handleChildNodes(secondarychilds);

							} else
								result.append("\n  " + asd.getNodeName() + ": " + asd.getTextContent());
						}
					}

				} else
					result.append("\n" + tempNode.getNodeName() + ": " + tempNode.getTextContent());
			}
		}
	}

	//if there are multiple child nodes, this method handles them
	private static void handleChildNodes(NodeList childs) {
		for (int i = 0; i < childs.getLength(); i++) {

			Node nthChild = childs.item(i);

			if (nthChild.getNodeType() == Node.ELEMENT_NODE) {
				if (nthChild.getChildNodes().getLength() != 1) {
					NodeList qwe = nthChild.getChildNodes();
					result.append("\n    " + nthChild.getNodeName());
					handleChildNodes(qwe);
				} else
					result.append("\n  " + nthChild.getNodeName() + ": " + nthChild.getTextContent());
			}
		}
	}

	//create a FileWriter, and create/overwrite the file given the name
	private static void writeTofile(StringBuilder result) throws IOException {
		FileWriter myWriter = new FileWriter("DomReadM95ETT.txt");
		myWriter.write(result.toString());
		myWriter.close();
	}
}