package XMLParse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * @author hongliang.dinghl SAX�ĵ�����
 */
public class SaxDemo implements XmlDocumentInterface {
	public static void main(String args[]){
		new SaxDemo().parserXml("employeeError.xml");
	}
	
	public void createXml(String fileName) {
		System.out.println("<<" + fileName + ">>");
	}

	public void parserXml(String fileName) {
		SAXParserFactory saxfac = SAXParserFactory.newInstance();
		try {
			SAXParser saxparser = saxfac.newSAXParser();
			InputStream is = new FileInputStream(fileName);
			saxparser.parse(is, new MySAXHandler());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class MySAXHandler extends DefaultHandler {
	boolean hasAttribute = false;
	Attributes attributes = null;

	public void startDocument() throws SAXException {
		System.out.println("�ĵ���ʼ��ӡ��");
	}

	public void endDocument() throws SAXException {
		System.out.println("�ĵ���ӡ������");
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("employees")) {
			return;
		}
		if (qName.equals("employee")) {
			System.out.println(qName);
		}
		if (attributes.getLength() > 0) {
			this.attributes = attributes;
			this.hasAttribute = true;
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (hasAttribute && (attributes != null)) {
			for (int i = 0; i < attributes.getLength(); i++) {
				System.out.println(attributes.getQName(i)
						+ attributes.getValue(i));
			}
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		System.out.println(new String(ch, start, length));
	}
}