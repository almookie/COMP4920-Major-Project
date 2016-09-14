import java.util.ArrayList;

public class Grade {
	private ArrayList<Student> students;
	
	public Grade() {
		
	}
	
	public void addStudent(Student student) {
		 students.add(student);
	}
	
	public void removeStudent(Student student) {
		students.remove(student);
	}
	
	public int getStudentCount() {
		return students.size();
	}
	
	public boolean hasStudent(int ID) {
		for (Student student : students) {
			if (student.getID() == ID) {
				return true;
			}
		}
		
		return false;
	}
	
	public Student getStudent(int ID) {
		for (Student student : students) {
			if (student.getID() == ID) {
				return student;
			}
		}
		
		return null;
	}

}
