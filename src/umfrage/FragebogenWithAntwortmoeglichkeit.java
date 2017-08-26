package umfrage;

import java.util.ArrayList;

import auswertung.Fragebogenauswertung;
/**
 * Klasse inkl. Konstruktor des Fragebogen mit Antwortmöglichkeit sowie der Möglichkeit eine Antowrt hinzuzufügen
 * @author marvi
 *
 */
public class FragebogenWithAntwortmoeglichkeit extends Fragebogen{
	private Fragebogenauswertung antwortDestination;
	/**
	 * Konstruktor des FragebogenWithAntwortmoeglichkeit
	 * @param titel
	 * @param exposee
	 * @param fragen
	 * @param antwortDestination
	 */
	public FragebogenWithAntwortmoeglichkeit(String titel, String exposee, ArrayList<Frage> fragen, Fragebogenauswertung antwortDestination){
		super(titel, exposee, fragen);
		this.antwortDestination = antwortDestination;
	}
	/**
	 * Hinzufügen einer Antwort
	 * @param antwort
	 */
	public void addAntwort(ArrayList<ArrayList<Integer>> antwort){
		this.antwortDestination.addAntwort(antwort);
	}
	
	
}
