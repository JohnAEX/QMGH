package user;

import java.util.ArrayList;

import umfrage.FragebogenWithAntwortmoeglichkeit;

/**
 * The user identities with permission to answer surveys ({@link auswertung.Fragebogenauswertung Fragebogenauswertung}) through solvable questionnaires ({@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit}) 
 * are managed by the {@link user.Solver Solver} class in the QuestionMark-System. <br> 
 * {@link user.Solver Solver} is child to {@link user.User User}. 
 * The {@link user.Solver Solver} object has limited one-time write-only access to surveys ({@link auswertung.Fragebogenauswertung Fragebogenauswertung}) through solvable questionnaires ({@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit}). 
 * 
 * @author Dominik
 * 
 */
public class Solver extends User{
	private static final long serialVersionUID = 1L;
	
	/**
	 * {@link java.util.ArrayList ArrayList} representation of the {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} objects this {@link user.Solver Solver} object has been receiving
	 */
	private ArrayList<FragebogenWithAntwortmoeglichkeit> activeFrageboegenWithAntwortmoeglichkeit;
	
	/**
	 * <b><i>Solver</i></b><br>
	 * &nbsp;&nbsp;&nbsp;<CODE>public Solver(String loginName, String passwort, String vorname, String nachname)</CODE>
	 * constructs a {@link user.Solver Solver} object with no default {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit}<CODE>s</CODE>. 
	 * 
	 * @param loginName - inherited from {@link user.User User}
	 * @param passwort - inherited from {@link user.User User}
	 * @param vorname - inherited from {@link user.User User}
	 * @param nachname - inherited from {@link user.User User}
	 */
	public Solver(String loginName, String passwort, String vorname, String nachname){
		super(loginName, passwort, vorname, nachname);
		this.activeFrageboegenWithAntwortmoeglichkeit = new ArrayList<FragebogenWithAntwortmoeglichkeit>();
	}
	
	/**
	 * Returns the {@link java.util.ArrayList ArrayList} of {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit}<CODE>s</CODE> this {@link user.Solver Solver} object has been receiving. 
	 * @return the {@link java.util.ArrayList ArrayList} of {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} objects this {@link user.Solver Solver} object has been receiving
	 */
	public ArrayList<FragebogenWithAntwortmoeglichkeit> getActiveFragebogenWithAntwortmoeglichkeit(){
		return this.activeFrageboegenWithAntwortmoeglichkeit;
	}
	
	/**
	 * Adds one {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} to the {@link java.util.ArrayList ArrayList} of {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} owned by this {@link user.Solver Solver}. 
	 * @param newFragebogenWithAntwortmoeglichkeit - {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} object to be added to the {@link java.util.ArrayList ArrayList} of this {@link user.Solver Solver} object
	 */
	public void addActiveFragebogenWithAntwortmoeglichkeit(FragebogenWithAntwortmoeglichkeit newFragebogenWithAntwortmoeglichkeit){
		this.activeFrageboegenWithAntwortmoeglichkeit.add(newFragebogenWithAntwortmoeglichkeit);
	}
	
	/**
	 * Submits one set of answers to the {@link auswertung.Fragebogenauswertung Fragebogenauswertung} specified in the {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit}. 
	 * 
	 * @param fragebogenWithAntwortmoeglichkeit - {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} object to be answered
	 * @param answers - nested {@link java.util.ArrayList ArrayList} containing the answers to be submitted to the {@link auswertung.Fragebogenauswertung Fragebogenauswertung}
	 * @return a boolean value stating the methods success. <br>
	 * &emsp; true if the answers could be submitted <br>
	 * &emsp; false if the {@link user.Solver Solver} has no known access to the specified {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit}.
	 */
	public boolean submitFragebogenergebnis(FragebogenWithAntwortmoeglichkeit fragebogenWithAntwortmoeglichkeit, ArrayList<ArrayList<Integer>> answers){
		if(this.activeFrageboegenWithAntwortmoeglichkeit.contains(fragebogenWithAntwortmoeglichkeit)){
			fragebogenWithAntwortmoeglichkeit.addAntwort(answers);
			boolean success = this.activeFrageboegenWithAntwortmoeglichkeit.remove(fragebogenWithAntwortmoeglichkeit);
			if(success){
				return true;
			}
				
		}
		
		return false;
	}
}
