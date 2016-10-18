package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import main.Subject_Class;
import main.Markbook;
import main.Student;

//TODO can be more efficient

/*	Populates and stores ClassPanel elements
 * 
 */
public class ClassDisplay extends JPanel {

	private static final long serialVersionUID = 1L;

	//ArrayList of all ClassPanels stored
	ArrayList<ClassPanel> allClasses;
	Markbook mB;
	StudentFilterSelected selectedStudents;
	
	/*	default constructor
	 * 
	 */
	public ClassDisplay(Markbook newmB, StudentFilterSelected newSelectedStudents) {
		allClasses = new ArrayList<ClassPanel>();
		mB = newmB;
		selectedStudents = newSelectedStudents;
		
		setupGraphical();
	}
	
	
	/*	constructor to initiate with an ArrayList of Class
	 * 
	 */
	public ClassDisplay(ArrayList<Subject_Class> newClasses, Markbook newmB, StudentFilterSelected newSelectedStudents) {
		allClasses = new ArrayList<ClassPanel>();
		mB = newmB;
		selectedStudents = newSelectedStudents;
		
		setupGraphical();
		
		refreshClass(newClasses);
	}
	
	
	/*	refresh all Class with a new ArrayList of Class
	 * 
	 */
	public void refreshClass(ArrayList<Subject_Class> newClasses) {
		//clear all students
		this.removeAll();
		allClasses.clear();
		
		//add new students
		for (Subject_Class thisClass : newClasses) {
			addClass(thisClass);
		}
		this.revalidate();
		this.repaint();
	}
	
	
	/*	refresh class directly from backend
	 * 
	 */
	public void refreshClass() {
		refreshClass(mB.getClasses());
	}
	

	/*	add a new classPanel to classDislay
	 * 
	 */
	private void addClass(Subject_Class newClass) {
		ClassPanel newPanel = 
				new ClassPanel(newClass.getStudents(), newClass, mB, this,selectedStudents);
		
		allClasses.add(newPanel);
		this.add(newPanel, getConstraint());
	}
	
	
	/*	set up the display elements
	 * 
	 */
	private void setupGraphical() {
		this.setLayout(new GridBagLayout());
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
