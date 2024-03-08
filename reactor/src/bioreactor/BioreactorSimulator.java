package bioreactor;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Représente le réacteur en tant que tel. Les temps sont donnés par un double (de 0 à 5812)
 * Il va stocker la valeur actuelle de la temp, O2, Ph ainsi que les valeurs précédentes (dans des listes)
 * Il va actualiser les valeurs des variables à chaque tic de la simulation cad toutes les 3 secondes (t1 = t0 + 3sec => donne currentTime = 1)
 */
public class BioreactorSimulator {
    public final PropertyChangeSupport pcSupport;
    public DataManager data; //Change data file address
    public int currentNumberOfTicks = 0;
    Variable currentTemperature = new Temperature(currentNumberOfTicks);
    //        Variable currentOxygen = new Oxygen(currentNumberOfTicks);
    Variable currentPh = new Ph(currentNumberOfTicks);
    List<Variable> listPreviousTemperature = new ArrayList<Variable>();
    List<Variable> listPreviousOxygen = new ArrayList<Variable>();
    List<Variable> listPreviousPh = new ArrayList<Variable>();
    public int tf = 10; //time in minutes of end of simulator
    public int tick = 2;//call setUpdateValues every tick seconds
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private List<Variable> currentParams;

    public BioreactorSimulator() throws IOException {
        data = new DataManager(null);
        pcSupport = new PropertyChangeSupport(this);
        init();
    }
    public void init(){
        final Runnable updater = new Runnable() {
            @Override
            public void run() {
                try {
                    setUpdateValues();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };
        };
        final ScheduledFuture<?> bioreactorHandler =
                scheduler.scheduleAtFixedRate(updater, 2, 2, SECONDS); // Calls setUpdateValues() every "tick" seconds
        scheduler.schedule(new Runnable() {             //Stops calling setUpdateValues() after one hour of simulation.
            public void run() { bioreactorHandler.cancel(true); }
        }, 10*60, SECONDS);
    }
    public void setUpdateValues() throws IOException {
        currentNumberOfTicks += 1;
        currentTemperature.setTimeAndValue(currentNumberOfTicks, (double) data.getVariableValueAtTime(currentNumberOfTicks, currentTemperature));
        currentPh.setTimeAndValue(currentNumberOfTicks, (double) data.getVariableValueAtTime(currentNumberOfTicks, currentPh));
//        currentO2.setTimeAndValue(currentNumberOfTicks, (double) data.getVariableValueAtTime(currentNumberOfTicks, "T"));
        listPreviousTemperature.add(currentTemperature);
        listPreviousPh.add(currentTemperature);
//        listPreviousO2.add(currentTemperature);
        reloadCurrentParam(Arrays.asList(currentTemperature, currentPh));
    }
    public List<Variable> getCurrentParams() {
        return currentParams;
    }
    public void reloadCurrentParam(List<Variable> newParams) {
        System.out.println("Changed params");
        List<Variable> oldParams = currentParams;
        currentParams = newParams;
        pcSupport.firePropertyChange("currentParams", oldParams, currentParams);
    }
}
