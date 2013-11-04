package com.natman.regions;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * A window.
 * @author Natman64
 * @created  Nov 3, 2013
 */
public class Window extends JFrame {

	private static final long serialVersionUID = 2490296657786185869L;
	
	private JLabel emptyLabel;
	
	/**
	 * Constructor.
	 * @param title
	 */
	public Window(String title) {
		super(title);
		
		emptyLabel = new JLabel("");
	}
	
	@Override
	public void setSize(int width, int height) {
		emptyLabel.setPreferredSize(new Dimension(width, height));
        getContentPane().add(emptyLabel, BorderLayout.CENTER);
        pack();
	}
	
}
