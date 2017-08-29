package user;

import java.util.ArrayList;

import auswertung.Fragebogenauswertung;
import umfrage.Fragebogen;
import verwaltung.Kurs;

/**
 * The user identities with permission to create and manage questionnaires ({@link umfrage.Fragebogen Fragebogen}) as well as surveys ({@link auswertung.Fragebogenauswertung Fragebogenauswertung}) 
 * are managed by the {@link user.Creator Creator} class in the QuestionMark-System. <br>
 * {@link user.Creator Creator} is child to {@link user.User User}. 
 * The {@link user.Creator Creator} object has limited read/write access to objects originating from the packages {@link auswertung} and {@link umfrage}. 
 * 
 * @author Dominik
 * 
 */
public class Creator extends User{
	private static final long serialVersionUID = 1L;
	
	/**
	 * {@link java.util.ArrayList ArrayList} representation of the {@link verwaltung.Kurs Kurs} objects this {@link user.Creator Creator} object is active in
	 */
	@SuppressWarnings("unused")
	private final ArrayList<Kurs> activeKurse;
	/**
	 * {@link java.util.ArrayList ArrayList} representation of the {@link umfrage.Fragebogen Fragebogen} objects this {@link user.Creator Creator} object has created
	 */
	private ArrayList<Fragebogen> ownedFrageboegen;
	/**
	 * {@link java.util.ArrayList ArrayList} representation of the {@link auswertung.Fragebogenauswertung Fragebogenauswertung} objects this {@link user.Creator Creator} object has created through distributing a {@link umfrage.Fragebogen Fragebogen}
	 */
	private ArrayList<Fragebogenauswertung> ownedFragebogenauswertungen;
	
	/**
	 * <b><i>Creator</i></b><br>
	 * &nbsp;&nbsp;&nbsp;<CODE>public Creator(String loginName, String passwort, String vorname, String nachname, ArrayList<Kurs> activeKurse)</CODE>
	 * constructs a {@link user.Creator Creator} object with no default {@link umfrage.Fragebogen Fragebogen}<CODE>s</CODE> or {@link auswertung.Fragebogenauswertung Fragebogenauswertung}<CODE>s</CODE>. 
	 * 
	 * @param loginName - inherited from {@link user.User User}
	 * @param passwort - inherited from {@link user.User User}
	 * @param vorname - inherited from {@link user.User User}
	 * @param nachname - inherited from {@link user.User User}
	 * @param activeKurse - {@link java.util.ArrayList ArrayList} object of {@link verwaltung.Kurs Kurs}<CODE>'s</CODE> this {@link user.Creator Creator} object is active in
	 */
	public Creator(String loginName, String passwort, String vorname, String nachname, ArrayList<Kurs> activeKurse){
		super(loginName, passwort, vorname, nachname);
		this.activeKurse = activeKurse;
		this.ownedFrageboegen = new ArrayList<Fragebogen>();
		this.ownedFragebogenauswertungen = new ArrayList<Fragebogenauswertung>();
	}
	
	/**
	 * Returns a <CODE>clone</CODE> object of the {@link java.util.ArrayList ArrayList} containing the {@link verwaltung.Kurs Kurs}<CODE>'s</CODE> this {@link user.Creator Creator} object is active in 
	 * making this method of access read-only.
	 * 
	 * @return a cloned {@link java.util.ArrayList ArrayList} containing the {@link verwaltung.Kurs Kurs} objects this {@link user.Creator Creator} object is active in 
	 */
	//currently disable due to setting of the default entities active in the QuestionMark-System
	//Has to be updated along with guiModules.FBDistributionModul upon re-enabling - IMPORTANT!
	/*
	@SuppressWarnings("unchecked")
	public ArrayList<Kurs> getActiveKurse(){
		return ArrayList.class.cast(this.activeKurse.clone());
	}
	*/
	/**
	 * Returns the {@link java.util.ArrayList ArrayList} of {@link umfrage.Fragebogen Fragebogen}<CODE>s</CODE> this {@link user.Creator Creator} object has created.
	 * @return the {@link java.util.ArrayList ArrayList} object of {@link umfrage.Fragebogen Fragebogen} objects this {@link user.Creator Creator} object has created
	 */
	public ArrayList<Fragebogen> getOwnedFrageboegen(){
		return this.ownedFrageboegen;
	}

	/**
	 * Returns a <CODE>clone</CODE> object of the {@link java.util.ArrayList ArrayList} containing the {@link auswertung.Fragebogenauswertung Fragebogenauswertung}<CODE>'s</CODE> this {@link user.Creator Creator} object has created through distributing a {@link umfrage.Fragebogen Fragebogen}  
	 * making this method of access read-only.
	 * 
	 * @return a cloned {@link java.util.ArrayList ArrayList} containing the {@link auswertung.Fragebogenauswertung Fragebogenauswertung} objects this {@link user.Creator Creator} object has created through distributing a {@link umfrage.Fragebogen Fragebogen}
	 */
	
	@SuppressWarnings("unchecked")
	public ArrayList<Fragebogenauswertung> getOwnedFragebogenauswertungen(){
		return ArrayList.class.cast(this.ownedFragebogenauswertungen.clone());
	}
	
	/**
	 * Adds one {@link umfrage.Fragebogen Fragebogen} to the {@link java.util.ArrayList ArrayList} of {@link umfrage.Fragebogen Fragebogen} owned by this {@link user.Creator Creator}. 
	 * @param newFragebogen - {@link umfrage.Fragebogen Fragebogen} object to be added to the {@link java.util.ArrayList ArrayList} of this {@link user.Creator Creator} object 
	 */
	public void addOwnedFragebogen(Fragebogen newFragebogen){
		this.ownedFrageboegen.add(newFragebogen);
	}

	/**
	 * Files a new survey ({@link auswertung.Fragebogenauswertung Fragebogenauswertung}) for this {@link user.Creator Creator} object. 
	 * Also distributes this survey to a specified {@link verwaltung.Kurs Kurs} object.
	 * 
	 * @param receivingKurs - {@link verwaltung.Kurs Kurs} object receiving the specified survey
	 * @param distributedFragebogen - {@link umfrage.Fragebogen Fragebogen} object to be distributed to the specified course
	 */
	public void distributeFragebogenTo(Kurs receivingKurs, Fragebogen distributedFragebogen){
		//Generates new Fragebogenauswertung from the specified Fragebogen object
		Fragebogenauswertung ownedFragebogenauswertung = new Fragebogenauswertung(distributedFragebogen, receivingKurs);
		
		//Files the Fragebogenauswertung and hands a reference to the specified Kurs
		this.ownedFragebogenauswertungen.add(ownedFragebogenauswertung);
		receivingKurs.relayFragebogenToAllSolvers(ownedFragebogenauswertung);
	}
	
	
}
