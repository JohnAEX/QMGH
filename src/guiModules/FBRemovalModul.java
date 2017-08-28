package guiModules;

import umfrage.Fragebogen;
import user.Creator;

/**
 * Module directly communicating with the user interface classes. Removes questionnaires. 
 * 
 * @author Dominik
 *
 */
public class FBRemovalModul {
	/**
	 * Removes the specified questionnaire from the requesting {@link user.Creator Creator}<CODE>s</CODE> owned questionnaires.   
	 * 
	 * @param fbCreator - {@link user.Creator Creator} requesting removal of the specified questionnaire
	 * @param fragebogenreferenz - {@link umfrage.Fragebogen Fragebogen} to be removed
	 * @return a boolean value stating the method's success <br>
	 * &emsp; true if the specified questionnaire could be removed <br>
	 * &emsp; false if the specified questionnaire could not be removed
	 */
	public static boolean removeFB(Creator fbCreator, Fragebogen fragebogenreferenz){
		if(fbCreator.getOwnedFrageboegen().remove(fragebogenreferenz)){
			return true;
		}
		return false;
	}
}
