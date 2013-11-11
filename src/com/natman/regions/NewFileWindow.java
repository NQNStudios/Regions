package com.natman.regions;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Popup window for creating a new sprite sheet.
 * @author Natman64
 * @created  Nov 3, 2013
 */
public class NewFileWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 3687920556816789705L;
	
	private static final int WINDOW_WIDTH = 750;
	private static final int WINDOW_HEIGHT = 475;
	
	private SpriteSheet spriteSheet;
	
	private String sheetDirectory;
	
	private JLabel prefixLabel;
	private JTextField nameField;
	private JLabel suffixLabel;
	
	private JPanel namePanel;
	
	private JLabel filePrefix;
	private JTextField fileField;
	private JButton fileBrowseButton;
	private JPanel textureInfoPanel;
	
	private JFileChooser textureChooser;
	
	private JButton createButton;
	
	private AppWindow window;
	
	public NewFileWindow(AppWindow window) {
		super("New Sprite Sheet");
		
		this.window = window;
		
		setLayout(new GridBagLayout());
		
		Preferences prefs = Preferences.userRoot().node("Natman64_RegionsPrefs");
		sheetDirectory = prefs.get("sheetDirectory", "");
		
		prefixLabel = new JLabel("File name: ");
		nameField = new JTextField("spritesheet");
		nameField.setColumns(10);
		suffixLabel = new JLabel(".xml");
		namePanel = new JPanel();
		namePanel.add(prefixLabel);
		namePanel.add(nameField);
		namePanel.add(suffixLabel);
		
		filePrefix = new JLabel("Texture file: ");
		
		fileField = new JTextField();
		fileField.setColumns(20);
		
		fileBrowseButton = new JButton("Browse");
		fileBrowseButton.setActionCommand("browseTexture");
		fileBrowseButton.addActionListener(this);
		
		textureInfoPanel = new JPanel();
		textureInfoPanel.add(filePrefix);
		textureInfoPanel.add(fileField);
		textureInfoPanel.add(fileBrowseButton);
		
		textureChooser = new JFileChooser(sheetDirectory);
		textureChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		createButton = new JButton("Create");
		createButton.setActionCommand("createSpritesheet");
		createButton.addActionListener(this);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0; c.gridy = 0;
		add(namePanel);
		
		c.gridx = 0; c.gridy = 1;
		add(textureInfoPanel, c);
		
		c.gridx = 0; c.gridy = 2;
		add(createButton, c);
		
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("browseTexture")) {
			int result = textureChooser.showDialog(this, "Select");
			
			if (result == JFileChooser.APPROVE_OPTION) {
				String path = textureChooser.getSelectedFile().getAbsolutePath();
				path = path.replace(sheetDirectory + "\\", "");
				
				fileField.setText(path);
			}
		} else if (e.getActionCommand().equals("createSpritesheet")) {
			File file = new File(sheetDirectory + "\\" + nameField.getText() + ".xml");
			spriteSheet = new SpriteSheet(fileField.getText());
			spriteSheet.saveToFile(file);
			window.openFile(file);
			setVisible(false);
		}
	}
	
}
