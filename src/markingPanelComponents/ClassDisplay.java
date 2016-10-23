package markingPanelComponents;

import graphicalUI.markingPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import main.Assessment;
import main.Markbook;
import main.Student;
import main.Subject_Class;

//TODO setup an on click action listener for collapsible panel funnctionality

/*	Collapsible panel which stores studentPanels 
 * 	and handles interaction with whole classes
 */
public class ClassDisplay extends JPanel {

	private static final long serialVersionUID = 1L;

	//ArrayList of all StudentPanels stored
	//ArrayList<Assements> allStudents;
	Subject_Class thisClass;
	Markbook mB;
	markingPanel markingPanel;
	//two panels that make up the collapsible panel
	JPanel overall;
	JPanel mainPanel;
	JPanel contentPanel;
	String AssesmentFilter;

	String NameFilter;

	
	public ClassDisplay(markingPanel markingPanel,Subject_Class newClass, Markbook newmB, String AssesmentFilter) {
		//allStudents = new ArrayList<StudentPanel>();
		thisClass = newClass;
		mB = newmB;
		this.markingPanel = markingPanel;
		this.AssesmentFilter = AssesmentFilter;
		setupGraphical();
	}
	
	public ClassDisplay(markingPanel markingPanel,Subject_Class newClass, Markbook newmB, String AssesmentFilter, String NameRegex) {
		//allStudents = new ArrayList<StudentPanel>();
		thisClass = newClass;
		mB = newmB;
		this.AssesmentFilter = AssesmentFilter;
		this.NameFilter = NameRegex;
		this.markingPanel = markingPanel;


		setupGraphical();
	}
	
	
	/*	constructor to initiate with an ArrayList of Student
	 * 
	 */
	public ClassDisplay(markingPanel markingPanel,ArrayList<Student> newStudents, Subject_Class newClass, Markbook newmB) {
		//allStudents = new ArrayList<StudentPanel>();
		thisClass = newClass;
		mB = newmB;
		this.markingPanel = markingPanel;

		setupGraphical();
		
	}
	
	

	
	// TODO Auto-generated constructor stub
/*	refresh students in class with new list of students
	 * 
	 */
	public void refreshClass(ArrayList<Student> newStudents) {
		//clear all students
		contentPanel.removeAll();
		//allStudents.clear();
		
//		//add new students
//		for (Student thisStudent : newStudents) {
//			addStudent(thisStudent);
//		}
	}
	

	
	/*	set up the display elements
	 * 
	 */
	private void setupGraphical() {
		//use gridbag format
		
		final CardLayout cardLayout = new CardLayout();
		overall = new JPanel(cardLayout);
	

		//setup main panel
		mainPanel = new JPanel(new GridBagLayout());

		JButton addAssesment = new JButton("Add Assesment");
		GridBagConstraints c1 = new GridBagConstraints();
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;

	  	JButton swap = new JButton("Swap");
	  	swap.setPreferredSize(new Dimension(50,30));
		
		
		JPanel newAssesment = new JPanel();
		JButton cancel = new JButton("Cancel");
	  	cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(overall, "mP");
            }
        });
	  	newAssesment.add(cancel);
	  	
	    newAssesment.add(Box.createRigidArea(new Dimension(5,70)));

	  	newAssesment.setLayout(new BoxLayout(newAssesment, BoxLayout.Y_AXIS));
	  	newAssesment.add(new JLabel("AssesmentName"));
	    final JTextField assName = new JTextField();
	    assName.setMaximumSize(new Dimension(1000,50));
	    newAssesment.add(assName);
	    newAssesment.add(Box.createRigidArea(new Dimension(5,70)));

	  	newAssesment.add(new JLabel("AssesmentWeighting"));
	    final JTextField assWeighting = new JTextField();
	    
	    assWeighting.setMaximumSize(new Dimension(1000,50));

	    newAssesment.add(assWeighting);
	    newAssesment.add(Box.createRigidArea(new Dimension(5,50)));



	  	final JPanel assesments = new JPanel();
	     final CardLayout newcardLayout = new CardLayout();
	  	assesments.setLayout(newcardLayout);
		for(Assessment a: thisClass.getAssessments()){

			//System.out.println("CONTAINT" + a.getName() );
			
			if(AssesmentFilter!=null ){ 
				
				if(a.getName().contains(AssesmentFilter) ){

					AssesmentDisplay aD = new AssesmentDisplay(markingPanel, a,mB,thisClass,NameFilter);
					assesments.add(aD,a.getName());				
				}else if(AssesmentFilter.equals("")){

					AssesmentDisplay aD = new AssesmentDisplay(markingPanel, a,mB,thisClass,NameFilter);

					assesments.add(aD,a.getName());
				}
			
			}else{

				AssesmentDisplay aD = new AssesmentDisplay(markingPanel, 
						a,
						mB,
						thisClass,
						NameFilter);
				assesments.add(aD,a.getName());
			}
		}
	  	JButton create = new JButton("Create");
	  	create.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	AssesmentDisplay aD =null;
            	for(Subject_Class c: mB.getClasses()){
            		if(c.equals(thisClass)){
      					aD = new AssesmentDisplay(markingPanel, mB.createAssessment(thisClass,assName.getText(), Double.parseDouble(assWeighting.getText())),mB,thisClass,null);
      					

                                                           
            		}
            	}
            	
            	markingPanel.setMyMb(mB);
            	markingPanel.refreshClasses(null, null, null, mB.getClasses());
            	System.out.print("OTHERPANEL");
            	assesments.add(aD,aD.getName());
                cardLayout.show(overall, "mP");
            }
        });
	  	newAssesment.add(create);
	  	  	
	  	
		swap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	newcardLayout.next(assesments);
            }
        });


		mainPanel.add(assesments,c);

		c.gridx=0;
		c.anchor = GridBagConstraints.SOUTH;
		
		JPanel navi = new JPanel(new BorderLayout()); 
		JButton prevIter = new JButton("<-- Previous Assessment");
		JButton nextIter = new JButton("    Next Assessment -->");



		navi.add(prevIter,BorderLayout.WEST);

		navi.add(nextIter,BorderLayout.EAST);
		
		prevIter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	newcardLayout.previous(assesments);
            }
        });
		
		nextIter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	newcardLayout.next(assesments);
            }
        });

		mainPanel.add(navi,c);
		
		mainPanel.add(addAssesment,c);

	
		overall.add(mainPanel,"mP");

	  	
		overall.add(newAssesment,"nA");
		
		addAssesment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
            	markingPanel.setMyMb(mB);
            	markingPanel.refreshClasses(null, null, null, mB.getClasses());

                cardLayout.show(overall, "nA");

            }
        });
		
		this.add(overall);

		
	}

	
}
