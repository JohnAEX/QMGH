package user;

import java.io.Serializable;

/**
 * Representation of generic actors is managed by the {@link user.User User} class in the QuestionMark-System. <br>
 * Super class for user objects. {@link user.User User} is parent to {@link user.Creator Creator}. {@link user.User User} is parent to {@link user.Solver Solver}. 
 * 
 * @author Dominik <br><br>
 *
 */
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * {@link java.lang.String String} object containing the login name of this {@link user.User User} object
	 */
	private final String loginName;
	/**
	 * {@link java.lang.String String} object containing the login password of this {@link user.User User} object
	 */
	private final String passwort;
	
	/**
	 * {@link java.lang.String String} object containing the surname of this {@link user.User User} object
	 */
	private final String vorname;
	/**
	 * {@link java.lang.String String} object containing the last name of this {@link user.User User} object
	 */
	private final String nachname;
	
	/**
	 * <b><i>User</i></b><br>
	 * &nbsp;&nbsp;&nbsp;<CODE>protected User(String loginName, String passwort, String vorname, String nachname)</CODE>
	 * constructs a {@link user.User User} object with basic login and personal information set. 
	 * 
	 * @param loginName - {@link java.lang.String String} object representing the login name of the {@link user.User User} object
	 * @param passwort - {@link java.lang.String String} object representing the login password of the {@link user.User User} object
	 * @param vorname - {@link java.lang.String String} object representing the surname of the {@link user.User User} object
	 * @param nachname - {@link java.lang.String String} object representing the last name of the {@link user.User User} object
	 */
	protected User(String loginName, String passwort, String vorname, String nachname){
		this.loginName = loginName;
		this.passwort = passwort;
		this.vorname = vorname;
		this.nachname = nachname;
	}
	
	/**
	 * Returns a {@link boolean} stating the success of the attempt to log in. <br>
	 * &emsp; true if login name and password match the system data
	 * 
	 * @param loginName - {@link java.lang.String String} object used as name during login
	 * @param passwort - {@link java.lang.String String} object used as password during login
	 * @return a boolean value stating the method's success <br>
	 * &emsp; true if login name and login password match the system data <br>
	 * &emsp; false if at least one of login name and login password do not match the system data
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
