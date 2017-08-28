package umfrage;

import java.util.ArrayList;

import auswertung.Fragebogenauswertung;

/**
 * Representation of questionnaires with a possibility to answer and submit said answer is managed by the {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} class in the QuestionMarkSystem. <br>
 * {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} is child to {@link umfrage.Fragebogen Fragebogen}.
 * The {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} object additionally contains a reference to a {@link auswertung.Fragebogenauswertung Fragebogenauswertung} making it possible to submit answers.
 * 
 * @author Dominik
 *
 */
public class FragebogenWithAntwortmoeglichkeit extends Fragebogen{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Reference to the {@link auswertung.Fragebogenauswertung Fragebogenauswertung} answers submitted through this {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} object are send to
	 */
	private Fragebogenauswertung antwortDestination;
	
	/**
	 * <b><i>FragebogenWithAntwortmoeglichkeit</i></b><br>
	 * &nbsp;&nbsp;&nbsp;<CODE>public FragebogenWithAntwortmoeglichkeit(String titel, String exposee, ArrayList<Frage> fragen, Fragebogenauswertung antwortDestination)</CODE>
	 * constructs a {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} inheriting the constructor of the super class {@link umfrage.Fragebogen Fragebogen}. Additionally sets a destination address ({@link auswertung.Fragebogenauswertung Fragebogenauswertung}) for answers submitted through this {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} object to be send to.
	 * 
	 * @param titel - inherited from {@link umfrage.Fragebogen Fragebogen}
	 * @param exposee - inherited from {@link umfrage.Fragebogen Fragebogen }
	 * @param fragen - inherited from {@link umfrage.Fragebogen Fragebogen }
	 * @param antwortDestination - {@link auswertung.Fragebogenauswertung Fragebogenauswertung} answers are submitted to through this {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} object
	 */
	public FragebogenWithAntwortmoeglichkeit(String titel, String exposee, ArrayList<Frage> fragen, Fragebogenauswertung antwortDestination){
		super(titel, exposee, fragen);
		this.antwortDestination = antwortDestination;
	}
	
	/**
	 * Submits an answer to the {@link auswertung.Fragebogenauswertung Fragebogenauswertung} filed in this {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} object.
	 * @param antwort - nested {@link java.util.ArrayList ArrayList} of two dimensions containing all answers to be submitted
	 */
	public void addAntwort(ArrayList<ArrayList<Integer>> antwort){
		this.antwortDestination.addAntwort(antwort);
	}
	
	
}
