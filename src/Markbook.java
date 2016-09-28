/***************************************
		IMPORTS
***************************************/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Markbook {

	
	/***************************************
			MAIN
	 ***************************************/
	public static void main(String args[]) {
		Markbook school = new Markbook();
		school.demo();
	}
	
	private void demo() {
		
		// method for creating random temporary data
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
				for(Class c : s.getClasses()) {
					//Assessment ass = new Assessment(s.getStudents());
					//	TODO: finish demo soon				}
			}
		}
		
	}
}
