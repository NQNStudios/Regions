package com.natman.regions;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * Window allowing the user to name a new rectangle.
 * @author Natman64
 * @created  Nov 8, 2013
 */
public class NameRegionWindow extends JFrame {

	private static final long serialVersionUID = 7941529434795650708L;
	
	private JTextField nameField;
	private JButton enterButton;
	
	public String getName() {
		return nameField.getText();
	}
	
	public NameRegionWindow(AddRegionPanel panel) {		
		setTitle("Name Region");
		
		nameField = new JTextField("");
		nameField.setColumns(25);
		
		enterButton = new JButton("Enter");
		enterButton.setActionCommand("nameRegion");
		enterButton.addActionListener(panel);
		
		add(nameField, BorderLayout.CENTER);
		add(enterButton, BorderLayout.EAST);
		getRootPane().setDefaultButton(enterButton);
		
		pack();
	}
	
}
