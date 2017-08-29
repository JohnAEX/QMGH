package guiModules;

import java.util.ArrayList;

import umfrage.Fragebogen;
import user.Creator;

/**
 * Module directly communicating with the user interface classes. Loads questionnaires.
 * 
 * @author Dominik
 *
 */
public class FBLoaderModul {
	/**
	 * Returns the {@link java.util.ArrayList ArrayList} of {@link umfrage.Fragebogen Fragebogen}<CODE>s</CODE> of the specified {@link user.Creator Creator}. 
	 * 
	 * @param requestingUser - {@link user.Creator Creator} requesting his owned {@link umfrage.Fragebogen Fragebogen}<CODE>s</CODE>
	 * @return the {@link java.util.ArrayList ArrayList} of {@link umfrage.Fragebogen Fragebogen} objects owned by the requesting {@link user.Creator Creator} 
	 */
	public static ArrayList<Fragebogen> loadFB(Creator requestingUser){
		return requestingUser.getOwnedFrageboegen();
	}
}
