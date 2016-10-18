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
	JPanel gradePanel;
	JPanel SubjectPanel;
	JPanel StudentPanel;
	
	/*	default constructor
	 * 
	 */
	public FilterPanel(Markbook newmB) {
		mB = newmB;
		
		setupGraphical();
	}
	
	
	 /*	set up the display elements
	 * 
	 */
	private void setupGraphical() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//create panels to contain each page
		
	}
}
