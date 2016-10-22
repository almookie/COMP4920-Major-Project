package graphicalUI.managementScreenSource;

import graphicalUI.managementScreen;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import main.Grade;
import main.Markbook;

public class GradeFilterResults extends JPanel {
	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	
	Markbook mB;
	managementScreen mS;
	
	//Scroll pane attached to this
	JScrollPane myScroll = null;
	JTextField myFilterBar;
	
	
	/*	default constructor
	 * 
	 */
	public GradeFilterResults(Markbook newmB, JTextField newFilterBar, managementScreen newmS) {
		mB = newmB;
		mS = newmS;
		myFilterBar = newFilterBar;
		
		setupGraphical();
	}
	
	
	/*	attach a scroll pane to this panel
	 * 
	 */
	public void setScroll(JScrollPane newScroll) {
		myScroll = newScroll;
		myScroll.getVerticalScrollBar().setUnitIncrement(16);
	}
	
	
	/*	refresh the whole management screen
	 * 
	 */
	public void refreshWholePage() {
		mS.refresh();
	}
	
	
	/*	refresh displayed results from linked textbox
	 * 
	 */
	public void refreshResults() {
		//TODO	add filter
		//updateResults(mB.searchSubjects(myFilterBar.getText()));
		updateResults();
		
		this.revalidate();
		this.repaint();
		updateScrollPane();
	}
	
	
	/*	clear old display and display the new Students with provided array
	 * 
	 */
	public void updateResults(ArrayList<Grade> newResults) {
		if (newResults.size() > 0) {
			//clear old display
			//allResults.clear();
			this.removeAll();
			
			//add new students
			for (Grade grade : newResults) {
				this.addGrade(grade);
			}
			this.revalidate();
			this.repaint();
			updateScrollPane();
		} else {
			updateResults();
		}
	}
	
	
	/*	clear old display and display the new Students with all subjects in backend
	 * 
	 */
	public void updateResults() {
		updateResults(mB.getGrades());
	}
	
	
	/*	add a new StudentFilterItem to display
	 * 
	 */
	private void addGrade(Grade grade) {
		GradePanel newResult =
				new GradePanel(grade, mB, this);
		//allResults.add(newResult);
		this.add(newResult, getConstraint());
	}
	
	
	/*	update attached scroll pane, if there is one
	 * 
	 */
	private void updateScrollPane() {
		if (myScroll != null) {
			myScroll.setViewportView(this);
			this.revalidate();
			this.repaint();
		}
	}
	
	
	/*	set up the display elements
	 * 
	 */
	private void setupGraphical() {
		//use gridbag format
		this.setLayout(new GridBagLayout());
		
		//set background color
		this.setOpaque(true);
		this.setBackground(backgroundColor);
	}
	
	
	/*	get GridBagConstraints required to list downwards
	 * 
	 */
	private GridBagConstraints getConstraint() {
		GridBagConstraints newc = new GridBagConstraints();
		newc.fill = GridBagConstraints.HORIZONTAL;
		newc.gridx = 1;
		newc.gridwidth = 2;
		newc.weightx = 0.8;		
		
		return newc;
	}
	
}
