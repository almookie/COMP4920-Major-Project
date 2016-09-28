import java.util.ArrayList;
import java.util.HashMap;

public class Student {

	/***************************************
			FIELDS
	 ***************************************/
	
	private int ID;
	private String givenName, surname;
	private Grade grade;
	private HashMap<Subject, Class> classes;
	
	
	/***************************************
			CONSTRUCTOR
	 ***************************************/
	
	public Student(int ID, String givenName, String surname, Grade grade) {
		this.ID = ID;
		this.givenName= givenName;
		this.surname = surname;
		this.grade = grade;
		this.classes = new HashMap<Subject, Class>();
	}
	
	/***************************************
			METHODS
	 ***************************************/
	//	ADD
	//	e_class (enrolled class) since class is taken by java
	public void addClass(Subject subject, Class e_class) {
		this.classes.put(subject, e_class);
	}
	
	//	REMOVE
	//	by class
	public void removeClass(Subject subject) {
		this.classes.remove(subject);
	}
	// 	by subject
	public void removeSubject(Class e_class) {
		this.classes.values().remove(e_class);
	}
	
	//	GETS
	public int getID() { return ID; }
	public String getGivenName() { return this.givenName; }
	public String getSurname() { return this.surname; }
	public Grade getGrade() { return this.grade; }
		//	classes
	public HashMap<Subject, Class> getCourses() { return this.classes; }
	public ArrayList<Class> getClasses() { return (ArrayList<Class>)this.classes.values(); } 	//	I think this works?
	public ArrayList<Subject> getSubjects() { return (ArrayList<Subject>)this.classes.keySet(); } 		//	I think this works?
	
	//	SETS
	public void setID(int ID) { this.ID = ID; }
	public void setGivenName(String givenName) { this.givenName = givenName; }
	public void setSurname(String surname) { this.surname = surname; }
	public void setGrade(Grade grade) { this.grade = grade; }
	
	//	MISC
	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (!(o instanceof Student)) return false;
		Student student = (Student) o;
		return ID == student.ID;
	}
		
	@Override
	public int hashCode() {
		final int result = 17;
		return 37 * result + ID;
	}
	
}