import java.util.ArrayList;
import java.util.HashMap;

public class Student {

	/***************************************
			FIELDS
	 ***************************************/
	
	private int ID;
	private String givenName, surname;
	private Grade grade;
	private HashMap<Class, Subject> classMap;
	private HashMap<Assessment, Class> assessmentMap;
	
	
	/***************************************
			CONSTRUCTOR
	 ***************************************/
	
	public Student(int ID, String givenName, String surname, Grade grade) {
		this.ID = ID;
		this.givenName= givenName;
		this.surname = surname;
		this.grade = grade;
		this.classMap = new HashMap<Class, Subject>();
		this.assessmentMap = new HashMap<Assessment, Class>();
	}
	
	/***************************************
			METHODS
	 ***************************************/
	//	CLASSES
	public void addClass(Class c, Subject s) { this.classMap.put(c, s); }
	public void removeClass(Class c) { this.classMap.remove(c); }
	
	//	ASSESSMENT
	public void addAssessment(String aName, Class c) {
		this.assessmentMap.put(new Assessment(aName), c);
	}
	public void removeAssessment(Assessment a) { 
		this.assessmentMap.remove(a); 
	}
	
	//	GETS
	public HashMap<Class, Subject> getClassMap() { return this.classMap; }
	public HashMap<Assessment, Class> getAssMap() { return this.assessmentMap; }
	public int getID() { return ID; }
	public String getGivenName() { return this.givenName; }
	public String getSurname() { return this.surname; }
	public Grade getGrade() { return this.grade; }
	
	//	SETS
	public void setID(int ID) { this.ID = ID; }
	public void setGivenName(String givenName) { this.givenName = givenName; }
	public void setSurname(String surname) { this.surname = surname; }
	public void setGrade(Grade grade) { this.grade = grade; }
	
	/*
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
	*/
}