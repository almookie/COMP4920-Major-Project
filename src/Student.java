/***************************************
			IMPORTS
***************************************/

import java.util.HashMap;


public class Student {

	/***************************************
			FIELDS
	 ***************************************/
	
	private int ID;
	private String givenName, surname;
	private Grade grade;
	private HashMap<Class, Subject> classMap;
	private HashMap<Assessment, Class> assClassMap;
	
	
	/***************************************
			CONSTRUCTOR
	 ***************************************/
	
	public Student(int ID, String givenName, String surname, Grade grade) {
		this.ID = ID;
		this.givenName= givenName;
		this.surname = surname;
		this.grade = grade;
		this.classMap = new HashMap<Class, Subject>();
		this.assClassMap = new HashMap<Assessment, Class>();
	}
	
	
	/***************************************
			METHODS
	 ***************************************/
	
	//	CLASSES
	public void _addClass(Class c, Subject s) { classMap.put(c, s); }
	public void _removeClass(Class c) { classMap.remove(c); }
	
	//	ASSESSMENT
	public void _addAssessment(String aName, double maxMark, double weight, Class c) { assClassMap.put(new Assessment(aName, maxMark, weight), c); }
	public void _removeAssessment(Assessment a) {  assClassMap.remove(a);  }
	
	//	GETS
	public int _getID() { return ID; }
	public String _getGivenName() { return givenName; }
	public String _getSurname() { return surname; }
	public Grade _getGrade() { return grade; }
	public HashMap<Class, Subject> _getClassMap() { return classMap; }
	public HashMap<Assessment, Class> _getAssMap() { return assClassMap; }

	//	SETS
	public void _setID(int ID) { this.ID = ID; }
	public void _setGivenName(String givenName) { this.givenName = givenName; }
	public void _setSurname(String surname) { this.surname = surname; }
	public void _setGrade(Grade grade) { this.grade = grade; }
	
	
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