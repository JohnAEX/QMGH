package guiModules;

import umfrage.Fragebogen;
import user.Creator;
/**
 * L�schen eines Fragebogen
 * @author marvi
 *
 */
public class FBRemovalModul {
	/**
	 * L�scht den gew�nschten Fragebogen
	 * @param fbCreator
	 * @param fragebogenreferenz
	 * @return true bei gel�schten FB, false bei Fehler
	 */
	public static boolean removeFB(Creator fbCreator, Fragebogen fragebogenreferenz){
		if(fbCreator.getOwnedFrageboegen().remove(fragebogenreferenz)){
			return true;
		}
		return false;
	}
}
