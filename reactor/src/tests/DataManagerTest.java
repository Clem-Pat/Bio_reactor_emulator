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
        manager = new DataManager("bioreactor\\DataManager\\2022-10-03-Act2-1.txt");
    }
    @Test
    public void represent_specific_time_specific_parameter() throws IOException {
        double res = (double) manager.getVariableValueAtTime(30, "T");
        System.out.println(res);
        System.out.println(((Object) res).getClass().getName());
    }

}