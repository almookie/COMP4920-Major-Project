package graphicalUI.managementScreenSource;

import graphicalUI.managementScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import main.Markbook;
import main.Student;

/*	panel for searching and selecting students
 * 
 */
public class StudentFilterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	//private static Color backgroundColor = Color.WHITE;
	
	StudentFilterSelected selectedStudents;
	StudentFilterBar filterBar;
	Markbook mB;
	
	//font for title labels
	//private Font titlefont = new Font("Helvetica", Font.BOLD, 14);
	
	//titles for this panel
	private String searchTitle = "Search Students:";
	private String selectedTitle = "Selected Students:";
	
	/*	default constructor
	 * 
	 */
	public StudentFilterPanel(Markbook newmB, managementScreen mS) {
		mB = newmB;
		selectedStudents = new StudentFilterSelected(mB);
		filterBar = new StudentFilterBar(mB, selectedStudents, mS);
		
		setupGraphical();
	}
	
	
	public StudentFilterSelected getSelectedPanel() {
		return selectedStudents;
	}
	
	
	/*	set up the display elements
	 * 
	 */
	private void setupGraphical() {
		//use gridbag format
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//set background
		//this.setBackground(backgroundColor);
		
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
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.05;
		c.weightx = 1;
		c.insets = new Insets(0,10,20,10);
		this.add(filterBar, c);
		
		
		c.insets = new Insets(0,10,0,10);
		//add title for selected student
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.03;
		c.weightx = 1;
		JLabel selectedLabel = new JLabel(selectedTitle, SwingConstants.LEFT);
		selectedLabel.setOpaque(false);
		//selectedLabel.setFont(titlefont);
		this.add(selectedLabel, c);
		
		JScrollPane selectedStudentsScroll = new JScrollPane(selectedStudents);
		selectedStudents.setScroll(selectedStudentsScroll);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.89;
		c.weightx = 1;
		this.add(selectedStudentsScroll, c);
		

	}
}
