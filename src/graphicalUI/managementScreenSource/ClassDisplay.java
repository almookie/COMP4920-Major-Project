package graphicalUI.managementScreenSource;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import main.Class;
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
	
	
	/*	Default constructor
	 * 
	 */
	public ClassDisplay(Markbook newmB) {
		allClasses = new ArrayList<ClassPanel>();
		mB = newmB;
		
		setupGraphical();
	}
	
	
	/*	constructor to initiate with an ArrayList of Class
	 * 
	 */
	public ClassDisplay(ArrayList<Class> newClasses, Markbook newmB) {
		allClasses = new ArrayList<ClassPanel>();
		mB = newmB;
		
		setupGraphical();
		
		refreshClass(newClasses);
	}
	
	
	/*	refresh all Class with a new ArrayList of Class
	 * 
	 */
	public void refreshClass(ArrayList<Class> newClasses) {
		//clear all students
		this.removeAll();
		allClasses.clear();
		
		//add new students
		for (Class thisClass : newClasses) {
			addClass(thisClass);
		}
	}
	

	/*	add a new classPanel to classDislay
	 * 
	 */
	private void addClass(Class newClass) {
		ClassPanel newPanel = 
				new ClassPanel(newClass.getStudents(), newClass, mB);
		
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
