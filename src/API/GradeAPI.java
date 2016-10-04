package API;
/***************************************
			IMPORTS
 ***************************************/
import java.util.ArrayList;

import Grade;


/***************************************
			GRADE
 ***************************************/
public interface GradeAPI {
	
	
	/***********************************
	* ADD Grade
	* @param currGrade current Grade
	* @param gradYear graduating year
	* @return boolean success or failed
	************************************/
	public boolean addGrade(int currGrade, int gradYear);
	
	
	/***********************************
	 * REMOVE Grade
	 * @param g Grade Object
	 * @return boolean success or failed
	 ***********************************/
	public boolean removeGrade(Grade g);
	
	
	/***********************************
	 * GET ArrayList of Grade in School
	 * @return ArrayList of Grade
	 ***********************************/
	public ArrayList<Grade> getGradeList();
	
	
	//	Other methods to be included...
	
	//	Specific gets?
	/*
	*//***********************************
	 * GET a Grade by currGrade
	 * @return Grade
	 ***********************************//*
	public Grade getGradebyCurrGrade(int currGrade);
	
	
	*//***********************************
	 * GET a Grade by graduating year
	 * @return Grade
	 ***********************************//*
	public Grade getGradebyGradYear(int gradYear);
	*/
	
}