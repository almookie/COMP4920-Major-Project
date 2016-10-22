package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Subject {
	private String name;
	private String shortcode;
	private ArrayList<Subject_Class> classes;
	private HashMap<Grade, ArrayList<Subject_Class>> classesWithinGrades;
	
	public Subject(String name, String shortcode) {
		this.name = name;
		this.shortcode = shortcode;
		this.classes = new ArrayList<Subject_Class>();	
		this.classesWithinGrades = new HashMap<Grade, ArrayList<Subject_Class>>();
	}
	
	// creates a new class and returns it
	public Subject_Class addClass(Grade grade) {
		int classNumberCount;
		
		if (classesWithinGrades.get(grade) != null) {
			classNumberCount = classesWithinGrades.get(grade).size() + 1;
		} else {
			classNumberCount = 1;
		}
		Subject_Class c = new Subject_Class(grade, this, classNumberCount);
		classes.add(c);
		if (classesWithinGrades.get(grade) != null) {
			classesWithinGrades.get(grade).add(c);		
		} else {	
			ArrayList<Subject_Class> newClassList = new ArrayList<Subject_Class>();
			newClassList.add(c);
			classesWithinGrades.put(grade, newClassList);
		}
		return c;
	}
	
	public void removeClass(Subject_Class delete_class) {
		classes.remove(delete_class);
		for (Grade g : classesWithinGrades.keySet()) {
			ArrayList<Subject_Class> classesList = classesWithinGrades.get(g);
			if (classesList.remove(delete_class)) {
				continue;
			}
		}
	}
	
	public ArrayList<Subject_Class> getClasses(Grade g) {
		return classesWithinGrades.get(g);
	}
	
	public boolean hasClass(Subject_Class check_class) {
		return classes.contains(check_class);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}
	
	public String getShortcode() {
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
		for (Subject_Class c : classes) {
			output += c.toString() + "\n";
		}
		return output;
	}

	public ArrayList<Subject_Class> getClasses() {
		return classes;
	}
}