package guiModules;

import java.util.ArrayList;

import umfrage.FragebogenWithAntwortmoeglichkeit;
import user.Solver;
/**
 * "Einreichen" eines beantworteten Fragebogens
 * @author marvi
 *
 */
public class FBSSubmissionModul {
	/**
	 * Gibt den beantworteten Fragebogen ab
	 * @param requestingUser
	 * @param answeredFB
	 * @param antworten
	 * @return true bei eingereichtem Fragebogen, false bei Fehler
	 */
	public static boolean submitFBS(Solver requestingUser, FragebogenWithAntwortmoeglichkeit answeredFB, ArrayList<ArrayList<Integer>> antworten){
		try{
			requestingUser.submitFragebogenergebnis(answeredFB, antworten);
		}catch(Exception e){
			System.out.println(e.getMessage());
			//e.printStackTrace();
			return false;
		}
		return true;
	}
}
