package com.natman.regions;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Regions");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(1000, 600));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}

}
