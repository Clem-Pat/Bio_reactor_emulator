import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
public class DataManager {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
//        if (path == null){
//            path = "C:\\Users\\Clément Patrizio\\Desktop\\2022-10-03-Act2-2.txt"
//        }
        File file = new File(
                "C:\\Users\\Clément Patrizio\\Desktop\\2022-10-03-Act2-2.txt");
        br = new BufferedReader(new FileReader(file));
    }
    public static ArrayList<String> get_lines() throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        while ((br.readLine()) != null){
            list.add(br.readLine());
        }
        return list;
    }
    public String repr(String time, IVariable variable) throws IOException{
        String line = repr(time);
        if (variable.type == "T"){
            return line.substring(16, Math.min(line.length(), 25));
        } else if (variable.type == "O2") {
            return line.substring(26, Math.min(line.length(), 35));
        } else if (variable.type == "PH") {
            return line.substring(36, Math.min(line.length(), 45));
        }
        return "Not a correct variable type";
    }
    /**
     * Print a line at a specific time
     */
    public String repr(String time) throws IOException{
        String line;
        while ((line = br.readLine())!= null){
            String this_time = line.substring(0, Math.min(line.length(), 15));
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