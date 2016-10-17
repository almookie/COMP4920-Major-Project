package graphicalUI.managementScreenSource;

import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import main.Markbook;
import main.Student;

/*	panel for searching and selecting students
 * 
 */
public class StudentFilterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	ArrayList<Student> selectedStudents;
	Markbook mB;
	
	/*	default constructor
	 * 
	 */
	public StudentFilterPanel(Markbook newmB) {
		mB = newmB;
		selectedStudents = new ArrayList<Student>();
		
		setupGraphical();
	}
	
	private void setupGraphical() {
		this.setLayout(new GridBagLayout());
		
		
	}
}
