package bioreactor.DataManager;

import bioreactor.Bioreactor;
import bioreactor.variableManager.*;

import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.*;

/**
 * Cette classe gère les données du bioreacteur.
 */
public class DataManager {
    public static BufferedReader br;
    public static Dictionary allO2 = new Hashtable();
    public static Dictionary allPh = new Hashtable();
    public static Dictionary allTemperatures = new Hashtable();
    public List<String> variablesTypesToConsider = Arrays.asList("T", "O2", "Ph");

    /**
     * Constructeur de la classe DataManager.
     *
     * @param path Chemin du fichier contenant les données du bioreacteur.
     * @throws IOException En cas d'erreur d'entrée/sortie.
     */
    public DataManager(String path) throws IOException {
        if (path == null){
            path = "reactor/src/bioreactor/DataManager/2022-10-03-Act2-1.txt";
        }
        File file = new File(path);
        br = new BufferedReader(new FileReader(file));
        this.getAllData();
    }

    /**
     * Récupère la valeur de la variable à un moment donné.
     *
     * @param time     Temps auquel on veut récupérer la valeur.
     * @param variable Variable dont on veut récupérer la valeur.
     * @return La valeur de la variable au temps spécifié.
     */
    public Object getVariableValueAtTime(double time, Variable variable){
        return getVariableValueAtTime(time, variable.type);
    }

    /**
     * Récupère la valeur de la variable à un moment donné.
     *
     * @param time         Temps auquel on veut récupérer la valeur.
     * @param variableType Type de la variable dont on veut récupérer la valeur.
     * @return La valeur de la variable au temps spécifié.
     */
    public Double getVariableValueAtTime(double time, String variableType){
        return switch (variableType) {
            case "T" -> (Double) allTemperatures.get(time);
            case "O2" -> (Double) allO2.get(time);
            case "Ph" -> (Double) allPh.get(time);
            default -> null;
        };
    }

    /**
     * Récupère toutes les données du fichier.
     *
     * @throws IOException En cas d'erreur d'entrée/sortie.
     */
    public void getAllData() throws IOException {
        String line;
        double this_time = -1;
        boolean begin = false;
        while ((line = br.readLine()) != null){
            if (begin){
                this_time += 1;
                List<String> lineList = Arrays.asList(line.split("\t"));
                allTemperatures.put(this_time, Double.valueOf(lineList.get(2).replace( "," , "." )));
                allO2.put(this_time, Double.valueOf(lineList.get(3).replace( "," , "." )));
                allPh.put(this_time, Double.valueOf(lineList.get(4).replace( "," , "." )));
            }
            if (line.substring(0, Math.min(line.length(), 5)).equals("Temps")){begin = true;}
        }
    }

    /**
     * Ajoute une variable au collecteur.
     *
     * @param reactor Réacteur biologique auquel ajouter la variable.
     * @param index   Index de la variable.
     * @param t       Temps auquel ajouter la variable.
     */
    public void addVariableToCollector(Bioreactor reactor, int index, double t){
        String variableType = variablesTypesToConsider.get(index);
        switch (variableType) {
            case "T" -> reactor.listPreviousVariables.get(index).add(new Temperature(t, getVariableValueAtTime(t, variableType)));
            case "O2" -> reactor.listPreviousVariables.get(index).add(new Oxygen(t, getVariableValueAtTime(t, variableType)));
            case "Ph" -> reactor.listPreviousVariables.get(index).add(new Ph(t, getVariableValueAtTime(t, variableType)));
            default -> System.out.println("No variable named " + variableType + " found");
        };
    }
}