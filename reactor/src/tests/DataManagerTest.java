package tests;

import bioreactor.DataManager.DataManager;
import bioreactor.variableManager.Temperature;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DataManagerTest {
    private DataManager manager;

    @Before
    public void setUp() throws IOException {
        manager = new DataManager("C:\\Users\\Clément Patrizio\\Desktop\\2022-10-03-Act2-1.txt");
    }
    @Test
    public void represent_specific_time_specific_parameter() throws IOException {
        Temperature temp = new Temperature(30);
        double res = (double) manager.getVariableValueAtTime(30, temp);
        System.out.println(res);
        System.out.println(((Object) res).getClass().getName());

//        Oxygen ox = new Oxygen();
//        double res = manager.repr("03/10/2022 18:15", ox);
//        System.out.println(res);
//        System.out.println(((Object) res).getClass().getName());
    }
}