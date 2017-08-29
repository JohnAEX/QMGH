package umfrage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representation of questions is managed by the {@link umfrage.Frage Frage} class in the QuestionMark-System. 
 * The {@link umfrage.Frage Frage} object contains a description to the question and it's question type. Also contains a list of options for answers.
 * 
 * @author Dominik <br>
 *
 */
public class Frage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * {@link java.lang.String String} representation of the description to this {@link umfrage.Frage Frage} object
	 */
	private final String fragebeschreibung;
	/**
	 * {@link int} representation of the question type to this {@link umfrage.Frage Frage} object<br>
	 * <CODE>
	 * Question types:<br>
	 * &emsp; 0 resolves to a yes/no question<br>
	 * &emsp; 1 resolves to a single choice question<br>
	 * &emsp; 2 resolves to a multiple choice question<br>
	 * </CODE>
	 */
	private final int frageTyp;
	/**
	 * {@link java.util.ArrayList ArrayList} representation of the options for answers available to this {@link umfrage.Frage Frage} object
	 */
	private final ArrayList<String> antwortmoeglichkeiten;
	
	/**
	 * <b><i>Frage</i></b><br>
	 * &nbsp;&nbsp;&nbsp;<CODE>public Frage(String fragebeschreibung, int frageTyp, ArrayList<String> antwortmoeglichkeiten)</CODE>
	 * constructs a {@link umfrage.Frage Frage} object with required descriptions for interpretation set. 
	 * 
	 * @param fragebeschreibung - {@link java.lang.String String} object representing the description of the question
	 * @param frageTyp - {@link int} object representing the type of the question
	 * @param antwortmoeglichkeiten - {@link java.util.ArrayList ArrayList} object representing the options for answers of the question
	 */
	public Frage(String fragebeschreibung, int frageTyp, ArrayList<String> antwortmoeglichkeiten) {
		this.fragebeschreibung = fragebeschreibung;
		this.frageTyp = frageTyp;
		this.antwortmoeglichkeiten = antwortmoeglichkeiten;
	}
	
	/**
	 * Returns the {@link java.lang.String String} describing this {@link umfrage.Frage Frage} object.
	 * @return the {@link java.lang.String String} object describing this {@link umfrage.Frage Frage} object
	 */
	public String getFragebeschreibung(){
		return this.fragebeschreibung;
	}
	/**
	 * Returns the {@link int} type to this {@link umfrage.Frage Frage} object.
	 * @return the {@link int} object representing the type to this {@link umfrage.Frage Frage} object
	 */
	public int getFragetyp(){
		return this.frageTyp;
	}
	/**
	 * Returns the options for answers to this {@link umfrage.Frage Frage} object.
	 * @return the {@link java.util.ArrayList ArrayList} of {@link java.lang.String String}<CODE>s</CODE> of options for answers to this {@link umfrage.Frage Frage} object
	 */
	public ArrayList<String> getAntwortmoeglichkeiten(){
		return this.antwortmoeglichkeiten;
	}
}
