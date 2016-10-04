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
	public void _addStudent(Student stu) { studentList.add(stu); }
	public void _removeStudent(Student stu) { studentList.remove(stu); }
	
	//	ASSESSMENT
	public void _addAssessment(String name, double maxMark, double weight) { assessmentList.add(new Assessment(name, maxMark, weight)); }
	public void _removeAssessment(Assessment a, Class c) { assessmentList.remove(a); }
	
	//	GETS
	public int _getClassID() { return classID; }
	public Grade _getGrade() { return grade; }
	public ArrayList<Student> _getStudentList() { return studentList; }
	public ArrayList<Assessment> _getAssessmentList() { return assessmentList; }

	//	SETS
	public void _setClassID(int classID) { this.classID = classID; }
	public void _setGrade(Grade grade) { this.grade = grade; }
	
	
	/*
	public boolean hasStudent(Student student) {
		return (students.contains(student));
	}*/
	
}
