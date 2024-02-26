package bioreactor;

import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

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
    public double repr(String time, Variable variable) throws IOException{
        String line = repr(time);
        return switch (variable.type) {
            case "T" -> Double.parseDouble(line.substring(17, Math.min(line.length(), 26)).replace(',', '.'));
            case "O2" -> Double.parseDouble(line.substring(27, Math.min(line.length(), 36)).replace(',', '.'));
            case "Ph" -> Double.parseDouble(line.substring(37, Math.min(line.length(), 46)).replace(',', '.'));
            default -> 1000000.00;

        };
    }
    /**
     * Print a line at a specific time
     */
    public String repr(String time) throws IOException{
        String line;
        while ((line = br.readLine())!= null){
            String this_time = line.substring(0, Math.min(line.length(), 16));
            if (this_time.equals(time)){
                return line;
            };
        }
        return "None";
    }
    /**
     * Print a specific line
     */
    public String repr(int line) throws IOException {
        ArrayList<String> lines = get_lines();
        return lines.get(line);
    }
    /**
     * Print entire file
     */
    public static void repr() throws IOException {
        while (br.readLine() != null){
            System.out.println(br.readLine());
        }
    }
}