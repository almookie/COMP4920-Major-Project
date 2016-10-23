package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Subject {
	private int id;
	private String name;
	private String shortcode;
	private ArrayList<Subject_Class> classes;
	private HashMap<Grade, ArrayList<Subject_Class>> classesWithinGrades;
	
	public Subject(int id, String name, String shortcode) {
		this.id = id;
		this.name = name;
		this.shortcode = shortcode;
		this.classes = new ArrayList<Subject_Class>();	
		this.classesWithinGrades = new HashMap<Grade, ArrayList<Subject_Class>>();
	}
	
	public int getID() {
		return id;
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

	public void addClass(Subject_Class subject_class) {
		Grade g = subject_class.getGrade();
		this.classes.add(subject_class);
		ArrayList<Subject_Class> gradeClasses = this.classesWithinGrades.get(g);
		if (gradeClasses != null) {
			gradeClasses.add(subject_class);
		} else {
			ArrayList<Subject_Class> newGradeClasses = new ArrayList<Subject_Class>();
			newGradeClasses.add(subject_class);
			this.classesWithinGrades.put(g, newGradeClasses);
		}
	}

	public void removeStudent(Student student) {
		for (Subject_Class c : classes) {
			c.removeStudent(student);
		}
		
	}
}