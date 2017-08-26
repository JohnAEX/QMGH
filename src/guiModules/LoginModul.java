package guiModules;

import java.util.ArrayList;
import java.util.Iterator;

import upper.containertier.Gesamtsystem;
import user.Creator;
import user.Solver;
/**
 * Modul zum Login für Creator und Solver
 * @author marvi
 *
 */
public class LoginModul {
	/**
	 * Anmeldung des Creators
	 * @param targetSystem
	 * @param loginname
	 * @param passwort
	 * @return Nutzer aus entsprechenden Logindaten, null, falls Nutzerdaten nicht gefunden werden konnten
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
	
	/**
	 * Anmeldung des Solvers
	 * @param targetSystem
	 * @param loginname
	 * @param passwort
	 * @return Nutzer aus entsprechenden Logindaten, null, falls Nutzerdaten nicht gefunden werden konnte
	 */
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
