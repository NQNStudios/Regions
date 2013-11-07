package com.natman.regions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * JFrame performing all the main functions of Regions.
 * @author Natman64
 * @created  Nov 3, 2013
 */
public class AppWindow extends JFrame implements ActionListener, 
												ChangeListener, 
												ListSelectionListener {
	
	//region Config
	
	private static final long serialVersionUID = -5202563554367760480L;
	
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;
	
	//endregion
	
	//region Fields
	
	private JMenuBar menuBar;
	
	private JMenu fileMenu;
	private JMenuItem newFileButton;
	private JMenuItem openFileButton;
	private JMenuItem saveFileButton;
	
	private JMenu editMenu;
	private JMenuItem texturePathButton;
	
	private JMenu optionsMenu;
	private JMenuItem directoryOptionsButton;
	private JMenuItem backgroundColorOptionsButton;
	
	private ImagePanel textureCanvas;
	private ModePanel modePanel;
	private JScrollPane textureScrollPanel;
	private JSlider zoomSlider;
	
	private JScrollPane regionsPane;
	private JTable regionsTable;
	private JButton deselectButton;
	
	private RegionsTableModel tableModel;
	
	private SpriteSheet spriteSheet;
	private File spriteSheetFile;
	
	//endregion
	
	//region Initialization
	
	/**
	 * Creates and sizes the application window.
	 */
	public AppWindow() {
		super("Regions");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
		
        createMenuBar();
        createTexturePanel();
        createRegionsTable();
        
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	private void createTexturePanel() {
		textureCanvas = new ImagePanel();
		
		Preferences prefs = Preferences.userRoot().node("Natman64_RegionsPrefs");
		Color bgColor = Color.decode(prefs.get("BackgroundColor", "" + Color.gray.getRGB()));
		textureCanvas.setBackgroundColor(bgColor);
		
		textureScrollPanel = new JScrollPane(textureCanvas);
		
		zoomSlider = new JSlider(JSlider.VERTICAL, 1, 8, 1);
		zoomSlider.addChangeListener(this);
		
		modePanel = new ModePanel();
		modePanel.setEnabled(false);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 0; 
		c.fill = GridBagConstraints.NONE; 
		add(zoomSlider, c);
		
		c.gridx = 1; c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 14;
		c.weighty = 4;
		add(textureScrollPanel, c);
		
		c.gridx = 1; c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		add(modePanel, c);
	}
	
	private void createRegionsTable() {
		//Create the regions table
        tableModel = new RegionsTableModel(this);

        regionsTable = new JTable(tableModel);
        regionsTable.setFillsViewportHeight(true);
        
        regionsTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        regionsTable.getSelectionModel().addListSelectionListener(this);
        
        regionsPane = new JScrollPane(regionsTable);
        regionsPane.setPreferredSize(new Dimension(300, 0));
        
        deselectButton = new JButton("Clear Selection");
        deselectButton.setActionCommand("clearSelection");
        deselectButton.addActionListener(this);
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2; c.gridy = 0; 
        c.fill = GridBagConstraints.BOTH; 
        c.weightx = 1;
        add(regionsPane, c);
        
        c.gridx = 2; c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        add(deselectButton, c);
	}

	private void createMenuBar() {
		//Create the menu bar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        //Create the file menu
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        
        newFileButton = new JMenuItem("New");
        newFileButton.setActionCommand("newFile");
        newFileButton.addActionListener(this);
        fileMenu.add(newFileButton);
        
        openFileButton = new JMenuItem("Open");
        openFileButton.setActionCommand("openFile");
        openFileButton.addActionListener(this);
        fileMenu.add(openFileButton);
        
        saveFileButton = new JMenuItem("Save");
        saveFileButton.setActionCommand("saveFile");
        saveFileButton.addActionListener(this);
        fileMenu.add(saveFileButton);
        
        //Create the edit menu
        editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        
        texturePathButton = new JMenuItem("Texture Path");
        texturePathButton.setActionCommand("editTexturePath");
        texturePathButton.addActionListener(this);
        texturePathButton.setEnabled(false);
        editMenu.add(texturePathButton);
        
        //Create the options menu
        optionsMenu = new JMenu("Options");
        menuBar.add(optionsMenu);
        
        directoryOptionsButton = new JMenuItem("Sprite Sheet Directory");
        directoryOptionsButton.setActionCommand("directoryOptions");
        directoryOptionsButton.addActionListener(this);
        optionsMenu.add(directoryOptionsButton);
        
        backgroundColorOptionsButton = new JMenuItem("Background Color");
        backgroundColorOptionsButton.setActionCommand("colorOptions");
        backgroundColorOptionsButton.addActionListener(this);
        optionsMenu.add(backgroundColorOptionsButton);
	}
	
	//endregion

	//region Accessors
	
	public ImagePanel getTextureCanvas() {
		return textureCanvas;
	}
	
	public void refreshImage() {
		try {
			Preferences prefs = Preferences.userRoot().node("Natman64_RegionsPrefs");
			String directory = prefs.get("sheetDirectory", "");
			
			BufferedImage image = null;
			image = ImageIO.read(new File(directory + "\\" + spriteSheet.texturePath));
			textureCanvas.setImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void repaintImage() {
		textureCanvas.repaint();
	}
	
	//endregion
	
	//region Events
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("newFile")) {
			NewFileWindow newFileWindow = new NewFileWindow();
			newFileWindow.setVisible(true);
		} else if (e.getActionCommand().equals("openFile")) {
			JFileChooser openFileDialog = new JFileChooser();
			openFileDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			Preferences prefs = Preferences.userRoot().node("Natman64_RegionsPrefs");
			openFileDialog.setCurrentDirectory(new File(prefs.get("sheetDirectory", "")));
			
			int result = openFileDialog.showDialog(this, "Open File");
			
			if (result == JFileChooser.APPROVE_OPTION) {
				openFile(openFileDialog.getSelectedFile());
			}
		} else if (e.getActionCommand().equals("saveFile")) {
			if (spriteSheet != null) {
				spriteSheet.saveToFile(spriteSheetFile);
			}
		} else if (e.getActionCommand().equals("editTexturePath")) {
			TexturePathWindow texturePathWindow = new TexturePathWindow(this, spriteSheet);
			texturePathWindow.setVisible(true);
		} else if (e.getActionCommand().equals("directoryOptions")) {
			DirectoryOptionsWindow directoryOptionsWindow = new DirectoryOptionsWindow();
			directoryOptionsWindow.setVisible(true);
		} else if (e.getActionCommand().equals("colorOptions")) {
			ColorOptionsWindow colorOptionsWindow = new ColorOptionsWindow(this);
			colorOptionsWindow.setVisible(true);
		} else if (e.getActionCommand().equals("clearSelection")) {
			regionsTable.clearSelection();
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (zoomSlider.getValueIsAdjusting()) {
			return;
		}
		
		float scale = zoomSlider.getValue();
		textureCanvas.setScale(scale);
		
		textureScrollPanel.setViewportView(textureCanvas);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int row = regionsTable.getSelectedRow();
		
		if (row == -1) {
			spriteSheet.currentRegion = "";
		} else {
			String key = (String) regionsTable.getValueAt(row, 0);
			
			spriteSheet.currentRegion = key;
		}
		
		repaintImage();
	}
	
	//endregion
	
	//region File Management
	
	private void openFile(File file) {
		tableModel.setRowCount(0);
		spriteSheet = null;
		
		try {
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document sheetXML = docBuilder.parse(file);
			
			Node sheetNode = sheetXML.getLastChild();
			NodeList regionList = sheetNode.getChildNodes();
			
			for (int i = 0; i < regionList.getLength(); i++) {
				Node node = regionList.item(i);
				
				if (node.getNodeName().equals("texture")) {
					
					Preferences prefs = Preferences.userRoot().node("Natman64_RegionsPrefs");
					String directory = prefs.get("sheetDirectory", "");
					String path = node.getTextContent();
					spriteSheet = new SpriteSheet(path);
					tableModel.setSpriteSheet(spriteSheet);
					
					BufferedImage image = null;
					image = ImageIO.read(new File(directory + "\\" + path));
					textureCanvas.setImage(image);
					textureCanvas.setSpriteSheet(spriteSheet);
					textureCanvas.repaint();
					
				} else if (node.getNodeName().equals("rect")) {
				
					String key = node.getAttributes().getNamedItem("key").getTextContent();
					String value = node.getTextContent();
					
					Rectangle region = Rectangle.parseRectangle(value);
					
					spriteSheet.addRegion(key, region);
					
					tableModel.addRow(
							new Object[] { key, region });
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		spriteSheetFile = file;
		
		texturePathButton.setEnabled(true);
		modePanel.setEnabled(true);
		regionsTable.repaint();
	}
	
	//endregion
	
}
