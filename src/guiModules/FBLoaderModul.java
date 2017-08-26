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
	 * L�dt den zum User zugeh�rigen (rohen) FB
	 * @param requestingUser
	 * @return zum User zugeh�rigen FB
	 */
	public static ArrayList<Fragebogen> loadFB(Creator requestingUser){
		return requestingUser.getOwnedFrageboegen();
	}
}
