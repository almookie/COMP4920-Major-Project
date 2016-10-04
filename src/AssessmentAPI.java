import java.util.ArrayList;

/***************************************
			ASSESSMENT
***************************************/
public interface AssessmentAPI {
	
	
	/***********************************
	* ADD Assessment from Class
	* @param aName assessment name
	* @param maxMark maximum possible mark
	* @param weighing weight of assessment
	* @param c Class Object
	* @return boolean success or failed
	************************************/
	public boolean addAssessment(String aName, Double maxMark, Double weighing, Class c);
	

	/***********************************
	* REMOVE Assessment from Class
	* @param a Assessment Object
	* @param c Class Object
	* @return boolean success or failed
	************************************/
	public boolean removeAssessment(Assessment a, Class c);
	

	/***********************************
	* GET ArrayList of Assessments in Class
	* @param c Class Object
	* @return ArrayList<Assessment>
	************************************/
	public ArrayList<Assessment> getClassAssessmentList(Class c);
	
	
	//	Other methods to be included...
}
