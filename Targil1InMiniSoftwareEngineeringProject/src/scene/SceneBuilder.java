package scene;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Scene Builder form xml
 * 
 * @author yosefHaim
 *
 */
public class SceneBuilder {
	/**
	 * scene to build
	 */
	Scene scene;
	/**
	 * path to xml file
	 */
	String filePath;
	/**
	 * name of scene
	 */
	String name;

	/**
	 * ctor for scene Builder
	 * 
	 * @param path string whit location of the file
	 * @param name name of scene
	 */
	public SceneBuilder(String path, String name) {
		this.filePath = path;
		this.name = name;
		this.scene = loadSceneFormFile();
	}

	/**
	 * get the scene
	 * 
	 * @return scene
	 */
	public Scene getScene() {
		return this.scene;
	}

	/**
	 * load the scene form the file
	 * 
	 * @return the scene
	 */
	public Scene loadSceneFormFile() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			File file = new File(filePath);
			Document document;
			document = builder.parse(file);
			document.getDocumentElement().normalize();
			Element root = document.getDocumentElement();
			scene = SceneXMLParser.sceneXMLParser(name, root);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return scene;
	}
}
