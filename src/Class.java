/***************************************
		IMPORTS
***************************************/
import java.util.ArrayList;


public class Class {

	/***************************************
			FIELDS
	 ***************************************/
	private int classID;
	private Grade grade;
	private ArrayList<Student> studentList; 
	private ArrayList<Assessment> assessmentList;
	
	
	/***************************************
			CONSTRUCTOR
	 ***************************************/
	public Class(int classID, Grade grade) {
		this.classID = classID;
		this.grade = grade;
		this.studentList = new ArrayList<Student>();
		this.assessmentList = new ArrayList<Assessment>();
	}
	
	
	/***************************************
			METHODS
	 ***************************************/
	
	//	STUDENT
	public void addStudent(Student stu) { studentList.add(stu); }
	public void removeStudent(Student stu) { studentList.remove(stu); }
	
	//	ASSESSMENT
	public void addAssessment(String name) {
		this.assessmentList.add(new Assessment(name));
	}
	public void removeAssessment(Assessment a, Class c) { this.assessmentList.remove(a); }
	
	//	GETS
	public ArrayList<Student> getStudentList() { return this.studentList; }
	public ArrayList<Assessment> getAssessmentList() { return this.assessmentList; }
	
	public int getClassID() { return this.classID; }
	public Grade getGrade() { return this.grade; }

	//	SETS
	public void setClassID(int classID) { this.classID = classID; }
	public void setGrade(Grade grade) { this.grade = grade; }
	
	
	/*
	public boolean hasStudent(Student student) {
		return (students.contains(student));
	}*/
	
}
