package guiModules;

import java.util.ArrayList;

import umfrage.FragebogenWithAntwortmoeglichkeit;
import user.Solver;

public class FBSSubmissionModul {
	public static boolean submitFBS(Solver requestingUser, FragebogenWithAntwortmoeglichkeit answeredFB, ArrayList<ArrayList<Integer>> antworten){
		try{
			requestingUser.submitFragebogenergebnis(answeredFB, antworten);
		}catch(Exception e){
			return false;
		}
		return true;
	}
}
