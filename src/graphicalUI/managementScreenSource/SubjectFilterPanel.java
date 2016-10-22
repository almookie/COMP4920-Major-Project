package graphicalUI.managementScreenSource;

import graphicalUI.managementScreen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import main.Markbook;

/*	filter and selection panel for subjects
 * 
 */
public class SubjectFilterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JTextField subjectFilter;
	SubjectFilterResults filterResults;
	Markbook mB;
	
	//titles for this panel
	private String searchTitle = "Filter Subjects:";
	
	
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
		
		//set border
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);
		this.setBorder(compound);
		
		//set insets
		c.insets = new Insets(0,10,0,10);
		
		//add title for search
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.03;
		c.weightx = 1;
		JLabel title = new JLabel(searchTitle, SwingConstants.LEFT);
		title.setOpaque(false);
		//title.setFont(titlefont);
		this.add(title, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.05;
		c.weightx = 1;
		this.add(subjectFilter, c);
		
		JScrollPane subjectScroll = new JScrollPane(filterResults);
		filterResults.setScroll(subjectScroll);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.92;
		c.weightx = 1;
		this.add(subjectScroll, c);
		
	}
	
	
}
