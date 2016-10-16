package main;
import java.util.HashMap;

public class Report {
	private Student student;
	private HashMap<Subject, String> comments;
	
	public Report(Student student) {
		this.student = student;
	}
	
	public void addComment(Subject s, String comment) {
		comments.put(s, comment);
	}
	
	public String getComment(Subject s) {
		return comments.get(s);
	}
}
