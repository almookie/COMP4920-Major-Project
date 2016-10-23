package graphicalUI.statsSearchBar;

import graphicalUI.managementScreenSource.GradePanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import main.Grade;
import main.Markbook;
import main.Subject_Class;

public class StatsClassFilter extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	
	StatsSearchPanel myParent;
	Markbook mB;
	JTextField myFilterBar;
	JScrollPane myScroll = null;
	
	public StatsClassFilter(Markbook newmB, JTextField newFilterBar, StatsSearchPanel newParent) {
		mB = newmB;
		myFilterBar = newFilterBar;
		myParent = newParent;
		
		setupGraphical();
	}
	
	
	/*	attach a scroll pane to this panel
	 * 
	 */
	public void setScroll(JScrollPane newScroll) {
		myScroll = newScroll;
		myScroll.getVerticalScrollBar().setUnitIncrement(16);
	}
	
	
	/*	refresh displayed results from linked textbox
	 * 
	 */
	public void refreshResults() {
		//updateResults(mB.searchSubjects(myFilterBar.getText()));
		updateResults();
		
		this.revalidate();
		this.repaint();
		updateScrollPane();
	}
	
	
	/*	clear old display and display the new Students with provided array
	 * 
	 */
	public void updateResults(ArrayList<Subject_Class> newResults) {
		//clear old display
		//allResults.clear();
		this.removeAll();
		
		//add new students
		for (Subject_Class grade : newResults) {
			this.addItem(grade);
		}
		this.revalidate();
		this.repaint();
		updateScrollPane();
	}
	
	/*	clear old display and display the new Students with all subjects in backend
	 * 
	 */
	public void updateResults() {
		updateResults(mB.getClasses());
	}
	
	/*	add a new StudentFilterItem to display
	 * 
	 */
	private void addItem(Subject_Class newItem) {
		StatsClassFilterItem newResult =
				new StatsClassFilterItem(newItem, mB, myParent);
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
