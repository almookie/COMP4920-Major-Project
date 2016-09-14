import java.util.ArrayList;
import java.util.HashMap;

//Jackie has been here
public class Assessment {
	private HashMap<Student, Double> marks; 
	private double mean, standardDeviation, mode, median, range, weighting;
	
	public Assessment(ArrayList<Student> students) {
		mode = median = range =	mean = standardDeviation = weighting = 0;
		marks = new HashMap<Student, Double>();
		
		for (Student student : students) {
			marks.put(student, null);
		}
	}
	
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
		
		for (Student student : marks.keySet()) {
			mark = marks.get(student);
			
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
			
			for (Student student : marks.keySet()) { 
				mark = marks.get(student);
				
				// if a mark hasn't been entered yet, don't count the student in calculations
				if (mark == null) {
					continue;
				}
				
				standardDeviationSum += Math.pow(mark - mean, 2);
			}
			
			standardDeviation = Math.sqrt(standardDeviationSum / count);
		}
	}

	public void addMark(Student student, double mark) {
		marks.put(student, mark);
	}
}
