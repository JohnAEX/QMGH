package guiModules;

import java.util.ArrayList;

import auswertung.Fragebogenauswertung;
import user.Creator;
/**
 * Modul zum Laden der zum User zugehörigen Auswertung des Fragebogens
 * @author marvin
 */
public class FBALoaderModul {
	/**
	 * @param requestingUser
	 * @return zum User zugehörige Fragebogenauswertung
	 */
	public static ArrayList<Fragebogenauswertung> loadFBA(Creator requestingUser){
		return requestingUser.getOwnedFragebogenasuwertungen();
	}
}
