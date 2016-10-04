package main;

import java.util.ArrayList;

public class Markbook {
	private ArrayList<Subject> subjects;
	private ArrayList<Grade> grades;
	private int studentIDCounter;

	public Markbook() {
		this.subjects = new ArrayList<Subject>();
		this.grades = new ArrayList<Grade>();
		this.studentIDCounter = 0;
	}
	
	public void generateRandomData() {
		
		// method for creating random temporary data
		final int startingYear = 2016;
		final int endYear = 2022;
		
		// generate a number of grades
		for (int i = startingYear; i <= endYear; i++) {
			Grade grade = new Grade(i);
			grades.add(grade);
		}
		
		// generate students for each grade
		for (Grade g : grades) {
			for (int i = 0; i < 10; i++) {
				Student s = new Student(studentIDCounter, "Name " + studentIDCounter, "Surname " + studentIDCounter);
				g.addStudent(s);
				studentIDCounter++;				
			}
		}
		
		// generate a number of subjects
		String subject_names[] = { 	"Chemistry",
				"English",
				"Mathematics",
				"Physics",
				"Geography",
				"History",
				"Art",
				"Design and Technology"
		};
		
		String subject_shortcodes[] = { "Ch",
				"En",
				"Ma",
				"Ph",
				"Ge",
				"Hi",
				"Ar",
				"Dt"
		};
		
		for (int i = 0; i <= subject_names.length - 1; i++) {
			Subject subject = new Subject(subject_names[i], subject_shortcodes[i]);
			subjects.add(subject);
		}
		
		// Create a bunch of classes to flesh out subjects
		for (int i = 0; i <= subjects.size(); i++) {
			
			// add 3 classes to each subject
			for (int j = 0; i <= 2; i++) {
				int Min = 0;
				int Max = grades.size() - 1;
				int random_value = Min + (int)(Math.random() * ((Max - Min) + 1));
				Class c = new Class(grades.get(random_value));
				
				// add 3 random students to this class
				for (int k = 0; k <= 2; k++) {
					
					// Grade tempGrade = c.getGrade();
					// ArrayList<Student> tempStudents = tempGrade.getStudents();
					// Student tempStudent = tempStudents.get(0 + (int)(Math.random() * ((c.getGrade().getStudents().size() - 1) + 1)));
					c.addStudent(c.getGrade().getStudents().get(0 + (int)(Math.random() * ((c.getGrade().getStudents().size() - 1) + 1))));
				}
				
				// generate an assessment for each class
				Assessment a = new Assessment(c.getStudents(), "Test Assessment");
				for (Student s : c.getStudents()) {
					
					// generate a random mark between 0 and 100
					a.addMark(s, 0 + (int)(Math.random() * ((100 - 0) + 1)));
				}
				
				c.addAssessment(a);
				subjects.get(i).addClass(c);				
			}
		}
		
				
		// TODO: Unfinished
	}
	
	public ArrayList<Class> getClasses() {
		ArrayList<Class> classes = new ArrayList<Class>();
		
		for (Subject s : subjects) {
			classes.addAll(s.getClasses());
		}
		
		return classes;
	}
	
	public ArrayList<Student> getStudents() {
		ArrayList<Student> classes = new ArrayList<Student>();
		
		for (Grade g : grades) {
			classes.addAll(g.getStudents());
		}
		
		return classes;
	}
	
	public ArrayList<Subject> getSubjects() {
		return subjects;
	}
	
	public void addGrade (Grade g) {
		grades.add(g);
	}
	
	public Student createStudent(String givenName, String surname) {
		return new Student(studentIDCounter++, givenName, surname);
	}
}