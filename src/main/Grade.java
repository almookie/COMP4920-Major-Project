package main;

/***************************************
			IMPORTS
***************************************/

import java.util.ArrayList;


public class Grade {
	
	/***************************************
			FIELDS
	 ***************************************/
	
	private int grade;
	private int graduationYear;
	private ArrayList<Subject> subjectList;
	private ArrayList<Student> studentList;
	
	
	/***************************************
			CONSTRUCTOR
	 ***************************************/
	
	public Grade(int graduationYear, int grade) {
		this.grade = grade;
		this.graduationYear = graduationYear;
		this.subjectList = new ArrayList<Subject>();
		this.studentList = new ArrayList<Student>();
	}
	
	
	/***************************************
			METHODS
	 ***************************************/
	
	//	SUBJECT
	public void _addSubject(Subject s) { subjectList.add(s); }
	public void _removeSubject(Subject s) { subjectList.remove(s); }
	
	//	STUDENT
	public void _addStudent(Student stu) { studentList.add(stu); }
	public void _removeStudent(Student stu) { studentList.remove(stu); }
	
	//	GETS
	public int _getGrade() { return grade; }
	public int _getGraduationYear() { return graduationYear; }
	public ArrayList<Subject> _getSubjectList() { return subjectList; }
	public ArrayList<Student> _getStudentList() { return studentList; }
	
	//	SETS
	public void _setGrade(int grade) { this.grade = grade; }
	public void _setGraduationYear(int graduationYear) { this.graduationYear = graduationYear; }

	
	//	Commenting these for now just testing basics
/*	
 * public int getStudentCount() { return students.size(); }
 * 
 * public boolean hasStudent(int ID) {
		for (Student student : students) {
			if (student.getID() == ID) return true;
		}
		return false;
	}
	
	public Student getStudent(int ID) {
		for (Student student : students) {
			if (student.getID() == ID) return student;
		}
		return null;
	}*/
}