package graphicalUI.managementScreenSource;

import graphicalUI.managementScreen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import main.Markbook;

/*	filter and selection panel for subjects
 * 
 */
public class SubjectFilterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JTextField subjectFilter;
	SubjectFilterResults filterResults;
	Markbook mB;
	
	
	/*	default constructor
	 *
	 */
	public SubjectFilterPanel(Markbook newmB, managementScreen mS) {
		mB = newmB;
		subjectFilter = new JTextField("enter subject name");
		filterResults = new SubjectFilterResults(mB, subjectFilter, mS);
		
		setupGraphical();
		//default to displaying all subjects
		filterResults.updateResults();
	}
	
	
	/*	refresh this panel
	 * 
	 */
	public void refresh() {
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
		this.add(subjectFilter, c);
		
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
