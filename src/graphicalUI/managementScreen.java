package graphicalUI;

import javax.swing.*;

import java.awt.*;

public class managementScreen extends JPanel  {

	private static final long serialVersionUID = 1L;
	
	public JPanel searchPanel;
	public JPanel contentPanel;
	
	//current JPanel configuration for filters
	private JPanel currentSearchBar;
	private JPanel currentSearchResults;
	
	//panels for the different filter modes
	private JPanel studentResults;
	private JPanel subjectResults;
	private JPanel studentSearch;
	private JPanel subjectSearch;
	private JPanel classSearch;
	
	
	//sizes for UI elements
	private static Dimension searchPanelMinimumSize = new Dimension(150, 0);
	private static Dimension contentPanelMinimumSize = new Dimension(200, 0);
	
	//maximum size for input fields
	private static int MAXSEARCHSIZE = 20;
	
	public managementScreen() {
		setUpPanel();
	}
	
	
	/*	Set up the initial panel
	 * 
	 */
	private void setUpPanel() {
		//initial setup of main panel components
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		setupSearchPanel();
		setupContentPanel();
		
		//use a split panel to store search and content panels
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
	
	
	/*	set up the left hand side search panel
	 * 
	 */
	private void setupSearchPanel() {
		searchPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		searchPanel.setMinimumSize(searchPanelMinimumSize);
		
		//!here to debug container boundary
		searchPanel.setBackground(Color.CYAN);
		
		//!debug; setup results constraints
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 2;
		c.gridwidth = 3;
		c.weighty = 0.98;
		c.weightx = 1;
		//create the filters; search bar and results panels
		JPanel filterDisplayPanel = setupFilterDisplayPanel();
		searchPanel.add(filterDisplayPanel, c);
		
		//!debug; setup button constraints
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.02;
		c.weightx = 1;
		//create the buttons for filters on the top left
		JPanel filterPanel = setupFilterPanel();
		searchPanel.add(filterPanel,c);
		
		
	}
	
	
	/*	set up the filter panel in the top of searchPanel
	 * 	!debug this is a dummy panel
	 */
	private JPanel setupFilterPanel() {
		JPanel filterPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//button to set filter to students
		JButton studentButton = new JButton("Student");
		//button to set filter to subject
		JButton subjectButton = new JButton("Subject");
		//button to set filter to class
		JButton classButton = new JButton("Class");
		
		//setup locations and size ratios for buttons
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.weighty = 0.5;
		c.weightx = 0.80;
		filterPanel.add(studentButton, c);
		c.gridx = 0;
		c.gridy = 1;
		filterPanel.add(subjectButton, c);
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 0.20;
		filterPanel.add(classButton, c);
	
		return filterPanel;
	}
	
	
	/*	setup filter display panel on bottom half of search panel
	 * 	Stores and switches between 2 panels for Student and Subject search
	 * 	and 3 search bars, one for each button
	 * 	!debug does not contain filter yet
	 */
	private JPanel setupFilterDisplayPanel() {
		JPanel filterDisplayPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//create panel containers for search bar and search results location
		JPanel searchBarLocation = new JPanel(new GridBagLayout());
		JPanel searchResultsLocation = new JPanel(new GridBagLayout());
		
		//constraints to fill the whole panel
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		c.gridwidth = 3;
		c.weighty = 1;
		c.weightx = 1;
		
		//create student search bar
		studentSearch = new JPanel(new GridBagLayout());
		JTextField searchBarStudent = new JTextField
				("search students", MAXSEARCHSIZE);
		studentSearch.setBackground(Color.GRAY);
		studentSearch.add(searchBarStudent, c);
		studentSearch.setVisible(true);
		searchBarLocation.add(studentSearch, c);
		
		//create class search bar
		subjectSearch = new JPanel(new GridBagLayout());
		JTextField searchBarSubject = new JTextField
				("search subjects", MAXSEARCHSIZE);
		subjectSearch.add(searchBarSubject, c);
		subjectSearch.setVisible(false);
		//searchBarLocation.add(subjectSearch, c);
		
		classSearch = new JPanel(new GridBagLayout());
		JTextField searchBarClass = new JTextField
				("search classes", MAXSEARCHSIZE);
		classSearch.add(searchBarStudent, c);
		classSearch.setVisible(false);
		//searchBarLocation.add(classSearch, c);
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.05;
		c.weightx = 1;
		filterDisplayPanel.add(searchBarLocation, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 2;
		c.weighty = 0.95;
		c.weightx = 1;
		filterDisplayPanel.add(searchResultsLocation, c);
		
		return filterDisplayPanel;
	}
	
	
	/*	set up the right hand side content panel
	 * 
	 */
	private void setupContentPanel() {
		contentPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		contentPanel.setMinimumSize(contentPanelMinimumSize);
		
		//!debug; setup button constraints
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		
		//!debug; :use the search bar to set minimum size
		contentPanel.add(new JLabel("Content1?"), c);
		
		//!here to debug container boundary
		contentPanel.setBackground(Color.GREEN);
	}
	
	
}
