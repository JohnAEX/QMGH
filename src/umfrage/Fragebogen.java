package umfrage;

import java.io.Serializable;
import java.util.ArrayList;

import auswertung.Fragebogenauswertung;
/**
 * Klasse inkl. Konstruktor des Fragebogens
 * @author marvi
 *
 */
public class Fragebogen implements Cloneable, Serializable{
	private final  String titel;
	private final String exposee;
	private final ArrayList<Frage> fragen;
	/**
	 * Konstruktor des Fragebogens
	 * @param titel
	 * @param exposee
	 * @param fragen
	 */
	public Fragebogen(String titel, String exposee, ArrayList<Frage> fragen){
		this.titel = titel;
		this.exposee = exposee;
		this.fragen = fragen;
	}
	/**
	 * Klonen des Super-Konstruktors
	 */
	public Object clone() { 
		try { 
			return super.clone(); 
		} catch (Exception e) { 
			return null; 
		} 
	}
	/**
	 * Getter des Titels
	 * @return den Titel
	 */
	public String getTitel(){
		return this.titel;
	}
	/**
	 * Getter des Exposees
	 * @return das Exposee
	 */
	public String getExposee(){
		return this.exposee;
	}
	/**
	 * Getter der Fragen
	 * @return die Fragen
	 */
	public ArrayList<Frage> getFragen(){
		return this.fragen;
	}
	
	/**
	 * Cast des FragebogenWithAntwortmoeglichkeit
	 * @param antwortDestination
	 * @return
	 */
	public FragebogenWithAntwortmoeglichkeit castToFragebogenWithAntwortmoeglichkeit(Fragebogenauswertung antwortDestination){
		return (new FragebogenWithAntwortmoeglichkeit(this.titel, this.exposee, this.fragen, antwortDestination));
	}
}
