package com.natman.regions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class TexturePathWindow extends Window implements ActionListener {

	private static final long serialVersionUID = -7779530467230160609L;
	
	private JTextField directoryField;
	private JButton browseButton;
	
	private Preferences prefs;
	
	private AppWindow window;
	private SpriteSheet spriteSheet;
	
	public TexturePathWindow(AppWindow window, SpriteSheet spriteSheet) {
		super("Edit Texture Path");
		
		setSize(525, 30);
		
		this.window = window;
		this.spriteSheet = spriteSheet;
		
		prefs = Preferences.userRoot().node("Natman64_RegionsPrefs");
		
		directoryField = new JTextField(40);
		add(directoryField, BorderLayout.LINE_START);
		
		browseButton = new JButton("Choose");
		browseButton.setActionCommand("browse");
		browseButton.addActionListener(this);
		add(browseButton, BorderLayout.LINE_END);
		
		directoryField.setText(spriteSheet.texturePath);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("browse")) {
			String directory = prefs.get("sheetDirectory", "");
			
			JFileChooser fileBrowser = new JFileChooser(directory + "\\");
			fileBrowser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			int result = fileBrowser.showDialog(this, "Select Texture");
			
			if (result == JFileChooser.APPROVE_OPTION) {
				String sheetPath = fileBrowser.getSelectedFile().getAbsolutePath();
				
				
				sheetPath = sheetPath.replace(directory + "\\", "");
				directoryField.setText(sheetPath);
				spriteSheet.texturePath = sheetPath;
				window.refreshImage();
			}
		}
	}

}
