package guiModules;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

import upper.containertier.Gesamtsystem;

/**
 * Module directly communicating with the user interface classes. Persistently saves QuestionMark-System states.
 * 
 * @author Dominik
 *
 */
public class PersistenzModul {
	/**
	 * Saves a QuestionMark-System to the specified location under the specified name. 
	 * 
	 * @param speicherort - {@link java.lang.String String} representing the save location of the specified QuestionMark-System
	 * @param dateiname - {@link java.lang.String String} representing the save name of the specified QuestionMark-System
	 * @param gesSysToBeSaved - {@link upper.containertier.Gesamtsystem Gesamtsystem} to be saved according to the specified data.  
	 * @return a boolean value stating the method's success <br>
	 * &emsp; true if no runtime-error occurs <br>
	 * &emsp; false if any runtime-error occurs
	 */
	public static boolean saveGesamtsystem(String speicherort, String dateiname, Gesamtsystem gesSysToBeSaved){
		try{
			FileOutputStream gesSysFileOut = new FileOutputStream(speicherort +"\\"+ dateiname +".wi16");
			ObjectOutputStream gesSysObjectOut = new ObjectOutputStream(gesSysFileOut);
			
			gesSysObjectOut.writeObject(gesSysToBeSaved);
			
			gesSysObjectOut.close();
			gesSysFileOut.close();
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 * Loads a QuestionMark-System from the specified location under the specified name. 
	 * 
	 * @param dateipfad - {@link java.lang.String String} representing the save location of the specified QuestionMark-System
	 * @param dateiname - {@link java.lang.String String} representing the save name of the specified QuestionMark-System
	 * @return a QuestionMark-System <br>
	 * &emsp; {@link upper.containertier.Gesamtsystem Gesamtsystem} if no runtime-error occurs <br>
	 * &emsp; {@link null} if any runtime-error occurs
	 */
	public static Gesamtsystem loadGesamtsystem(String dateipfad, String dateiname){
		try{
			FileInputStream gesSysFileIn = new FileInputStream(dateipfad +"\\"+ dateiname +".wi16");
			ObjectInputStream gesSysObjectIn = new ObjectInputStream(gesSysFileIn);
			return (Gesamtsystem) gesSysObjectIn.readObject();

		}catch(Exception e){
			return null;
		}
	}
}
