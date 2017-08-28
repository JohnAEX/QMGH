package umfrage;

import java.io.Serializable;
import java.util.ArrayList;

import auswertung.Fragebogenauswertung;

/**
 * Representation of questionnaires is managed by the {@link umfrage.Fragebogen Fragebogen} class in the QuestionMark-System. <br>
 * {@link umfrage.Fragebogen Fragebogen} is parent to {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit}. 
 * The {@link umfrage.Fragebogen Fragebogen} object contains title and description to the represented questionnaire. Also contains a list of questions.
 * 
 * @author Dominik <br>
 *
 */
public class Fragebogen implements Cloneable, Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * {@link java.lang.String String} representation of the title to this {@link umfrage.Fragebogen Fragebogen} object
	 */
	private final String titel;
	/**
	 * {@link java.lang.String String} representation of the description to this {@link umfrage.Fragebogen Fragebogen} object
	 */
	private final String exposee;
	/**
	 * {@link java.util.ArrayList ArrayList} representation of the {@link umfrage.Frage Frage} objects part to this {@link umfrage.Fragebogen Fragebogen} object
	 */
	private final ArrayList<Frage> fragen;
	
	/**
	 * <b><i>Fragebogen</i></b><br>
	 * &nbsp;&nbsp;&nbsp;<CODE>public Fragebogen(String titel, String exposee, ArrayList<Frage> fragen)</CODE>
	 * constructs a {@link umfrage.Fragebogen Fragebogen} object with the required descriptions and questions set for interpretation. 
	 * 
	 * @param titel - {@link java.lang.String String} object representing the title to this {@link umfrage.Fragebogen Fragebogen} object
	 * @param exposee - {@link java.lang.String String} object representing the description to this {@link umfrage.Fragebogen Fragebogen} object
	 * @param fragen - {@link java.util.ArrayList ArrayList} object of {@link umfrage.Frage Frage}<CODE>s</CODE> part to this {@link umfrage.Fragebogen Fragebogen} object
	 */
	public Fragebogen(String titel, String exposee, ArrayList<Frage> fragen){
		this.titel = titel;
		this.exposee = exposee;
		this.fragen = fragen;
	}
	
	/**
	 * Reimplementation of the .clone()-method for objects
	 * see more via {@link java.lang.Object}.
	 */
	/*
	 * for a reason unknown to the project group not accesible without reimplementation
	 */
	
	public Object clone() { 
		try { 
			return super.clone(); 
		} catch (Exception e) { 
			return null; 
		} 
	}
	
	/**
	 * Returns the {@link java.lang.String String} representing the title to this {@link umfrage.Fragebogen Fragebogen} object.
	 * @return the {@link java.lang.String String} object describing the title to this {@link umfrage.Fragebogen Fragebogen} object
	 */
	public String getTitel(){
		return this.titel;
	}
	/**
	 * Returns the {@link java.lang.String String} describing this {@link umfrage.Fragebogen Fragebogen} object.
	 * @return the {@link java.lang.String String} object describing this {@link umfrage.Fragebogen Fragebogen} object
	 */
	public String getExposee(){
		return this.exposee;
	}
	/**
	 * Returns the {@link java.util.ArrayList ArrayList} of {@link umfrage.Frage Frage}<CODE>s</CODE> part to this {@link umfrage.Fragebogen Fragebogen} object
	 * @return the {@link java.util.ArrayList ArrayList} object of {@link umfrage.Frage Frage} objects part to this {@link umfrage.Fragebogen Fragebogen} object
	 */
	public ArrayList<Frage> getFragen(){
		return this.fragen;
	}
	
	/**
	 * Returns a {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} upon specification of a {@link auswertung.Fragebogenauswertung Fragebogenauswertung} as destination for submission of answers.
	 * @param antwortDestination - {@link auswertung.Fragebogenauswertung Fragebogenauswertung} object representing the destination for answers to be submitted to this {@link umfrage.Fragebogen Fragebogen} object
	 * @return a {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} object linked to the specified {@link auswertung.Fragebogenauswertung Fragebogenauswertung} for submission
	 */
	public FragebogenWithAntwortmoeglichkeit castToFragebogenWithAntwortmoeglichkeit(Fragebogenauswertung antwortDestination){
		return (new FragebogenWithAntwortmoeglichkeit(this.titel, this.exposee, this.fragen, antwortDestination));
	}
}
