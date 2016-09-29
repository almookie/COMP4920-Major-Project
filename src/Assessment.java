/***************************************
		IMPORTS
 ***************************************/
import java.util.ArrayList;
import java.util.HashMap;


public class Assessment {

	
	/***************************************
			FIELDS
	 ***************************************/
	private String assignTitle;
	private HashMap<Student, Double> marksMap;
	private double mean, standardDeviation, mode, median, range, weighting;
	
	
	/***************************************
			CONSTRUCTOR
	 ***************************************/
	public Assessment(String name) {
		this.assignTitle = name;
		mode = median = range =	mean = standardDeviation = weighting = 0;
		this.marksMap = new HashMap<Student, Double>();
	}
	

	/***************************************
			PUBLIC METHODS
	 ***************************************/
	//	STUDENT
	public void addStudent(Student stu) { this.marksMap.put(stu, null); }
	public void removeStudent(Student stu) { this.marksMap.remove(stu); }
	
	//	MARK
	public void addMark(Student stu, Double mark) { this.marksMap.put(stu, mark); }
	public void removeMark(Student stu) { this.marksMap.remove(stu, null); }
	
	//	GETS
	public HashMap<Student, Double> getmarksMap() { return this.marksMap; }
	public String getAssignName() { return this.assignTitle; }
	
	//	SETS
	public void setAssignName(String name) { this.assignTitle = name; }
	
	
	/***************************************
			PRIVATE METHODS
	 ***************************************/
	
	// TODO: Unfinished
	/**
	 * Recalculates required values
	 */
	private void updateStatistics() {
		
		// declare required variables for calculations 
		Double mark;
		double count, sum; 
		count = sum = 0;
		double lowest = Double.MIN_VALUE;
		double highest = Double.MAX_VALUE;
		
		for (Student student : marksMap.keySet()) {
			mark = marksMap.get(student);
			
			// if a mark hasn't been entered yet, don't count the student in calculations
			if (mark == null) {
				continue;
			}
			
			count++;
			sum += mark;
			lowest = (lowest < mark) ? lowest : mark;
			highest = (highest > mark) ? highest : mark;
		}
		
		// if there are marks for us to use, calculate the statistics
		if (count > 0) {
			mean = sum / count;
			range = highest - lowest;
			
			
			// calculate the standard deviation
			double standardDeviationSum = 0;
			
			for (Student student : marksMap.keySet()) { 
				mark = marksMap.get(student);
				
				// if a mark hasn't been entered yet, don't count the student in calculations
				if (mark == null) {
					continue;
				}
				
				standardDeviationSum += Math.pow(mark - mean, 2);
			}
			
			standardDeviation = Math.sqrt(standardDeviationSum / count);
		}
	}	
}