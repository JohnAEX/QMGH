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
	 * Konstruktor des Creators<br>
	 * <b><i>Creator</i></b><br>
	 * &nbsp;&nbsp;&nbsp;<CODE>public Creator(String loginName, String passwort, String vorname, String nachname)</CODE>
	 * constructs a {@link user.Creator Creator} as a child of <CODE>{@link user.User User}</CODE> with <br>
	 * &nbsp; a <CODE>final ArrayList</CODE> containing {@link verwaltung.Kurs Kurs} <i>(pl.)</i> the <CODE>Creator instance</CODE> is active in<br>
	 * &nbsp; an empty <CODE>ArrayList</CODE> containing {@link umfrage.Fragebogen Fragebogen} <i>(pl.)</i> the <CODE>Creator instance</CODE> has created<br>
	 * &nbsp; an empty <CODE>ArrayList</CODE> containing {@link auswertung.Fragebogenauswertung} <i>(pl.)</i> the <CODE>Creator instance</CODE> has created whilst distributing <CODE>Fragebogen</CODE> <i>(pl.)</i>.
	 * 
	 * 
	 * @param loginName - inherited from {@link user.User User}
	 * @param passwort - inherited from {@link user.User User}
	 * @param vorname - inherited from {@link user.User User}
	 * @param nachname - inherited from {@link user.User User}
	 * @param activeKurse - <CODE>ArrayList</CODE> of {@link verwaltung.Kurs} <i>(pl.)</i> the instance is active in.
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
	 * Returns the object of the <CODE>ArrayList</CODE> containing {@link umfrage.Fragebogen Fragebogen} <i>(pl.)</i>
	 * @return an object of the <CODE>ArrayList</CODE> containing {@link umfrage.Fragebogen Fragebogen} <i>(pl.)</i> the instance is active in.
	 */
	public ArrayList<Fragebogen> getOwnedFrageboegen(){
		return this.ownedFrageboegen;
	}

	/**
	 * Returns a <CODE>clone</CODE> object of the <CODE>ArrayList</CODE> containing {@link auswertung.Fragebogenauswertung Fragebogenauswertung} <i>(pl.)</i>
	 * making this method of access read-only.
	 * @return a <CODE>clone</CODE> of the <CODE>ArrayList</CODE> containing {@link auswertung.Fragebogenauswertung Fragebogenauswertung} <i>(pl.)</i> the instance is active in.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Fragebogenauswertung> getOwnedFragebogenasuwertungen(){
		return ArrayList.class.cast(this.ownedFragebogenauswertungen.clone());
	}
	
	
	/**
	 * Adds one {@link umfrage.Fragebogen Fragebogen} to the <CODE>ArrayList</CODE> of the {@link user.Creator Creator} instance's owned {@link umfrage.Fragebogen Fragebogen} <i>(pl.)</i>. 
	 * @param newFragebogen - {@link umfrage.Fragebogen Fragebogen} object to be added to the <CODE>ArrayList</CODE> of the {@link user.Creator Creator} instance's owned {@link umfrage.Fragebogen Fragebogen} <i>(pl.)</i>.
	 */
	public void addOwnedFragebogen(Fragebogen newFragebogen){
		this.ownedFrageboegen.add(newFragebogen);
	}

	/**
	 * Distributes a {@link umfrage.Fragebogen Fragebogen} object to the specified {@link verwaltung.Kurs Kurs} object.
	 * @param receivingKurs - {@link verwaltung.Kurs Kurs} object to be receiving the {@link umfrage.Fragebogen Fragebogen} object.
	 * @param distributedFragebogen - {@link umfrage.Fragebogen Fragebogen} to be distributed to the {@link verwaltung.Kurs Kurs} object.
	 */
	public void distributeFragebogenTo(Kurs receivingKurs, Fragebogen distributedFragebogen){
		Fragebogenauswertung ownedFragebogenauswertung = new Fragebogenauswertung(distributedFragebogen);
		this.ownedFragebogenauswertungen.add(ownedFragebogenauswertung);
		receivingKurs.relayFragebogenToAllSolvers(ownedFragebogenauswertung);
	}
	
	
}
