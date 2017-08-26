package guiModules;

import java.util.ArrayList;

import umfrage.FragebogenWithAntwortmoeglichkeit;
import user.Solver;
/**
 * Laden eines lösbaren Fragebogen
 * @author marvi
 *
 */
public class FBSLoaderModul {
	/**
	 * Lädt den gewünschten lösbaren Fragebogen
	 * @param requestingUser
	 * @return einen lösbaren Fragebogen
	 */
	public static ArrayList<FragebogenWithAntwortmoeglichkeit> loadFBS(Solver requestingUser){
		return requestingUser.getActiveFragebogenWithAntwortmoeglichkeit();
	}
}
