/***************************************
		IMPORTS
***************************************/
import java.util.ArrayList;


public class Grade {

	
	/***************************************
			FIELDS
	 ***************************************/
	private ArrayList<Student> students;
	private int grade;
	private int graduationYear;
	
	
	/***************************************
			CONSTRUCTOR
	 ***************************************/
	public Grade(int graduationYear, int grade) {
		students = new ArrayList<Student>();
		this.grade = grade;
		this.graduationYear = graduationYear;
	}
	
	
	/***************************************
			METHODS
	 ***************************************/
	//	ADD
	public void addStudent(Student student) {
		 students.add(student);
	}
	
	//	REMOVE
	public void removeStudent(Student student) {
		students.remove(student);
	}
	
	//	HAS
	public boolean hasStudent(int ID) {
		for (Student student : students) {
			if (student.getID() == ID) return true;
		}
		return false;
	}
	
	//	GETS
	public Student getStudent(int ID) {
		for (Student student : students) {
			if (student.getID() == ID) return student;
		}
		return null;
	}
	public int getGrade() { return this.grade; }
	public int getGraduationYear() { return this.graduationYear; }
	
	public int getStudentCount() { return students.size(); }
	
	//	SETS
	public void setGrade(int grade) { this.grade = grade; }
	public void setGraduationYear(int graduationYear) { this.graduationYear = graduationYear; }

}