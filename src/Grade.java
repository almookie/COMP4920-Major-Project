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
	public void addSubject(Subject s) { this.subjectList.add(s); }
	public void removeSubject(Subject s) { this.subjectList.remove(s); }
	
	//	STUDENT
	public void addStudent(Student stu) { this.studentList.add(stu); }
	public void removeStudent(Student stu) { this.studentList.remove(stu); }
	
	//	GETS
	public ArrayList<Subject> getSubjectList() { return this.subjectList; }
	public ArrayList<Student> getStudentList() { return this.studentList; }
	
	public int getGrade() { return this.grade; }
	public int getGraduationYear() { return this.graduationYear; }

	//	SETS
	public void setGrade(int grade) { this.grade = grade; }
	public void setGraduationYear(int graduationYear) { this.graduationYear = graduationYear; }

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