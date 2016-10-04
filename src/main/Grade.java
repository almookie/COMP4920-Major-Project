package main;

import java.util.ArrayList;

public class Grade {
	private ArrayList<Student> students;
	private int graduationYear;
	
	public Grade(int graduationYear) {
		students = new ArrayList<Student>();
		this.graduationYear = graduationYear;
	}
	
	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}
	
	public int getGraduationYear() {
		return this.graduationYear;
	}
	
	public void addStudent(Student student) {
		 students.add(student);
	}
	
	public void removeStudent(Student student) {
		students.remove(student);
	}
	
	public int getStudentCount() {
		return students.size();
	}
	
	public boolean hasStudent(int ID) {
		for (Student student : students) {
			if (student.getID() == ID) {
				return true;
			}
		}
		
		return false;
	}
	
	public Student getStudent(int ID) {
		for (Student student : students) {
			if (student.getID() == ID) {
				return student;
			}
		}
		
		return null;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}
	
	public int getYear(int year) {
		return 12 - (year - 2016);
	}

}