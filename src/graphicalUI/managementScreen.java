package graphicalUI;

import graphicalUI.managementScreenSource.ClassDisplay;
import graphicalUI.managementScreenSource.StudentFilterBar;
import graphicalUI.managementScreenSource.StudentFilterPanel;

import javax.swing.*;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import main.Grade;
import main.Markbook;
import main.Student;
import main.Subject;
import main.Subject_Class;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

//note change get initial data names
//add more info in students

public class managementScreen extends JPanel  {

	private static final long serialVersionUID = 1L;
	
	//two main panels that store everything
	public JPanel searchPanel;
	public JPanel contentPanel;
	
	//action listener
	private managementScreenActionListener actionListener;
	
	private Markbook mB;
	
	//current JPanel configuration for filters
	private JPanel currentSearchBar;
	private JPanel currentSearchResults;
	
	//panels for the different filter modes
	private JPanel studentResults;
	private JPanel subjectResults;
	private JPanel gradeResults;
	private JPanel studentSearch;
	private JPanel subjectSearch;
	private JPanel gradeSearch;

	
	//text fields for creation of new items
	//	new subject
	private JTextField inputSubjectName;
	private JTextField inputShortForm;
	//	new student
	private JTextField inputGivenName;
	private JTextField inputSurName;
	private JComboBox inputStudentGrade;
	//	new grade
	private JTextField inputGradeName;
	//	new class
	private JComboBox inputClassGrade;
	private JComboBox inputClassSubject;
	
	//tables which store search results
	private JTable subjectTable;
	private JTable studentTable;
	private JTable gradeTable;
	
	//panel storing search results for classes
	private ClassDisplay classContents;

	//variable to keep track of current filter mode
	private JPanel currentResults;
	private JPanel currentSearch;
	
	//sizes for UI elements
	private static Dimension searchPanelMinimumSize = new Dimension(150, 0);
	private static Dimension contentPanelMinimumSize = new Dimension(200, 0);
	//maximum size for input fields
	private static int MAXSEARCHSIZE = 20;
	
	//keep track of selected class
	private JLabel selectedLabel = null;
	private Class selectedClass = null;
	private JTable selectedTable = null;
	private Color defaultBackground = Color.LIGHT_GRAY;
	private Color selectedBackground = Color.GRAY;
	
	StudentFilterPanel studentBar;
	
	public managementScreen(Markbook markbook) {
		mB = markbook;
		actionListener = new managementScreenActionListener(this, mB);
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
	
	
	/*	switch the current filter panels to
	 * 	grade panels
	 * 
	 */
	public void switchToGrade() {
		currentResults.setVisible(false);
		currentSearch.setVisible(false);
		currentResults = gradeResults;
		currentSearch = gradeSearch;
		gradeResults.setVisible(true);
		gradeSearch.setVisible(true);
	}
	
	
	/*	reads from text fields to add a new subject
	 * 
	 */
	public void addSubject() {
		String fullName = inputSubjectName.getText();
		String shortenedName = inputShortForm.getText();
		//!debug input sanitation here
		if ((!fullName.isEmpty()) && (!shortenedName.isEmpty())) {
			//create new subject
			mB.addSubject(fullName, shortenedName);
			
			//refresh subject table
			filterDisplayTableModel model = (filterDisplayTableModel) subjectTable.getModel();
			model.refreshData(getInitialDataSubject());
			model.fireTableDataChanged();
			
			//refresh subject drop downs
			filterDisplayComboBoxModel boxModel = (filterDisplayComboBoxModel) inputClassSubject.getModel();
			boxModel.refreshData(getInitialDataSubject());
			
		}
	}
	
	
	/*	reads from text fields to add a new student
	 * 
	 */
	public void addStudent() {
		String givenName = inputGivenName.getText();
		String surname = inputSurName.getText();
		filterDisplayComboBoxModel gradeBox = (filterDisplayComboBoxModel) inputStudentGrade.getModel();
		Object studentGradeObject = gradeBox.getSelectedObject();
		//!debug input sanitation here
		
		if (studentGradeObject != null) {
			/*System.out.print("name: " + givenName + "\n");
			System.out.print("name: " + surname + "\n");
			System.out.print("name: " + ((Grade)studentGradeObject).getYear(mB.getCurrentYear()) + "\n");
			*/
			//create new student
			mB.addStudent(givenName, surname, (Grade) studentGradeObject);
			
			//refresh student table
			filterDisplayTableModel model = (filterDisplayTableModel) studentTable.getModel();
			model.refreshData(getInitialDataStudent());
			model.fireTableDataChanged();
			
		} else {
			//!debug TODO
		}
	}
	
	
	/*	reads from drop downs to add a new class
	 * 
	 */
	public void addClass() {
		filterDisplayComboBoxModel classGradeBox = (filterDisplayComboBoxModel) inputClassGrade.getModel();
		Object classGradeObject = classGradeBox.getSelectedObject();
		filterDisplayComboBoxModel classSubjectBox = (filterDisplayComboBoxModel) inputClassSubject.getModel();
		Object classSubjectObject = classSubjectBox.getSelectedObject();
		
		if ((classGradeObject != null) && (classSubjectObject != null)) {
			/*System.out.print("name: " + ((Grade)classGradeObject).getYear(mB.getCurrentYear()) + "\n");
			System.out.print("name: " + ((Subject)classSubjectObject).getName() + "\n");
			*/
			
			//create new class
			Subject subjectObject = (Subject)classSubjectObject;
			subjectObject.addClass((Grade)classGradeObject);
			
			//refresh the class display
			classContents.refreshClass(mB.getClasses());
		}
			
	}
		

	
	
	/*	check if a string is numeric
	 * 	credit to "CraigTP"
	 */
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");
	}
	
	
	/*	reads from text fields to add a new grade
	 * 
	 */
	public void addGrade() {
		String gradeName = inputGradeName.getText();
		
		//!debug input sanitation here
		if ((isNumeric(gradeName)) && (!gradeName.isEmpty())) {
			int gradeNum = Integer.parseInt(gradeName);
			
			//create new subject
			mB.addGrade(gradeNum);
			
			//refresh subject table
			filterDisplayTableModel model = (filterDisplayTableModel) gradeTable.getModel();
			model.refreshData(getInitialDataGrade());
			model.fireTableDataChanged();
		}
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
	 * 	
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
		
		//button to set filter to grades
		JButton gradeButton = new JButton("Grade");
		gradeButton.setActionCommand("setFilterGrade");
		
		//button to set filter to class
		JButton classButton = new JButton("Add to Class ->");
		classButton.setActionCommand("addStudentToClass");
		
		//listen to actions from the buttons
		studentButton.addActionListener(actionListener);
		subjectButton.addActionListener(actionListener);
		gradeButton.addActionListener(actionListener);
		classButton.addActionListener(actionListener);
		
		//setup locations and size ratios for buttons
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.weighty = 0.33;
		c.weightx = 0.80;
		filterPanel.add(studentButton, c);
		c.gridx = 0;
		c.gridy = 1;
		filterPanel.add(subjectButton, c);
		c.gridx = 0;
		c.gridy = 2;
		filterPanel.add(gradeButton, c);
		
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 3;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 0.20;
		filterPanel.add(classButton, c);
		
		return filterPanel;
	}
	
	
	/*	setup filter display panel on bottom half of search panel
	 * 	Stores and switches between 3 panels for Student, grade and Subject search
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
		studentBar = new StudentFilterPanel(mB);

		//create subject search bar
		subjectSearch = new JPanel(new GridBagLayout());
		JTextField searchBarSubject = new JTextField
				("search subjects", MAXSEARCHSIZE);
		subjectSearch.add(searchBarSubject, c);
		subjectSearch.setVisible(false);
		searchBarLocation.add(subjectSearch, c);
		
		//create grade search bar
		gradeSearch = new JPanel(new GridBagLayout());
		JTextField searchBarGrade = new JTextField
				("search grades", MAXSEARCHSIZE);
		gradeSearch.add(searchBarGrade, c);
		gradeSearch.setVisible(false);
		searchBarLocation.add(gradeSearch, c);
		
		//create dummy student search bar
		studentSearch = new JPanel(new GridBagLayout());
		currentSearch = studentSearch;
		
		//display settings for the search bars
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.04;
		c.weightx = 1;
		filterDisplayPanel.add(searchBarLocation, c);
		
		//create search results scroll panels
		subjectTable = setupFilterResultsPanel(getInitialDataSubject());
		gradeTable = setupFilterResultsPanel(getInitialDataGrade());
		JScrollPane subjectResultsScroll = new JScrollPane(subjectTable);
		JScrollPane gradeResultsScroll = new JScrollPane(gradeTable);
		
		//create the search result panel to store the scroll panel
		studentResults = new JPanel(new GridBagLayout());
		subjectResults = new JPanel(new GridBagLayout());
		gradeResults = new JPanel(new GridBagLayout());
		c.weighty = 0.96;
		c.weightx = 1;
		c.gridheight = 2;
		studentResults.add(studentBar, c);
		subjectResults.add(subjectResultsScroll, c);
		gradeResults.add(gradeResultsScroll, c);
		
		currentResults = studentResults;
		subjectResults.setVisible(false);
		gradeResults.setVisible(false);
		c.gridheight = 3;
		c.weighty = 1;
		c.weightx = 1;
		searchResultsLocation.add(studentResults, c);
		searchResultsLocation.add(subjectResults, c);
		searchResultsLocation.add(gradeResults, c);
		
		//display settings for search results location
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.weighty = 0.86;
		c.weightx = 1;
		filterDisplayPanel.add(searchResultsLocation, c);
		
		
		
		
		//"add new" item section
		//panel to add new subject
		JPanel addSubjectPanel = new JPanel(new GridBagLayout());
		JButton newSubject = new JButton("Add New Subject");
		newSubject.setActionCommand("newSubject");
		inputSubjectName = new JTextField
				("Full Name", MAXSEARCHSIZE);
		inputShortForm = new JTextField
				("Shortened Name", MAXSEARCHSIZE);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = 1;
		c.weighty = 0.66;
		c.weightx = 0.5;
		addSubjectPanel.add(inputSubjectName, c);
		c.gridx = 1;
		c.gridheight = 2;
		c.gridwidth = 1;
		addSubjectPanel.add(inputShortForm, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 2;
		addSubjectPanel.add(newSubject, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.weighty = 0.14;
		c.weightx = 1;
		//subjectResults.add(addSubjectPanel, c);
		newSubject.addActionListener(actionListener);	
		
		//panel to add new student
		JPanel addStudentPanel = new JPanel(new GridBagLayout());
		JButton newStudent  = new JButton("Add New Student");
		newStudent.setActionCommand("newStudent");
		inputGivenName = new JTextField
				("Given Name", MAXSEARCHSIZE);
		inputSurName = new JTextField
				("Surname", MAXSEARCHSIZE);
		inputStudentGrade = new JComboBox();
		inputStudentGrade.setModel(new filterDisplayComboBoxModel(getInitialDataGrade(), inputStudentGrade));
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weighty = 0.66;
		c.weightx = 0.4;
		addStudentPanel.add(inputGivenName, c);
		c.gridx = 1;
		c.gridheight = 2;
		c.gridwidth = 1;
		addStudentPanel.add(inputSurName, c);
		c.weightx = 0.2;
		c.gridx = 2;
		addStudentPanel.add(inputStudentGrade, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.4;
		c.weightx = 1;
		addStudentPanel.add(newStudent, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.weighty = 0.14;
		c.weightx = 1;
		//studentResults.add(addStudentPanel, c);
		newStudent.addActionListener(actionListener);
		
		//panel to add new grade
		JPanel addGradePanel = new JPanel(new GridBagLayout());
		JButton newGrade  = new JButton("Add New Grade");
		newGrade.setActionCommand("newGrade");
		inputGradeName = new JTextField
				("Grade Number", MAXSEARCHSIZE);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.5;
		addGradePanel.add(inputGradeName, c);
		c.gridy = 1;
		addGradePanel.add(newGrade, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.weighty = 0.14;
		c.weightx = 1;
		//gradeResults.add(addGradePanel, c);
		newGrade.addActionListener(actionListener);
		
		//add the "add new" panels to view
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.04;
		c.weightx = 1;
		subjectResults.add(addSubjectPanel, c);
		studentResults.add(addStudentPanel, c);
		gradeResults.add(addGradePanel, c);
		
		return filterDisplayPanel;
	}
	
	
	/*	panel for the display of student and subject filter results
	 * 	!debug
	 * 		may want to set a max size, and add more when end reached
	 * 
	 */
	private JTable setupFilterResultsPanel(ArrayList<Object[]> initialData) {
		//create panel to store all results
		JPanel resultsPanel = new JPanel(new GridBagLayout());
		
		//Create JTable to store results
		JTable resultsTable = new JTable(new filterDisplayTableModel(initialData));
		//store the object reference inside a hidden column
		TableColumnModel columnModel = resultsTable.getColumnModel();
		columnModel.removeColumn(columnModel.getColumn(1));
		
		//don't show the header
		resultsTable.setTableHeader(null);
		
		return resultsTable;
	}
	
	
	/*	read from back end to obtain Student initial data to display
	 * 
	 */
	private ArrayList<Object[]> getInitialDataStudent() {
		/*Object[][] data = {
							{"Jackie", "102"},
							{"Leon", "103"},
							{"James", "104"},
							{"Ali", "105"}
		};*/
		
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		
		//obtain an array list of all students
		ArrayList<Student> allStudents = mB.getStudents();
		
		//add each student into the table
		for (Student student : allStudents) {
			String name = student.getSurname()+ ","+ student.getGivenName();
			Object[] item = {name, student};
			data.add(item);
		}
		
		return data;
	}
	
	
	
	/*	read from back end to obtain Student initial data to display
	 * 
	 */
	private ArrayList<Object[]> getInitialDataSubject() {
		/*
		Object[][] data = {
							{"Maths", "10"},
							{"English", "11"},
							{"Science", "12"},
		};
		*/
		
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		
		//obtain an array list of all students
		ArrayList<Subject> allSubjects = mB.getSubjects();
		
		//add each student into the table
		for (Subject subject : allSubjects) {;
			Object[] item = {subject.getName(), subject};
			data.add(item);
		}
		
		return data;
	}
	
	
	/*	read from back end to obtain Grade initial data to display
	 * 
	 */
	private ArrayList<Object[]> getInitialDataGrade() {
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		
		//obtain an array list of all grades
		ArrayList<Grade> allGrades = mB.getGrades();
		
		//add each student into the table
		
		for (Grade grade : allGrades) {;
			Object[] item = {grade.getYear(mB.getCurrentYear()), grade};
			data.add(item);
		}
		
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
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		c.weighty = 0.95;
		
		
		ArrayList<Subject_Class> allClasses = mB.getClasses();
		classContents = new ClassDisplay(allClasses, mB, studentBar.getSelectedPanel());
		
		
		JScrollPane contentScroll = new JScrollPane(classContents);
		contentScroll.getVerticalScrollBar().setUnitIncrement(16);
		contentPanel.add(contentScroll, c);
		
		//panel for creating new classes
		JPanel classCreationPanel = new JPanel(new GridBagLayout());
		JButton newClassButton = new JButton("Add New Class");
		newClassButton.setActionCommand("addNewClass");
		inputClassGrade = new JComboBox();
		inputClassGrade.setModel(new filterDisplayComboBoxModel(getInitialDataGrade(),inputClassGrade ));
		
		inputClassSubject = new JComboBox();
		inputClassSubject.setModel(new filterDisplayComboBoxModel(getInitialDataSubject(), inputClassSubject));
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.weighty = 1;
		classCreationPanel.add(inputClassGrade, c);
		c.gridx = 1;
		classCreationPanel.add(inputClassSubject, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.weightx = 1;
		classCreationPanel.add(newClassButton, c);
		
		newClassButton.addActionListener(actionListener);
		//add to interface
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		c.weighty = 0.05;
		contentPanel.add(classCreationPanel, c);
	}
	
}