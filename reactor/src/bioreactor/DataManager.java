package bioreactor;

import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class DataManager {
    public static BufferedReader br;
    public static Dictionary allO2 = new Hashtable();
    public static Dictionary allPh = new Hashtable();
    public static Dictionary allTemperatures = new Hashtable();

    public DataManager(String path) throws IOException {
        if (path == null){
            path = "C:\\Users\\Clément Patrizio\\Desktop\\2022-10-03-Act2-1.txt";
        }
        File file = new File(path);
        br = new BufferedReader(new FileReader(file));
        this.getAllData();
    }
    public Object getVariableValueAtTime(double time, Variable variable){
        return switch (variable.type) {
            case "T" -> allTemperatures.get(time);
            case "O2" -> allO2.get(time);
            case "Ph" -> allPh.get(time);
            default -> 1000000.00;
        };
    }
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
}