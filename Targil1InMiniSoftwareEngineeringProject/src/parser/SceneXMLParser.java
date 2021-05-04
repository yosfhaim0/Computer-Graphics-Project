package parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import elements.AmbientLight;
import geometries.Geometries;
import geometries.Geometry;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Point3D;
import scene.Scene;

/**
 * build scene form xml file .@author yosefHaim
 */
public class SceneXMLParser {
	/**
	 * build scene form xml file
	 * 
	 * @param path to the xml file
	 * @param name name of the scene
	 * @return scene whit all the components form xml file
	 */
	public static Scene sceneXMLParser(String path, String name) {
		// sceneDescriptor = new SceneDescriptor();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Scene scene = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			File file = new File(path);
			Document document;
			document = builder.parse(file);
			document.getDocumentElement().normalize();
			Element root = document.getDocumentElement();
			Color backGroundColor = getColor(root.getAttribute("background-color"));
			AmbientLight ambientLight = getAmbientLight(root);
			Geometries geometries = getGeometries(root);
			scene = new Scene(name).setAmbientLight(ambientLight).setBackground(backGroundColor)
					.setGeometries(geometries);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scene;

	}

	/**
	 * parser color form the element
	 * 
	 * @param element form the xml contain the color
	 * @return color called from the element
	 */
	private static Color getColor(String element) {
		String[] value = element.split(" ");
		Color returnNewColor = new Color(Double.parseDouble(value[0]), Double.parseDouble(value[1]),
				Double.parseDouble(value[2]));
		return returnNewColor;
	}

	/**
	 * parser ambientLight form element
	 * 
	 * @param element form the xml contain the ambientLight
	 * @return ambientLight called from the element
	 */
	private static AmbientLight getAmbientLight(Element element) {
		var ambientLightElement = (Element) element.getElementsByTagName("ambient-light").item(0);
		Color color = getColor(ambientLightElement.getAttribute("color"));
		String KaString = ambientLightElement.getAttribute("Ka");

		if (KaString.isEmpty() || KaString == null) {
			KaString = "1";
		}
		double ka = Double.parseDouble(KaString);
		return new AmbientLight(color, ka);

	}

	/**
	 * parser geometry (triangle, plane,sphere, tube, cylinder...) <br>
	 * form element
	 * 
	 * @param element form the xml contain the geometry shape
	 * @return geometry called from the element
	 */
	private static Geometry getGeometry(Element element) {
		Geometry result = null;
		switch (element.getNodeName()) {
		case "sphere":
			var pc = getPoint(element.getAttribute("center"));
			var radius = Double.parseDouble(element.getAttribute("radius"));
			result = new Sphere(pc, radius);
			break;
		case "triangle":
			var p0 = getPoint(element.getAttribute("p0"));
			var p1 = getPoint(element.getAttribute("p1"));
			var p2 = getPoint(element.getAttribute("p2"));
			result = new Triangle(p0, p1, p2);
			break;
		default:
			break;
		}
		return result;

	}

	/**
	 * parser point form string
	 * 
	 * @param str string contain the coordinates of the point
	 * @return point called from the string
	 */
	private static Point3D getPoint(String str) {
		String[] value = str.split(" ");
		Point3D returnNewColor = new Point3D(Double.parseDouble(value[0]), Double.parseDouble(value[1]),
				Double.parseDouble(value[2]));
		return returnNewColor;
	}

	/**
	 * parser Geometries form Element
	 * 
	 * @param element form the xml contain the Geometries object
	 * @return Geometries (collection of shape) called from the Element
	 */
	private static Geometries getGeometries(Element element) {
		Geometries geometries = new Geometries();
		var geo = element.getElementsByTagName("geometries").item(0).getChildNodes();
		for (int i = 0; i < geo.getLength(); i++) {
			Node node = geo.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				geometries.add(getGeometry((Element) node));
			}
		}
		return geometries;
	}
}