package auswertung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import umfrage.Frage;
import umfrage.Fragebogen;
import umfrage.FragebogenWithAntwortmoeglichkeit;
import verwaltung.Kurs;
import auswertung.Fragebogenauswertung;

/**
 * 	Storage of survey results is managed by the {@link auswertung.Fragebogenauswertung Fragebogenauswertung} class in the QuestionMark-System.
 *	The {@link auswertung.Fragebogenauswertung Fragebogenauswertung} object contains a {@link umfrage.Fragebogen Fragebogen} for structural recognition.
 *	Also contains a nested list to save all answers submitted to the {@link auswertung.Fragebogenauswertung Fragebogenauswertung} object.
 * 
 * @author Dominik <br>
 *
 */
public class Fragebogenauswertung implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * {@link umfrage.Fragebogen Fragebogen} representation of the questionnaire this {@link auswertung.Fragebogenauswertung Fragebogenauswertung} corresponds to
	 */
	private Fragebogen sourceFragebogen;
	/**
	 * Nested {@link java.util.ArrayList ArrayList} containing all answers submitted by {@link user.User User} in the Fragebogen-System.<br>
	 * The outer {@link java.util.ArrayList ArrayList}<CODE>s</CODE> represent the questions from a {@link umfrage.Fragebogen Fragebogen} in the Fragebogen-System.
	 * The inner {@link java.util.ArrayList ArrayList}<CODE>s</CODE> represent the answer-index(es) for each question.
	 */
	private ArrayList<ArrayList<Integer>> allAntworten;
	
	/**
	 * {@link verwaltung.Kurs Kurs} representation of the course this {@link auswertung.Fragebogenauswertung Fragebogenauswertung} object has been distributed to
	 */
	private Kurs ownedBy;
	
	/**
	 * {@link int} representation of the number of submitted answers to this {@link auswertung.Fragebogenauswertung Fragebogenauswertung} object
	 */
	private int anzahlAntworten;
	
	/**
	 * Returns the {@link int} representation for the count of submitted answers.
	 * @return an {@link int} object containing the number of submitted answers
	 */
	public int getAnzahlAntworten(){
		return this.anzahlAntworten;
	}
	/**
	 * <b><i>Fragebogenauswertung</i></b><br>
	 * &nbsp;&nbsp;&nbsp;<CODE>public Fragebogenauswertung(Fragebogen sourceFragebogen)</CODE>
	 * constructs a {@link auswertung.Fragebogenauswertung Fragebogenauswertung} object with a source {@link umfrage.Fragebogen Fragebogen} object for recognition purposes
	 * and an empty {@link java.util.ArrayList ArrayList} supposed to contain the answers submitted to this  {@link auswertung.Fragebogenauswertung Fragebogenauswertung} object.
	 * @param sourceFragebogen - {@link umfrage.Fragebogen Fragebogen} object representing the corresponding questionnaire of this {@link auswertung.Fragebogenauswertung Fragebogenauswertung}
	 * @param currentKurs - {@link verwaltung.Kurs Kurs} object representing the course this {@link auswertung.Fragebogenauswertung Fragebogenauswertung} object is to be distributed to
	 */
	public Fragebogenauswertung(Fragebogen sourceFragebogen, Kurs currentKurs){
		//Standardbelegung der Variablen
		this.ownedBy = currentKurs;
		this.sourceFragebogen = (Fragebogen) sourceFragebogen.clone();
		this.allAntworten = new ArrayList<ArrayList<Integer>>();
		this.anzahlAntworten = 0;
		
		//Bringt this.allAntworten auf die richtige Größe und belegt es mit ArrayListen
		Iterator<Frage> sourceFragenIt = sourceFragebogen.getFragen().iterator();
		//Platzhalter für die jeweilige Frage
		Frage sourceFrageShell;
		//Loopt durch die Anzahl der Fragen
		while(sourceFragenIt.hasNext()){
			this.allAntworten.add(new ArrayList<Integer>());
			sourceFrageShell = sourceFragenIt.next();
			//Loopt solange in this.allAntworten weniger antwortmoeglichgkeiten sind als in sourceFragebogen 
			while(this.allAntworten.get(this.allAntworten.size()-1).size() < sourceFrageShell.getAntwortmoeglichkeiten().size()){
				//Belegung des Ausgangswertes mit 0
				this.allAntworten.get(this.allAntworten.size()-1).add(0);
			}
		}
		
	}
	/**
	 * Returns the {@link umfrage.Fragebogen Fragebogen} this {@link auswertung.Fragebogenauswertung Fragebogenauswertung} includes.
	 * @return the {@link umfrage.Fragebogen Fragebogen} object this {@link auswertung.Fragebogenauswertung Fragebogenauswertung} includes for reading purposes
	 */
	public Fragebogen getSourceFragebogen(){
		return this.sourceFragebogen;
	}
	/**
	 * Returns the {@link verwaltung.Kurs Kurs} this {@link auswertung.Fragebogenauswertung Fragebogenauswertung} has been distributed to.
	 * @return the {@link verwaltung.Kurs Kurs} object this {@link auswertung.Fragebogenauswertung Fragebogenauswertung} has been distributed to
	 */
	public Kurs getOwnedBy(){
		return this.ownedBy;
	}
	/**
	 * Returns all answers submitted up until the point of invocation of this method. The number of times the option for each question has been chosen is stored in an {@link java.lang.Integer Integer} object.
	 * @return a nested {@link java.util.ArrayList ArrayList} of two dimensions containing all answers submitted up until the point of invocation of this method
	 */
	public ArrayList<ArrayList<Integer>> getAllAntworten(){
		return this.allAntworten;
	}
	
	/**
	 * Adds one {@link java.util.ArrayList ArrayList} of {@link java.lang.String String}<CODE>s</CODE> to the {@link java.util.ArrayList ArrayList} of all answers. 
	 * <br>Also increments the count of submitted answers.
	 * @param submittedAntworten - {@link java.util.ArrayList ArrayList} of answers to be added to the {@link auswertung.Fragebogenauswertung Fragebogenauswertung} object
	 */
	/*
	 * Indize der zweiten Dimension der ArrayList allAntworten entsprechen den Indize der Antwortmöglichkeiten
	 * Von den eingesendeten Antworten werden ausgewählten Integer genutzt, um das Element der entsprechenden Stelle der ArrayList allAntworten zu inkrementieren 
	 */
	public void addAntwort(ArrayList<ArrayList<Integer>> submittedAntworten){
		Iterator<ArrayList<Integer>> targetAntwortenIt = this.allAntworten.iterator();
		Iterator<ArrayList<Integer>> submittedAntwortenIt = submittedAntworten.iterator();
		ArrayList<Integer> targetFrageShell;
		
		this.anzahlAntworten++;
		
		Iterator<Integer> submittedAntwortmoeglichkeitenIt;
		Integer antwortmoeglichkeitIndex;
		
		while(targetAntwortenIt.hasNext()){
			//Platzhalter für die Zieladresse der Frage
			targetFrageShell = targetAntwortenIt.next();
			//Platzhalter für Iterator der Antwortmoeglichkeiten
			submittedAntwortmoeglichkeitenIt = submittedAntwortenIt.next().iterator();
			//Loopt durch alle möglichen Antwortmoeglichkeiten
			while(submittedAntwortmoeglichkeitenIt.hasNext()){
				//Platzhalter für die zu inkrementierende Antwortmoeglichkeit
				antwortmoeglichkeitIndex = submittedAntwortmoeglichkeitenIt.next();
				//Inkrementierung
				targetFrageShell.set(antwortmoeglichkeitIndex-1, targetFrageShell.get(antwortmoeglichkeitIndex-1)+1); //Last Argument was +1
				
			}
		
		}
		
		
	}
	
	/**
	 * Returns a {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} upon specification of a {@link auswertung.Fragebogenauswertung Fragebogenauswertung} as destination for submission of answers.
	 * @param antwortDestination - {@link auswertung.Fragebogenauswertung Fragebogenauswertung} object representing the destination for answers to be submitted to this {@link umfrage.Fragebogen Fragebogen} object
	 * @return a {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} object linked to the specified {@link auswertung.Fragebogenauswertung Fragebogenauswertung} for submission
	 */
	public FragebogenWithAntwortmoeglichkeit castToFragebogenWithAntwortmoeglichkeit(){
		return (new FragebogenWithAntwortmoeglichkeit(this.getSourceFragebogen().getTitel(), this.getSourceFragebogen().getExposee(), this.getSourceFragebogen().getFragen(), this));
	}
}



