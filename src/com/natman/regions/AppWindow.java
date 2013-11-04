package com.natman.regions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * JFrame performing all the main functions of Regions.
 * @author Natman64
 * @created  Nov 3, 2013
 */
public class AppWindow extends Window implements ActionListener {
	
	private static final long serialVersionUID = -5202563554367760480L;
	
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;
	
	private JMenuBar menuBar;
	
	private JMenu fileMenu;
	private JMenuItem newFileButton;
	private JMenuItem openFileButton;
	private JMenuItem saveFileButton;
	
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
        fileMenu.setMnemonic(KeyEvent.VK_F);
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
			
		}
	}
	
}
