package auswertung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import umfrage.Frage;
import umfrage.Fragebogen;
import auswertung.Fragebogenauswertung;

/**
 *  @author Dominik <br>
 *	The <CODE>Fragebogenauswertung</CODE> contains a {@link umfrage.Fragebogen Fragebogen} for structural recognition of an {@link umfrage umfrage}.
 *	The <CODE>Fragebogenauswertung</CODE> contains a nested {@link java.util.ArrayList ArrayList} to save all answers submitted to the <CODE>Fragebogenauswertung</CODE> object.
 *	Storage of survey results in the Fragebogen-System is managed by the <CODE>Fragebogenasuwertung</CODE> class.
 */
public class Fragebogenauswertung implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * {@link umfrage.Fragebogen Fragebogen} representing and assisting the structure of answers
	 */
	private Fragebogen sourceFragebogen;
	/**
	 * Nested {@link java.util.ArrayList ArrayList} containing all answers submitted by {@link user.User User} in the Fragebogen-System.<br>
	 * The outer {@link java.util.ArrayList ArrayList}<CODE>s</CODE> represent the questions from a {@link umfrage.Fragebogen Fragebogen} in the Fragebogen-System.
	 * The inner {@link java.util.ArrayList ArrayList}<CODE>s</CODE> represent the answer-index(es) for each question.
	 */
	private ArrayList<ArrayList<Integer>> allAntworten;
	
	private int anzahlAntworten;
	/**
	 * <b><i>Fragebogenauswertung</i></b><br>
	 * &nbsp;&nbsp;&nbsp;<CODE>public Fragebogenauswertung(Fragebogen sourceFragebogen)</CODE>
	 * constructs a <CODE>Fragebogenauswertung</CODE> with a source {@link umfrage.Fragebogen Fragebogen} for recognition purposes
	 * and an empty {@link java.util.ArrayList ArrayList} containing {@link java.util.ArrayList ArrayList} of answers submitted.
	 * @param sourceFragebogen - {@link umfrage.Fragebogen Fragebogen} representing and assisting the structure of answers
	 */
	public Fragebogenauswertung(Fragebogen sourceFragebogen){
		this.sourceFragebogen = (Fragebogen) sourceFragebogen.clone();
		this.allAntworten = new ArrayList<ArrayList<Integer>>();
		this.anzahlAntworten = 0;
		
		//Bringt this.allAntworten auf die richtige Größe
		Iterator<Frage> sourceFragenIt = sourceFragebogen.getFragen().iterator();
		Frage sourceFrageShell;
		//Loopt durch die Anzahl der Fragen

		while(sourceFragenIt.hasNext()){
			this.allAntworten.add(new ArrayList<Integer>());
			sourceFrageShell = sourceFragenIt.next();
			//Loopt solange in this.allAntworten weniger Antwortmoeglichgkeiten sind als in sourceFragebogen 
			while(this.allAntworten.get(this.allAntworten.size()-1).size() < sourceFrageShell.getAntwortmoeglichkeiten().size()){
				this.allAntworten.get(this.allAntworten.size()-1).add(0);
			}
		}
		
	}
	/**
	 * Returns the object reference of the source {@link umfrage.Fragebogen Fragebogen}.
	 * @return a {@link umfrage.Fragebogen Fragebogen} object for reading purposes
	 */
	public Fragebogen getSourceFragebogen(){
		return this.sourceFragebogen;
	}
	/**
	 * 
	 * @return alle Antworten
	 */
	public ArrayList<ArrayList<Integer>> getAllAntworten(){
		return this.allAntworten;
	}
	
	/**
	 * Adds one {@link java.util.ArrayList ArrayList} of {@link java.lang.String String}<CODE>s</CODE> to the {@link java.util.ArrayList ArrayList} of all answers.
	 * @param antwort - {@link java.util.ArrayList ArrayList} to be added to the <CODE>Fragebogenauswertung</CODE>
	 */
	public void addAntwort(ArrayList<ArrayList<Integer>> submittedAntworten){
		System.out.println("submissionSize=="+ submittedAntworten.size());
		//Antwort und Frage haben exakt dieselbe Struktur!
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
}



