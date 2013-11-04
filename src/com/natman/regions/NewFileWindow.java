package com.natman.regions;

/**
 * Popup window for creating a new sprite sheet.
 * @author Natman64
 * @created  Nov 3, 2013
 */
public class NewFileWindow extends Window {

	private static final long serialVersionUID = 3687920556816789705L;
	
	private static final int WINDOW_WIDTH = 400;
	private static final int WINDOW_HEIGHT = 150;
	
	/**
	 * Constructor.
	 */
	public NewFileWindow() {
		super("New Sprite Sheet");
		
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
}
