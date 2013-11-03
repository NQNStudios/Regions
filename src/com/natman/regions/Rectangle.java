package com.natman.regions;

/**
 * A rectangle defined by its top left corner and dimmensions.
 * @author Natman64
 * @created  Nov 2, 2013
 */
public class Rectangle {

	public int x;
	public int y;
	public int width;
	public int height;
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * @param key The key that this Rectangle is represented by in the sprite sheet.
	 * @return An XML node encoding this Rectangle's data.
	 */
	public String xmlNode(String key) {
		return "<rect key=\"" + key + "\">" + 
				x + ", " + y + ", " + width + ", " + height + "</rect>";
	}
	
}
