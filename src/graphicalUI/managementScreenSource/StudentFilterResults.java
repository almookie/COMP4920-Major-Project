package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Markbook;
import main.Student;

/*	displays filter results
 * 
 */
public class StudentFilterResults extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	
	//all stored StudentFilterItems
	//ArrayList<StudentFilterItem> allResults;
	
	Markbook mB;
	JTextField myFilterBar;
	//panel to store selected students
	StudentFilterSelected selectedPanel;
	
	/*	default constructor
	 * 
	 */
	public StudentFilterResults(Markbook newmB, StudentFilterSelected newSelectedPanel, JTextField newFilterBar) {
		//allResults = new ArrayList<StudentFilterItem>();
		mB = newmB;
		selectedPanel = newSelectedPanel;
		myFilterBar = newFilterBar;
		
		setupGraphical();
	}
	
	
	/*	refresh displayed results from linked textbox
	 * 
	 */
	public void refreshResults() {
		updateResults(mB.searchStudents(myFilterBar.getText()));
	}
	
	
	/*	clear old display and display the new Students with provided array
	 * 
	 */
	public void updateResults(ArrayList<Student> newResults) {
		//clear old display
		//allResults.clear();
		this.removeAll();
		
		//add new students
		for (Student student : newResults) {
			if (!selectedPanel.hasStudent(student)) {
				addStudent(student);
			}
		}
		this.revalidate();
		this.repaint();
	}
	
	
	/*	add a new StudentFilterItem to display
	 * 
	 */
	private void addStudent(Student student) {
		StudentFilterItem newResult =
				new StudentFilterItem(student, this, selectedPanel, mB);
		//allResults.add(newResult);
		this.add(newResult, getConstraint());
	}
	
	
	/*	set up the display elements
	 * 
	 */
	private void setupGraphical() {
		//use gridbag format
		this.setLayout(new GridBagLayout());
		
		//set background color
		this.setOpaque(true);
		this.setBackground(backgroundColor);
	}
	
	
	/*	get GridBagConstraints required to list downwards
	 * 
	 */
	private GridBagConstraints getConstraint() {
		GridBagConstraints newc = new GridBagConstraints();
		newc.fill = GridBagConstraints.HORIZONTAL;
		newc.gridx = 1;
		newc.gridwidth = 2;
		newc.weightx = 0.8;		
		
		return newc;
	}
	
}
