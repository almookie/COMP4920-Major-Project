/***************************************
			IMPORTS
 ***************************************/

import java.util.ArrayList;
import java.util.HashMap;


public class Subject {
	
	/***************************************
			FIELDS
	 ***************************************/
	
	private String name;
	private String shortcode;
	private ArrayList<Grade> gradeList;
	private HashMap<Class, Grade> classGradeMap;
	private ArrayList<Student> studentList;
	
	
	/***************************************
			CONSTRUCTOR
	 ***************************************/
	
	public Subject(String name, String sCode, Grade g) {
		this.name = name;
		this.shortcode = sCode;
		this.gradeList = new ArrayList<Grade>();
		this.classGradeMap = new HashMap<Class, Grade>();	
		this.studentList = new ArrayList<Student>();
	}
	
	
	/***************************************
			METHODS
	 ***************************************/
	
	//	GRADE
	public void _addGrade(Grade g) { gradeList.add(g); }
	public void _removeGrade(Grade g) { gradeList.remove(g); }
	
	// 	CLASS
	public void _addClass(int classID, Grade g) { classGradeMap.put(new Class(classID, g), g); }
	public void _removeClass(Class c) { classGradeMap.remove(c); }
	
	//	STUDENT
	public void _addStudent(Student stu) { studentList.add(stu); }
	public void _removeStudent(Student stu) { studentList.remove(stu); }
	
	//	GETS
	public String _getName() { return name; }
	public String _getShortcode() { return shortcode; }
	public ArrayList<Grade> _getGradeList() { return gradeList; }
	public HashMap<Class, Grade> _getClassGradeMap() { return classGradeMap; }
	public ArrayList<Student> _getStudentList() { return studentList; }
	
	//	SETS
	public void _setName(String name) { this.name = name; }
	public void _setShortcode(String shortcode) { this.shortcode = shortcode; }
	
	
/*	//	MISC
	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (!(o instanceof Subject)) return false;
		Subject subject = (Subject) o;
		return name.equals(subject.name);
	}
		
	@Override
	public int hashCode() {
		final int result = 17;
		return 37 * result + name.hashCode();
	}	
	
	public String toString() {
		String output = "Name: " + name + "\nShortcode: " + shortcode + "\n";
		for (Class c : classes) {
			output += c.toString();
		}
		return output;
	}
	
	public boolean hasClass(Class check_class) { 
		return classes.contains(check_class);
	}*/

}