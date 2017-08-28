package upper.containertier;

import java.io.Serializable;
import java.util.ArrayList;

import user.Creator;
import user.Solver;
import verwaltung.Kurs;

/**
 * The {@link upper.containertier.Gesamtsystem Gesamtsystem} class is container to all independent objects. Independent objects are intended to be saved "persistently" and implemented accordingly. <br>
 * The {@link upper.containertier.Gesamtsystem Gesamtsystem} object contains all {@link user.User User}<CODE>s</CODE> ({@link user.Creator Creator}<CODE>s</CODE> and {@link user.Solver Solver}<CODE>s</CODE> at this point in time).
 * Also contains a list of {@link verwaltung.Kurs Kurs}<CODE>'s</CODE>.
 * 
 * @author Dominik
 *
 */
public class Gesamtsystem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * {@link java.util.ArrayList ArrayList} representation of the {@link user.Creator Creator} objects active in the QuestionMark-System.
	 */
	private ArrayList<Creator> allCreators;
	/**
	 * {@link java.util.ArrayList ArrayList} representation of the {@link user.Solver Solver} objects active in the QuestionMark-System.
	 */
	private ArrayList<Solver> allSolvers;
	
	/**
	 * {@link java.util.ArrayList ArrayList} representation of the {@link verwaltung.Kurs Kurs} objects active in the QuestionMark-System.
	 */
	private ArrayList<Kurs> allKurse;
	
	/**
	 * Test-{@link java.lang.String String} from persistency checks. Reminder and remainder of the happy moment when the system could be stored past the program's runtime. 
	 * @author Dominik
	 * @author Jonathan
	 */
	protected String signalString = "Hello World";
	
	/**
	 * <b><i>Gesamtsystem</i></b><br>
	 * &nbsp;&nbsp;&nbsp;<CODE>public Gesamtsystem(ArrayList<Creator> allCreators, ArrayList<Solver> allSolvers, ArrayList<Kurs> allKurse)</CODE>
	 * constructs a {@link upper.containertier.Gesamtsystem Gesamtsystem} object with all elements for persistent save set. 
	 * 
	 * @param allCreators - {@link java.util.ArrayList ArrayList} of {@link user.Creator Creator} objects representing all creators registered to the QuestionMark-System
	 * @param allSolvers - {@link java.util.ArrayList ArrayList} of {@link user.Solver Solver} objects representing all solvers registered to the QuestionMark-System
	 * @param allKurse - {@link java.util.ArrayList ArrayList} of {@link verwaltung.Kurs Kurs} objects representing all creators registered to the QuestionMark-System
	 */
	public Gesamtsystem(ArrayList<Creator> allCreators, ArrayList<Solver> allSolvers, ArrayList<Kurs> allKurse) {
		this.allCreators = allCreators;
		this.allSolvers = allSolvers;
		this.allKurse = allKurse;
	}
	
	/**
	 * Returns an {@link java.util.ArrayList ArrayList} of {@link user.Creator Creator}<CODE>s</CODE> registered to the QuestionMark-System.
	 * @return an {@link java.util.ArrayList ArrayList} of {@link user.Creator Creator} objects registered to the QuestionMark-System
	 */
	public ArrayList<Creator> getAllCreators(){
		return this.allCreators;
	}
	
	/**
	 * Returns an {@link java.util.ArrayList ArrayList} of {@link user.Solver Solver}<CODE>s</CODE> registered to the QuestionMark-System.
	 * @return an {@link java.util.ArrayList ArrayList} of {@link user.Solver Solver} objects registered to the QuestionMark-System
	 */
	public ArrayList<Solver> getAllSolvers(){
		return this.allSolvers;
	}
	
	/**
	 * Returns an {@link java.util.ArrayList ArrayList} of {@link verwaltung.Kurs Kurs}<CODE>'s</CODE> registered to the QuestionMark-System.
	 * @return an {@link java.util.ArrayList ArrayList} of {@link verwaltung.Kurs Kurs} objects registered to the QuestionMark-System
	 * @return
	 */
	public ArrayList<Kurs> getAllKurse(){
		return this.allKurse;
	}
	
}
