package guiModules;

import java.util.ArrayList;

import umfrage.Frage;
import umfrage.Fragebogen;
import user.Creator;

/**
 * Module directly communicating with the user interface classes. Creates a questionnaire. 
 * 
 * @author Dominik
 *
 */
public class FBCreationModul {
	/**
	 * Returns a {@link umfrage.Fragebogen Fragebogen} generated from the specified data.
	 * 
	 * @param fbCreator - {@link user.Creator Creator} requesting the creation and storage of a questionnaire. 
	 * @param titel - {@link java.lang.String String} representing the title to the {@link umfrage.Fragebogen Fragebogen} object to be created
	 * @param exposee - {@link java.lang.String String} representing the description to the {@link umfrage.Fragebogen Fragebogen} object to be created
	 * @param fragenListInput - {@link java.util.ArrayList ArrayList} of {@link umfrage.Frage Frage} representing the questions to be included in the questionnaire
	 * @return a boolean value stating the method's success <br>
	 * &emsp; true if no runtime-error occurs <br>
	 * &emsp; false if any runtime error occurs
	 */
	public static boolean createFB(Creator fbCreator, String titel, String exposee, ArrayList<Frage> fragenListInput){
		try{
			fbCreator.addOwnedFragebogen(new Fragebogen(titel, exposee, fragenListInput));
		}catch(Exception e){
			//Falls Fehler bei der Anlage unterläuft
			return false;
		}
		return true;
	}
}
