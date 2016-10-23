package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Assessment {
	private HashMap<Student, Double> marks; 
	private double mean, standardDeviation, median, range, weighting, mode;
	private String name;
	private int id;
	
	public Assessment(int id, String name, double weighting, ArrayList<Student> students) {
		median = range = mean = standardDeviation = mode = 0;
		this.name = name;
		this.id = id;
		this.weighting = weighting;
		
		marks = new HashMap<Student, Double>();
		
		for (Student student : students) {
			marks.put(student, 0.0);
		}
	}
	
	public int getID() {
		return this.id;
	}
	
	// TODO: Unfinished
	/**
	 * Recalculates required values
	 */
	//	Temporailiy public (was private)
	//	Need to make it public for demo, can change later.
	public void updateStatistics() {
		
		double sum = 0;
		int totalCount = 0;
		HashMap<Double, Integer> marksMap = new HashMap<Double, Integer>();
		ArrayList<Double> markList = new ArrayList<Double>();
		
		//	Iterate through all the marks
		for (Double mark : marks.values()) {
			sum += mark;
			totalCount++;
			
			//	If that mark exists [+ 1]
			if (marksMap.containsKey(mark)) {
				marksMap.put(mark, marksMap.get(mark) + 1);
			
			//	If mark doesn't exist [set = 1]
			} else {
				marksMap.put(mark, 1);
			}
			
			//	Add mark to the list (used to find Median)
			markList.add(mark);
	
		}
		
		
		//	Mean
		//	[sum/count]
		mean = sum/totalCount;
		
		//	Mode
		Integer highest = 0;
		ArrayList<Double> mode_mark = new ArrayList<Double>();
		
		for (Map.Entry<Double, Integer> element : marksMap.entrySet()) {
			
			Double mark = element.getKey();
			Integer freq = element.getValue();
			
			if (freq == highest) {
				mode_mark.add(mark);
				System.out.println("SAME " + mark);
			} else if(freq > highest) {
				highest = freq;
				mode_mark = new ArrayList<Double>();
				mode_mark.add(mark);
				System.out.println("New " + mark);
			}
		}
		
		//	[Most Frequent Mark]
		mode = mode_mark.get(0);
		
		
		//	Median
		//	[Middle Mark]
		Collections.sort(markList);
		
		if (totalCount % 2 == 0) {
			double m1 = markList.get(totalCount/2 - 1);
			double m2 =  markList.get(totalCount/2);
			median = (m1 + m2) / 2;
		} else {
			median = markList.get((totalCount + 1) / 2 - 1);
		}
		
		
		//	Range
		//	[Max - Min]
		range = markList.get(markList.size() - 1) - markList.get(0);
		
		
		//	Variance (needed for Std Dev)
		Double temp = 0.0;
		
		for (Double mark : markList) {
			temp += (mark - mean)*(mark - mean);
		}
		
		Double variance = temp/totalCount;
		
		// 	Standard Deviation
		standardDeviation = Math.sqrt(variance);

		/*// declare required variables for calculations 
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
		}*/
	}
	
	public double getWeighting() {
		return weighting;
	}
	
	public void setWeighting(double weighting) {
		this.weighting = weighting;
	}
	
	public double getMark(Student s) {
		return marks.get(s);
	}

	public void addMark(Student student, double mark) {
		marks.put(student, mark);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getMode() {
		return mode;
	}
	
	public double getMean() {
		return mean;
	}
	
	public double getRange() {
		return range;
	}
	
	public double getStandardDeviation() {
		return standardDeviation;
	}
	
	public String toString() {
		String output = "Assessment with name: " + name + " and the statistics:\n";
		output += "Mode: " + mode + "\n";
		output += "Median: " + median + "\n";
		output += "Mean: " + mean + "\n";
		output += "Range: " + range + "\n";
		output += "Standrd Deviation: " + standardDeviation + "\n";
		output += "\nand Weighting: " + weighting + "\n";
		
		return output;
	}

	public double getMedian() {
		return median;
	}
	
	public HashMap<Student, Double> getMarks() {
		return marks;
	}

	public void removeStudent(Student student) {
		marks.remove(student);		
	}

	public void addStudents(ArrayList<Student> students) {
		for (Student s : students) {
			marks.put(s, 0.0);
		}
	}
}