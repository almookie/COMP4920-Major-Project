/***************************************
			IMPORTS
***************************************/
import java.util.HashMap;


/***************************************
			STUDENT
***************************************/
public interface StudentAPI {
	
	
	/***********************************
	* ADD Student to School
	* @param stuID student ID
	* @param name first name
	* @param sName surname
	* @param g Grade Object of current grade
	* @return boolean success or failed
	************************************/
	public boolean addStudent(int stuID, String name, String sName, Grade g);
	
	
	/***********************************
	* REMOVE Student from School
	* @param stu Student Object
	* @return boolean success or failed
	************************************/
	public boolean removeStudent(Student stu);
	

	/***********************************
	* ENROL Student into a class
	* @param stu Student Object
	* @param c Class Object
	* @return boolean success or failed
	************************************/
	public boolean enrolStudent(Student stu, Class c);
	

	/***********************************
	* UNENROL Student from a class
	* @param stu Student Object
	* @param s Subject Object
	* @param c Class Object
	* @return boolean success or failed
	************************************/
	public boolean unenrolStudent(Student stu, Class c);


	/***********************************
	* GET HashMap of Student's Classes and Subjects
	* @param stu Student
	* @param s Subject Object
	* @param g Grade Object
	* @return HashMap<Class, Subject>
	************************************/
	public HashMap<Class, Subject> getClassList(Student stu);
	
	
	/***********************************
	* GET HashMap of Student's Assessments and Classes
	* @param stu Student Object
	* @return HashMap<Assessment, Class>
	************************************/
	public HashMap<Assessment, Class> getAssessmentList(Student stu);
	
	
	//	Other methods to be included...
}
