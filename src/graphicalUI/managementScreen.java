package graphicalUI;

import javax.swing.*;

import java.awt.*;

public class managementScreen extends JPanel  {

	public JPanel searchPanel;
	public JPanel contentPanel;
	private static final long serialVersionUID = 1L;
	
	
	public managementScreen() {
		setUpPanel();
	}
	
	
	/*	Set up the initial panel
	 * 
	 */
	private void setUpPanel() {
		//initial setup of main panel components
		this.setLayout(new BorderLayout());
		setupSearchPanel();
		
		this.add(searchPanel, BorderLayout.LINE_START);
	}
	
	
	/*	set up the left hand side search panel
	 * 
	 */
	private void setupSearchPanel() {
		searchPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		searchPanel.setMinimumSize(new Dimension(150,400));
		
		//!here to debug container boundary
		searchPanel.setBackground(Color.CYAN);
		
		//setup button constraints
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 2;
		searchPanel.add(new JLabel("Option1?"), c);
	}
	
	
	/*	set up the right hand side display panel
	 * 
	 */
	private void setupViewPanel() {
		contentPanel = new JPanel();
		
	}
}
