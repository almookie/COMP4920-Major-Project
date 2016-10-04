package main;

public class Student {
	private int ID;
	private String givenName, surname;
	
	public Student(int ID, String givenName, String surname) {
		this.ID = ID;
		this.givenName= givenName;
		this.surname = surname;
	}
	
	public String getGivenName() {
		return givenName;
	}
	
	public String getSurname() { 
		return surname;
	}
	
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
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
	
	public String toString() {
		return "Student Name: " + givenName + " " + surname;
	}
}