package org.bond.xmldemo.dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Attribute;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jHelper {
	public Document createDocument() throws Exception {
		Document document = DocumentHelper.createDocument();

		Element root = document.addElement("root");
		Element author1 = root.addElement("author").addAttribute("name", "James").addAttribute("location", "UK").addText("张三");
		Element author2 = root.addElement("author").addAttribute("name", "Bob").addAttribute("location", "US").addText("Bob McWhirter");

		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置输出编码
		format.setEncoding("UTF-8");
		format.setIndent(" ");
		// 创建需要写入的File对象
		File file = new File("C:\\foo.xml");
		// 生成XMLWriter对象，构造函数中的参数为需要输出的文件流和格式
		XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
		// 开始写入，write方法中包含上面创建的Document对象
		writer.write(document);

		return document;
	}

	public void parserXml(String fileName) throws Exception {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File(fileName));

		// 根节点
		Element root = document.getRootElement();
		for (Iterator i = root.elementIterator(); i.hasNext();) {
			Element user = (Element) i.next();
			// 属性
			Iterator attrs = user.attributeIterator();
			while (attrs.hasNext()) {
				Attribute attr = (Attribute) attrs.next();
				System.out.println(attr.getName() + " <-> " + attr.getValue());
			}
			// 节点值
			System.out.println(user.getName() + ":" + user.getText());
		}
	}

	public static void main(String[] args) {
		try {
			Dom4jHelper helper = new Dom4jHelper();
			helper.createDocument();
			helper.parserXml("C:\\foo.xml");

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}
}
