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
	private ArrayList<Student> studentList;
	private HashMap<Class, Grade> classMap;
	
	
	/***************************************
			CONSTRUCTOR
	 ***************************************/
	public Subject(String name, String sCode, Grade g) {
		this.name = name;
		this.shortcode = sCode;
		this.gradeList = new ArrayList<Grade>();
		this.studentList = new ArrayList<Student>();
		this.classMap = new HashMap<Class, Grade>();	
	}
	
	
	/***************************************
			METHODS
	 ***************************************/
	// 	CLASS
	public void addClass(int classID, Grade g) { 
		this.classMap.put(new Class(classID, g), g);
	}
	public void removeClass(Class c) { this.classMap.remove(c); }
	
	//	STUDENT
	public void addStudent(Student stu) { this.studentList.add(stu); }
	public void removeStudent(Student stu) { this.studentList.remove(stu); }
	
	//	GRADE
	public void addGrade(Grade g) { this.gradeList.add(g); }
	public void removeGrade(Grade g) { this.gradeList.remove(g); }
	
	//	GETS
	public ArrayList<Student> getStudentList() { return this.studentList; }
	public HashMap<Class, Grade> getClassMap() { return this.classMap; }
	
	public String getName() { return this.name; }
	public String getShortcode() { return this.shortcode; }
	public ArrayList<Grade> getGradeList() { return this.gradeList; }
	
	//	SETS
	public void setName(String name) { this.name = name; }
	public void setShortcode(String shortcode) { this.shortcode = shortcode; }
	
	
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