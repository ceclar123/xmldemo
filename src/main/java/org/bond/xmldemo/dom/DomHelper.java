package org.bond.xmldemo.dom;

import java.io.File;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomHelper {
	public Document createDocument() throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();

		Element root = document.createElement("root");
		document.appendChild(root);

		Element author1 = document.createElement("author");
		author1.setAttribute("name", "宋祖德");
		author1.setAttribute("location", "USA");
		author1.setTextContent("王五");
		root.appendChild(author1);

		Element author2 = document.createElement("author");
		author2.setAttribute("name", "宋慧乔");
		author2.setAttribute("location", "UK");
		author2.setTextContent("哈哈哈");
		root.appendChild(author2);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();

		DOMSource source = new DOMSource(document);
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		PrintWriter pw = new PrintWriter(new File("C:\\foo.xml"));
		StreamResult streamResult = new StreamResult(pw);
		transformer.transform(source, streamResult);

		return document;
	}

	public void parserXml(String fileName) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(fileName);

		// 根节点
		NodeList root = document.getChildNodes();
		for (int i = 0; i < root.getLength(); i++) {
			// author节点
			NodeList users = root.item(i).getChildNodes();
			for (int j = 0; j < users.getLength(); j++) {
				System.out.print(users.item(j).getNodeName() + ":" + users.item(j).getTextContent());

				NodeList userMeta = users.item(j).getChildNodes();
				for (int k = 0; k < userMeta.getLength(); k++) {
					if (userMeta.item(k).getNodeType() == Node.ATTRIBUTE_NODE) {
						System.out.println(userMeta.item(k).getNodeName() + ":" + userMeta.item(k).getTextContent());
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			DomHelper helper = new DomHelper();
			helper.createDocument();

			helper.parserXml("C:\\foo.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
