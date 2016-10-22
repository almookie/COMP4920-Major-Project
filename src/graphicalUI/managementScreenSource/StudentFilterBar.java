package graphicalUI.managementScreenSource;

import graphicalUI.managementScreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.Markbook;

/*	a search bar for Students which populates a selection box beneath it
 * 
 */
public class StudentFilterBar extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	private static Integer preferredHeight = 100;
	//maximum size for input fields
	private static int MAXSEARCHSIZE = 20;
	
	JPanel self = this;
	
	Markbook mB;
	JTextField searchBar;
	StudentFilterResults myResults;
	JScrollPane scrollableResults;
	
	
	/*	default constructor
	 * 
	 */
	public StudentFilterBar(Markbook newmB, StudentFilterSelected selectionBox, managementScreen mS) {
		mB = newmB;
		scrollableResults = new JScrollPane(myResults);
		
		setupGraphical();
		
		myResults = new StudentFilterResults(newmB, selectionBox, searchBar, mS);
		
		setupResultsToggle();
		setupResultsUpdate();
	}
	
	
	/*	set up the display elements
	 * 
	 */
	private void setupGraphical() {
		//use gridbag format
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//set background color
		this.setBackground(backgroundColor);
		
		//setup the search bar
		searchBar = new JTextField
				("enter student name", MAXSEARCHSIZE);
		
		//set preferred size
		Dimension preferredSize = this.getPreferredSize();
		preferredSize.height = preferredHeight;
		scrollableResults.setPreferredSize(preferredSize);
		
		//add the search bar and related results panel
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.1;
		c.weightx = 1;
		this.add(searchBar, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 2;
		c.gridwidth = 3;
		c.weighty = 0.9;
		c.weightx = 1;
		scrollableResults.setVisible(false);
		scrollableResults.getVerticalScrollBar().setUnitIncrement(16);
		this.add(scrollableResults, c);
	}
	
	
	/*	set up the search bar such that selecting it toggles the results appear
	 * 
	 */
	private void setupResultsToggle() {
		searchBar.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				scrollableResults.setVisible(true);
				myResults.updateResults(mB.searchStudents(searchBar.getText()));
				scrollableResults.setViewportView(myResults);
				
				self.revalidate();
				self.repaint();
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				scrollableResults.setVisible(false);
				
				self.revalidate();
				self.repaint();
			}
			
		});
	}
	
	
	/*	set up the search bar such that changing the input refreshes the results
	 * 
	 */
	private void setupResultsUpdate() {
		searchBar.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				myResults.updateResults(mB.searchStudents(searchBar.getText()));
				scrollableResults.setViewportView(myResults);
				self.revalidate();
				self.repaint();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				myResults.updateResults(mB.searchStudents(searchBar.getText()));
				scrollableResults.setViewportView(myResults);
				self.revalidate();
				self.repaint();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				myResults.updateResults(mB.searchStudents(searchBar.getText()));
				scrollableResults.setViewportView(myResults);
				self.revalidate();
				self.repaint();
			}
			
		});
		
	}
}
