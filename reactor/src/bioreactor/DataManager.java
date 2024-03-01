package bioreactor;

import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataManager {
    public static BufferedReader br;

    public DataManager(String path) throws IOException {
        if (path == null){
            path = "C:\\Users\\Clément Patrizio\\Desktop\\2022-10-03-Act2-2.txt";
        }
        File file = new File(path);
        br = new BufferedReader(new FileReader(file));
    }
    public static ArrayList<String> get_lines() throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        while ((br.readLine()) != null){
            list.add(br.readLine());
        }
        return list;
    }
    public double getVariableValueAtTime(double time, Variable variable) throws IOException{
        String line = representLineAtTime(time);
        List<String> lineList = Arrays.asList(line.split("\t"));
        return switch (variable.type) {
            case "T" -> Double.valueOf(lineList.get(2).replace( "," , "." ));
            case "O2" -> Double.valueOf(lineList.get(3).replace( "," , "." ));
            case "Ph" -> Double.valueOf(lineList.get(4).replace( "," , "." ));
            default -> 1000000.00;
        };
    }
    /**
     * Print a line at a specific time
     */
    public String representLineAtTime(double time) throws IOException{
        String line;
        boolean begin = false;
        double this_time = -1;
        while ((line = br.readLine())!= null){
            if (begin){
                try{
                    this_time += 1;
                    if (this_time == time){
                        return line;
                    };
                }catch (Exception e){
                    System.out.println(e);
                }
            }
            if (line.substring(0, Math.min(line.length(), 5)).equals("Temps")){begin = true;}
        }
        return "None";
    }
//    /**
//     * Print a specific line
//     */
//    public String repr(int line) throws IOException {
//        ArrayList<String> lines = get_lines();
//        return lines.get(line);
//    }
    /**
     * Print entire file
     */
    public static void repr() throws IOException {
        while (br.readLine() != null){
            System.out.println(br.readLine());
        }
    }
}