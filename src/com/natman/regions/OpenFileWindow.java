package com.natman.regions;

/**
 * Popup window for opening an existing sprite.
 * @author Natman64
 * @created  Nov 3, 2013
 */
public class OpenFileWindow extends Window {

	private static final long serialVersionUID = -8496067020182795067L;
	
	private static final int WINDOW_WIDTH = 400;
	private static final int WINDOW_HEIGHT = 150;
	
	/**
	 * Constructor.
	 */
	public OpenFileWindow() {
		super("Open Existing Sprite Sheet");
		
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

}
