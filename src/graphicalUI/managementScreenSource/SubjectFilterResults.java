package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import main.Markbook;
import main.Student;
import main.Subject;

/*	displays subject filter results
 * 
 */
public class SubjectFilterResults extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	
	Markbook mB;

	//Scroll pane attached to this
	JScrollPane myScroll = null;
	JTextField myFilterBar;
	
	/*	default constructor
	 * 
	 */
	public SubjectFilterResults(Markbook newmB, JTextField newFilterBar) {
		mB = newmB;
		myFilterBar = newFilterBar;
		
		setupGraphical();
	}
	
	
	/*	attach a scroll pane to this panel
	 * 
	 */
	public void setScroll(JScrollPane newScroll) {
		myScroll = newScroll;
	}
	
	
	/*	refresh displayed results from linked textbox
	 * 
	 */
	public void refreshResults() {
		//TODO	add filter
		//updateResults(mB.searchSubjects(myFilterBar.getText()));
		
		this.revalidate();
		this.repaint();
		updateScrollPane();
	}
	
	
	/*	clear old display and display the new Students with provided array
	 * 
	 */
	public void updateResults(ArrayList<Subject> newResults) {
		//clear old display
		//allResults.clear();
		this.removeAll();
		
		//add new students
		for (Subject subject : newResults) {
			this.addSubject(subject);
		}
		this.revalidate();
		this.repaint();
		updateScrollPane();
	}
	
	
	/*	clear old display and display the new Students with all subjects in backend
	 * 
	 */
	public void updateResults() {
		updateResults(mB.getSubjects());
	}
	
	
	/*	add a new StudentFilterItem to display
	 * 
	 */
	private void addSubject(Subject subject) {
		SubjectPanel newResult =
				new SubjectPanel(subject, mB, this);
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
