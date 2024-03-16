package bioreactor;

import bioreactor.DataManager.DataManager;
import bioreactor.TCPManager.ServeurTCP;
import bioreactor.variableManager.Oxygen;
import bioreactor.variableManager.Ph;
import bioreactor.variableManager.Temperature;
import bioreactor.variableManager.Variable;

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
public class Bioreactor {
    public final PropertyChangeSupport pcSupport;
    public DataManager data; //Change data file address
    public int currentNumberOfTicks = 0;
    Variable currentTemperature = new Temperature(currentNumberOfTicks);
    Variable currentOxygen = new Oxygen(currentNumberOfTicks);
    Variable currentPh = new Ph(currentNumberOfTicks);
    public static Dictionary listPreviousTemperature = new Hashtable<Double, Double>();
    public static Dictionary listPreviousOxygen = new Hashtable<Double, Double>();
    public static Dictionary listPreviousPh = new Hashtable<Double, Double>();
    public int tf = 30; //time in minutes of end of simulator
    public int tick = 2;    //call setUpdateValues every tick seconds
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private List<Variable> currentParams;

    public Bioreactor() throws IOException {
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
                scheduler.scheduleAtFixedRate(updater, tick, tick, SECONDS); // Calls setUpdateValues() every "tick" seconds
        scheduler.schedule(new Runnable() {             //Stops calling setUpdateValues() after one hour of simulation.
            public void run() { bioreactorHandler.cancel(true); }
        }, tf*60, SECONDS);
    }
    public void setUpdateValues() throws IOException {
        currentNumberOfTicks += 1;
        Double temperatureValue = (Double) data.getVariableValueAtTime(currentNumberOfTicks, currentTemperature);
        Double PhValue = (Double) data.getVariableValueAtTime(currentNumberOfTicks, currentPh);
        Double OxygenValue = (Double) data.getVariableValueAtTime(currentNumberOfTicks, currentOxygen);
        currentTemperature.setTimeAndValue(currentNumberOfTicks, temperatureValue);
        currentPh.setTimeAndValue(currentNumberOfTicks, PhValue);
        currentOxygen.setTimeAndValue(currentNumberOfTicks, OxygenValue);
        listPreviousTemperature.put(currentNumberOfTicks, new Temperature(currentNumberOfTicks, temperatureValue));
        listPreviousPh.put(currentNumberOfTicks, new Ph(currentNumberOfTicks, PhValue));
        listPreviousOxygen.put(currentNumberOfTicks, new Oxygen(currentNumberOfTicks, OxygenValue));
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
    public List<Double> getCurrentParameters(){
        List<Double> res = new ArrayList<>();
        for (int i = 0; i<currentParams.toArray().length; i++){
            res.add(currentParams.get(i).getValue());
        }
        return res;
    }
    public List<Double> getParametersAtTime(int t){
        if (t>0 && t<=listPreviousTemperature.size()){
            return  Arrays.asList(((Variable) listPreviousTemperature.get(t)).getValue(), ((Variable) listPreviousOxygen.get(t)).getValue(), ((Variable) listPreviousPh.get(t)).getValue());
        }
        return Arrays.asList(0.,0.,0.);
    }
    public void reloadCurrentParam(List<Variable> newParams) {
        currentParams = newParams;
        pcSupport.firePropertyChange("currentParams", null, newParams);
    }
    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcSupport;
    }
}
