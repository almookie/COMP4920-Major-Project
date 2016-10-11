package graphicalUI.managementScreenSource;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Markbook;
import main.Student;
import main.Class;

//TODO setup an on click action listener for collapsible panel funnctionality

/*	Collapsible panel which stores studentPanels 
 * 	and handles interaction with whole classes
 */
public class ClassPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	//ArrayList of all StudentPanels stored
	ArrayList<StudentPanel> allStudents;
	Class thisClass;
	Markbook mB;
	
	//two panels that make up the collapsible panel
	JPanel mainPanel;
	JPanel collapsiblePanel;
	JPanel contentPanel;
	
	/*	default constructor
	 * 
	 */
	public ClassPanel(Class newClass, Markbook newmB) {
		allStudents = new ArrayList<StudentPanel>();
		thisClass = newClass;
		mB = newmB;
		
		setupGraphical();
	}
	
	
	/*	constructor to initiate with an ArrayList of Student
	 * 
	 */
	public ClassPanel(ArrayList<Student> newStudents, Class newClass, Markbook newmB) {
		allStudents = new ArrayList<StudentPanel>();
		thisClass = newClass;
		mB = newmB;
		
		setupGraphical();
		
		refreshClass(newStudents);
	}
	
	
	/*	refresh students in class with new list of students
	 * 
	 */
	public void refreshClass(ArrayList<Student> newStudents) {
		//clear all students
		contentPanel.removeAll();
		allStudents.clear();
		
		//add new students
		for (Student thisStudent : newStudents) {
			addStudent(thisStudent);
		}
	}
	
	
	/*	add a student to the classPanel to be displayed
	 * 
	 */
	private void addStudent(Student newStudent) {
		StudentPanel newPanel = new StudentPanel(newStudent);
		allStudents.add(newPanel);
		
		contentPanel.add(newPanel);
	}
	
	
	/*	set up the display elements
	 * 
	 */
	private void setupGraphical() {
		//use gridbag format
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//setup main panel
		mainPanel = new JPanel(new GridBagLayout());
		//display label with class name
		String className = mB.getLongName(thisClass);
		JLabel displayName = new JLabel(className);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 1;
		mainPanel.add(displayName, c);
		
		//setup collapsible panel
		collapsiblePanel = new JPanel(new GridBagLayout());
		contentPanel = new JPanel(new FlowLayout());
		collapsiblePanel.add(contentPanel, c);
		
		//add to display
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.5;
		c.weightx = 0.5;
		this.add(mainPanel, c);
		c.gridy = 1;
		this.add(collapsiblePanel, c);
	}
	
	
}
