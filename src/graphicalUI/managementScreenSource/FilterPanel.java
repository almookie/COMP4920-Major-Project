package graphicalUI.managementScreenSource;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.Markbook;

/*	manages 3 panels for selecting, adding and deleting of Students, Grades and Subjects
 * 
 */
public class FilterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	Markbook mB;
	
	PageSelectionPanel pageSelection;
	
	//panels to store all components of each screen
	JPanel gradePanel;
	JPanel subjectPanel;
	JPanel studentPanel;
	
	//panels to handle filtering
	GradeFilterPanel gradeFilterPanel;
	SubjectFilterPanel subjectFilterPanel;
	StudentFilterPanel studentFilterPanel;
	
	
	/*	default constructor
	 * 
	 */
	public FilterPanel(Markbook newmB) {
		mB = newmB;
		
		//create the panels to store each page
		gradePanel = new JPanel(new GridBagLayout());
		subjectPanel = new JPanel(new GridBagLayout());
		studentPanel = new JPanel(new GridBagLayout());
		
		//create panels to handle filtering
		gradeFilterPanel = new GradeFilterPanel(mB);
		subjectFilterPanel = new SubjectFilterPanel(mB);
		studentFilterPanel = new StudentFilterPanel(mB);
		
		pageSelection = new PageSelectionPanel(gradePanel, subjectPanel, studentPanel);
		
		setupGraphical();
	}
	
	
	 /*	set up the display elements
	 * 
	 */
	private void setupGraphical() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
	}
	

}
