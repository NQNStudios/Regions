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
	
	private JLabel resizeLabel;
	private BasicArrowButton decWidth;
	private BasicArrowButton incWidth;
	private BasicArrowButton decHeight;
	private BasicArrowButton incHeight;
	
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
		
		resizeLabel = new JLabel("Resize");
		
		decWidth = new BasicArrowButton(BasicArrowButton.WEST);
		decWidth.setActionCommand("decWidth");
		
		incWidth = new BasicArrowButton(BasicArrowButton.EAST);
		incWidth.setActionCommand("incWidth");
		
		decHeight = new BasicArrowButton(BasicArrowButton.SOUTH);
		decHeight.setActionCommand("decHeight");
		
		incHeight = new BasicArrowButton(BasicArrowButton.NORTH);
		incHeight.setActionCommand("incHeight");
		
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
		add(resizeLabel, c);
		
		c.gridx = 3;
		c.gridy = 1;
		add(decWidth, c);
		
		c.gridx = 5;
		c.gridy = 1;
		add(incWidth, c);
		
		c.gridx = 4;
		c.gridy = 0;
		add(incHeight, c);
		
		c.gridx = 4;
		c.gridy = 2;
		add(decHeight, c);
		
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
		
		case "decWidth":
			region.width--;
			break;
			
		case "incWidth":
			region.width++;
			break;
			
		case "decHeight":
			region.height--;
			break;
			
		case "incHeight":
			region.height++;
			break;
			
		}
		
		window.repaintImage();
	}
	
}
