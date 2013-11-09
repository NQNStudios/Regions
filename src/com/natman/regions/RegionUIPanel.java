package com.natman.regions;

import javax.swing.JPanel;

/**
 * Contains the appropriate panel for working with regions visually.
 * @author Natman64
 * @created  Nov 8, 2013
 */
public class RegionUIPanel extends JPanel {

	private static final long serialVersionUID = 3640079057981950301L;
	
	private JPanel panel;
	private AppWindow window;
	
	public RegionUIPanel(AppWindow window) {
		this.window = window;
	}
	
	public void setPanel(JPanel panel) {
		if (this.panel != null) {
			remove(this.panel);
		}
		
		this.panel = panel;
		add(panel);
		
		window.validate();
	}
	
}
