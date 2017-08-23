package user;

import java.io.Serializable;

/**
 * 
 * @author Dominik <br><br>
 * Parent of {@link user.Creator}
 * Parent of {@link user.Solver}
 * <CODE>User</CODE> class with basic user data. Not be initialized as such but through child classes.<br>
 * Might be expanded depending on required user data
 *
 */
public class User implements Serializable{
	/*
	 * Login-Verwaltungs-Daten
	 */
	/**
	 * <CODE>String</CODE> containing the name for signing in, used during login
	 */
	private final String loginName;
	/**
	 * <CODE>String</CODE> containing the passwort for signing in, used during login
	 */
	private final String passwort;
	/*
	 * Persönliche Nutzer-Daten
	 */
	/**
	 * <CODE>String</CODE> containing the surname of the user
	 */
	private final String vorname;
	/**
	 * <CODE>String</CODE> containing the last name of the user
	 */
	private final String nachname;
	
	/**
	 * <b><i>User</i></b><br>
	 * &nbsp;&nbsp;&nbsp;<CODE>protected User(String loginName, String passwort, String vorname, String nachname)</CODE>
	 * constructs a User with final attributes as user data
	 * @param loginName - name for signing in, used during login
	 * @param passwort - passwort for signing in, used during login
	 * @param vorname - surname of the user
	 * @param nachname - last name of the user
	 */
	protected User(String loginName, String passwort, String vorname, String nachname){
		this.loginName = loginName;
		this.passwort = passwort;
		this.vorname = vorname;
		this.nachname = nachname;
	}
	
	/*
	 * keine Getter/Setter für Login-Verwaltungs-Daten wegen Sicherheit
	 */
	/**
	 * returns <CODE>true</CODE> if the login name and password matches the system data
	 * @param loginName - login name used to attempt sign in
	 * @param passwort - password used to attempt sign in
	 * @return true if the password matches with the system data
	 */
	public boolean isProperPasswort(String loginName, String passwort){
		//System.out.println("Name: " + this.loginName + " Claim: " + loginName + " PSW: " + this.passwort + " Claim: " + passwort);
		return (this.loginName.equals(loginName) && this.passwort.equals(passwort));
	}
	
	/**
	 * Returns the surname of the <CODE>User</CODE> instance as initialized
	 * @return the surname of the user
	 */
	public String getVorname(){
		return this.vorname;
	}
	/**
	 * Returns the last name of the <CODE>User</CODE> instance as initialized
	 * @return the last name of the user
	 */
	public String getNachname(){
		return this.nachname;
	}
}
