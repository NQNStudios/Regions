package com.natman.regions;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorOptionsWindow extends Window implements ChangeListener {

	private static final long serialVersionUID = 55373949416234483L;
	
	private JLabel redLabel;
	private JLabel greenLabel;
	private JLabel blueLabel;
	
	private JSlider redSlider;
	private JSlider greenSlider;
	private JSlider blueSlider;
	
	private AppWindow window;
	
	public ColorOptionsWindow(AppWindow window) {
		super("Background Color");
		
		this.window = window;
		
		getContentPane().setLayout(new GridBagLayout());
		
		Preferences prefs = Preferences.userRoot().node("Natman64_RegionsPrefs");
		Color bgColor = Color.decode(prefs.get("BackgroundColor", "" + Color.gray.getRGB()));
		
		redLabel = new JLabel("Red: ");
		greenLabel = new JLabel("Green: ");
		blueLabel = new JLabel("Blue: ");
		
		redSlider = new JSlider(0, 255, bgColor.getRed());
		greenSlider = new JSlider(0, 255, bgColor.getGreen());
		blueSlider = new JSlider(0, 255, bgColor.getBlue());
		
		redSlider.addChangeListener(this);
		greenSlider.addChangeListener(this);
		blueSlider.addChangeListener(this);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0; c.gridy = 0; getContentPane().add(redLabel, c);
		c.gridx = 0; c.gridy = 1; getContentPane().add(greenLabel, c);
		c.gridx = 0; c.gridy = 2; getContentPane().add(blueLabel, c);
		
		c.gridx = 1; c.gridy = 0; getContentPane().add(redSlider, c);
		c.gridx = 1; c.gridy = 1; getContentPane().add(greenSlider, c);
		c.gridx = 1; c.gridy = 2; getContentPane().add(blueSlider, c);
		
		pack();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (redSlider.getValueIsAdjusting()
				|| greenSlider.getValueIsAdjusting()
				|| blueSlider.getValueIsAdjusting()) {
			return;
		}
		
		int red = redSlider.getValue();
		int green = greenSlider.getValue();
		int blue = blueSlider.getValue();
		
		Color bgColor = new Color(red, green, blue);
		
		window.getTextureCanvas().setBackgroundColor(bgColor);
		
		Preferences prefs = Preferences.userRoot().node("Natman64_RegionsPrefs");
		prefs.put("BackgroundColor", "" + bgColor.getRGB());
		try {
			prefs.flush();
		} catch (BackingStoreException e1) {
			e1.printStackTrace();
		}
	}
	
}
