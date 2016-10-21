package graphicalUI;

import graphicalUI.managementScreenSource.AddClassPanel;
import graphicalUI.managementScreenSource.ClassDisplay;
import graphicalUI.managementScreenSource.FilterPanel;
import graphicalUI.managementScreenSource.StudentFilterBar;
import graphicalUI.managementScreenSource.StudentFilterPanel;

import javax.swing.*;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import main.Grade;
import main.Markbook;
import main.Student;
import main.Subject;
import main.Subject_Class;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

//note change get initial data names
//add more info in students

public class managementScreen extends JPanel  {

	private static final long serialVersionUID = 1L;
	
	//two main panels that store everything
	public FilterPanel searchPanel;
	public JPanel contentPanel;
	
	private Markbook mB;
	
	
	//panel storing search results for classes
	private ClassDisplay classContents;
	
	//sizes for UI elements
	private static Dimension searchPanelMinimumSize = new Dimension(150, 0);
	private static Dimension contentPanelMinimumSize = new Dimension(200, 0);
	
	public managementScreen(Markbook markbook) {
		mB = markbook;
		setUpPanel();
	}
	
	
	/*	refresh the screen
	 * 
	 */
	public void refresh() {
		classContents.refreshClass();
		
		searchPanel.refresh();
		
		this.revalidate();
		this.repaint();
	}
	
	
	/*	Set up the initial panel
	 * 
	 */
	private void setUpPanel() {
		//initial setup of main panel components
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		setupSearchPanel();
		setupContentPanel();
		
		//use a split panel to store search and content panels
		JSplitPane overall = new JSplitPane
				(JSplitPane.HORIZONTAL_SPLIT, searchPanel, contentPanel);
		overall.setResizeWeight(0.2);
		
		//add the split panel
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		c.gridwidth = 3;
		c.weighty = 1;
		c.weightx = 1;
		this.add(overall, c);	
	}
	
	
	/*	set up the left hand side search panel
	 * 
	 */
	private void setupSearchPanel() {
		searchPanel = new FilterPanel(mB, this);
		searchPanel.setMinimumSize(searchPanelMinimumSize);
	}
	
	
	
	


	/*	set up the right hand side content panel
	 * 
	 */
	private void setupContentPanel() {
		contentPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		contentPanel.setMinimumSize(contentPanelMinimumSize);
		
		//!debug; setup button constraints
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		c.weighty = 0.95;
		
		
		ArrayList<Subject_Class> allClasses = mB.getClasses();
		classContents = new ClassDisplay(allClasses, mB, searchPanel.getSelectedPanel());
		
		
		JScrollPane contentScroll = new JScrollPane(classContents);
		contentScroll.getVerticalScrollBar().setUnitIncrement(16);
		contentPanel.add(contentScroll, c);
		
		//panel for creating new classes
		AddClassPanel classCreationPanel = new AddClassPanel(mB);
		
		//newClassButton.addActionListener(actionListener);
		//add to interface
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		c.weighty = 0.05;
		contentPanel.add(classCreationPanel, c);
	}
	
}