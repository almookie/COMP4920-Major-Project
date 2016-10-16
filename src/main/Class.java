package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Class {
	private ArrayList<Student> students; 
	private ArrayList<Assessment> assessments;
	private Grade grade;
	private Subject subject;
	private int classNumber;
	
	public Class(Grade grade, Subject subject, int classNumber) {
		this.grade = grade;
		this.subject = subject;
		this.classNumber = classNumber;
		students = new ArrayList<Student>();
		assessments = new ArrayList<Assessment>();
	}
	
	public int getClassNumber() {
		return classNumber;
	}
	
	public Subject getSubject() {
		return subject;
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
	
	public String toString() {
		String returnString = "Class: " + classNumber + "\n";
		returnString += "Students within the class:\n";
		
		for (Student s : students) {
			returnString += s.toString() + "\n";
		}
		
		returnString += "\nClass assessments:\n";	
		
		for (Assessment a : assessments) {		
			returnString += a.toString() + "\n";			
		}
		
		return returnString;
	}
	
	public int getStudentCount() {
		return students.size();
	}
	
	public HashMap<Student, Double> getStudentMarks() {
		HashMap<Student, Double> studentMarks = new HashMap<Student, Double>();
		
		// Initialse the marks map
		for (Student s : students) {
			studentMarks.put(s, 0.0);
		}
		
		// Add the marks for each assessment
		for (Assessment assessment : assessments) {
			HashMap<Student, Double> marks = assessment.getMarks();
			double weighting = assessment.getWeighting();
			
			for(Student s : marks.keySet()) {
				studentMarks.put(s, studentMarks.get(s) + marks.get(s) / weighting);
			}
		}
		
		return studentMarks;
	}
}