/***************************************
			IMPORTS
***************************************/
import java.util.ArrayList;


/***************************************
			SUBJECT
***************************************/
public interface SubjectAPI {
	
	
	/***********************************
	* ADD Subject
	* @param sName Subject Name
	* @param sCode Subject Code
	* @param g Grade Object
	* @return boolean success or failed
	************************************/
	public boolean addSubject(String sName, String sCode, Grade g);
	
	
	/***********************************
	* REMOVE Subject
	* @param s Subject Object
	* @param g Grade Object
	* @return boolean success or failed
	************************************/
	public boolean removeSubject(Subject s, Grade g);
	
	
	/***********************************
	* GET ArrayList of Subjects in a grade
	* @param g Grade Object
	* @return ArrayList of Subjects
	************************************/
	public ArrayList<Subject> getSubjectList(Grade g);
	
	
	//	Other methods to be included...
}
