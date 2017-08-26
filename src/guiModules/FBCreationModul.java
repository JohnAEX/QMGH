package guiModules;

import java.util.ArrayList;

import umfrage.Frage;
import umfrage.Fragebogen;
import user.Creator;
/**
 * Modul zur Erstellung eines Fragebogens
 * @author marvi
 */
public class FBCreationModul {
	/**
	 * Hinzufügen des erstellten FB zum Creator
	 * @param fbCreator
	 * @param titel
	 * @param exposee
	 * @param fragenListInput
	 * @return true erfolgreicher Erstellung eines FB, false bei Fehler
	 */
	public static boolean createFB(Creator fbCreator, String titel, String exposee, ArrayList<Frage> fragenListInput){
		try{
			ArrayList<Frage> frageList = new ArrayList<Frage>();
			
			fbCreator.addOwnedFragebogen(new Fragebogen(titel, exposee, fragenListInput));
		}catch(Exception e){
			//Falls Fehler bei der Anlage unterläuft
			return false;
		}
		return true;
	}
}
