package guiModules;

import java.util.ArrayList;

import umfrage.Fragebogen;
import upper.containertier.Gesamtsystem;
import user.Creator;
import verwaltung.Kurs;

/**
 * Module directly communicating with the user interface classes. Distributes questionnaires.
 * 
 * @author Dominik
 *
 */
public class FBDistributionModul {
	//Exactly two courses of index 0 and 1 in the current version of the system --> specific implementation
	/**
	 * Distributes a questionnaire to the specified course. 
	 * 
	 * @param targetSystem - {@link upper.containertier.Gesamtsystem Gesamtsystem} to be searched for courses
	 * @param kursA - {@link boolean} value representing the course questionnaires are to be distributed to
	 * @param fbToBeDistributed - {@link umfrage.Fragebogen Fragebogen} to be distributed to the specified course
	 * @param requestingUser - {@link user.Creator Creator} requesting distribution of the specified questionnaire to the specified course
	 * @return a boolean value stating the method's success <br>
	 * &emsp; true if no runtime-error occurs <br>
	 * &emsp; false if any runtime-error occurs
	 */
	public static boolean distributeFB(Gesamtsystem targetSystem, boolean kursA, Fragebogen fbToBeDistributed, Creator requestingUser){
		try{
			ArrayList<Kurs> kursList = targetSystem.getAllKurse();
			if(kursA){
				requestingUser.distributeFragebogenTo(kursList.get(0), fbToBeDistributed);
			}else{
				requestingUser.distributeFragebogenTo(kursList.get(1), fbToBeDistributed);
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
