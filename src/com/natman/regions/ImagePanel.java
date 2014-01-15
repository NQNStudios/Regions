package com.natman.regions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 * JPanel subclass that draws the sprite sheet texture and visual region info.
 * @author Natman64
 * @created Nov 3, 2013
 */
public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = -4866023796840902035L;
	
	private float scale = 1f;
	private Color backgroundColor = Color.gray;
	
	private Color regionColor = new Color(0f, 0f, 1f, 0.3f);
	private Color outlineColor = Color.white;
	
	private Image image;
	private SpriteSheet spriteSheet;
	
	private AddRegionPanel addRegionPanel;
	private JTable regionsTable;
	
	public ImagePanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void setScale(float scale) {
		this.scale = scale;
		
		if (image != null) {
			setPreferredSize(imageSize());
		}
		
		repaint();
	}
	
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		
		repaint();
	}
	
	public void setImage(Image image) {
		this.image = image;
		
		setPreferredSize(imageSize());
	}
	
	public void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	
	public void setAddRegionPanel(AddRegionPanel addRegionPanel) {
		this.addRegionPanel = addRegionPanel;
	}
	
	public void setRegionsTable(JTable table) {
		regionsTable = table;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (image != null) {
			Dimension size = imageSize();
			
			g.drawImage(image, 
					0, 0, 
					size.width,
					size.height,
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

	//region Helpers
	
	private Dimension imageSize() {
		ImageIcon icon = new ImageIcon(image);
		return new Dimension(
				(int) (icon.getIconWidth() * scale), 
				(int) (icon.getIconHeight() * scale));
	}
	
	//endregion
	
	//region Mouse Events
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (addRegionPanel != null) {
			int x = Math.round(e.getX() / scale);
			int y = Math.round(e.getY() / scale);
			
			if (x >= 0 && x < image.getWidth(null)
					&& y >= 0 && y < image.getHeight(null)) {
				addRegionPanel.imageClicked(x, y);
			}
		} else if (!getToolTipText().isEmpty()) {
			String key = getToolTipText();
			
			for (int i = 0; i < regionsTable.getRowCount(); i++) {
				if (regionsTable.getValueAt(i, 0).equals(key)) {
					regionsTable.setRowSelectionInterval(i, i);
				}
			}
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

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (spriteSheet == null) return;
		if (addRegionPanel != null) return;
		
		int x = Math.round(e.getX() / scale);
		int y = Math.round(e.getY() / scale);
		
		Iterator<Entry<String, Rectangle>> it = spriteSheet.regions.entrySet().iterator();
		
		boolean regionSelected = false;
		
		while (it.hasNext()) {
			Entry<String, Rectangle> entry = it.next();
			
			String key = entry.getKey();
			Rectangle region = entry.getValue();
			
			if (region.contains(x, y)) {				
				setToolTipText(key);
				
				repaint();
				
				regionSelected = true;
				break;
			}
		}
		
		if (!regionSelected) {
			setToolTipText("");
		}
	}
	
	//endregion
	
}
