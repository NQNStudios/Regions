package com.natman.regions;

import java.awt.Color;
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
	
	private float scale = 1f;
	private Color backgroundColor = Color.gray;
	
	private Image image;
	
	public ImagePanel() {
		setPreferredSize(new Dimension(650, 500));
	}
	
	public ImagePanel(Image image) {
		setImage(image);
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		
		repaint();
	}
	
	public void setImage(Image image) {
		this.image = image;
		
		setPreferredSize(
				new Dimension(
						(int) (image.getWidth(null) * scale),
						(int) (image.getHeight(null) * scale)));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (image != null) {
			g.drawImage(image, 
					0, 0, 
					(int) (image.getWidth(null) * scale),
					(int) (image.getHeight(null) * scale),
					backgroundColor, null);
		}
	}
	
}
