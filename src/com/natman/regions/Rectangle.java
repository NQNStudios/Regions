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

	@Override
	public String toString() {
		return x + ", " + y + ", " + width + ", " + height;
	}
	
	/**
	 * Parses a Rectangle from a string.
	 * @param text The Rectangle in string form. Example: "(4, 24, 32, 32)"
	 * @return Rectangle instance parsed from the given string.
	 */
	public static Rectangle parseRectangle(String text) {
		String[] dimmensions = text.split(", ");
		
		int x = Integer.parseInt(dimmensions[0]);
		int y = Integer.parseInt(dimmensions[1]);
		int width = Integer.parseInt(dimmensions[2]);
		int height = Integer.parseInt(dimmensions[3]);
		
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Checks if a given point is contained within the rectangle.
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean contains(int x, int y) {
		return x >= this.x && y >= this.y && x <= this.x + width && y <= this.y + height;
	}
	
}
