package user;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Iterator;
import java.util.Iterator;

import umfrage.FragebogenWithAntwortmoeglichkeit;

public class Solver extends User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<FragebogenWithAntwortmoeglichkeit> activeFrageboegenWithAntwortmoeglichkeit;
	
	public Solver(String loginName, String passwort, String vorname, String nachname){
		super(loginName, passwort, vorname, nachname);
		this.activeFrageboegenWithAntwortmoeglichkeit = new ArrayList<FragebogenWithAntwortmoeglichkeit>();
	}
	
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