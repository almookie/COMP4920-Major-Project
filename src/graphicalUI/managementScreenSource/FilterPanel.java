package graphicalUI.managementScreenSource;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.Markbook;

/*	manages 3 panels for selecting, adding and deleting of Students, Grades and Subjects
 * 
 */
public class FilterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	Markbook mB;
	
	
	/*	default constructor
	 * 
	 */
	public FilterPanel(Markbook newmB) {
		mB = newmB;
		
		setupGraphical();
	}
	
	private void setupGraphical() {
		this.setLayout(new GridBagLayout());
		
	}
}
