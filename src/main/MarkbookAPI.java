package main;


import java.util.ArrayList;
import java.util.HashMap;
import main.*;


public interface MarkbookAPI {
	
/*************************************************
				GRADE
*************************************************/
	/***********************************
	* ADD Grade
	* @param currGrade current Grade
	* @param gradYear graduating year
	* @return boolean success or failed
	************************************/
	public boolean addGrade(int currGrade, int gradYear);
	
	
	/***********************************
	 * REMOVE Grade
	 * @param g Grade Object
	 * @return boolean success or failed
	 ***********************************/
	public boolean removeGrade(Grade g);
	
	
	/***********************************
	 * GET ArrayList of Grade in School
	 * @return ArrayList of Grade
	 ***********************************/
	public ArrayList<Grade> getGradeList();
	
	
	
/*************************************************
				SUBJECT
*************************************************/
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
	
	
	
/*************************************************
				CLASS
*************************************************/
	/***********************************
	* ADD Class
	* @param classID
	* @param s Subject Object
	* @param g Grade Object
	* @return boolean success or failed
	************************************/
	public boolean addClass(int classID, Subject s, Grade g);
	
	
	/***********************************
	* REMOVE Class
	* @param c Class Object
	* @param s Subject Object
	* @return boolean success or failed
	************************************/
	public boolean removeClass(Class c, Subject s);
	

	/***********************************
	* GET HashMap of Class and Grade given Subject s
	* @param s Subject Object
	* @return HashMap<Class, Grade>
	************************************/
	public HashMap<Class, Grade> getClassList(Subject s);
	
	
	
/*************************************************
				STUDENT
*************************************************/
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
	
	
/*************************************************
				ASSESSMENT
*************************************************/
	/***********************************
	* ADD Assessment from Class
	* @param aName assessment name
	* @param maxMark maximum possible mark
	* @param weighing weight of assessment
	* @param c Class Object
	* @return boolean success or failed
	************************************/
	public boolean addAssessment(String aName, Double maxMark, Double weighing, Class c);
	

	/***********************************
	* REMOVE Assessment from Class
	* @param a Assessment Object
	* @param c Class Object
	* @return boolean success or failed
	************************************/
	public boolean removeAssessment(Assessment a, Class c);
	

	/***********************************
	* GET ArrayList of Assessments in Class
	* @param c Class Object
	* @return ArrayList<Assessment>
	************************************/
	public ArrayList<Assessment> getClassAssessmentList(Class c);
	
	
}
