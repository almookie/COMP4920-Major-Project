package graphicalUI.managementScreenSource;

import main.Grade;
import main.Markbook;

/*	Stores a Grade item in a format suitable for commboboxes
 * 
 */
public class GradeComboBoxHolder {
	Grade grade;
	Markbook mB;
	
	
	/*	default constructor
	 * 
	 */
	public GradeComboBoxHolder(Grade newGrade, Markbook newmB) {
		grade = newGrade;
		mB = newmB;
	}
	
	
	/*	get the stored Grade item
	 * 
	 */
	public Grade getGrade() {
		return grade;
	}
	
	
	/*	display name for stored Grade
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return String.valueOf(grade.getYear(mB.getCurrentYear()));
	}
}
