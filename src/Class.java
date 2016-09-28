/***************************************
		IMPORTS
***************************************/
import java.util.ArrayList;


public class Class {

	/***************************************
			FIELDS
	 ***************************************/
	private int classID;
	private ArrayList<Student> students; 
	private ArrayList<Assessment> assessments;
	private Grade grade;
	
	
	/***************************************
			CONSTRUCTOR
	 ***************************************/
	public Class(int classID, Grade grade) {
		this.classID = classID;
		this.grade = grade;
		students = new ArrayList<Student>();
		assessments = new ArrayList<Assessment>();
	}
	
	
	/***************************************
			METHODS
	 ***************************************/
	//	ADD
	public void addStudent(Student student) {
		students.add(student);
	}
	public void addMark(Assessment assessment, Student student, double mark) {
		assessment.addMark(student, mark);
	}
	
	//	REMOVE
	public void removeStudent(Student student) {
		students.remove(student);
	}
	
	//	HAS
	public boolean hasStudent(Student student) {
		return (students.contains(student));
	}
	
	//	GETS
	public int getClassID() { return this.classID; }
	public Grade getGrade() { return this.grade; }

	//	SETS
	public void setClassID(int classID) { this.classID = classID; }
	public void setGrade(Grade grade) { this.grade = grade; }
	
}
