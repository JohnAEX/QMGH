package guiModules;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

import upper.containertier.Gesamtsystem;
/**
 * Modul zum persistenten Speichern
 * @author marvi
 *
 */
public class PersistenzModul {
	/**
	 * Speichert die Datei am gewünschten Speicherort
	 * @param speicherort
	 * @param dateiname
	 * @param gesSysToBeSaved
	 * @return true bei erfolgreicher Speicherung, false bei Fehler
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
	 * Lädt Dateipfad sowie Dateiname
	 * @param dateipfad
	 * @param dateiname
	 * @return
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
