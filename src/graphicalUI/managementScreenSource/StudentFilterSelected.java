package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import main.Markbook;
import main.Student;

/*	stores and displays selected students
 * 
 */
public class StudentFilterSelected extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	
	Markbook mB;
	ArrayList<StudentFilterSelectedItem> selectedStudentItems;
	ArrayList<Student> selectedStudents;
	
	
	/*	default constructor
	 * 
	 */
	public StudentFilterSelected(Markbook newmB) {
		mB = newmB;
		selectedStudentItems = new ArrayList<StudentFilterSelectedItem>();
		selectedStudents = new ArrayList<Student>();
		
		setupGraphical();
	}
	
	
	/*	add a student to this panel
	 * 	
	 */
	public void addStudent(Student student) {
		StudentFilterSelectedItem newStudentItem = new StudentFilterSelectedItem(student, mB, this);
	
		selectedStudents.add(student);
		selectedStudentItems.add(newStudentItem);
		this.add(newStudentItem, getConstraint());
	}
	
	
	public boolean hasStudent(Student student) {
		boolean hasStudent = false;
		
		if (selectedStudents.contains(student)) {
			hasStudent = true;
		}
		
		return hasStudent;
	}
	
	
	/*	remove a student from this panel
	 * 
	 */
	public void removeStudent(StudentFilterSelectedItem student) {
		selectedStudents.remove(student);
		selectedStudentItems.remove(student);
		this.remove(student);
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
