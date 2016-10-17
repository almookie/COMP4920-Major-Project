package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import main.Markbook;
import main.Student;

/*	displays filter results
 * 
 */
public class StudentFilterResults extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	
	//all stored StudentFilterItems
	ArrayList<StudentFilterItem> allResults;
	
	Markbook mB;
	
	
	/*	default constructor
	 * 
	 */
	public StudentFilterResults(Markbook newmB) {
		allResults = new ArrayList<StudentFilterItem>();
		mB = newmB;
		
		setupGraphical();
	}
	
	
	/*	clear old display and display the new Students
	 * 
	 */
	public void updateResults(ArrayList<Student> newResults) {
		//clear old display
		allResults.clear();
		this.removeAll();
		
		//add new students
		for (Student student : newResults) {
			addStudent(student);
		}
		this.revalidate();
		this.repaint();
	}
	
	
	/*	add a new StudentFilterItem to display
	 * 
	 */
	private void addStudent(Student student) {
		StudentFilterItem newResult =
				new StudentFilterItem(student, mB);
		allResults.add(newResult);
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
