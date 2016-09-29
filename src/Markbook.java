import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/***************************************
		IMPORTS
 ***************************************/


public class Markbook implements API{

	public static boolean DEBUG;
	
/***************************************
		CONSTUCTOR
***************************************/

	private String markBookName;
	private ArrayList<Grade> gradeList;
	
	public Markbook(String name, boolean DEBUG) {
		this.markBookName = name;
		this.gradeList = new ArrayList<Grade>();
		if (DEBUG == true) System.out.println("Created School System: " + this.markBookName);
	}
	
	
/***************************************
		API (PUBLIC METHODS)
 ***************************************/
	
	//	ADD
	//	Should it return anything? boolean? or object itself?
	//	Should it pass argument in strings or objects? (can use gets here to get objects)
	//	arguments passes objects so that it is more 'mutable' not sure if it is violating abstraction since your giving references away
	
/***************************************
		GRADE
***************************************/
	//	ADD GRADE
	public void addGrade(int currGrade, int gradYear) {
		Grade grade = new Grade(currGrade, gradYear);
		this.gradeList.add(grade);
		if (DEBUG == true) System.out.println("Added Grade " + currGrade + " ,graduating on " + gradYear);
	}
	
	//	REMOVE GRADE
	public void removeGrade(Grade g) {
		this.gradeList.remove(g);
		if (DEBUG == true) System.out.println("Removed Grade " + g.getGrade() + " ,graduating on " + g.getGraduationYear());
	}

	//	GETS
	public ArrayList<Grade> getGradeList() { return this.gradeList; }
	public ArrayList<Student> getAllStudents() {
		ArrayList<Student> tempList = new ArrayList<Student>();
		
		for (Grade g : this.gradeList) {
			for (Student s : g.getStudentList()) {
				tempList.add(s);
			}
		}
		return tempList;
	}
	public ArrayList<Student> getStudentsfromGrade(Grade grade) {
		for(Grade g : this.gradeList) {
			if (g.equals(grade)) {
				return g.getStudentList();
			}
		}
		return null;
	}
	
	
/***************************************
		SUBJECT
***************************************/
	//	ADD SUBJECT
	public void addSubject(String sName, String sCode, Grade g) {
		g.addSubject(new Subject(sName, sCode, g));
		if (DEBUG == true) System.out.println("Added Subject " + sName + ":" + sCode + " to grade " + g.getGrade());
	}
	
	//	REMOVE SUBJECT
	public void removeSubject(Subject s, Grade g) {
		g.removeSubject(s);
		if (DEBUG == true) System.out.println("Removed Subject " + s.getName() + ":" + s.getShortcode() + " to grade " + g.getGrade());
	}
	
	//	GETS
	public ArrayList<Subject> getSubjectList(Grade g) { return g.getSubjectList(); }
	public ArrayList<Student> getAllSubjStudents(Subject s) { return s.getStudentList(); }
	public ArrayList<Student> getSubjStudentsFromGrade(Subject subj, Grade grade) {
		for (Grade g : this.gradeList) {
			for (Subject s : g.getSubjectList()) {
				if (s.equals(subj)) {
					return s.getStudentList();
				}
			}
		}
		return null;
	}
	
/***************************************
		CLASS
***************************************/
	public void addClass(int classID, Subject s, Grade g) {
		s.addClass(classID, g);
		if (DEBUG == true) System.out.println("Added Class " + classID + " to " + s.getName() + ":" + s.getShortcode());
	}
	
	public void removeClass(Class c, Subject s) {
		s.removeClass(c);
		if (DEBUG == true) System.out.println("Removed Class " + c.getClassID() + " to " + s.getName() + ":" + s.getShortcode());
	}
	
	public HashMap<Class, Grade> getClassList(Subject s) { return s.getClassMap(); }
	public ArrayList<Student> getClassStudentList(Class c) { return c.getStudentList(); }
	
	
/***************************************
		STUDENT
***************************************/
	//	ADD STUDENT
	public void addStudent(int stuID, String givenName, String surname, Grade grade) {
		grade.addStudent(new Student(stuID, givenName, surname, grade));
		if (DEBUG == true) System.out.println("Created Student " + givenName + " " + surname + " ID: " + stuID + " and added to grade " + grade.getGrade());
	}
	
	//	REMOVE STUDENT
	public void removeStudent(Student s) {
		s.getGrade().removeStudent(s);
		if (DEBUG == true) System.out.println("REMOVED STUDENT");
	}
	
	//	ENROL STUDENT TO CLASS
	public void enrolStudent(Student stu, Class c) {
		c.addStudent(stu);
		if (DEBUG == true) System.out.println("Enrolled Stsudent " + stu.getGivenName() + " " + stu.getSurname() + ":" + stu.getID() + " into " + c.getClassID());
	}
	
	//	UNENROL STUDENT FROM CLASS
	public void unenrolStudent(Student stu, Class c) {
		c.removeStudent(stu);
		if (DEBUG == true) System.out.println("UNENROLLED STUDENT");
	}
	
	//	GETS
	public HashMap<Class, Subject> getClassList(Student stu) { return stu.getClassMap(); }
	public HashMap<Assessment, Class> getAssessmentList(Student stu) { return stu.getAssMap(); }
	public ArrayList<Subject> getStuSubjList(Student stu) { return (ArrayList<Subject>)stu.getClassMap().values(); }

	
/***************************************
		ASSESSMENT
***************************************/
	//	ADD ASSESSMENT
	//	Maxmark and weighing not used yet
	public void addAssessment(String aName, Double maxMark, Double weighing, Class c) {
		c.addAssessment(aName);
		if (DEBUG == true) System.out.println("ADDED ASSESSMENT");
	}
	
	//	REMOVE ASSESSMENT
	public void removeAssessment(Assessment a, Class c) {
		c.removeAssessment(a, c);
		if (DEBUG == true) System.out.println("REMOVED ASSESSMENT");
	}
	
	//	GETS
	public ArrayList<Assessment> getClassAssessmentList(Class c) {
		return c.getAssessmentList();
	}


	//	DEMO
	//	NEED TO FIX ACCORDING TO API
	
	public void demo() {
		
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
}
