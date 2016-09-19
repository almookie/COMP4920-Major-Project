import java.util.ArrayList;

public class Markbook {

	public static void main(String args[]) {
		
		// method for creating random temporary data
		final int startingYear = 2016;
		final int endYear = 2022;
		
		ArrayList<Grade> grades = new ArrayList<Grade>();
		
		// generate a number of grades
		for (int i = startingYear; i <= endYear; i++) {
			Grade grade = new Grade(i);
			grades.add(grade);
		}
		
		// generate students for each grade
		int studentID = 0;
		for (Grade g : grades) {
			for (int i = 0; i < 10; i++) {
				Student s = new Student(studentID, "Name " + studentID, "Surname " + studentID);
				studentID++;				
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
		
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		for (int i = 0; i <= subject_names.length; i++) {
			Subject subject = new Subject(subject_names[i], subject_shortcodes[i]);
			subjects.add(subject);
		}
		
		// TODO: Unfinished
		
		int Min = 5;
		int Max = 15;
		int random_value = Min + (int)(Math.random() * ((Max - Min) + 1));
	}
}
