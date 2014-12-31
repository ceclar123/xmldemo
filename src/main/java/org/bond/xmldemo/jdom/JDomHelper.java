package org.bond.xmldemo.jdom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.bond.xmldemo.dom4j.Dom4jHelper;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jdom.Attribute;
import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class JDomHelper {
	public Document createDocument() throws Exception {
		Document document = new Document();

		Element root = new Element("root");
		document.addContent(root);

		Element author1 = new Element("author");
		author1.setAttribute("name", "James").setAttribute("location", "UK").addContent("李四");
		root.addContent(author1);

		Element author2 = new Element("author");
		author2.setAttribute("name", "Bob").setAttribute("location", "US").addContent("Bob McWhirter");
		root.addContent(author2);

		Format format = Format.getCompactFormat();
		format.setEncoding("UTF-8");
		format.setIndent("  ");
		XMLOutputter out = new XMLOutputter(format);
		out.output(document, new FileOutputStream("C:\\foo.xml"));

		return document;
	}

	public void parserXml(String fileName) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(fileName);

		Element root = document.getRootElement();
		List userList = root.getChildren();

		for (int i = 0; i < userList.size(); i++) {
			Element user = (Element) userList.get(i);
			// 属性
			List attrs = user.getAttributes();
			for (int j = 0; j < attrs.size(); j++) {
				Attribute attr = (Attribute) attrs.get(j);
				System.out.println(attr.getName() + " <-> " + attr.getValue());
			}
			// 节点值
			System.out.println(user.getName() + ":" + user.getValue());
		}
	}

	public static void main(String[] args) {
		try {
			JDomHelper helper = new JDomHelper();
			helper.createDocument();

			helper.parserXml("C:\\foo.xml");

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}
}
