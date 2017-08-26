package guiModules;

import java.util.ArrayList;

import umfrage.Frage;
/**
 * Modul zur Erstellung einer Frage
 * @author marvi
 *
 */
public class FCreationModul {
	/**
	 * Erstellt eine Frage
	 * Titel, Fragetyp und Antwortmöglichkeiten werden festgelegt
	 * @param frageTyp
	 * @param titel
	 * @param antwortmoeglichkeiten
	 * @return Die erstellte Frage
	 */
	public static Frage createFrage(int frageTyp, String titel, ArrayList<String> antwortmoeglichkeiten){
		return (new Frage(titel, frageTyp, antwortmoeglichkeiten));
	}
}
