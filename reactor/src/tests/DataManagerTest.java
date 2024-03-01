package tests;

import bioreactor.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class DataManagerTest {
    private DataManager manager;

    @Before
    public void setUp() throws IOException {
        manager = new DataManager("C:\\Users\\Clément Patrizio\\Desktop\\2022-10-03-Act2-1.txt");
    }

    @Test
    public void represent_specific_time() throws IOException {
        String line = manager.br.readLine();
        System.out.println(line.substring(0, Math.min(line.length(), 16)));
        System.out.println(manager.representLineAtTime(15));
    }
    @Test
    public void represent_specific_time_specific_parameter() throws IOException {
        Temperature temp = new Temperature(30);
        double res = manager.getVariableValueAtTime(30, temp);
        System.out.println(res);
        System.out.println(((Object) res).getClass().getName());

//        Oxygen ox = new Oxygen();
//        double res = manager.repr("03/10/2022 18:15", ox);
//        System.out.println(res);
//        System.out.println(((Object) res).getClass().getName());
    }
}