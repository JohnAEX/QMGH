package guiModules;

import umfrage.Fragebogen;
import user.Creator;
/**
 * Löschen eines Fragebogen
 * @author marvi
 *
 */
public class FBRemovalModul {
	/**
	 * Löscht den gewünschten Fragebogen
	 * @param fbCreator
	 * @param fragebogenreferenz
	 * @return true bei gelöschten FB, false bei Fehler
	 */
	public static boolean removeFB(Creator fbCreator, Fragebogen fragebogenreferenz){
		if(fbCreator.getOwnedFrageboegen().remove(fragebogenreferenz)){
			return true;
		}
		return false;
	}
}
