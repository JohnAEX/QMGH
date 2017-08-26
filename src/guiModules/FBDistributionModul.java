package guiModules;

import java.util.ArrayList;

import umfrage.Fragebogen;
import upper.containertier.Gesamtsystem;
import user.Creator;
import verwaltung.Kurs;
/**
 * Modul zur Verteilung eines Fragebogens
 * @author marvi
 *
 */
public class FBDistributionModul {
	/**
	 * Verteilen des gewünschten FB an gewünschten Kurs
	 * @param targetSystem
	 * @param kursA
	 * @param fbToBeDistributed
	 * @param requestingUser
	 * @return true bei Erfolg der verteilung des FB, bei Fehler wird false sowie die StackTrace ausgegeben
	 */
	//Hier müssen 2 Kurse (Indize 0, 1) vorhanden sein...
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
