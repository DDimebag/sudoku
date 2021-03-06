package toplist;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Manages the high scores of the game via XML.
 * 
 * Used to create and xml document, it can also read, and write from it.
 *
 */
public class Toplist {
	private static Logger logger = LoggerFactory.getLogger(Toplist.class);
	
	private String fileSeparator = System.getProperty("file.separator");
	private File toplistFile;
	File dir = new File(System.getProperty("user.home") +  fileSeparator + "sudokutoplist");
	boolean successful = dir.mkdir();
	/**
	 * Assigns {@code toplistFile} depending on from which directory we run the
	 * program.
	 */
	public Toplist() {
		toplistFile = new File(System.getProperty("user.home") + fileSeparator + "sudokutoplist" + fileSeparator + "toplist.xml");
	}

	/**
	 * Creates the toplist.xml, if it doesn't exist yet.
	 */
	public void createToplistXML() {
		//toplistFile = new File(System.getProperty("user.home") + fileSeparator + "toplist.xml");
		try {
			if (!toplistFile.exists()) {
				
				logger.trace("toplist.xml created");
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.newDocument();

				Element rootElement = doc.createElement("levels");
				doc.appendChild(rootElement);

				Element level = doc.createElement("level");
				Attr id = doc.createAttribute("id");
				id.setValue("1");
				level.setAttributeNode(id);
				rootElement.appendChild(level);

				Element nickname = doc.createElement("nickname");
				nickname.appendChild(doc.createTextNode(""));
				level.appendChild(nickname);

				Element time = doc.createElement("time");
				time.appendChild(doc.createTextNode("0"));
				level.appendChild(time);

				Element level2 = doc.createElement("level");
				Attr id2 = doc.createAttribute("id");
				id2.setValue("2");
				level2.setAttributeNode(id2);
				rootElement.appendChild(level2);

				Element nickname2 = doc.createElement("nickname");
				nickname2.appendChild(doc.createTextNode(""));
				level2.appendChild(nickname2);

				Element time2 = doc.createElement("time");
				time2.appendChild(doc.createTextNode("0"));
				level2.appendChild(time2);

				Element level3 = doc.createElement("level");
				Attr id3 = doc.createAttribute("id");
				id3.setValue("3");
				level3.setAttributeNode(id3);
				rootElement.appendChild(level3);

				Element nickname3 = doc.createElement("nickname");
				nickname3.appendChild(doc.createTextNode(""));
				level3.appendChild(nickname3);

				Element time3 = doc.createElement("time");
				time3.appendChild(doc.createTextNode("0"));
				level3.appendChild(time3);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(toplistFile);

				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				transformer.transform(source, result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the text content of the time element -in the XML document with the
	 * given number of seconds- whose parent element's id is the first parameter.
	 * 
	 * @param levelId
	 *            the id of the element to be updated
	 * @param time
	 *            the new time value
	 */
	public void updateTimeById(int levelId, int time) {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(toplistFile);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("level");

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (Integer.toString(levelId).equals(eElement.getAttribute("id")))
						eElement.getElementsByTagName("time").item(0).setTextContent(Integer.toString(time));
				}
			}

			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty(OutputKeys.METHOD, "xml");
			tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			DOMSource domSource = new DOMSource(doc);
			StreamResult result = new StreamResult(toplistFile);
			tf.transform(domSource, result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the text content of the nickname element -in the XML document with
	 * the given String- whose parent element's id is the first parameter.
	 * 
	 * @param levelId
	 *            the id of the element to be updated
	 * @param nickname
	 *            the new nickname value
	 */
	public void updateNicknameById(int levelId, String nickname) {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(toplistFile);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("level");

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (Integer.toString(levelId).equals(eElement.getAttribute("id")))
						eElement.getElementsByTagName("nickname").item(0).setTextContent(nickname);
				}
			}

			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty(OutputKeys.METHOD, "xml");
			tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			DOMSource domSource = new DOMSource(doc);
			StreamResult result = new StreamResult(toplistFile);
			tf.transform(domSource, result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the text content of the time element, whose parent element has the given
	 * id.
	 * 
	 * @param levelId
	 *            parent elements id
	 * @return time elements text content casted to int
	 */
	public int getTimeById(int levelId) {
		int seconds = 0;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(toplistFile);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("level");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (eElement.getAttribute("id").equals(Integer.toString(levelId)))
						seconds = Integer.parseInt(eElement.getElementsByTagName("time").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			logger.error("exception", e);
		}
		return seconds;
	}

	/**
	 * Gets the text content of the nickname element, whose parent element has the
	 * given id.
	 * 
	 * @param levelId
	 *            parent elements id
	 * @return nickname elements text content
	 */
	public String getNicknameById(int levelId) {
		String nickname = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(toplistFile);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("level");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (eElement.getAttribute("id").equals(Integer.toString(levelId)))
						nickname = eElement.getElementsByTagName("nickname").item(0).getTextContent();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nickname;
	}
}
