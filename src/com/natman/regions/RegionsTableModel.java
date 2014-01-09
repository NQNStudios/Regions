package com.natman.regions;

import javax.swing.table.DefaultTableModel;

/**
 * TableModel subclass for the texture regions table.
 * @author Natman64
 * @created  Nov 5, 2013
 */
public class RegionsTableModel extends DefaultTableModel {

	private static final long serialVersionUID = -2435920231046304541L;
	
	private SpriteSheet spriteSheet;
	
	private AppWindow window;
	
	public RegionsTableModel(AppWindow window) {
		super(new String[] { "Key", "Region" }, 0);
		
		this.window = window;
	}
	
	public void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	
	@Override
	public void setValueAt(Object value, int row, int column) {
		if (getValueAt(row, column) == null) {
			//New data
			if (column == 1) {
				String key = (String) getValueAt(row, column - 1);
				String region = (String) value;
				
				Rectangle rect = Rectangle.parseRectangle(region);
				
				spriteSheet.regions.put(key, rect);
			}
		} else {
			//Data changed
			if (column == 0) {
				//Key changed
				String oldKey = (String) getValueAt(row, column);
				String newKey = (String) value;
				
				if (newKey.isEmpty()) return;
				
				Rectangle region = spriteSheet.regions.get(oldKey);
				spriteSheet.regions.remove(oldKey);
				spriteSheet.regions.put(newKey, region);
			} else {
				//Region changed
				String key = (String) getValueAt(row, column - 1);
				String region = (String) value;
				
				try {
					Rectangle rect = Rectangle.parseRectangle(region);
					
					spriteSheet.regions.remove(key);
					spriteSheet.regions.put(key, rect);
				} catch (Exception e) {
					return;
				}
			}
		}
		
		super.setValueAt(value, row, column);
		
		window.repaintImage();
	}
	
}
