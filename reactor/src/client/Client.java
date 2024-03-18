package client;

import client.TCPManager.ClientTCP;
import client.TCPManager.RecurrentUpdateRequest;
import client.TCPManager.SpecificUpdateRequest;

import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Classe représentant le client de l'application.
 */
public class Client {
    public final PropertyChangeSupport pcSupport;
    public List<Double> currentParams;
    private int port = 7777;
    private ClientTCP monClientTCP;
    public int tf = 30; //time in minutes of end of simulator
    public int tick = 2;//call setUpdateValues every tick seconds
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * Constructeur de la classe Client.
     */
    public Client() {
        super();
        monClientTCP = new ClientTCP("localhost", port);
        pcSupport = new PropertyChangeSupport(this);
        init();
    }

    /**
     * Initialise le client.
     */
    public void init(){
        final Runnable updater = new Runnable() {
            @Override
            public void run() {askCurrentParams();};
        };
        final ScheduledFuture<?> bioreactorHandler =
                scheduler.scheduleAtFixedRate(updater, tick, tick, SECONDS); // Calls setUpdateValues() every "tick" seconds
        scheduler.schedule(new Runnable() {             //Stops calling setUpdateValues() after one hour of simulation.
            public void run() { bioreactorHandler.cancel(true); }
        }, tf*60, SECONDS);
    }

    /**
     * Retourne le support de changement de propriété.
     *
     * @return Le support de changement de propriété.
     */
    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcSupport;
    }

    /**
     * Convertit une liste de chaînes en une liste de doubles en utilisant une fonction de conversion.
     *
     * @param listOfString Liste de chaînes à convertir.
     * @param function     Fonction de conversion.
     * @param <T>          Type de la liste d'entrée.
     * @param <U>          Type de la liste de sortie.
     * @return La liste convertie.
     */
    public static <T, U> List<U> convertStringListTodoubleList(List<T> listOfString, Function<T, U> function) {
        return listOfString.stream()
                .map(function)
                .collect(Collectors.toList());
    }

    /**
     * Retourne les paramètres actuels.
     *
     * @return Les paramètres actuels.
     */
    public List<Double> getCurrentParams(){
        return currentParams;
    }

    /**
     * Demande les paramètres à un moment spécifié.
     *
     * @param timeOrder Ordre de temps auquel demander les paramètres.
     * @return Les paramètres demandés.
     */
    public List askParamsAtTime(int timeOrder){
        monClientTCP.sender.setSenderStrategy(new SpecificUpdateRequest(timeOrder));
        String TransmittedResponse = monClientTCP.performSending();
        List<String> listTransmittedResponse = Arrays.asList(TransmittedResponse.split(" "));
        List<String> paramsStrings = listTransmittedResponse.subList(1, listTransmittedResponse.size());
        return convertStringListTodoubleList(paramsStrings, Double::parseDouble);
    }

    /**
     * Demande les paramètres actuels.
     *
     * @return Les paramètres actuels.
     */
    public List askCurrentParams(){
        monClientTCP.sender.setSenderStrategy(new RecurrentUpdateRequest());
        String TransmittedResponse = monClientTCP.performSending();
        List<String> listTransmittedResponse = Arrays.asList(TransmittedResponse.split(" "));
        List<String> paramsStrings = listTransmittedResponse.subList(1, listTransmittedResponse.size());
        List<Double> response = convertStringListTodoubleList(paramsStrings, Double::parseDouble);
        reloadCurrentParam(response);
        return response;
    }

    /**
     * Recharge les paramètres actuels.
     *
     * @param newParams Nouveaux paramètres à charger.
     */
    public void reloadCurrentParam(List<Double> newParams) {
        currentParams = newParams;
        pcSupport.firePropertyChange("currentParams", null, currentParams);
    }
}