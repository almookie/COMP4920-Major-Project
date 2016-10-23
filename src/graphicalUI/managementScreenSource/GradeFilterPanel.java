package graphicalUI.managementScreenSource;

/*	paanel to handlle filtering and displlay of grades
 * 
 */
import graphicalUI.managementScreen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.Grade;
import main.Markbook;

public class GradeFilterPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	JTextField gradeFilter;
	GradeFilterResults filterResults;
	Markbook mB;
	JCheckBox confirmationCheck;
	
	//titles for this panel
	private String searchTitle = "Filter Grades:";

	private boolean hasChanged;
	
	public GradeFilterPanel(Markbook newmB, managementScreen mS) {
		mB = newmB;
		gradeFilter = new JTextField("enter grade name");
		confirmationCheck = new JCheckBox("Do not ask for confirmation upon deleting");
		filterResults = new GradeFilterResults(mB, gradeFilter, mS, confirmationCheck);
		hasChanged = false;
		
		setupGraphical();
		setupfilterBar();
		//default to displaying all grades
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
		this.add(gradeFilter, c);
		
		JScrollPane subjectScroll = new JScrollPane(filterResults);
		filterResults.setScroll(subjectScroll);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.90;
		c.weightx = 1;
		this.add(subjectScroll, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.02;
		c.weightx = 1;
		this.add(confirmationCheck, c);
		
	}
	
	
	/*	setup filter bar functionality
	 * 
	 */
	private void setupfilterBar() {
		gradeFilter.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				if (!hasChanged) {
					filterResults.updateResults();
				} else {
					if (gradeFilter.getText().equals("")) {
						filterResults.updateResults();
					} else {
						if (checkInput()) {
							filterResults.updateResults(mB.searchGrades(Integer.valueOf(gradeFilter.getText())));
						} else {
							filterResults.updateResults(new ArrayList<Grade>());
						}
					}
				}
				
				hasChanged = true;
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if (!hasChanged) {
					filterResults.updateResults();
				} else {
					if (gradeFilter.getText().equals("")) {
						filterResults.updateResults();
					} else {
						if (checkInput()) {
							filterResults.updateResults(mB.searchGrades(Integer.valueOf(gradeFilter.getText())));
						} else {
							filterResults.updateResults(new ArrayList<Grade>());
						}
					}
				}
				
				hasChanged = true;
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (!hasChanged) {
					filterResults.updateResults();
				} else {
					if (gradeFilter.getText().equals("")) {
						filterResults.updateResults();
					} else {
						if (checkInput()) {
							filterResults.updateResults(mB.searchGrades(Integer.valueOf(gradeFilter.getText())));
						}
					}
				}
				
				hasChanged = true;
			}
			
		});
	}
	
	
	/*	check if textbox entry is of the correct format
	 * 	true if item only has allowed characters, otherwise false
	 */
	private boolean checkInput() {
		boolean isAllowed = false;
		
		/*	check if a string is numeric
		 * 	credit to "CraigTP"
		 */
		if (gradeFilter.getText().matches("-?\\d+(\\.\\d+)?")) {
			isAllowed = true;
		}
		
		return isAllowed;
	}
}
