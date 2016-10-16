package main;

import java.util.ArrayList;
import java.util.HashMap;

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

		String first_names[] = { "Ali",
				"Bill",
				"Tony",
				"Tim",
				"Steve",
				"Adam",
				"Natalie",
				"Sara",
				"Sarah",
				"Mark",
				"Bruce",
				"Andrew"
		};

		String last_names[] = {	"Johnson",
				"Smith",
				"Williams",
				"Wu",
				"Sun",
				"Broseph",
				"Jones",
				"Adams",
				"Li",
				"Pham",
				"Banner",
				"Davidson"
		};
		
		// generate students for each grade
		for (Grade g : grades) {
			for (int i = 0; i < 10; i++) {
				Student s = new Student(studentIDCounter, first_names[(int)(Math.random() * ((first_names.length)))], last_names[(int)(Math.random() * ((first_names.length)))]);
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
		for (int i = 0; i <= subjects.size() - 1; i++) {
			
			// add 10 classes to each subject
			for (int j = 0; j <= 10; j++) {
				int Min = 0;
				int Max = grades.size() - 1;
				int random_value = Min + (int)(Math.random() * ((Max - Min) + 1));
				Class c = subjects.get(i).addClass(grades.get(random_value));
				
				// add 5 random students to this class
				for (int k = 0; k <= 4; k++) {
					
					// Grade tempGrade = c.getGrade();
					// ArrayList<Student> tempStudents = tempGrade.getStudents();
					// Student tempStudent = tempStudents.get(0 + (int)(Math.random() * ((c.getGrade().getStudents().size() - 1) + 1)));
					c.addStudent(c.getGrade().getStudents().get(0 + (int)(Math.random() * ((c.getGrade().getStudents().size() - 1) + 1))));
				}
				
				// generate an assessment for each class
				Assessment a = new Assessment("Test Assessment", 100, c.getStudents());
				for (Student s : c.getStudents()) {
					
					// generate a random mark between 0 and 100
					a.addMark(s, 0 + (int)(Math.random() * ((100 - 0) + 1)));
				}
				
				c.addAssessment(a);				
			}
		}	
		
		System.out.println(this.toString());		
	}
	
	// Function that searches student names
	public ArrayList<Student> searchStudents(String searchString) {
		ArrayList<Student> returnSearch = new ArrayList<Student>();
		for (Grade g : grades) {
			for (Student s : g.getStudents()) {
				if (s.getGivenName().matches(searchString) || s.getSurname().matches(searchString)) {
					returnSearch.add(s);
				}
			}
		}
		
		return returnSearch;
	}
	
	// Function that searches class names
	public ArrayList<Class> searchClasses(String searchString) {
		ArrayList<Class> returnClass = new ArrayList<Class>();
		for (Subject subject : subjects) {		
			for (Class c : subject.getClasses()) {
				if (getLongName(c).matches(searchString)) {
					returnClass.add(c);
				}
			}
		}
		
		return returnClass;
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
	
	public void addSubject(String name, String shortcode) {
		Subject s = new Subject(name, shortcode);
		subjects.add(s);
	}
	
	public ArrayList<Subject> getSubjects() {
		return subjects;
	}
	
	public void addGrade (int grade) {
		Grade g = new Grade(12 - grade + getCurrentYear());
		grades.add(g);
	}
	
	public Student addStudent(String givenName, String surname, Grade g) {
		Student s = new Student(studentIDCounter++, givenName, surname);
		g.addStudent(s);
		return s;
	}
	
	public int getCurrentYear() {
		return 2016;
	}
	
	public String getLongName(Class c) {
		return c.getGrade().getYear(getCurrentYear()) + c.getSubject().getShortcode() + c.getClassNumber();
	}
	
	public ArrayList<Grade> getGrades() {
		return grades;
	}
	
	public String toString() {
		String returnString = "Subjects:\n";
		
		for (Subject s : subjects) {
			returnString += s.toString();
		}
		
		returnString += "\n";
		
		for (Grade g : grades) {
			returnString += g.toString();			
		}
		
		return returnString;
	}
	
	public int getRank(Student student, Subject subject, Grade grade) {
		ArrayList<Class> classes = subject.getClasses(grade);
		HashMap<Student, Double> marks = new HashMap<Student, Double>();
		
		for (Class c : classes) {
			marks.putAll(c.getStudentMarks());
		}
		
		return 0;
	}
	public int getStudentCount(Subject subject, Grade g) {
		ArrayList<Class> classes = subject.getClasses(g);
		return getStudentCount(classes);
	}
	
	public int getStudentCount(ArrayList<Class> classes) {
		int count = 0;
		for (Class c : classes) {
			count += c.getStudentCount();
		}
		
		return count;		
	}
}