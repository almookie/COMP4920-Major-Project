package graphicalUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class managementScreen extends JPanel  {

	private static final long serialVersionUID = 1L;
	
	public JPanel searchPanel;
	public JPanel contentPanel;
	
	//action listener
	managementScreenActionListener actionListener = new managementScreenActionListener(this);
	
	//current JPanel configuration for filters
	private JPanel currentSearchBar;
	private JPanel currentSearchResults;
	
	//panels for the different filter modes
	private JPanel studentResults;
	private JPanel subjectResults;
	private JPanel studentSearch;
	private JPanel subjectSearch;
	private JPanel classSearch;
	
	//variable to keep track of current filter mode
	private JPanel currentResults;
	private JPanel currentSearch;
	
	//sizes for UI elements
	private static Dimension searchPanelMinimumSize = new Dimension(150, 0);
	private static Dimension contentPanelMinimumSize = new Dimension(200, 0);
	//maximum size for input fields
	private static int MAXSEARCHSIZE = 20;
	
	public managementScreen() {
		setUpPanel();
	}
	
	
	/*	switch the current filter panels to
	 * 	subject panels
	 * 
	 */
	public void switchToSubject() {
		currentResults.setVisible(false);
		currentSearch.setVisible(false);
		currentResults = subjectResults;
		currentSearch = subjectSearch;
		subjectResults.setVisible(true);
		subjectSearch.setVisible(true);
	}
	
	
	/*	switch the current filter panels to
	 * 	student panels
	 * 
	 */
	public void switchToStudent() {
		currentResults.setVisible(false);
		currentSearch.setVisible(false);
		currentResults = studentResults;
		currentSearch = studentSearch;
		studentResults.setVisible(true);
		studentSearch.setVisible(true);
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
		studentButton.setActionCommand("setFilterStudent");
		
		//button to set filter to subject
		JButton subjectButton = new JButton("Subject");
		subjectButton.setActionCommand("setFilterSubject");
		
		//button to set filter to class
		JButton classButton = new JButton("Add to Class ->");
		
		//listen to actions from the buttons
		studentButton.addActionListener(actionListener);
		subjectButton.addActionListener(actionListener);
		
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
	 * 	and 3 search bars, one for each button in filterPanel
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
		currentSearch = studentSearch;
		searchBarLocation.add(studentSearch, c);
		
		//create subject search bar
		subjectSearch = new JPanel(new GridBagLayout());
		JTextField searchBarSubject = new JTextField
				("search subjects", MAXSEARCHSIZE);
		subjectSearch.add(searchBarSubject, c);
		subjectSearch.setVisible(false);
		searchBarLocation.add(subjectSearch, c);
		
		//create class search bar
		classSearch = new JPanel(new GridBagLayout());
		JTextField searchBarClass = new JTextField
				("search classes", MAXSEARCHSIZE);
		classSearch.add(searchBarClass, c);
		classSearch.setVisible(false);
		searchBarLocation.add(classSearch, c);
		
		//display settings for the search bars
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.04;
		c.weightx = 1;
		filterDisplayPanel.add(searchBarLocation, c);
		
		//create search results scroll panels
		JScrollPane studentResultsScroll = setupFilterResultsPanel(getInitialDataStudent());
		JScrollPane subjectResultsScroll = setupFilterResultsPanel(getInitialDataSubject());

		//create the search result panel to store the scroll panel
		studentResults = new JPanel(new GridBagLayout());
		subjectResults = new JPanel(new GridBagLayout());
		c.weighty = 0.96;
		c.weightx = 1;
		c.gridheight = 2;
		studentResults.add(studentResultsScroll, c);
		c.weighty = 0.96;
		c.weightx = 1;
		c.gridheight = 2;
		subjectResults.add(subjectResultsScroll, c);
		
		currentResults = studentResults;
		subjectResults.setVisible(false);
		c.gridheight = 3;
		c.weighty = 1;
		c.weightx = 1;
		searchResultsLocation.add(studentResults, c);
		searchResultsLocation.add(subjectResults, c);
		
		//display settings for search location
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.weighty = 0.86;
		c.weightx = 1;
		filterDisplayPanel.add(searchResultsLocation, c);		
		
		JButton newSubject = new JButton("Add New Subject");
		JButton newStudent  = new JButton("Add New Student");
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.weighty = 0.04;
		c.weightx = 1;
		subjectResults.add(newSubject, c);
		studentResults.add(newStudent, c);
		
		//display settings for add new button
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.weighty = 0.10; 
		c.weightx = 1;
		
		return filterDisplayPanel;
	}
	
	
	/*	panel for the display of student and subject filter results
	 * 	!debug
	 * 		may want to set a max size, and add more when end reached
	 * 
	 */
	private JScrollPane setupFilterResultsPanel(Object[][] initialData) {
		//create panel to store all results
		JPanel resultsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Create JTable to store results
		JTable resultsTable = new JTable(new filterDisplayTableModel(initialData));
		
		//don't show the header
		resultsTable.setTableHeader(null);
		
		//enable scrolling
		JScrollPane scrollPane = new JScrollPane(resultsTable);
		
		return scrollPane;
	}
	
	
	/*	read from back end to obtain Student initial data to display in
	 * 
	 */
	private Object[][] getInitialDataStudent() {
		Object[][] data = {
							{"Jackie", "102"},
							{"Leon", "103"},
							{"James", "104"},
							{"Ali", "105"}
		};
		
		//TODO:
		//	written below; provides the contents of data
		//data = getStudents(int grade, int year);
		
		return data;
	}
	
	
	//TODO:
	//provided a grade and year I would want to get a list of all the
	//students inside and a way to reference back to them
	//ie in {"Ali", "105"}
	//	"Ali" would be the name to be displayed
	//	"105" would be the ID/key provided to that student
	
	//public Object[][] getStudents(int grade, int year);
	
	
	/*	read from back end to obtain Student initial data to display in
	 * 
	 */
	private Object[][] getInitialDataSubject() {
		Object[][] data = {
							{"Maths"},
							{"English"},
							{"Science"},
		};
		
		return data;
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