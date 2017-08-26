package guiModules;

import java.util.ArrayList;

import umfrage.Fragebogen;
import user.Creator;
/**
 * Modul zum Laden eines "rohen" Fragebogen
 * @author marvi
 *
 */
public class FBLoaderModul {
	/**
	 * Lädt den zum User zugehörigen (rohen) FB
	 * @param requestingUser
	 * @return zum User zugehörigen FB
	 */
	public static ArrayList<Fragebogen> loadFB(Creator requestingUser){
		return requestingUser.getOwnedFrageboegen();
	}
}
