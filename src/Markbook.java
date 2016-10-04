/***************************************
		IMPORTS
 ***************************************/

import java.util.ArrayList;
import java.util.HashMap;


public class Markbook implements GradeAPI, SubjectAPI, ClassAPI, StudentAPI, AssessmentAPI {

	/***************************************
				FIELDS
	***************************************/
	
	private static boolean DEBUG_MODE = false;
	
	private String markBookName;
	private ArrayList<Grade> gradeList;
	
	
	/***************************************
				CONSTUCTOR
	 ***************************************/
	
	public Markbook(String name) {
		this.markBookName = name;
		this.gradeList = new ArrayList<Grade>();
		if (DEBUG_MODE == true) System.out.println("Created School System: " + this.markBookName);
	}
	
	
	/***************************************
			API (PUBLIC METHODS)
	 ***************************************/
	//	TODO: Sanitisation checks
	
	
	/***************************************
			GRADE
	***************************************/
	
	public boolean addGrade(int currGrade, int gradYear) {
		Grade grade = new Grade(currGrade, gradYear);
		gradeList.add(grade);
		if (DEBUG_MODE == true) System.out.println("Added Grade " + currGrade + " ,graduating on " + gradYear);
		//	Most boolean methods atm returns True at the moment
		//	need to check sanitisation for all methods and to return false if it fails?
		return true;
	}
	
	
	public boolean removeGrade(Grade g) {
		gradeList.remove(g);
		if (DEBUG_MODE == true) System.out.println("Removed Grade " + g._getGrade() + " ,graduating on " + g._getGraduationYear());
		return true;
	}

	
	public ArrayList<Grade> getGradeList() { return this.gradeList; }
	
	
	/***************************************
				SUBJECT
	***************************************/
	
	public boolean addSubject(String sName, String sCode, Grade g) {
		g._addSubject(new Subject(sName, sCode, g));
		if (DEBUG_MODE == true) System.out.println("Added Subject " + sName + ":" + sCode + " to grade " + g._getGrade());
		return true;
	}
	
	
	public boolean removeSubject(Subject s, Grade g) {
		g._removeSubject(s);
		if (DEBUG_MODE == true) System.out.println("Removed Subject " + s._getName() + ":" + s._getShortcode() + " to grade " + g._getGrade());
		return true;
	}
	
	
	public ArrayList<Subject> getSubjectList(Grade g) { return g._getSubjectList(); }
	
	
	/***************************************
				CLASS
	***************************************/
	
	public boolean addClass(int classID, Subject s, Grade g) {
		s._addClass(classID, g);
		if (DEBUG_MODE == true) System.out.println("Added Class " + classID + " to " + s._getName() + ":" + s._getShortcode());
		return true;
	}
	
	
	public boolean removeClass(Class c, Subject s) {
		s._removeClass(c);
		if (DEBUG_MODE == true) System.out.println("Removed Class " + c._getClassID() + " to " + s._getName() + ":" + s._getShortcode());
		return true;
	}
	
	
	public HashMap<Class, Grade> getClassList(Subject s) { return s._getClassGradeMap(); }

	
	/***************************************
				STUDENT
	***************************************/
	
	public boolean addStudent(int stuID, String givenName, String surname, Grade g) {
		g._addStudent(new Student(stuID, givenName, surname, g));
		if (DEBUG_MODE == true) System.out.println("Created Student " + givenName + " " + surname + " ID: " + stuID + " and added to grade " + g._getGrade());
		return true;
	}
	
	public boolean removeStudent(Student s) {
		s._getGrade()._removeStudent(s);
		if (DEBUG_MODE == true) System.out.println("REMOVED STUDENT");
		return true;
	}
	
	public boolean enrolStudent(Student stu, Class c) {
		c._addStudent(stu);
		if (DEBUG_MODE == true) System.out.println("Enrolled Stsudent " + stu._getGivenName() + " " + stu._getSurname() + ":" + stu._getID() + " into " + c._getClassID());
		return true;
	}
	
	public boolean unenrolStudent(Student stu, Class c) {
		c._removeStudent(stu);
		if (DEBUG_MODE == true) System.out.println("UNENROLLED STUDENT");
		return true;
	}
	
	
	public HashMap<Class, Subject> getClassList(Student stu) { return stu._getClassMap(); }
	public HashMap<Assessment, Class> getAssessmentList(Student stu) { return stu._getAssMap(); }

	
	/***************************************
				ASSESSMENT
	***************************************/
	
	//	ADD ASSESSMENT
	public boolean addAssessment(String aName, Double maxMark, Double weighing, Class c) {
		c._addAssessment(aName, maxMark, weighing);
		if (DEBUG_MODE == true) System.out.println("ADDED ASSESSMENT");
		return true;
	}
	
	//	REMOVE ASSESSMENT
	public boolean removeAssessment(Assessment a, Class c) {
		c._removeAssessment(a, c);
		if (DEBUG_MODE == true) System.out.println("REMOVED ASSESSMENT");
		return true;
	}
	
	//	GETS
	public ArrayList<Assessment> getClassAssessmentList(Class c) {
		return c._getAssessmentList();
	}

	
	
	/***************************************
				DEMO
	 ***************************************/
	public void demoFill() {
		
		/*// method for creating random temporary data
		final int startingYear = 2016;
		final int endYear = 2022;
		int startGrade = 7;		// yr 7-12
		
		//	CREATE SCHOOL (LIST OF GRADES)
		ArrayList<Grade> grades = new ArrayList<Grade>();
		
		for (int i = startingYear; i <= endYear; i++) {
			Grade grade = new Grade(i, startGrade);
			startGrade++;
			grades.add(grade);
		}

		//	CREATE SUBJECTS
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
		
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		
		for (int i = 0; i <= subject_names.length; i++) {
			Subject subject = new Subject(subject_names[i], subject_shortcodes[i]);
			subjects.add(subject);
		}
		
		//	CREATE CLASSES
		//	Start from 6000 just so we can distinguish from studentIDs
		int classID = 6000;
		for (Grade g : grades) {
			for (Subject s : subjects) {
				s.addClass(new Class(classID, g));
				classID++;
			}
		}
		
		//	CREATE STUDENTS
		//	From list 50 randomly generated names
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("names.txt"));
			String[] fullName;
			 
			int studentID = 0;
			for (Grade g : grades) {
				for (int i = 0; i < 10; i++) {
					fullName = reader.readLine().split(" ");
					Student s = new Student(studentID, fullName[0], fullName[1], g);
					studentID++;
				}
			}
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    try {
		    	if (reader != null) { reader.close(); }
		    } catch (IOException e) { }
		}
	
		//	CREATE ASSESSMENTS
		for (Grade g : grades) {
			for(Subject s : subjects) {
				for(Class c : s.getClassMap()) {
					//Assessment ass = new Assessment(s.getStudents());
					//	TODO: finish demo soon				}
				}
			}
		}	
		*/
	}


	public void setDebug(boolean b) {
		DEBUG_MODE = b;
	}
}
