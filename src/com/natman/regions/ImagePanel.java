package com.natman.regions;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * JPanel that draws an image without scaling.
 * @author Natman64
 * @created Nov 3, 2013
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = -4866023796840902035L;
	
	private Image image;
	
	public ImagePanel() {
		setPreferredSize(new Dimension(650, 500));
	}
	
	public ImagePanel(Image image) {
		setImage(image);
	}
	
	public void setImage(Image image) {
		this.image = image;
		
		setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
	
}
