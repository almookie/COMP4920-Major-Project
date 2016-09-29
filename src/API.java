import java.util.ArrayList;
import java.util.HashMap;

public interface API {

	//	Here is a brief documentation on the API for GUI
	//	Hopefully it will make it easier
	//	Let me know if you guys have any ideas
	// 	or feedback for these API (its my first time making one)
	//	Also created UML diagram thats updated with backend
	//	The fields for each object can be found there
	
	
	/***************************************
			GRADE
	 ***************************************/
	
	//	Adds Grade to the gradeList in Markbook.java
	public void addGrade(int currGrade, int gradYear);
	
	//	Removes Grade from the gradeList in Markbook.java
	public void removeGrade(Grade g);
	
	//	Get	gradeList
	public ArrayList<Grade> getGradeList();
	
	//	Get All Enrolled Students
	public ArrayList<Student> getAllStudents();
	
	//	Get Students from a Specific Grade
	public ArrayList<Student> getStudentsfromGrade(Grade g);

	
	/***************************************
			SUBJECT
	 ***************************************/
	
	//	Add a Subject to a given Grade
	public void addSubject(String sName, String sCode, Grade g);
	
	//	Removes a Subject from a given Grade
	public void removeSubject(Subject s, Grade g);
	
	//	Get Subject List from a grade
	public ArrayList<Subject> getSubjectList(Grade g);
	
	//	Get All Students taking the Subject
	public ArrayList<Student> getAllSubjStudents(Subject s);
	
	//	Get Students taking the Subject in a Specific Grade
	public ArrayList<Student> getSubjStudentsFromGrade(Subject s, Grade g);
	
	
	/***************************************
			CLASS
	 ***************************************/
	
	//	Add Class to Subject in given Grade
	public void addClass(int classID, Subject s, Grade g);
	
	//	Remove Class from Subject in given Grade
	public void removeClass(Class c, Subject s);
	
	//	Get Class List in given Subject in Grade
	public HashMap<Class, Grade> getClassList(Subject s);
	
	//	Get List of Students in the Class
	public ArrayList<Student> getClassStudentList(Class c);
	
	
	/***************************************
			STUDENT
	 ***************************************/
	
	//	Add Student to the given Grade
	public void addStudent(int stuID, String name, String sName, Grade g);
	
	//	Remove Student from the given Grade
	public void removeStudent(Student stu);
	
	//	Enrol Student to given Class
	public void enrolStudent(Student s, Class c);
	
	//	Unenrol Student from given Class
	public void unenrolStudent(Student s, Class c);
	
	//	Get Student's Assessments
	public HashMap<Assessment, Class> getAssessmentList(Student s);
	
	//	Get Student's Classes
	public HashMap<Class, Subject> getClassList(Student s);
	
	//	Get Students Subjects
	public ArrayList<Subject> getStuSubjList(Student s);
	
	
	/***************************************
			ASSESSMENT
	 ***************************************/
	
	//	Add Assessment to the Class
	public void addAssessment(String aName, Double maxMark, Double weighing, Class c);
	
	//	Remove Assessment from the Class
	public void removeAssessment(Assessment a, Class c);
	
	//	Get a List of Assessments in the Class
	public ArrayList<Assessment> getClassAssessmentList(Class c);
	
/*	//	Statistics
	public void getMarkMap();
	public void getMean();
	public void getMedian();
	public void getStdDev();
	public void getMode();
	public void getRange();
	public void getWeighting();*/
}
