package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Subject {
	private String name;
	private String shortcode;
	private ArrayList<Class> classes;
	private HashMap<Grade, ArrayList<Class>> classesWithinGrades;
	
	public Subject(String name, String shortcode) {
		this.name = name;
		this.shortcode = shortcode;
		this.classes = new ArrayList<Class>();	
		this.classesWithinGrades = new HashMap<Grade, ArrayList<Class>>();
	}
	
	// creates a new class and returns it
	public Class addClass(Grade grade) {
		int classNumberCount;
		
		if (classesWithinGrades.get(grade) != null) {
			classNumberCount = classesWithinGrades.get(grade).size() + 1;
		} else {
			classNumberCount = 1;
		}
		Class c = new Class(grade, classNumberCount);
		classes.add(c);
		if (classesWithinGrades.get(grade) == null) {
			ArrayList<Class> newClassList = new ArrayList<Class>();
			newClassList.add(c);
			classesWithinGrades.put(grade, newClassList);
		} else {
			classesWithinGrades.get(grade).add(c);			
		}
		return c;
	}
	
	public void removeClass(Class delete_class) {
		classes.remove(delete_class);
	}
	
	public boolean hasClass(Class check_class) {
		return classes.contains(check_class);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName(String name) {
		return name;
	}
	
	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}
	
	public String getShortcode(String shortcode) {
		return shortcode;
	}
	
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
		String output = "Subject with name: " + name + "\nShortcode: " + shortcode + ", containing the following classes: \n\n";
		for (Class c : classes) {
			output += c.toString() + "\n";
		}
		return output;
	}

	public ArrayList<Class> getClasses() {
		return classes;
	}

}