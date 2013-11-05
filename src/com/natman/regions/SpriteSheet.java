package com.natman.regions;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * A SpriteSheet storing the path of its texture and a map of texture regions.
 * @author Natman64
 * @created  Nov 2, 2013
 */
public class SpriteSheet {
	
	public String texturePath;
	public Map<String, Rectangle> regions = new HashMap<String, Rectangle>();
	
	/**
	 * Constructor.
	 * @param texturePath File path of the sprite sheet texture
	 * (relative to the game's assets directory).
	 */
	public SpriteSheet(String texturePath) {
		this.texturePath = texturePath;
	}
	
	/**
	 * Adds a texture region to the sprite sheet.
	 * @param key The key of the texture region.
	 * @param region Rectangle representing the texture region.
	 */
	public void addRegion(String key, Rectangle region) {
		regions.put(key, region);
	}
	
	public void saveToFile(File file) {
		try {
			 
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("SpriteSheet");
			doc.appendChild(rootElement);
	 
			//Texture element
			Element texture = doc.createElement("texture");
			texture.setTextContent(texturePath);
			rootElement.appendChild(texture);
			
			Iterator<Entry<String, Rectangle>> it = regions.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Rectangle> regionEntry = it.next();
				
				Element regionChild = doc.createElement("rect");
				Attr key = doc.createAttribute("key");
				key.setValue(regionEntry.getKey());
				regionChild.setAttributeNode(key);
				
				Rectangle rect = regionEntry.getValue();
				regionChild.setTextContent(rect.toString());
				
				rootElement.appendChild(regionChild);
			}
	 
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
	 
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
	 
			transformer.transform(source, result);
	 
		} catch (Exception e) {
		  
		}
	}
	
}
