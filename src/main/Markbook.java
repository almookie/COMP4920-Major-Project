package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Markbook {
	private ArrayList<Subject> subjects;
	private ArrayList<Grade> grades;
	static final String JDBC_DRIVER = "org.postgresql.Driver";  
	static final String DB_NAME = "MarkbookDB";
	static final String DB_URL = "jdbc:postgresql://localhost:5433/" + DB_NAME;
	static final String USERNAME = "postgres";  
	static final String PASSWORD = "password";	
	
	// Counters to hold new ID locations
	private int availableSubjectID;
	private int availableStudentID;
	private int availableClassID;
	private int availableAssessmentID;
	private HashMap<Subject, HashMap<Grade, Integer>> availableClassNumbers;

	public Markbook() {
		this.subjects = new ArrayList<Subject>();
		this.grades = new ArrayList<Grade>();
		this.availableClassNumbers = new HashMap<Subject, HashMap<Grade, Integer>>();
		this.availableSubjectID = 0;
		this.availableStudentID = 0;
		this.availableClassID = 0;
		this.availableAssessmentID = 0;
	}
	
	/*public void generateRandomData() {

		// initialisePostgreSQLDatabase();
		// generateFromPostgreSQLDatabase();
		// generateData();			
		// saveDataToPSQL();
	}*/
	
	public void generateRandomData() {
		generateData();			
	}
	
	public void loadDatabase() {
		generateFromPostgreSQLDatabase();
	}
	
	public void saveDatabase() {
		saveDataToPSQL();
	}
	
	private void saveDataToPSQL() {

		// Connect to the database
		Connection connection = null;
	      try {
	         Class.forName(JDBC_DRIVER);
	         connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	         Statement statement = connection.createStatement();
	         
	         // Delete all the content saved in the database tables
	         String[] tables = {
	        		 "STUDENTS",
	        		 "GRADES",
	        		 "ASSESSMENTS",
	        		 "CLASS_ASSESSMENTS",
	        		 "ASSESSMENT_RESULTS",
	        		 "SUBJECTS",
	        		 "CLASSES",
	        		 "CLASS_ENROLMENTS"        		 
	         };
	         
	         // Clear the tables
	         for (String table : tables) {
	        	 statement.executeUpdate("DELETE FROM " + table + ";");
	         }
	         
	         // Update with new content	   
	         
	         // Update GRADES table
	         for (Grade g : grades) {
	        	 statement.executeUpdate("INSERT INTO GRADES (GRADUATION_YEAR) VALUES(" + g.getGraduationYear() + ");");
	         }
	         
	         // Update SUBJECTS, CLASSES, ASSESSMENTS and ASSESSMENT_RESULTS tables at the same time
	         for (Subject s : subjects) {
	        	 
	        	 // updating the SUBJECTS table
	        	 statement.executeUpdate("INSERT INTO SUBJECTS (ID, NAME, SHORTCODE) VALUES(" + s.getID() + ", '" + s.getName() + "', '" + s.getShortcode() + "');");	 
	        	 
	        	 // updating the CLASSES table
	        	 for (Subject_Class c : s.getClasses()) {
		        	 statement.executeUpdate("INSERT INTO CLASSES (ID, SUBJECT, GRADE, CLASS_NUMBER) VALUES(" 
		        			 + c.getID() + ", " + s.getID() +  ", " + c.getGrade().getGraduationYear() + ", " + c.getClassNumber() + ");");		 
		        	 
		        	 // updating the CLASS_ENROLMENTS table
		        	 for (Student student : c.getStudents()) {
			        	 statement.executeUpdate("INSERT INTO CLASS_ENROLMENTS (CLASS, STUDENT) VALUES(" 
			        			 + c.getID() + ", " + student.getID() + ");");		 
		        	 }
		        		        	 
		        	 // updating the ASSESSMENTS table        		 
		        	 for (Assessment a : c.getAssessments()) {
			        	 statement.executeUpdate("INSERT INTO ASSESSMENTS (ID, WEIGHTING, NAME, MEAN, MODE, MEDIAN, RANGE) VALUES(" 
			        			 + a.getID() + ", " + a.getWeighting() +  ", '" + a.getName() + "', " + a.getMean() + ", " + a.getMode() + ", " + a.getMedian() + ", " + a.getRange() +");");		 
			        	 
			        	 // updating the ASSESSMENT_RESULTS table
			        	 for (Student st : a.getMarks().keySet()) {
				        	 statement.executeUpdate("INSERT INTO ASSESSMENT_RESULTS (ASSESSMENT_ID, STUDENT, MARK) VALUES(" 
				        			 + a.getID() + ", " + st.getID() +  ", " + a.getMark(st) +");");					        		 
			        	 }
		        	 }
		        	 
		        	 // updating the CLASS_ASSESSMENTS table
		        	 for (Assessment a : c.getAssessments()) {
		        		 statement.executeUpdate("INSERT INTO CLASS_ASSESSMENTS (CLASS, ASSESSMENT) VALUES (" + c.getID() + ", " + a.getID() + ");");
		        	 }
	        	 }       	 
	        	 
	         }
	         
	         // Update STUDENTS table
	         for (Grade g : grades) {
	        	 for (Student s : g.getStudents()) {
		        	 statement.executeUpdate("INSERT INTO STUDENTS (ID, GIVEN_NAME, SURNAME, GRADE) VALUES(" 
		        			 + s.getID() + ", '" + s.getGivenName() +  "', '" + s.getSurname() + "', " + g.getGraduationYear() + ");");		        		 
	        	 }
	         }
	         
	         connection.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }		
	}

	private void generateData() {
			
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
				for (int i = 0; i < 25; i++) {
					Student s = new Student(availableStudentID++, first_names[(int)(Math.random() * ((first_names.length)))], last_names[(int)(Math.random() * ((first_names.length)))]);
					g.addStudent(s);			
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
				Subject subject = new Subject(availableSubjectID++, subject_names[i], subject_shortcodes[i]);
				subjects.add(subject);
			}
			
			// Create a bunch of classes to flesh out subjects
			for (int i = 0; i <= subjects.size() - 1; i++) {
				
				for (int j = 0; j <= 5; j++) {
					int Min = 0;
					int Max = grades.size() - 1;
					int random_value = Min + (int)(Math.random() * ((Max - Min) + 1));
					Subject_Class c = new Subject_Class(availableClassID++, grades.get(random_value), subjects.get(i), getNextAvailableClassNumber(subjects.get(i), grades.get(random_value)));
					subjects.get(i).addClass(c);
					
					// add 20 random students to this class
					for (int k = 0; k <= 5; k++) {
						
						// Grade tempGrade = c.getGrade();
						// ArrayList<Student> tempStudents = tempGrade.getStudents();
						// Student tempStudent = tempStudents.get(0 + (int)(Math.random() * ((c.getGrade().getStudents().size() - 1) + 1)));
						c.addStudent(c.getGrade().getStudents().get(0 + (int)(Math.random() * ((c.getGrade().getStudents().size() - 1) + 1))));
					}
					
					// generate an assessment for each class
					Assessment a = new Assessment(availableAssessmentID++, "Test Assessment", 25, c.getStudents());
					for (Student s : c.getStudents()) {
						
						// generate a random mark between 0 and 100
						a.addMark(s, 0 + (int)(Math.random() * ((100 - 0) + 1)));
					}
					
					c.addAssessment(a);		
					
					// generate a second assessment for each class
					a = new Assessment(availableAssessmentID++, "Test Assessment", 75, c.getStudents());
					for (Student s : c.getStudents()) {
						
						// generate a random mark between 0 and 100
						a.addMark(s, 0 + (int)(Math.random() * ((100 - 0) + 1)));
					}
					
					c.addAssessment(a);				
				}
			}	
			
			// System.out.println(this.toString());
			
	}
	
	public int getNextAvailableClassNumber(Subject subject, Grade grade) {
		
		// if there isn't an entry for the specific subject in this map yet
		if (availableClassNumbers.get(subject) == null) {
			
			HashMap<Grade, Integer> newGradeIntegerMap = new HashMap<Grade, Integer>();
			newGradeIntegerMap.put(grade, 0);
			availableClassNumbers.put(subject, newGradeIntegerMap);
		
		// otherwise if there isn't an entry in the grade hashmap for that specific grade
		} else if (availableClassNumbers.get(subject).get(grade) == null) {
			
			availableClassNumbers.get(subject).put(grade, 0);
		}
		
		// get the classnumber value and increment it		
		int classNumber = availableClassNumbers.get(subject).get(grade);
		availableClassNumbers.get(subject).put(grade, ++classNumber);
		
		return classNumber;
	}

	private void generateFromPostgreSQLDatabase() {
		HashMap<Integer, Subject_Class> classIDMap = new HashMap<Integer, Subject_Class>();
		HashMap<Integer, Student> studentIDMap = new HashMap<Integer, Student>();
		HashMap<Integer, Grade> gradeIDMap = new HashMap<Integer, Grade>();
		HashMap<Integer, Assessment> assessmentIDMap = new HashMap<Integer, Assessment>();
		HashMap<Integer, Subject> subjectIDMap = new HashMap<Integer, Subject>();

		// Connect to the database
		Connection connection = null;
	      try {
	         Class.forName(JDBC_DRIVER);
	         connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	         Statement statement = connection.createStatement();
	         ResultSet resultset;
	         
	         // Import the GRADES table from the database
	         resultset = statement.executeQuery("SELECT * FROM GRADES;");
	         
	         // Loop through each row of the GRADES table and generate Grade objects
	         while(resultset.next()) {
	        	 int graduationYear = resultset.getInt("GRADUATION_YEAR");
	        	 
	        	 Grade grade = new Grade(graduationYear);
	        	 grades.add(grade);
	        	 gradeIDMap.put(graduationYear, grade);
	         }
	         
	         // Import the SUBJECTS table from the database
	         resultset = statement.executeQuery("SELECT * FROM SUBJECTS;");
	         
	         // Loop through each row of the SUBJECTS table and generate Subject objects
	         while(resultset.next()) {
	        	 int id = resultset.getInt("ID");
	        	 String name = resultset.getString("NAME");
	        	 String shortcode = resultset.getString("SHORTCODE");
	        	 
	        	 Subject subject = new Subject(id, name, shortcode);
	        	 subjects.add(subject);
	        	 subjectIDMap.put(id, subject);
	        	 
	        	 if (id > availableSubjectID) {
	        		 availableSubjectID = id + 1;
	        	 }
	         }
	         
	         resultset = statement.executeQuery("SELECT * FROM STUDENTS;");
	         
	         while(resultset.next()) {
	        	 int id = resultset.getInt("ID");
	        	 String givenName = resultset.getString("GIVEN_NAME");
	        	 String surname = resultset.getString("SURNAME");
	        	 int gradeID = resultset.getInt("GRADE");
	        	 
	        	 Grade g = gradeIDMap.get(gradeID);
	        	 
	        	 Student s = new Student(id, givenName, surname);
	        	 g.addStudent(s);
	        	 studentIDMap.put(id, s);
	        	 
	        	 if (id > availableStudentID) {
	        		 availableStudentID = id + 1;
	        	 }	        	 
	         }
	         
	         resultset = statement.executeQuery("SELECT * FROM CLASSES" + ";");
	         
	         while (resultset.next()) {

	        	 int id = resultset.getInt("ID"); 
	        	 int gradeID = resultset.getInt("GRADE");
	        	 int subjectID = resultset.getInt("SUBJECT");
	        	 int classNumber = resultset.getInt("CLASS_NUMBER");
	        	 
	        	 Grade g = gradeIDMap.get(gradeID);
	        	 Subject s = subjectIDMap.get(subjectID);
	        	 Subject_Class subject_class = new Subject_Class(id, g, s, classNumber);
	        	 s.addClass(subject_class);	
	        	 classIDMap.put(id, subject_class);
	        	 
	        	 if (id > availableClassID) {
	        		 availableClassID = id + 1;
	        	 }	        	 
	         }
	         
	         resultset = statement.executeQuery("SELECT * FROM CLASS_ENROLMENTS" + ";");
	         
	         while (resultset.next()) {

	        	 int classID = resultset.getInt("CLASS"); 
	        	 int studentID = resultset.getInt("STUDENT");
	        	 
	        	 Subject_Class subject_class = classIDMap.get(classID);
	        	 Student s = studentIDMap.get(studentID);
	        	 
	        	 subject_class.addStudent(s);	        	 
	         }    
	         
	         resultset = statement.executeQuery("SELECT * FROM ASSESSMENTS" + ";");
	         
	         while (resultset.next()) {

	        	 int assessmentID = resultset.getInt("ID"); 
	        	 double weighting = resultset.getDouble("WEIGHTING");
	        	 String name = resultset.getString("NAME");   
	        	 
	        	 Assessment a = new Assessment(assessmentID, name, weighting, new ArrayList<Student>());
	        	 assessmentIDMap.put(assessmentID, a);
	         }      
	         
	         resultset = statement.executeQuery("SELECT * FROM CLASS_ASSESSMENTS" + ";");
	         
	         while (resultset.next()) {

	        	 int assessmentID = resultset.getInt("ASSESSMENT"); 
	        	 int classID = resultset.getInt("CLASS");  

	        	 Assessment a = assessmentIDMap.get(assessmentID);
	        	 Subject_Class c = classIDMap.get(classID);
	        	 
	        	 c.addAssessment(a);
	        	 a.addStudents(c.getStudents());
	         }    
	         
	         resultset = statement.executeQuery("SELECT * FROM ASSESSMENT_RESULTS" + ";");
	         
	         while (resultset.next()) {

	        	 int assessmentID = resultset.getInt("ASSESSMENT_ID"); 
	        	 int studentID = resultset.getInt("STUDENT");
	        	 double mark = resultset.getDouble("MARK");
	        	 
	        	 Student s = studentIDMap.get(studentID);
	        	 Assessment a = assessmentIDMap.get(assessmentID);
	        	 
	        	 a.addMark(s, mark);
	         }      
	         
	         
	         
	         
	         
	         connection.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }		
	}

	public void initialisePostgreSQLDatabase() {
		Connection connection = null;
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
		
		// Create a table that holds SUBJECTS, where each subject has an ID, a name and shortcode
		statement.executeUpdate(	
				"CREATE TABLE IF NOT EXISTS SUBJECTS"
				+ "(ID INT PRIMARY KEY NOT NULL,"
				+ "NAME TEXT NOT NULL,"
				+ "SHORTCODE TEXT NOT NULL)"
  		);
		
		// Create a table that holds CLASSES, where each grade has an end year
		statement.executeUpdate(	
				"CREATE TABLE IF NOT EXISTS CLASSES"
				+ "(ID INT PRIMARY KEY NOT NULL,"
				+ "SUBJECT INT NOT NULL,"
				+ "GRADE INT NOT NULL,"
				+ "CLASS_NUMBER INT NOT NULL)"
  		);
		
		// Create a table that holds CLASS_ENROLMENTS, which enrols students in a class
		statement.executeUpdate(	
				"CREATE TABLE IF NOT EXISTS CLASS_ENROLMENTS"
				+ "(CLASS INT NOT NULL,"
				+ "STUDENT INT NOT NULL)"
  		);		
		
		// Create a table that holds ASSESSMENTS
		statement.executeUpdate(	
				"CREATE TABLE IF NOT EXISTS ASSESSMENTS"
				+ "(ID INT PRIMARY KEY NOT NULL,"
				+ "WEIGHTING DOUBLE PRECISION NOT NULL,"
				+ "NAME TEXT NOT NULL,"
				+ "MEAN DOUBLE PRECISION NOT NULL,"
				+ "MODE DOUBLE PRECISION NOT NULL,"
				+ "MEDIAN DOUBLE PRECISION NOT NULL,"
				+ "RANGE DOUBLE PRECISION NOT NULL)"
  		);				
		
		// Create a table that holds CLASS_ASSESSMENTS
		statement.executeUpdate(	
				"CREATE TABLE IF NOT EXISTS CLASS_ASSESSMENTS"
				+ "(CLASS INT NOT NULL,"
				+ "ASSESSMENT INT NOT NULL)"
  		);					
		
		// Create a table that holds ASSESSMENT RESULTS
		statement.executeUpdate(	
				"CREATE TABLE IF NOT EXISTS ASSESSMENT_RESULTS"
				+ "(ASSESSMENT_ID INT NOT NULL,"
				+ "STUDENT INT NOT NULL,"
				+ "MARK DOUBLE PRECISION NOT NULL)"
  		);		
		
		
		statement.close();
	}
		
	// Function that searches student names
	public ArrayList<Student> searchStudents(String searchString) {
		ArrayList<Student> returnSearch = new ArrayList<Student>();
		String regexSearchString = ".*" + searchString + ".*";
		
		for (Grade g : grades) {
			for (Student s : g.getStudents()) {
				if ((s.getGivenName() + " " + s.getSurname()).matches(regexSearchString)) {
					returnSearch.add(s);
				}
			}
		}
		
		return returnSearch;
	}
	
	// Function that searches class names
	public ArrayList<Subject_Class> searchClasses(String searchString) {
		String regexSearchString = ".*" + searchString + ".*";
		regexSearchString.toLowerCase();
		
		ArrayList<Subject_Class> returnClass = new ArrayList<Subject_Class>();
		for (Subject subject : subjects) {		
			for (Subject_Class c : subject.getClasses()) {
				if (getLongName(c).matches(regexSearchString)) {
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
		Subject s = new Subject(availableSubjectID++, name, shortcode);
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
		Student s = new Student(availableStudentID++, givenName, surname);
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
		return c.createNewAssessment(availableAssessmentID++, name, weighting);
	}
	
	/**
	 * The grade object associated with a graduation year, or null if not found
	 * @param graduationYear The year that the grade graduates in
	 * @return The grade object associated with a graduation year, or null if not found
	 */
	private Grade getGradeByYear (int graduationYear) {
		
		for (Grade g : grades) {
			if (g.getGraduationYear() == graduationYear) {
				return g;
			}
		}
		
		return null;
	}
	
	/**
	 * The grade object associated with a graduation year, or null if not found
	 * @param graduationYear The year that the grade graduates in
	 * @return The grade object associated with a graduation year, or null if not found
	 */
	private Student getStudentById (int id) {
		
		for (Grade g : grades) {
			for (Student s : g.getStudents()) {
				if (s.getID() == id) {
					return s;
				}
			}
		}
		
		return null;
	}
	
	/** 
	 * 
	 * @return The next available Student ID so as to not conflict with current Students
	 */
	public int getNextAvailableStudentID() {
		return availableStudentID++;
	}
	
	/** 
	 * 
	 * @return The next available Subject ID so as to not conflict with current Subjects
	 */
	public int getNextAvailableSubjectID() {
		return availableSubjectID++;
	}
	
	/**
	 * 
	 * @return The next available Class ID so as to not conflict with current Classes
	 */
	public int getNextAvailableClassID() {
		return availableClassID++;
	}
	
	public int getNextAssessmentID() {
		return availableAssessmentID++;
	}
	
	public void deleteSubject(Subject s) {
		subjects.remove(s);
	}
	
	public void deleteGrade(Grade g) {
		for (Subject s : subjects) {
			
			ArrayList<Subject_Class> classesToRemove = new ArrayList<Subject_Class>();
			for (Subject_Class c : s.getClasses()) {
				if (c.getGrade() == g) {
					classesToRemove.add(c);
				}
			}
			
			for (Subject_Class c : classesToRemove) {
				s.removeClass(c);
			}
		}
		grades.remove(g);
	}
	
	public ArrayList<Subject> searchSubjects(String searchString) {
		String regexSearchString = ".*" + searchString + ".*";
		ArrayList<Subject> returnList = new ArrayList<Subject>();
		
		for (Subject s : subjects) {
			if (s.getName().matches(regexSearchString)) {
				returnList.add(s);
			}
		}
		
		return returnList;
	}
	
	public ArrayList<Grade> searchGrades(int searchInt) {
		String searchString = String.valueOf(searchInt);
		String regexSearchString = ".*" + (12 - searchInt + getCurrentYear()) + ".*";
		ArrayList<Grade> returnList = new ArrayList<Grade>();
		
		for (Grade g : grades) {
			if (String.valueOf(g.getGraduationYear()).matches(searchString) || String.valueOf(g.getGraduationYear()).matches(regexSearchString)) {
				returnList.add(g);
			}
		}
		return returnList;
	}
	
	public void addClass(Subject s, Grade g, ArrayList<Student> students) {
		Subject_Class newClass = new Subject_Class(availableClassID++, g, s, getNextAvailableClassNumber(s, g));
		s.addClass(newClass);
	}
	
	public void deleteStudent(Student student) {
		
		// remove student from all subjects that that contain it
		for (Subject s : subjects) {
			s.removeStudent(student);
		}
		
		// remove student from the grade that contains it
		for (Grade g : grades) {
			if (g.getStudents().contains(student)) {
				g.removeStudent(student);
				continue;
			}
		}
	}
}