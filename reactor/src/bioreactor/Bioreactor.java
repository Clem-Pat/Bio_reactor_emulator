package bioreactor;

import bioreactor.DataManager.DataManager;
import bioreactor.TCPManager.ServeurTCP;
import bioreactor.variableManager.*;

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
public class Bioreactor{
    public final PropertyChangeSupport pcSupport;
    public DataManager data; //Change data file address
    public int currentNumberOfTicks = -1;
    public List<CollectorVariable> listPreviousVariables = new ArrayList<>();
    public int tf = 30; //time in minutes of end of simulator
    public int tick = 2;    //call setUpdateValues every tick seconds
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public Bioreactor(String path) throws IOException {
        data = new DataManager(path);
        ServeurTCP serveurtcp = new ServeurTCP(7777, this);
        pcSupport = new PropertyChangeSupport(this);
        for (String type : data.variablesTypesToConsider){
            listPreviousVariables.add(new CollectorVariable());    // listPreviousVariables = [CollectorVariable listPreviousTemperature, CollectorVariable listPreviousOxygen, CollectorVariable listPreviousPh]
        }
        init();
    }
    public void init(){
        final Runnable updater = new Runnable() {
            @Override
            public void run() {
                setUpdateValues();
            };
        };
        final ScheduledFuture<?> bioreactorHandler =
                scheduler.scheduleAtFixedRate(updater, tick, tick, SECONDS); // Calls setUpdateValues() every "tick" seconds
        scheduler.schedule(new Runnable() {             //Stops calling setUpdateValues() after one hour of simulation.
            public void run() { bioreactorHandler.cancel(true); }
        }, tf*60L, SECONDS);
    }
    public void setUpdateValues() {
        currentNumberOfTicks += 1;
        for (int i = 0; i < data.variablesTypesToConsider.size(); i++){
            data.addVariableToCollector(this,i,currentNumberOfTicks);
        }
        reloadCurrentParam();
    }
    public List<Double> getCurrentVariablesValues(){
        List<Double> res = new ArrayList<>();
        for (int i = 0; i < listPreviousVariables.size(); i++){
            res.add(listPreviousVariables.get(i).getLastVariable().getValue());
        }
        return res;
    }
    public List<Double> getParametersAtTime(int t){
        if (t>0 && t <= listPreviousVariables.get(0).getLastVariable().getTime()){
            List<Double> res = new ArrayList<>();
            for (int i = 0; i < listPreviousVariables.size(); i++){
                res.add(listPreviousVariables.get(i).getValueAtTime(t));
            }
            return res;
        }
        return Arrays.asList(0.,0.,0.);
    }
    public void reloadCurrentParam() {
        List<Variable> res = new ArrayList<>();
        for (int i = 0; i < listPreviousVariables.size(); i++){
            res.add(listPreviousVariables.get(i).getLastVariable());
        }
        pcSupport.firePropertyChange("currentParams", null, res);
    }
    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcSupport;
    }
}
