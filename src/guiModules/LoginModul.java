package guiModules;

import java.util.ArrayList;
import java.util.Iterator;

import upper.containertier.Gesamtsystem;
import user.Creator;
import user.Solver;

public class LoginModul {
	/*
	 * Gibt Nutzer aus entsprechenden Logindaten zur�ck
	 * Gibt null zur�ck, falls Nutzerdaten nicht gefunden wurden
	 */
	public static Creator attemptCreatorLogin(Gesamtsystem targetSystem, String loginname, String passwort){
		ArrayList<Creator> creatorList = targetSystem.getAllCreators();
		Iterator<Creator> creatorListIt = creatorList.iterator();
		Creator creatorShell;
		//Loopt durch alle Creator und testet Login-Daten
		while(creatorListIt.hasNext()){
			//System.out.println("TestStatementFOrProval");
			creatorShell = creatorListIt.next();
			//System.out.println("Name: " + creatorShell.getVorname());
			if(creatorShell.isProperPasswort(loginname, passwort)){
				//Nutzerdaten gefunden
				return creatorShell;
			}
		}
		//Nutzerdaten nicht gefunden
		return null;
	}
	
	public static Solver attemptSolverLogin(Gesamtsystem targetSystem, String loginname, String passwort){
		ArrayList<Solver> solverList = targetSystem.getAllSolvers();
		Iterator<Solver> solverListIt = solverList.iterator();
		Solver solverShell;
		//Loopt durch alle Solver und testet Login-Daten
		while(solverListIt.hasNext()){
			solverShell = solverListIt.next();
			if(solverShell.isProperPasswort(loginname, passwort)){
				//Nutzerdaten gefunden
				return solverShell;
			}
		}
		//Nutzerdaten nicht gefunden
		return null;
	}
}
