package bioreactor;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Représente le réacteur en tant que tel. Les temps sont donnés par un double (de 0 à 5812)
 * Il va stocker la valeur actuelle de la temp, O2, Ph ainsi que les valeurs précédentes (dans des listes)
 * Il va actualiser les valeurs des variables à chaque tic de la simulation cad toutes les 3 secondes (t1 = t0 + 3sec => donne currentTime = 1)
 */

/**
 *  TODO : updateValues toutes les 3 secs, visiblement il faudrait utiliser un objet Runnable.
 */
public class BioreactorSimulator {
    private final Runnable updateValues = null;
    public final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public DataManager data = new DataManager(null); //Change data file address
    public double currentTime = 0;
    Variable currentTemperature = new Temperature(currentTime);
    //        Variable currentOxygen = new Oxygen(currentTime);
    Variable currentPh = new Ph(currentTime);
    List<Variable> listPreviousTemperature = new ArrayList<Variable>();
    List<Variable> listPreviousOxygen = new ArrayList<Variable>();
    List<Variable> listPreviousPh = new ArrayList<Variable>();
    LocalDate t0 = LocalDate.now();
    public BioreactorSimulator() throws IOException {
        scheduler.scheduleAtFixedRate(updateValues, 8, 8, TimeUnit.HOURS);
    }
    public void setUpdateValues() throws IOException {
        currentTemperature.setTimeAndValue(currentTime, data.getVariableValueAtTime(currentTime, currentTemperature));
        currentPh.setTimeAndValue(currentTime, data.getVariableValueAtTime(currentTime, currentPh));
//        currentOxygen.setTimeAndValue(currentTime, data.getVariableValueAtTime(currentTime, currentOxygen));
        listPreviousTemperature.add(currentTemperature);
        listPreviousPh.add(currentPh);
//        listPreviousOxygen.add(currentOxygen);
    }
}
