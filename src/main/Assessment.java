package main;

/***************************************
			IMPORTS
 ***************************************/

import java.util.HashMap;


public class Assessment {
	
	/***************************************
			FIELDS
	 ***************************************/
	
	private String name;
	private double maxMark;
	private double weight;
	private HashMap<Student, Double> marksMap;
	//private double mean, standardDeviation, mode, median, range, weighting;
	
	
	/***************************************
			CONSTRUCTOR
	 ***************************************/
	
	public Assessment(String name, double maxMark, double weight) {
		this.name = name;
		this.maxMark = maxMark;
		this.weight = weight;
		this.marksMap = new HashMap<Student, Double>();
		//mode = median = range =	mean = standardDeviation = weighting = 0;
	}
	

	/***************************************
			PUBLIC METHODS
	 ***************************************/
	
	//	STUDENT
	public void _addStudent(Student stu) { this.marksMap.put(stu, null); }
	public void _removeStudent(Student stu) { this.marksMap.remove(stu); }
	
	//	MARK
	public void _addMark(Student stu, double mark) { this.marksMap.put(stu, mark); }
	public void _removeMark(Student stu) { this.marksMap.remove(stu, null); }
	
	//	GETS
	public String _getName() { return this.name; }
	public Double _getMaxMark() { return this.maxMark; }
	public Double _getWeight() { return this.weight; }
	public HashMap<Student, Double> _getMarksMap() { return this.marksMap; }
	
	//	SETS
	public void _setName(String name) { this.name = name; }
	public void _setMaxMark(double maxMark) { this.maxMark = maxMark; }
	public void _setWeight(double weight) { this.weight = weight; }
	
	
	/***************************************
			PRIVATE METHODS
	 ***************************************/
	
	// TODO: Unfinished
	/**
	 * Recalculates required values
	 */
/*	private void updateStatistics() {
		
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
	}*/
}