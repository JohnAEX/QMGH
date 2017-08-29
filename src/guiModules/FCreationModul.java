package guiModules;

import java.util.ArrayList;

import umfrage.Frage;

/**
 * Module directly communicating with the user interface classes. Creates a question.
 * 
 * @author Dominik
 *
 */
public class FCreationModul {
	/**
	 * Returns a {@link umfrage.Frage Frage} object generated from the specified data. 
	 * 
	 * @param frageTyp - {@link int} object representing the type of question to be generated <br>
	 * &emsp; 0 - yes or no question <br>
	 * &emsp; 1 - single choice question <br>
	 * &emsp; 2 - multiple choice question <br>
	 * @param titel - {@link java.lang.String String} representing the description of the question
	 * @param antwortmoeglichkeiten - {@link java.util.ArrayList ArrayList} of {@link java.lang.String String} objects representing the available options for answers 
	 * @return a {@link umfrage.Frage Frage} object
	 */
	public static Frage createFrage(int frageTyp, String titel, ArrayList<String> antwortmoeglichkeiten){
		return (new Frage(titel, frageTyp, antwortmoeglichkeiten));
	}
}
