package guiModules;

import java.util.ArrayList;

import umfrage.FragebogenWithAntwortmoeglichkeit;
import user.Solver;

/**
 * Module directly communicating with the user interface classes. Loads answerable questionnaires ({@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit}). 
 * 
 * @author Dominik
 *
 */
public class FBSLoaderModul {
	/**
	 * Returns the {@link java.util.ArrayList ArrayList} of {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit}<CODE>s</CODE> of the specified {@link user.Solver Solver}. 
	 * 
	 * @param requestingUser - {@link user.Solver Solver} requesting his owned {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit}<CODE>s</CODE>
	 * @return the {@link java.util.ArrayList ArrayList} of {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} objects owned by the requesting {@link user.Solver Solver} 
	 */
	public static ArrayList<FragebogenWithAntwortmoeglichkeit> loadFBS(Solver requestingUser){
		return requestingUser.getActiveFragebogenWithAntwortmoeglichkeit();
	}
}
