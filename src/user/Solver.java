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
	 * Konstruktor des Solvers
	 * @param loginName
	 * @param passwort
	 * @param vorname
	 * @param nachname
	 */
	public Solver(String loginName, String passwort, String vorname, String nachname){
		super(loginName, passwort, vorname, nachname);
		this.activeFrageboegenWithAntwortmoeglichkeit = new ArrayList<FragebogenWithAntwortmoeglichkeit>();
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<FragebogenWithAntwortmoeglichkeit> getActiveFragebogenWithAntwortmoeglichkeit(){
		return this.activeFrageboegenWithAntwortmoeglichkeit;
	}
	
	public void addActiveFragebogenWithAntwortmoeglichkeit(FragebogenWithAntwortmoeglichkeit newFragebogenWithAntwortmoeglichkeit){
		this.activeFrageboegenWithAntwortmoeglichkeit.add(newFragebogenWithAntwortmoeglichkeit);
	}
	
	public void submitFragebogenergebnis(FragebogenWithAntwortmoeglichkeit fragebogenWithAntwortmoeglichkeit, ArrayList<ArrayList<Integer>> answers){
		if(this.activeFrageboegenWithAntwortmoeglichkeit.contains(fragebogenWithAntwortmoeglichkeit)){
			fragebogenWithAntwortmoeglichkeit.addAntwort(answers);
		}
	}
	
	public String testReturnAllFB(){
		String returnString = "";
		Iterator<FragebogenWithAntwortmoeglichkeit> localIt = this.activeFrageboegenWithAntwortmoeglichkeit.iterator();
		for(int i = 1;localIt.hasNext();i++){
			returnString += i +": "+ localIt.next().getTitel() + "\n";
		}
		return returnString;
	}
}
