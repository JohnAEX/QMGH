package user;

import java.util.ArrayList;

import auswertung.Fragebogenauswertung;
import umfrage.Fragebogen;
import verwaltung.Kurs;

/**
 * 
 * @author Dominik<br><br>
 * 	Child of {@link user.User}<br><br>
 * 	The <CODE>Creator</CODE> is able to perform administrative tasks in the Fragebogen-system. 
 * 	Main function is authoring Fragebogen <i>(pl.)</i> and evaluating them.<br>
 * 	The <CODE>Creator</CODE> does not have the permission to fill in Fragebogen <i>(pl.)</i> currently. 
 * 
 */
public class Creator extends User{
	/**
	 * <CODE>ArrayList</CODE> of <CODE>Kurs</CODE> <i>(pl.)</i> the instance is active in.<br>
	 * Set to <CODE>final</CODE> in the current project version. 
	 */
	private final ArrayList<Kurs> activeKurse;
	/**
	 * <CODE>ArrayList</CODE> of <CODE>Fragebogen</CODE> <i>(pl.)</i> the instance is active in.
	 */
	private ArrayList<Fragebogen> ownedFrageboegen;
	/**
	 * <CODE>ArrayList</CODE> of <CODE>Fragebogenauswertung</CODE> <i>(pl.)</i> the instance is active in.
	 */
	private ArrayList<Fragebogenauswertung> ownedFragebogenauswertungen;
	
	/**
	 * <b><i>Creator</i></b><br>
	 * &nbsp;&nbsp;&nbsp;<CODE>public Creator(String loginName, String passwort, String vorname, String nachname)</CODE>
	 * constructs a <CODE>Creator</CODE> as a child of <CODE>{@link user.User}</CODE> with <br>
	 * &nbsp; a <CODE>final ArrayList</CODE> containing <CODE>Kurs</CODE> <i>(pl.)</i> the <CODE>Creator instance</CODE> is active in<br>
	 * &nbsp; an empty <CODE>ArrayList</CODE> containing <CODE>Fragebogen</CODE> <i>(pl.)</i> the <CODE>Creator instance</CODE> has created<br>
	 * &nbsp; an empty <CODE>ArrayList</CODE> containing <CODE>Fragebogenauswertung</CODE> <i>(pl.)</i> the <CODE>Creator instance</CODE> has created whilst distributing <CODE>Fragebogen</CODE> <i>(pl.)</i>.
	 * 
	 * 
	 * @param loginName - inherited from {@link user.User}
	 * @param passwort - inherited from {@link user.User}
	 * @param vorname - inherited from {@link user.User}
	 * @param nachname - inherited from {@link user.User}
	 * @param activeKurse - <CODE>ArrayList</CODE> of <CODE>Kurs</CODE> <i>(pl.)</i> the instance is active in.
	 */
	public Creator(String loginName, String passwort, String vorname, String nachname, ArrayList<Kurs> activeKurse){
		super(loginName, passwort, vorname, nachname);
		this.activeKurse = activeKurse;
		this.ownedFrageboegen = new ArrayList<Fragebogen>();
		this.ownedFragebogenauswertungen = new ArrayList<Fragebogenauswertung>();
	}
	
	/**
	 * Returns a <CODE>clone</CODE> object of the <CODE>ArrayList</CODE> containing <CODE>Kurs</CODE> <i>(pl.)</i>
	 * making this method of access read-only.
	 * @return a <CODE>clone</CODE> of the <CODE>ArrayList</CODE> containing <CODE>Kurs</CODE> <i>(pl.)</i> the instance is active in.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Kurs> getActiveKurse(){
		return ArrayList.class.cast(this.activeKurse.clone());
	}

	/**
	 * Returns the object of the <CODE>ArrayList</CODE> containing <CODE>Fragebogen</CODE> <i>(pl.)</i>
	 * @return an object of the <CODE>ArrayList</CODE> containing <CODE>Fragebogen</CODE> <i>(pl.)</i> the instance is active in.
	 */
	public ArrayList<Fragebogen> getOwnedFrageboegen(){
		return this.ownedFrageboegen;
	}

	/**
	 * Returns a <CODE>clone</CODE> object of the <CODE>ArrayList</CODE> containing <CODE>Fragebogenauswertung</CODE> <i>(pl.)</i>
	 * making this method of access read-only.
	 * @return a <CODE>clone</CODE> of the <CODE>ArrayList</CODE> containing <CODE>Fragebogenauswertung</CODE> <i>(pl.)</i> the instance is active in.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Fragebogenauswertung> getOwnedFragebogenasuwertungen(){
		return ArrayList.class.cast(this.ownedFragebogenauswertungen.clone());
	}
	
	
	/**
	 * Adds one <CODE>Fragebogen</CODE> to the <CODE>ArrayList</CODE> of the <CODE>Creator</CODE> instance's owned <CODE>Fragebogen</CODE> <i>(pl.)</i>. 
	 * @param newFragebogen - <CODE>Fragebogen</CODE> object to be added to the <CODE>ArrayList</CODE> of the <CODE>Creator</CODE> instance's owned <CODE>Fragebogen</CODE> <i>(pl.)</i>.
	 */
	public void addOwnedFragebogen(Fragebogen newFragebogen){
		this.ownedFrageboegen.add(newFragebogen);
	}

	/**
	 * Distributes a <CODE>Fragebogen</CODE> object to the specified <CODE>Kurs</CODE> object.
	 * @param receivingKurs - <CODE>Kurs</CODE> object to be receiving the <CODE>Fragebogen</CODE> object.
	 * @param distributedFragebogen - <CODE>Fragebogen</CODE> to be distributed to the <CODE>Kurs</CODE> object.
	 */
	public void distributeFragebogenTo(Kurs receivingKurs, Fragebogen distributedFragebogen){
		Fragebogenauswertung ownedFragebogenauswertung = new Fragebogenauswertung(distributedFragebogen, receivingKurs);
		this.ownedFragebogenauswertungen.add(ownedFragebogenauswertung);
		receivingKurs.relayFragebogenToAllSolvers(ownedFragebogenauswertung);
	}
	
	
}
