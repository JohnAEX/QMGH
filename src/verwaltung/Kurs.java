package verwaltung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import auswertung.Fragebogenauswertung;
import umfrage.Fragebogen;
import user.Solver;

/**
 *  
 * @author Dominik<br><br>
 * 	The <CODE>Kurs</CODE> class contains an <CODE>ArrayList</CODE> of <CODE>Solver</CODE> <i>(pl.)</i>.<br>
 * 	The <CODE>Kurs</CODE> class is one of the <CODE>verwaltung</CODE> <i>(pl.)</i> classes bundling user groups for easier access. 
 * 	Distribution of data to the {@link user.Solver} <i>(pl.)</i> in the Fragebogen-system is managed through the <CODE>Kurs</CODE> class.
 *
 */
public class Kurs implements Serializable{
	/**
	 * display name of the <CODE>Kurs</CODE> object
	 */
	private final String kursName;
	/**
	 * <CODE>ArrayList</CODE> of <CODE>Solver</CODE> <i>(pl.)</i> participating in the <CODE>Kurs</CODE>
	 */
	public final ArrayList<Solver> activeSolvers;
	/**
	 * <CODE>ArrayList</CODE> of <CODE>Fragebogen</CODE> distributed to the <CODE>Kurs</CODE> object
	 */
	private ArrayList<Fragebogen> activeFrageboegen;
	
	/**
	 * Konstruktor des Kurses
	 * <b><i>Kurs</i></b><br>
	 * &nbsp;&nbsp;&nbsp;<CODE>public Kurs(String kursName, ArrayList<Solver> activeSolvers)</CODE>
	 * constructs a <CODE>Kurs</CODE> with a display name and an <CODE>ArrayList</CODE> of {@link user.Solver Solver} <i>(pl.)</i>
	 * 
	 * @param kursName - display name of the {@link verwaltung.Kurs} instance
	 * @param activeSolvers - <CODE>ArrayList</CODE> of {@link user.Solver Solver} <i>(pl.)</i> participating in the {@link verwaltung.Kurs Kurs}
	 */
	public Kurs(String kursName, ArrayList<Solver> activeSolvers){
		this.kursName = kursName;
		this.activeSolvers = activeSolvers;
		this.activeFrageboegen = new ArrayList<Fragebogen>();
	}
	/**
	 * Returns a {@link java.lang.String String}
	 * @return a {@link java.lang.String String} containing the courses' name 
	 */
	public String getKursName(){
		return this.kursName;
	}	
	
	/**
	 * Relays a {@link umfrage.Fragebogen Fragebogen} object to all {@link user.Solver Solver} <i>(pl.)</i> the {@link verwaltung.Kurs Kurs} object contains
	 * @param antwortDestination - {@link auswertung.Fragebogenauswertung Fragebogenauswertung} object to be be accessible for the {@link user.Solver Solver} <i>(pl.)</i> participating in the {@link verwaltung.Kurs Kurs}
	 */
	public void relayFragebogenToAllSolvers(Fragebogenauswertung antwortDestination){
		//Fügt dem Kurs eine Umfrage hinzu
		Fragebogen activeFragebogen = antwortDestination.getSourceFragebogen(); //Referenz mit Objekt überschrieben
		this.activeFrageboegen.add(activeFragebogen);
		//Kp wie ArrayList.foreach funktioniert, sonst hätte ich das benutzt
		Iterator<Solver> activeSolverIt = this.activeSolvers.iterator();
		while(activeSolverIt.hasNext()){
			//Gibt den Solver Objekten Zugriff auf den Fragebogen aus dem Kurs weiter und fügt die Auswertungsreferenz hinzu
			activeSolverIt.next().addActiveFragebogenWithAntwortmoeglichkeit(this.activeFrageboegen.get(this.activeFrageboegen.size()-1).castToFragebogenWithAntwortmoeglichkeit(antwortDestination));
		}
	}

}
