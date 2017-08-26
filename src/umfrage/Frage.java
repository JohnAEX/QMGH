package umfrage;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Klasse inkl. Konstruktor der Frage und Getter der FB-Beschreibung, des Fragetyps bzw. der Antwortmöglichkeiten
 * @author marvi
 *
 */
public class Frage implements Serializable{
	private final String fragebeschreibung;
	private final int frageTyp;
	private final ArrayList<String> antwortmoeglichkeiten;
	/**
	 * Konstruktor der Frage
	 * @param fragebeschreibung
	 * @param frageTyp
	 * @param antwortmoeglichkeiten
	 */
	public Frage(String fragebeschreibung, int frageTyp, ArrayList<String> antwortmoeglichkeiten) {
		this.fragebeschreibung = fragebeschreibung;
		this.frageTyp = frageTyp;
		this.antwortmoeglichkeiten = antwortmoeglichkeiten;
	}
	/**
	 * Getter der Fragebogenbeschreibung
	 * @return die Fragebogenbeschreibung
	 */
	public String getFragebeschreibung(){
		return this.fragebeschreibung;
	}
	/**
	 * Getter des Fragetyps
	 * @return den Fragetyp
	 */
	public int getFragetyp(){
		return this.frageTyp;
	}
	/**
	 * Getter der Antwortmöglichkeiten
	 * @return die Antwortmöglichkeiten
	 */
	public ArrayList<String> getAntwortmoeglichkeiten(){
		return this.antwortmoeglichkeiten;
	}
}
