package graphicalUI.managementScreenSource;

import main.Markbook;
import main.Subject;

public class SubjectComboBoxHolder {
	Subject subject;
	Markbook mB;
	
	
	/*	default constructor
	 * 
	 */
	public SubjectComboBoxHolder(Subject newSubject, Markbook newmB) {
		subject = newSubject;
		mB = newmB;
	}
	
	
	 /*	get the stored Subject item
	 * 
	 */
	public Subject getSubject() {
		return subject;
	}
	
	
	/*	display name for stored Subject
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String SubjectName = subject.getShortcode() + ": " + subject.getName();
		return SubjectName;
	}
}
