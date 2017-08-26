package guiModules;

import java.util.ArrayList;

import umfrage.FragebogenWithAntwortmoeglichkeit;
import user.Solver;
/**
 * Laden eines l�sbaren Fragebogen
 * @author marvi
 *
 */
public class FBSLoaderModul {
	/**
	 * L�dt den gew�nschten l�sbaren Fragebogen
	 * @param requestingUser
	 * @return einen l�sbaren Fragebogen
	 */
	public static ArrayList<FragebogenWithAntwortmoeglichkeit> loadFBS(Solver requestingUser){
		return requestingUser.getActiveFragebogenWithAntwortmoeglichkeit();
	}
}
