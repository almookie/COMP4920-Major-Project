package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Markbook {
	private ArrayList<Subject> subjects;
	private ArrayList<Grade> grades;
	private int studentIDCounter;
	static final String JDBC_DRIVER = "org.postgresql.Driver";  
	static final String DB_NAME = "MarkbookDB";
	static final String DB_URL = "jdbc:postgresql://localhost:5433/" + DB_NAME;
	static final String USERNAME = "postgres";  
	static final String PASSWORD = "password";	

	public Markbook() {
		this.subjects = new ArrayList<Subject>();
		this.grades = new ArrayList<Grade>();
		this.studentIDCounter = 0;
	}
	
	public void initialisePostgreSQLDatabase() {
		Connection connection = null;
		Statement statement = null;
	      try {
	         Class.forName(JDBC_DRIVER);
	         connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	         databaseInitialisation(connection);
	         connection.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
   }
	
	/**
	 * Initialises the database if it has not been created yet
	 * @param connection pass the DriverManager.getConnection() object
	 * @throws SQLException
	 */
	private void databaseInitialisation(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		
		// Create a table that holds STUDENTS, where each student has an ID, First Name, Last Name and Grade
		statement.executeUpdate(
				"CREATE TABLE IF NOT EXISTS STUDENTS"
				+ "(ID INT PRIMARY KEY NOT NULL,"
				+ "GIVEN_NAME TEXT NOT NULL,"
				+ "SURNAME TEXT NOT NULL,"
				+ "GRADE INT NOT NULL)"
  		);
		
		// Create a table that holds GRADES, where each grade has an end year
		statement.executeUpdate(	
				"CREATE TABLE IF NOT EXISTS GRADES" +
				"(GRADUATION_YEAR INT PRIMARY KEY NOT NULL)"
  		);
		
		// Create a table that holds ENROLMENTS, where each enrolment consists of a student within a year
		statement.executeUpdate(	
				"CREATE TABLE IF NOT EXISTS ENROLMENTS" +
				"(STUDENT INT PRIMARY KEY NOT NULL,"
				+ "GRADUATION_YEAR INT NOT NULL)"
  		);
		
		// Create a table that holds CLASSES, where each grade has an end year
		statement.executeUpdate(	
				"CREATE TABLE IF NOT EXISTS GRADES" +
				"(GRADUATION_YEAR INT PRIMARY KEY NOT NULL)"
  		);
		
		statement.close();
	}
	
	public void generateRandomData() {

		// initialisePostgreSQLDatabase();

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
				Subject_Class c = subjects.get(i).addClass(grades.get(random_value));
				
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
		
		// System.out.println(this.toString());		
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
	public ArrayList<Subject_Class> searchClasses(String searchString) {
		ArrayList<Subject_Class> returnClass = new ArrayList<Subject_Class>();
		for (Subject subject : subjects) {		
			for (Subject_Class c : subject.getClasses()) {
				if (getLongName(c).matches(searchString)) {
					returnClass.add(c);
				}
			}
		}
		
		return returnClass;
	}
	
	public ArrayList<Subject_Class> getClasses() {
		ArrayList<Subject_Class> classes = new ArrayList<Subject_Class>();
		
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
	
	/**
	 * 
	 * @param grade The finishing year of the grade to add
	 */
	public void addGrade (int grade) {
		Grade g = new Grade(12 - grade + getCurrentYear());
		grades.add(g);
	}
	
	/**
	 * 
	 * @param givenName The first name of the student
	 * @param surname The surname of the student
	 * @param g The grade to add the student to
	 * @return Returns the student object
	 */
	public Student addStudent(String givenName, String surname, Grade g) {
		Student s = new Student(studentIDCounter++, givenName, surname);
		g.addStudent(s);
		return s;
	}
	
	/**
	 * 
	 * @return The current year
	 */
	public int getCurrentYear() {
		return 2016;
	}
	
	/**
	 * 
	 * @param c The class to find the name of
	 * @return Returns the name of the class in the form of YearShortcodeClass#, e.g. 9Ma2
	 */
	public String getLongName(Subject_Class c) {
		return c.getGrade().getYear(getCurrentYear()) + c.getSubject().getShortcode() + c.getClassNumber();
	}
	
	/**
	 * 
	 * @return Gets the list of grades stored in the markbook
	 */
	public ArrayList<Grade> getGrades() {
		return grades;
	}
	
	/**
	 * @return Returns a user friendly version of the markbook db
	 */
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
	
	/**
	 * 
	 * @param student The student to find the rank of
	 * @param subject The subject the student is within
	 * @param grade The grade that the student is within
	 * @return Returns the rank of the student within the subject and grade
	 */
	public int getRank(Student student, Subject subject, Grade grade) {
		ArrayList<Subject_Class> classes = subject.getClasses(grade);
		HashMap<Student, Double> marks = new HashMap<Student, Double>();
		
		for (Subject_Class c : classes) {
			marks.putAll(c.getStudentMarks());
		}
		
		return 0;
	}
	
	/**
	 * 
	 * @param subject The subject to get the count of students from
	 * @param g The grade to get the count of students from
	 * @return Returns the count of students within the subject and grade
	 */
	public int getStudentCount(Subject subject, Grade g) {
		ArrayList<Subject_Class> classes = subject.getClasses(g);
		return getStudentCount(classes);
	}
	
	/**
	 * 
	 * @param Classes The classes to count the number of students from
	 * @return Returns the number of students within the set of classes
	 */
	public int getStudentCount(ArrayList<Subject_Class> classes) {
		int count = 0;
		for (Subject_Class c : classes) {
			count += c.getStudentCount();
		}
		
		return count;		
	}
	
	/**
	 * 
	 * @param s The subject that the class is contained in
	 * @param c The class to delete
	 */
	public void deleteClass(Subject s, Subject_Class c) {
		s.removeClass(c);
	}
	
	/**
	 * 
	 * @param c The class to add an assessment to
	 * @param name The name to set the assessment as
	 * @param weighting The weighting of the assessment, e.g. 0.3 = 30%
	 * @return The Assessment object
	 */
	public Assessment createAssessment(Subject_Class c, String name, double weighting) {
		return c.createNewAssessment(name, weighting);
	}
}