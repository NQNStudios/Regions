package com.natman.regions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * JFrame performing all the main functions of Regions.
 * @author Natman64
 * @created  Nov 3, 2013
 */
public class AppWindow extends Window implements ActionListener {
	
	private static final long serialVersionUID = -5202563554367760480L;
	
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;
	
	private static final int MAX_REGIONS = 300;
	
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
	
	/**
	 * Creates and sizes the application window.
	 */
	public AppWindow() {
		super("Regions");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
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
        
        //Create the regions table
        tableModel = new DefaultTableModel(
        		new String[] { "Key", "Region" }, 
        		MAX_REGIONS);
        
        regionsTable = new JTable(tableModel);
        regionsTable.setFillsViewportHeight(true);
        
        regionsPane = new JScrollPane(regionsTable);
        getContentPane().add(regionsPane, BorderLayout.LINE_END);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("newFile")) {
			NewFileWindow newFileWindow = new NewFileWindow();
			newFileWindow.setVisible(true);
		} else if (e.getActionCommand().equals("openFile")) {
			OpenFileWindow openFileWindow = new OpenFileWindow();
			openFileWindow.setVisible(true);
		} else if (e.getActionCommand().equals("saveFile")) {
			
		} else if (e.getActionCommand().equals("directoryOptions")) {
			DirectoryOptionsWindow directoryOptionsWindow = new DirectoryOptionsWindow();
			directoryOptionsWindow.setVisible(true);
		}
	}
	
}
