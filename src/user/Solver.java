package user;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.Iterator;

import umfrage.FragebogenWithAntwortmoeglichkeit;

public class Solver extends User{
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
