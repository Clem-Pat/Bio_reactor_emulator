package client;

import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Client {
    public final PropertyChangeSupport pcSupport;
    public List<Double> currentParams;
    private int port = 7777;
    private ClientTCP monClientTCP;
    public Client() {
        super();
        monClientTCP = new ClientTCP("localhost", port);
        pcSupport = new PropertyChangeSupport(this);
    }
    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcSupport;
    }
    public static <T, U> List<U> convertStringListTodoubleList(List<T> listOfString, Function<T, U> function) {
        return listOfString.stream()
                .map(function)
                .collect(Collectors.toList());
    }
    public List<Double> getCurrentParams(){
        return currentParams;
    }
    public List<Double> askParamsAtTime(int timeOrder){
        String TransmittedParams = monClientTCP.transmettreChaine("update " + Integer.toString(timeOrder));
        String[] paramsStrings = TransmittedParams.split(" ");
        List<Double> paramDouble = convertStringListTodoubleList(Arrays.asList(paramsStrings), Double::parseDouble);
        return paramDouble;
    }
    public List<Double> askCurrentParams(){
        currentParams = askParamsAtTime(-1);
        reloadCurrentParam(currentParams);
        return currentParams;
    }
    public void reloadCurrentParam(List<Double> newParams) {
        List<Double> oldParams = currentParams;
        currentParams = newParams;
        pcSupport.firePropertyChange("currentParams", oldParams, currentParams);
    }
}
