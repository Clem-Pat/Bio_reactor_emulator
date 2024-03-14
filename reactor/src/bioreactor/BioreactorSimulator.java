package bioreactor;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

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
    Variable currentOxygen = new Oxygen(currentNumberOfTicks);
    Variable currentPh = new Ph(currentNumberOfTicks);
    public static Dictionary listPreviousTemperature = new Hashtable<Double, Double>();
    public static Dictionary listPreviousOxygen = new Hashtable<Double, Double>();
    public static Dictionary listPreviousPh = new Hashtable<Double, Double>();
    public int tf = 10; //time in minutes of end of simulator
    public int tick = 2;//call setUpdateValues every tick seconds
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private List<Variable> currentParams;

    public BioreactorSimulator() throws IOException {
        data = new DataManager(null);
        ServeurTCP serveurtcp = new ServeurTCP(7777, this);
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
        currentOxygen.setTimeAndValue(currentNumberOfTicks, (double) data.getVariableValueAtTime(currentNumberOfTicks, currentOxygen));
        listPreviousTemperature.put(currentNumberOfTicks, currentTemperature);
        listPreviousPh.put(currentNumberOfTicks, currentPh);
        listPreviousOxygen.put(currentNumberOfTicks, currentOxygen);
        reloadCurrentParam(Arrays.asList(currentTemperature, currentPh, currentOxygen));
    }
    public Object getVariableValueAtTime(double t, Variable variable){
        return switch (variable.type) {
            case "T" -> listPreviousTemperature.get(t);
            case "O2" -> listPreviousOxygen.get(t);
            case "Ph" -> listPreviousPh.get(t);
            default -> 1000000.00;
        };
    }
    public List<Object> getParametersAtTime(int t){
        System.out.println(listPreviousTemperature);
        return  Arrays.asList(((Variable) listPreviousTemperature.get(t)).getValue(), ((Variable) listPreviousOxygen.get(t)).getValue(), ((Variable) listPreviousPh.get(t)).getValue());
    }
    public void reloadCurrentParam(List<Variable> newParams) {
        currentParams = newParams;
        pcSupport.firePropertyChange("currentParams", null, newParams);
    }
    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcSupport;
    }
}
