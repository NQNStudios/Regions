package com.natman.regions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JPanel;

/**
 * JPanel subclass that draws the sprite sheet texture and visual region info.
 * @author Natman64
 * @created Nov 3, 2013
 */
public class ImagePanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = -4866023796840902035L;
	
	private float scale = 1f;
	private Color backgroundColor = Color.gray;
	
	private Color regionColor = new Color(0f, 0f, 1f, 0.3f);
	private Color outlineColor = Color.white;;
	
	private Image image;
	private SpriteSheet spriteSheet;
	
	private AddRegionPanel addRegionPanel;
	
	public ImagePanel() {
		addMouseListener(this);
	}
	
	public void setScale(float scale) {
		this.scale = scale;
		
		if (image != null) {
			setPreferredSize(
					new Dimension(
							(int) (image.getWidth(null) * scale),
							(int) (image.getHeight(null) * scale)));
		}
		
		repaint();
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
	
	public void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	
	public void setAddRegionPanel(AddRegionPanel addRegionPanel) {
		this.addRegionPanel = addRegionPanel;
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
		
		if (spriteSheet != null && !spriteSheet.currentRegion.equals("Natman64_NOT_A_REGION")) {			
			Iterator<Entry<String, Rectangle>> it = 
					spriteSheet.regions.entrySet().iterator();
			
			while (it.hasNext()) {
				Entry<String, Rectangle> entry = it.next();
				
				String key = entry.getKey();
				if (!spriteSheet.currentRegion.isEmpty()) {
					if (!key.equals(spriteSheet.currentRegion)) {
						continue;
					}
				}
				
				Rectangle rect = entry.getValue();
				g.setColor(outlineColor);
				
				int left = (int) (rect.x * scale);
				int top = (int) (rect.y * scale);
				int right = (int) (left + rect.width * scale);
				int bottom = (int) (top + rect.height * scale);
				
				g.drawRect(left, top, right - left, bottom - top);
				
				g.setColor(regionColor);
				g.fillRect(left, top, right - left, bottom - top);
			}
		}
	}

	//region Mouse Events
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (addRegionPanel == null) return;
		
		int x = (int) (e.getX() / scale);
		int y = (int) (e.getY() / scale);
		
		if (x >= 0 && x < image.getWidth(null)
				&& y >= 0 && y < image.getHeight(null)) {
			addRegionPanel.imageClicked(x, y);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	//endregion
	
}
