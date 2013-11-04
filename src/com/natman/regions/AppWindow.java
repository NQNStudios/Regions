package com.natman.regions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
public class AppWindow extends Window implements ActionListener {
	
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
	
	private JMenu optionsMenu;
	private JMenuItem directoryOptionsButton;
	
	private JScrollPane regionsPane;
	private JTable regionsTable;
	
	private DefaultTableModel tableModel;
	
	//endregion
	
	//region Initialization
	
	/**
	 * Creates and sizes the application window.
	 */
	public AppWindow() {
		super("Regions");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        createMenuBar();
        createRegionsTable();
	}

	private void createRegionsTable() {
		//Create the regions table
        tableModel = new DefaultTableModel(
        		new String[] { "Key", "Region" }, 
        		0);
        
        regionsTable = new JTable(tableModel);
        regionsTable.setFillsViewportHeight(true);
        
        regionsPane = new JScrollPane(regionsTable);
        getContentPane().add(regionsPane, BorderLayout.LINE_END);
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
        
        //Create the options menu
        optionsMenu = new JMenu("Options");
        menuBar.add(optionsMenu);
        
        directoryOptionsButton = new JMenuItem("Sprite Sheet Directory");
        directoryOptionsButton.setActionCommand("directoryOptions");
        directoryOptionsButton.addActionListener(this);
        optionsMenu.add(directoryOptionsButton);
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
			
		} else if (e.getActionCommand().equals("directoryOptions")) {
			DirectoryOptionsWindow directoryOptionsWindow = new DirectoryOptionsWindow();
			directoryOptionsWindow.setVisible(true);
		}
	}
	
	//endregion
	
	//region File Management
	
	private void openFile(File file) {
		tableModel.setRowCount(0);
		
		try {
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document sheetXML = docBuilder.parse(file);
			
			Node sheetNode = sheetXML.getLastChild();
			NodeList regionList = sheetNode.getChildNodes();
			
			for (int i = 0; i < regionList.getLength(); i++) {
				Node node = regionList.item(i);
				
				if (!node.getNodeName().equals("rect")) continue;
				
				String key = node.getAttributes().getNamedItem("key").getTextContent();
				String value = node.getTextContent();
				
				Rectangle region = Rectangle.parseRectangle(value);
				
				tableModel.addRow(
						new Object[] { key, region });
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		regionsTable.repaint();
	}
	
	//endregion
	
}
