package guiModules;

import java.util.ArrayList;

import auswertung.Fragebogenauswertung;
import user.Creator;

/**
 * Module directly communicating with the user interface classes. Loads survey results.
 * 
 * @author Dominik
 *
 */
public class FBALoaderModul {
	/**
	 * Returns the {@link java.util.ArrayList ArrayList} of {@link auswertung.Fragebogenauswertung Fragebogenauswertung}<CODE>s</CODE> of the specified {@link user.Creator Creator}. 
	 * 
	 * @param requestingUser - {@link user.Creator Creator} requesting his owned {@link auswertung.Fragebogenauswertung Fragebogenauswertung}<CODE>s</CODE>
	 * @return the {@link java.util.ArrayList ArrayList} of {@link auswertung.Fragebogenauswertung Fragebogenauswertung} objects owned by the requesting {@link user.Creator Creator} 
	 */
	public static ArrayList<Fragebogenauswertung> loadFBA(Creator requestingUser){
		return requestingUser.getOwnedFragebogenauswertungen();
	}
}
