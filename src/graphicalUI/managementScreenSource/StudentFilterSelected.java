package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.Markbook;
import main.Student;

/*	stores and displays selected students
 * 
 */
public class StudentFilterSelected extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	//private static Integer preferredHeight = 100;
	
	
	Markbook mB;
	ArrayList<StudentFilterSelectedItem> selectedStudentItems;
	ArrayList<Student> selectedStudents;
	
	//Scroll pane attached to this
	JScrollPane myScroll = null;
	
	
	/*	default constructor
	 * 
	 */
	public StudentFilterSelected(Markbook newmB) {
		mB = newmB;
		selectedStudentItems = new ArrayList<StudentFilterSelectedItem>();
		selectedStudents = new ArrayList<Student>();
		
		setupGraphical();
	}
	
	
	/*	attach a scroll pane to this panel
	 * 
	 */
	public void setScroll(JScrollPane newScroll) {
		myScroll = newScroll;
		myScroll.getVerticalScrollBar().setUnitIncrement(16);
	}
	
	
	/*	add a student to this panel
	 * 	
	 */
	public void addStudent(Student student) {
		StudentFilterSelectedItem newStudentItem = new StudentFilterSelectedItem(student, mB, this);
	
		selectedStudents.add(student);
		selectedStudentItems.add(newStudentItem);
		this.add(newStudentItem, getConstraint());
		
		this.revalidate();
		this.repaint();
		updateScrollPane();
	}
	
	
	/*	returns true if has student, otherwise false
	 * 
	 */
	public boolean hasStudent(Student student) {
		boolean hasStudent = false;
		
		if (selectedStudents.contains(student)) {
			hasStudent = true;
		}
		
		return hasStudent;
	}
	
	
	/*	get all stored students
	 * 
	 */
	public ArrayList<Student> getSelectedStudents() {
		return selectedStudents;
	}
	
	
	/*	remove a student from this panel
	 * 
	 */
	public void removeStudent(StudentFilterSelectedItem student) {
		selectedStudents.remove(student.getStudent());
		selectedStudentItems.remove(student);
		this.remove(student);
		
		this.revalidate();
		this.repaint();
		updateScrollPane();
	}
	
	
	/*	update attached scroll pane, if there is one
	 * 
	 */
	private void updateScrollPane() {
		if (myScroll != null) {
			myScroll.setViewportView(this);
			this.revalidate();
			this.repaint();
			myScroll.revalidate();
			myScroll.repaint();
		}
	}
	
	
	/*	set up the display elements
	 * 
	 */
	private void setupGraphical() {
		//use gridbag format
		this.setLayout(new GridBagLayout());
		
		//set preferred size
		//Dimension preferredSize = this.getPreferredSize();
		//preferredSize.height = preferredHeight;
		//this.setPreferredSize(preferredSize);
		
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
