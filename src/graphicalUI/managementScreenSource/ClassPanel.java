package graphicalUI.managementScreenSource;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.Markbook;
import main.Student;
import main.Subject_Class;


/*	Collapsible panel which stores studentPanels 
 * 	and handles interaction with whole classes
 */
public class ClassPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public boolean isCollapsed = false;
	
	ClassDisplay parent;
	StudentFilterSelected selectedStudents;
	JPanel self = this;
	
	//for class deletioon confirmation box
	private static String messageTitle = "Confirm Deletion";
	private static String confirmationMessage = "Are you sure you wish to delete this class?";

	Subject_Class thisClass;
	Markbook mB;
	
	//two panels that make up the collapsible panel
	JPanel mainPanel;
	JPanel collapsiblePanel;
	JPanel contentPanel;
	JPanel buttonPanel;
	
	JPanel classBuffer;
	
	//text on button to hide/show panel
	JButton hidePanelButton;
	JLabel hideName1;
	JLabel hideName2;
	String hideText1 = "Hide";
	String hideText2 = "Contents";
	String showText1 = "Show";
	String showText2 = "Contents";
	
	JCheckBox confirmationCheck;
	
	private static Dimension bufferHeight = new Dimension(130, 25);
	
	private Color backgroundDefault = Color.WHITE;
	private Color titleFrameBackground = Color.LIGHT_GRAY;
	
	private Color backgroundHighlighted = new Color(220, 220, 220);
	private Color titleFrameHighlighted = new Color(160, 160, 160);
	
	private ArrayList<StudentPanel> allStudentPanels;
	
	//private Font nameFont = new Font("Helvetica", Font.BOLD, 16);
	/*	default constructor
	 * 
	 */
	public ClassPanel(Subject_Class newClass,
			Markbook newmB, ClassDisplay newParentDisplay, 
			StudentFilterSelected newSelectedStudents, JCheckBox newConfirmationCheck) {
		thisClass = newClass;
		mB = newmB;
		parent = newParentDisplay;
		selectedStudents = newSelectedStudents;
		allStudentPanels = new ArrayList<StudentPanel>();
		confirmationCheck = newConfirmationCheck;
		
		setupGraphical();
		setupCollapsible();
		setupHover();
	}
	
	
	/*	constructor to initiate with an ArrayList of Student
	 * 
	 */
	public ClassPanel(ArrayList<Student> newStudents,
			Subject_Class newClass, Markbook newmB, ClassDisplay newParentDisplay,
			StudentFilterSelected newSelectedStudents, JCheckBox newConfirmationCheck) {
		thisClass = newClass;
		mB = newmB;
		parent = newParentDisplay;
		selectedStudents = newSelectedStudents;
		allStudentPanels = new ArrayList<StudentPanel>();
		confirmationCheck = newConfirmationCheck;
		
		setupGraphical();
		setupCollapsible();
		
		refreshClass(newStudents);
		setupHover();
	}
	
	
	/*	refresh students in class with new list of students
	 * 
	 */
	public void refreshClass(ArrayList<Student> newStudents) {
		//clear all students
		contentPanel.removeAll();
		allStudentPanels.clear();
		
		ClassPanelRow currentRow = new ClassPanelRow();
		contentPanel.add(currentRow);
		
		//add new students to display
		for (Student thisStudent : newStudents) {
			StudentPanel newPanel = new StudentPanel(thisStudent, this);
			allStudentPanels.add(newPanel);
			contentPanel.add(newPanel);
			/*
			if (!currentRow.isFull()) {
				currentRow.addItem(newPanel);
			}  else {
				currentRow = new ClassPanelRow();
				contentPanel.add(currentRow);
				currentRow.addItem(newPanel);
			}
			*/
			
			
		}
		
		this.revalidate();
		this.repaint();
	}
	
	/*	refresh students in class with backend data
	 * 
	 */
	public void refreshClass() {
		refreshClass(thisClass.getStudents());
	}
	
	
	/*	add a student to the class
	 * 
	 */
	public void addStudent(Student newStudent) {
		thisClass.addStudent(newStudent);
	}
	
	
	public void removeStudent(Student newStudent) {
		thisClass.removeStudent(newStudent);
	}
	
	
	/*	set up the display elements
	 * 
	 */
	private void setupGraphical() {
		//use gridbag format
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//setup main panel
		mainPanel = new JPanel(new GridBagLayout());
		//display label with class name
		String className = mB.getLongName(thisClass);
		JLabel displayName = new JLabel(className);
		
		//set background colors for mainPanel
		displayName.setOpaque(false);
		mainPanel.setOpaque(true);
		mainPanel.setBackground(titleFrameBackground);
		
		//buffer panel
		JPanel buffer = new JPanel();
		buffer.setOpaque(false);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 0.6;
		mainPanel.add(buffer, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.05;
		mainPanel.add(displayName, c);
		//add buttons to mainPanel
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setOpaque(false);
		
		//hide/show panel button
		c.anchor = GridBagConstraints.WEST;
		
		//button to add student
		JButton addStudentButton = new JButton();
		addStudentButton.setLayout(new BorderLayout());
		JLabel name1 = new JLabel("Add Selected");
		JLabel name2 = new JLabel("Students");
		addStudentButton.add(BorderLayout.NORTH, name1);
		addStudentButton.add(BorderLayout.SOUTH, name2);
		addStudentButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		buttonPanel.add(addStudentButton);
		addStudentButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Student> allStudents = selectedStudents.getSelectedStudents();
				//check if a student has not been added
				boolean unaddedStudent = false;
				
				for (Student student : allStudents) {
					boolean isAdded = mB.addStudent(thisClass, student);
					if (!isAdded) {
						unaddedStudent = true;
					}
				}
				refreshClass();
			}
			
		});
		
		addStudentButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setHighlight();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				removeHighlight();
			}
		});
		
		//button to delete class
		JButton deleteClassButton = new JButton();
		deleteClassButton.setLayout(new BorderLayout());
		JLabel className1 = new JLabel("Delete");
		JLabel className2 = new JLabel("Class");
		deleteClassButton.add(BorderLayout.NORTH, className1);
		deleteClassButton.add(BorderLayout.SOUTH, className2);
		deleteClassButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		deleteClassButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (!confirmationCheck.isSelected()) {
					//with confirmation popup
					int result = JOptionPane.showConfirmDialog
							(self, confirmationMessage, messageTitle, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if (result == JOptionPane.YES_OPTION) {
						mB.deleteClass(thisClass.getSubject(), thisClass);
						parent.refreshClass();
					}
				} else {
					//without confirmation popup
					mB.deleteClass(thisClass.getSubject(), thisClass);
					parent.refreshClass();
				}
			}
			
		});
		
		deleteClassButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setHighlight();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				removeHighlight();
			}
		});
		
		buttonPanel.add(deleteClassButton);
		
		JPanel hideShowLocation = new JPanel(new GridBagLayout());
		hideShowLocation.setOpaque(false);
		buttonPanel.add(hideShowLocation);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		c.gridwidth = 3;
		c.weighty = 1;
		c.weightx = 1;
		
		hidePanelButton = new JButton();
		hidePanelButton.setLayout(new BorderLayout());
		hideName1 = new JLabel(hideText1);
		hideName2 = new JLabel(hideText2);
		hideName1.setOpaque(false);
		hideName2.setOpaque(false);
		hidePanelButton.add(BorderLayout.NORTH, hideName1);
		hidePanelButton.add(BorderLayout.SOUTH, hideName2);
		hidePanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		hideShowLocation.add(hidePanelButton, c);
		
		//add button panel to mainPanel
		c.gridx = 2;
		c.gridwidth = 1;
		c.weightx = 0.4;
		mainPanel.add(buttonPanel, c);
		
		c.anchor = GridBagConstraints.CENTER;
		
		//setup collapsible panel
		collapsiblePanel = new JPanel(new GridBagLayout());
		contentPanel = new JPanel();
		contentPanel.setLayout(new WrapLayout());
		c.fill = GridBagConstraints.BOTH;
		collapsiblePanel.add(contentPanel, c);
		contentPanel.setOpaque(true);
		contentPanel.setBackground(backgroundDefault);
		
		this.setOpaque(true);
		this.setBackground(backgroundDefault);
		
		//create a buffer panel for spacing
		classBuffer = new JPanel();
		classBuffer.setPreferredSize(bufferHeight);
		classBuffer.setBackground(backgroundDefault);
		
		//add to components to display
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.15;
		c.weightx = 1;
		this.add(mainPanel, c);
		c.weighty = 0.65;
		c.gridy = 1;
		this.add(collapsiblePanel, c);
		c.gridy = 2;
		c.weighty = 0.20;
		this.add(classBuffer, c);
		
	}

	
	/*	collapses panel if open, opens panel if collapsed
	 * 
	 */
	public void changeCollapseStatus() {
		if (isCollapsed) {
			//open the panel
			collapsiblePanel.setVisible(true);
			hideName1.setText(hideText1);
			hideName2.setText(hideText2);
			isCollapsed = false;
			
		} else {
			//close the panel
			collapsiblePanel.setVisible(false);
			hideName1.setText(showText1);
			hideName2.setText(showText2);
			isCollapsed = true;
		}
	}
	
	
	/*	highlights the panel
	 * 
	 */
	public void setHighlight() {
		contentPanel.setBackground(backgroundHighlighted);
		mainPanel.setBackground(titleFrameHighlighted);
		classBuffer.setBackground(backgroundHighlighted);
		
		for (StudentPanel student : allStudentPanels) {
			student.setHighlight();
		}
	}
	
	
	/*	cancels highlight on panel if there is one
	 * 
	 */
	public void removeHighlight() {
		contentPanel.setBackground(backgroundDefault);
		mainPanel.setBackground(titleFrameBackground);
		classBuffer.setBackground(backgroundDefault);
		
		for (StudentPanel student : allStudentPanels) {
			student.clearHighlight();
		}
	}
	
	
	/*	sets up the listeners for the collapsing of the panel 
	 * 
	 */
	private void setupCollapsible() {
		mainPanel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				changeCollapseStatus();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setHighlight();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				removeHighlight();
			}
		});
		
		hidePanelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changeCollapseStatus();
			}
			
		});
	}
	
	
	/*	sets up listeners for color change on hover
	 * 
	 */
	private void setupHover() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setHighlight();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				removeHighlight();
			}
		});
		
		
		hidePanelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setHighlight();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				removeHighlight();
			}
		});
	}
	
}
