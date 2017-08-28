package guiModules;

import java.util.ArrayList;
import java.util.Iterator;

import upper.containertier.Gesamtsystem;
import user.Creator;
import user.Solver;

/**
 * Module directly communicating with the user interface classes. Manages Login-requests.
 * 
 * @author Dominik
 *
 */
public class LoginModul {
	
	/**
	 * Returns a {@link user.Creator Creator} object upon proper entry of login data. 
	 * 
	 * @param targetSystem - {@link upper.containertier.Gesamtsystem Gesamtsystem} to be searched for login
	 * @param loginname - {@link java.lang.String String} to attempt user login with as name
	 * @param passwort - {@link java.lang.String String} to attempt user login with as password
	 * @return user object <br>
	 * &emsp; {@link user.Creator Creator} if successful login attempt <br>
	 * &emsp; null if failed login attempt
	 */
	public static Creator attemptCreatorLogin(Gesamtsystem targetSystem, String loginname, String passwort){
		ArrayList<Creator> creatorList = targetSystem.getAllCreators();
		Iterator<Creator> creatorListIt = creatorList.iterator();
		Creator creatorShell;
		//Loopt durch alle Creator und testet Login-Daten
		while(creatorListIt.hasNext()){
			//System.out.println("TestStatementForProval");
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
	 * Returns a {@link user.Solver Solver} object upon proper entry of login data. 
	 * 
	 * @param targetSystem - {@link upper.containertier.Gesamtsystem Gesamtsystem} to be searched for login
	 * @param loginname - {@link java.lang.String String} to attempt user login with as name
	 * @param passwort - {@link java.lang.String String} to attempt user login with as password
	 * @return user object <br>
	 * &emsp; {@link user.Solver Solver} if successful login attempt <br>
	 * &emsp; null if failed login attempt
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
