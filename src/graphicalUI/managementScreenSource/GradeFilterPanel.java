package graphicalUI.managementScreenSource;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import main.Markbook;

public class GradeFilterPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	JTextField gradeFilter;
	GradeFilterResults filterResults;
	Markbook mB;

	public GradeFilterPanel(Markbook newmB) {
		mB = newmB;
		gradeFilter = new JTextField("enter grade name");
		filterResults = new GradeFilterResults(mB, gradeFilter);
		
		setupGraphical();
		//default to displaying all grades
		filterResults.updateResults();
	}
	
	
	/*	set up the display elements
	 * 
	 */
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
		this.add(gradeFilter, c);
		
		JScrollPane subjectScroll = new JScrollPane(filterResults);
		filterResults.setScroll(subjectScroll);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.95;
		c.weightx = 1;
		this.add(subjectScroll, c);
		
	}
}
