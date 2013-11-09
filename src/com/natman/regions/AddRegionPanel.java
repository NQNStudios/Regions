package com.natman.regions;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Panel containing components for visually adding a texture region.
 * @author Natman64
 * @created  Nov 8, 2013
 */
public class AddRegionPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -4859377007616650161L;
	
	private AppWindow window;
	private SpriteSheet spriteSheet;
	private ImagePanel imagePanel;
	
	private JButton button;
	private JTextField firstCorner;
	private JTextField secondCorner;
	
	private NameRegionWindow nameRegionWindow;
	
	public AddRegionPanel(AppWindow window, ImagePanel imagePanel) {
		setLayout(new GridBagLayout());
		
		this.window = window;
		this.imagePanel = imagePanel;
		
		button = new JButton("Add Region");
		button.setActionCommand("addRegion");
		button.addActionListener(this);
		
		firstCorner = new JTextField("First Corner");
		firstCorner.setEditable(false);
		
		secondCorner = new JTextField("Second Corner");
		secondCorner.setEditable(false);
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		
		c.gridx = 0;
		c.gridy = 0;
		add(button, c);
		
		c.gridx = 1;
		c.gridy = 0;
		add(firstCorner, c);
		
		c.gridx = 2;
		c.gridy = 0;
		add(secondCorner, c);
	}

	public void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("addRegion")) {
			button.setText("Cancel");
			button.setActionCommand("cancel");
			
			spriteSheet.currentRegion = "Natman64_NOT_A_REGION";
			imagePanel.setAddRegionPanel(this);
		} else if (e.getActionCommand().equals("cancel")) {
			button.setText("Add Region");
			button.setActionCommand("addRegion");
			
			firstCorner.setText("First Corner");
			secondCorner.setText("Second Corner");
			
			spriteSheet.currentRegion = "";
			imagePanel.setAddRegionPanel(null);
		} else if (e.getActionCommand().equals("nameRegion")) {
			Rectangle newRegion = spriteSheet.regions.get("Natman64_NewRect");
			spriteSheet.regions.remove("Natman64_NewRect");
			spriteSheet.regions.put(nameRegionWindow.getName(), newRegion);
			
			nameRegionWindow.setVisible(false);
			window.getTableModel().addRow(
					new Object[] { nameRegionWindow.getName(), newRegion });
			
			JTable table = window.getRegionsTable();
			table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);
		}
	}
	
	public void imageClicked(int x, int y) {
		String corner = "" + x + ", " + y;
		
		if (firstCorner.getText().equals("First Corner")) {
			firstCorner.setText(corner);
		} else if (secondCorner.getText().equals("Second Corner")) {
			String first = firstCorner.getText();
			
			String[] firstCoords = first.split(", ");
			int x1 = Integer.parseInt(firstCoords[0]);
			int y1 = Integer.parseInt(firstCoords[1]);
			
			if (x < x1 || y < y1) {
				firstCorner.setText(corner);
				secondCorner.setText("" + x1 + ", " + y1);
			} else {
				secondCorner.setText(corner);
			}
			
			firstCoords = firstCorner.getText().split(", ");
			x1 = Integer.parseInt(firstCoords[0]);
			y1 = Integer.parseInt(firstCoords[1]);
			
			firstCoords = secondCorner.getText().split(", ");
			int x2 = Integer.parseInt(firstCoords[0]);
			int y2 = Integer.parseInt(firstCoords[1]);
			
			int width = x2 - x1;
			int height = y2 - y1;
			
			Rectangle rect = new Rectangle(x1, y1, width, height);
			
			String key = "Natman64_NewRect";
			
			spriteSheet.regions.put(key, rect);
			
			nameRegionWindow = new NameRegionWindow(this);
			nameRegionWindow.setVisible(true);
		}
	}
	
}
