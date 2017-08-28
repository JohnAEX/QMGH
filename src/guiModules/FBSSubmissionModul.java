package guiModules;

import java.util.ArrayList;

import umfrage.FragebogenWithAntwortmoeglichkeit;
import user.Solver;

/**
 * Module directly communicating with the user interface classes. Submits answers from a solvable questionnaire ({@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit}). 
 * 
 * @author Dominik
 *
 */
public class FBSSubmissionModul {
	/**
	 * Submits answers to the specified solvable questionnaire. 
	 * 
	 * @param requestingUser - {@link user.Solver Solver} requesting submission of his answers to a specified solvable questionnaire
	 * @param answeredFB - {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} to be answered by the specified {@link user.Solver Solver}
	 * @param antworten - nested {@link java.util.ArrayList ArrayList} of two dimension containing the answers to be submitted for the specified solvable questionnaire
	 * @return a boolean value stating the method's success <br>
	 * &emsp; true if no runtime-error occurs and the answers could be submitted <br>
	 * &emsp; fals if any runtime-error occurs or the answers could not be submitted
	 */
	public static boolean submitFBS(Solver requestingUser, FragebogenWithAntwortmoeglichkeit answeredFB, ArrayList<ArrayList<Integer>> antworten){
		try{
			if(!requestingUser.submitFragebogenergebnis(answeredFB, antworten)){
				return false;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			//e.printStackTrace();
			return false;
		}
		return true;
	}
}
