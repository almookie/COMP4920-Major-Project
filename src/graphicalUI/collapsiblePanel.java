package graphicalUI;
import java.awt.*;

import javax.swing.*;

public class collapsiblePanel {
	public JPanel parentPanel;
	public GridBagConstraints mainConstraint;
	
	
	public collapsiblePanel(JPanel parent, GridBagConstraints c) {
		parentPanel = parent;
		mainConstraint = c;
	}
	
	
	/*	create the collapsible panel with current parameters
	 * 
	 */
	public void create() {
		setUpMainPanel();
	}
	
	
	/*	Create and set up the main panel
	 * 
	 */
	private void setUpMainPanel() {
		//set up a GridBagLayout panel container
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		setUpGraphics(mainPanel);
		
		setUpCollapseButton(mainPanel, c);
		
		//add the collapsible panel to the UI
		parentPanel.add(mainPanel, mainConstraint);
	}
	
	
	/*	Create the button which collapses the panel
	 * 
	 */
	private void setUpCollapseButton(JPanel mainPanel, GridBagConstraints c) {
		//set button text
		JButton collapseButton = new JButton("Collapse here");
		
		//setup button constraints
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		
		//create the button
		mainPanel.add(collapseButton, c);
	}
	
	
	/*	setup all graphical parameters
	 * 
	 */
	private void setUpGraphics(JPanel mainPanel) {
		mainPanel.setBackground(Color.CYAN);
	}
	
}
