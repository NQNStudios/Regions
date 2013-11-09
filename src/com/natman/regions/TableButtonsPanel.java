package com.natman.regions;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel with two buttons for handling the regions table.
 * @author Natman64
 * @created  Nov 8, 2013
 */
public class TableButtonsPanel extends JPanel {

	private static final long serialVersionUID = -5550049541533158734L;
	
	public JButton deselectButton;
	public JButton deleteButton;
	
	public TableButtonsPanel(JButton deselectButton, JButton deleteButton) {
		setLayout(new BorderLayout());
		
		this.deselectButton = deselectButton;
		this.deleteButton = deleteButton;
		
		add(deselectButton, BorderLayout.WEST);
		add(deleteButton, BorderLayout.EAST);
	}
	
}
