package upper.containertier;

import java.io.Serializable;
import java.util.ArrayList;

import user.Creator;
import user.Solver;
import verwaltung.Kurs;
/**
 * Klasse inkl. Konstruktor des Gesamtsystems, sowie des Abrufs aller Creator, Solver bzw. Kurse
 * @author marvi
 *
 */
public class Gesamtsystem implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Creator> 	allCreators;
	private ArrayList<Solver> 	allSolvers;
	
	private ArrayList<Kurs> allKurse;
	
	public String signalString = "Hello World";
	/**
	 * Konstruktor des Gesamtsystems
	 * @param allCreators
	 * @param allSolvers
	 * @param allKurse
	 */
	public Gesamtsystem(ArrayList<Creator> allCreators, ArrayList<Solver> allSolvers, ArrayList<Kurs> allKurse) {
		this.allCreators = allCreators;
		this.allSolvers = allSolvers;
		this.allKurse = allKurse;
	}
	/**
	 * Abruf aller Creator
	 * @return alle Creator
	 */
	public ArrayList<Creator> getAllCreators(){
		return this.allCreators;
	}
	/**
	 * Abruf aller Solver
	 * @return alle Solver
	 */
	public ArrayList<Solver> getAllSolvers(){
		return this.allSolvers;
	}
	/**
	 * Abruf aller Kurse
	 * @return alle Kurse
	 */
	public ArrayList<Kurs> getAllKurse(){
		return this.allKurse;
	}
	
}
