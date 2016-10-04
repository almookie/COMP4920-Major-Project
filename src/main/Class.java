package main;

import java.util.ArrayList;

public class Class {
	private ArrayList<Student> students; 
	private ArrayList<Assessment> assessments;
	private Grade grade;
	
	public Class(Grade grade) {
		this.grade = grade;
		students = new ArrayList<Student>();
		assessments = new ArrayList<Assessment>();
	}
	
	public boolean hasStudent(Student student) {
		return (students.contains(student));
	}
	
	public void addStudent(Student student) {
		students.add(student);
	}
	
	public void removeStudent(Student student) {
		students.remove(student);
	}
	
	public Grade getGrade() { 
		return grade;
	}
	
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	public void addMark(Assessment assessment, Student student, double mark) {
		assessment.addMark(student, mark);
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void addAssessment(Assessment a) {
		assessments.add(a);		
	}
	
	public ArrayList<Assessment> getAssessments() {
		return assessments;
	}
}