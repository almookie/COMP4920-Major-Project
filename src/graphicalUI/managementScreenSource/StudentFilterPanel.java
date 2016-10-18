package graphicalUI.managementScreenSource;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.Markbook;
import main.Student;

/*	panel for searching and selecting students
 * 
 */
public class StudentFilterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	StudentFilterSelected selectedStudents;
	StudentFilterBar filterBar;
	Markbook mB;
	
	/*	default constructor
	 * 
	 */
	public StudentFilterPanel(Markbook newmB) {
		mB = newmB;
		selectedStudents = new StudentFilterSelected(mB);
		filterBar = new StudentFilterBar(mB, selectedStudents);
		
		setupGraphical();
	}
	
	
	public StudentFilterSelected getSelectedPanel() {
		return selectedStudents;
	}
	
	
	private void setupGraphical() {
		//use gridbag format
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.05;
		c.weightx = 1;
		this.add(filterBar, c);
		
		JScrollPane selectedStudentsScroll = new JScrollPane(selectedStudents);
		selectedStudents.setScroll(selectedStudentsScroll);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.95;
		c.weightx = 1;
		this.add(selectedStudentsScroll, c);
		

	}
}
