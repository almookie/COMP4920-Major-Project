package graphicalUI.statsSearchBar;

import graphicalUI.managementScreenSource.FilterPanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import main.Markbook;

public class StatsMainScreen extends JPanel {

	private static final long serialVersionUID = 1L;

	public StatsSearchPanel searchPanel;
	public JPanel contentPanel;
	
	private Markbook mB;
	
	//sizes for UI elements
	private static Dimension searchPanelMinimumSize = new Dimension(150, 0);
	private static Dimension contentPanelMinimumSize = new Dimension(200, 0);
	
	public StatsMainScreen(Markbook newmB) {
		mB = newmB;
		setupPanel();
	}
	
	
	private void setupPanel() {
		//initial setup of main panel components
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		setupSearchPanel();
		setupContentPanel();
		
		JSplitPane overall = new JSplitPane
				(JSplitPane.HORIZONTAL_SPLIT, searchPanel, contentPanel);
		overall.setResizeWeight(0.2);
		
		//add the split panel
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		c.gridwidth = 3;
		c.weighty = 1;
		c.weightx = 1;
		this.add(overall, c);	
	}
	
	private void setupSearchPanel() {
		searchPanel = new StatsSearchPanel(mB);
		
		
		searchPanel.setMinimumSize(searchPanelMinimumSize);
	}
	
	private void setupContentPanel() {
		
		//contentPanel.setMinimumSize(contentPanelMinimumSize);
	}
	
}
