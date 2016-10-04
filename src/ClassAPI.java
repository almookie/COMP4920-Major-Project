/***************************************
			IMPORTS
***************************************/
import java.util.HashMap;


/***************************************
			CLASS
***************************************/
public interface ClassAPI {

	
	/***********************************
	* ADD Class
	* @param classID
	* @param s Subject Object
	* @param g Grade Object
	* @return boolean success or failed
	************************************/
	public boolean addClass(int classID, Subject s, Grade g);
	
	
	/***********************************
	* REMOVE Class
	* @param c Class Object
	* @param s Subject Object
	* @return boolean success or failed
	************************************/
	public boolean removeClass(Class c, Subject s);
	

	/***********************************
	* GET HashMap of Class and Grade given Subject s
	* @param s Subject Object
	* @return HashMap<Class, Grade>
	************************************/
	public HashMap<Class, Grade> getClassList(Subject s);

	
	//	Other methods to be included...
}
