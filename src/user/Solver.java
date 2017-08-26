package user;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.Iterator;

import umfrage.FragebogenWithAntwortmoeglichkeit;
/**
 * 
 * @author marvi
 *
 */
public class Solver extends User{

	private ArrayList<FragebogenWithAntwortmoeglichkeit> activeFrageboegenWithAntwortmoeglichkeit;
	/**
	 * Konstruktor des Solvers<br>
	 * <b><i>Solver</i></b><br>
	 * &nbsp;&nbsp;&nbsp;<CODE>public Solver(String loginName, String passwort, String vorname, String nachname)</CODE>
	 * constructs a {@link user.Solver Sovler} as a child of <CODE>{@link user.User User}</CODE> with <br>
	 * &nbsp; an empty <CODE>ArrayList</CODE> containing {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} <i>(pl.)</i> the <CODE>Creator instance</CODE> has created<br>
	 * 
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
	 * Returns an <CODE>ArrayList</CODE> of {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit}.
	 * @return an <CODE>ArrayList</CODE> containing {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} the an <CODE>ArrayList</CODE> of {@link user.Solver Solver} is active in
	 */
	public ArrayList<FragebogenWithAntwortmoeglichkeit> getActiveFragebogenWithAntwortmoeglichkeit(){
		return this.activeFrageboegenWithAntwortmoeglichkeit;
	}
	/**
	 * Adds an instance of {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} to the {@link user.Solver Solver} object
	 * @param newFragebogenWithAntwortmoeglichkeit {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} to be added to the {@link user.Solver Solver}
	 */
	public void addActiveFragebogenWithAntwortmoeglichkeit(FragebogenWithAntwortmoeglichkeit newFragebogenWithAntwortmoeglichkeit){
		this.activeFrageboegenWithAntwortmoeglichkeit.add(newFragebogenWithAntwortmoeglichkeit);
	}
	/**
	 * Submits an answer to the {@link auswertung.Fragebogenauswertung Fragebogenauswertung} specified in the {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit}
	 * @param fragebogenWithAntwortmoeglichkeit - {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} to be answered
	 * @param answers - answers to the questions in the specified {@link umfrage.FragebogenWithAntwortmoeglichkeit FragebogenWithAntwortmoeglichkeit} object
	 */
	public void submitFragebogenergebnis(FragebogenWithAntwortmoeglichkeit fragebogenWithAntwortmoeglichkeit, ArrayList<ArrayList<Integer>> answers){
		if(this.activeFrageboegenWithAntwortmoeglichkeit.contains(fragebogenWithAntwortmoeglichkeit)){
			fragebogenWithAntwortmoeglichkeit.addAntwort(answers);
		}
	}
}
