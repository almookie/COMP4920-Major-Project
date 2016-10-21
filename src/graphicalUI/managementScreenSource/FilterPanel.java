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
	
	//panel to handle changing pages
	PageSelectionPanel pageSelection;
	
	//panels to store all components of each screen
	JPanel gradePanel;
	JPanel subjectPanel;
	JPanel studentPanel;
	
	//panels to handle filtering
	GradeFilterPanel gradeFilterPanel;
	SubjectFilterPanel subjectFilterPanel;
	StudentFilterPanel studentFilterPanel;
	
	//panels to handle adding
	AddGradePanel gradeAddPanel;
	AddSubjectPanel subjectAddPanel;
	AddStudentPanel studentAddPanel;
	
	
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
		
		//create panels to handle adding
		gradeAddPanel = new AddGradePanel(mB);
		subjectAddPanel = new AddSubjectPanel(mB);
		studentAddPanel = new AddStudentPanel(mB);
		
		pageSelection = new PageSelectionPanel(gradePanel, subjectPanel, studentPanel);
		
		setupGraphical();
	}
	
	
	 /*	set up the display elements
	 * 
	 */
	private void setupGraphical() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//page selection panel
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.2;
		c.weightx = 1;
		this.add(pageSelection, c);
		
		//filter panels
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.7;
		c.weightx = 1;
		gradePanel.add(gradeFilterPanel, c);
		subjectPanel.add(subjectFilterPanel, c);
		studentPanel.add(studentFilterPanel, c);
		
		//add new item panels
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.3;
		c.weightx = 1;
		gradePanel.add(gradeAddPanel, c);
		subjectPanel.add(subjectAddPanel, c);
		studentPanel.add(studentAddPanel, c);
		
		//add content panels to self
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.8;
		c.weightx = 1;
		this.add(gradePanel, c);
		this.add(subjectPanel, c);
		this.add(studentAddPanel, c);
	}
	

}
