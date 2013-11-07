package com.natman.regions;

import java.awt.Component;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Panel containing radio buttons to select the mode of region editing.
 * @author Natman64
 * @created  Nov 7, 2013
 */
public class ModePanel extends JPanel {

	private static final long serialVersionUID = -867070054405605539L;
	
	private ButtonGroup modeButtonGroup;
	
	private JRadioButton noneButton;
	private JRadioButton editButton;
	private JRadioButton addButton;
	
	public ModePanel() {
		modeButtonGroup = new ButtonGroup();
		
		noneButton = new JRadioButton("None");
		editButton = new JRadioButton("Edit");
		addButton = new JRadioButton("Add");
		
		noneButton.setSelected(true);
		
		modeButtonGroup.add(noneButton);
		modeButtonGroup.add(editButton);
		modeButtonGroup.add(addButton);
		
		add(noneButton);
		add(editButton);
		add(addButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		for (Component c : getComponents()) {
			c.setEnabled(enabled);
		}
	}
	
}
