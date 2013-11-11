package com.natman.regions;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

/**
 * Panel for editing regions.
 * @author Natman64
 * @created  Nov 8, 2013
 */
public class EditRegionPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -5648884121357373389L;
	
	private JLabel moveLabel;
	private BasicArrowButton moveLeft;
	private BasicArrowButton moveRight;
	private BasicArrowButton moveUp;
	private BasicArrowButton moveDown;
	
	private JLabel expandLabel;
	private BasicArrowButton expandLeft;
	private BasicArrowButton expandRight;
	private BasicArrowButton expandDown;
	private BasicArrowButton expandUp;
	
	private JLabel shrinkLabel;
	private BasicArrowButton shrinkLeft;
	private BasicArrowButton shrinkRight;
	private BasicArrowButton shrinkDown;
	private BasicArrowButton shrinkUp;
	
	private AppWindow window;
	private SpriteSheet spriteSheet;
	
	public EditRegionPanel(AppWindow window) {
		setLayout(new GridBagLayout());
		
		this.window = window;
		
		moveLabel = new JLabel("Move");
		
		moveLeft = new BasicArrowButton(BasicArrowButton.WEST);
		moveLeft.setActionCommand("moveLeft");
		
		moveRight = new BasicArrowButton(BasicArrowButton.EAST);
		moveRight.setActionCommand("moveRight");
		
		moveUp = new BasicArrowButton(BasicArrowButton.NORTH);
		moveUp.setActionCommand("moveUp");
		
		moveDown = new BasicArrowButton(BasicArrowButton.SOUTH);
		moveDown.setActionCommand("moveDown");
		
		expandLabel = new JLabel("Expand");
		
		expandLeft = new BasicArrowButton(BasicArrowButton.WEST);
		expandLeft.setActionCommand("expandLeft");
		
		expandRight = new BasicArrowButton(BasicArrowButton.EAST);
		expandRight.setActionCommand("expandRight");
		
		expandDown = new BasicArrowButton(BasicArrowButton.SOUTH);
		expandDown.setActionCommand("expandDown");
		
		expandUp = new BasicArrowButton(BasicArrowButton.NORTH);
		expandUp.setActionCommand("expandUp");
		
		shrinkLabel = new JLabel("Shrink");
		
		shrinkLeft = new BasicArrowButton(BasicArrowButton.RIGHT);
		shrinkLeft.setActionCommand("shrinkLeft");
		
		shrinkRight = new BasicArrowButton(BasicArrowButton.LEFT);
		shrinkRight.setActionCommand("shrinkRight");
		
		shrinkDown = new BasicArrowButton(BasicArrowButton.NORTH);
		shrinkDown.setActionCommand("shrinkDown");
		
		shrinkUp = new BasicArrowButton(BasicArrowButton.SOUTH);
		shrinkUp.setActionCommand("shrinkUp");
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		
		c.gridx = 1;
		c.gridy = 1;
		add(moveLabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(moveLeft, c);
		
		c.gridx = 2;
		c.gridy = 1;
		add(moveRight, c);
		
		c.gridx = 1;
		c.gridy = 0;
		add(moveUp, c);
		
		c.gridx = 1;
		c.gridy = 2;
		add(moveDown, c);
		
		c.gridx = 4;
		c.gridy = 1;
		add(expandLabel, c);
		
		c.gridx = 3;
		c.gridy = 1;
		add(expandLeft, c);
		
		c.gridx = 5;
		c.gridy = 1;
		add(expandRight, c);
		
		c.gridx = 4;
		c.gridy = 0;
		add(expandUp, c);
		
		c.gridx = 4;
		c.gridy = 2;
		add(expandDown, c);
		
		c.gridx = 7;
		c.gridy = 1;
		add(shrinkLabel, c);
		
		c.gridx = 6;
		c.gridy = 1;
		add(shrinkLeft, c);
		
		c.gridx = 8;
		c.gridy = 1;
		add(shrinkRight, c);
		
		c.gridx = 7;
		c.gridy = 2;
		add(shrinkDown, c);
		
		c.gridx = 7;
		c.gridy = 0;
		add(shrinkUp, c);
		
		for (Component button : getComponents()) {
			if (button instanceof BasicArrowButton) {
				((BasicArrowButton) button).addActionListener(this);
			}
		}
	}

	public void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (spriteSheet == null) return;
		
		String currentRegion = spriteSheet.currentRegion;
		
		if (currentRegion.isEmpty()) return;
		Rectangle region = spriteSheet.regions.get(currentRegion);
		
		switch (e.getActionCommand()) {
		
		case "moveLeft":
			region.x--;
			break;
			
		case "moveRight":
			region.x++;
			break;
			
		case "moveUp":
			region.y--;
			break;
			
		case "moveDown":
			region.y++;
			break;
		
		case "expandLeft":
			region.x--;
			region.width++;
			break;
			
		case "expandRight":
			region.width++;
			break;
			
		case "expandDown":
			region.height++;
			break;
			
		case "expandUp":
			region.y--;
			region.height++;
			break;
			
		case "shrinkLeft":
			region.width--;
			region.x++;
			break;
			
		case "shrinkRight":
			region.width--;
			break;
			
		case "shrinkDown":
			region.height--;
			break;
			
		case "shrinkUp":
			region.y++;
			region.height--;
			break;
			
		}
		
		window.repaint();
	}
	
}
