package main;

import java.util.ArrayList;

public class Subject {
	private String name;
	private String shortcode;
	private ArrayList<Class> classes;
	
	public Subject(String name, String shortcode) {
		this.name = name;
		this.shortcode = shortcode;
		this.classes = new ArrayList<Class>();		
	}
	
	public void addClass(Class new_class) {
		classes.add(new_class);
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
		String output = "Name: " + name + "\nShortcode: " + shortcode + "\n";
		for (Class c : classes) {
			output += c.toString();
		}
		return output;
	}

	public ArrayList<Class> getClasses() {
		return classes;
	}

}