package com.natman.regions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

/**
 * Popup window for setting the sprite sheet directory.
 * @author Natman64
 * @created  Nov 3, 2013
 */
public class DirectoryOptionsWindow extends Window implements ActionListener {

	private static final long serialVersionUID = -1084075550941948811L;
	
	private JTextField directoryField;
	private JButton browseButton;
	
	private Preferences prefs;
	
	public DirectoryOptionsWindow() {
		super("Sprite Sheet Directory");
		
		setSize(525, 30);
		
		directoryField = new JTextField(40);
		add(directoryField, BorderLayout.LINE_START);
		
		browseButton = new JButton("Choose");
		browseButton.setActionCommand("browse");
		browseButton.addActionListener(this);
		add(browseButton, BorderLayout.LINE_END);
		
		prefs = Preferences.userRoot().node("Natman64_RegionsPrefs");
		
		directoryField.setText(prefs.get("sheetDirectory", ""));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("browse")) {
			JFileChooser fileBrowser = new JFileChooser();
			fileBrowser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			int result = fileBrowser.showDialog(this, "Select Directory");
			
			if (result == JFileChooser.APPROVE_OPTION) {
				String directoryPath = fileBrowser.getSelectedFile().getAbsolutePath();
				directoryField.setText(directoryPath);
				
				prefs.put("sheetDirectory", directoryPath);
			}
		}
	}

}
