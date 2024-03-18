package tests;

import bioreactor.DataManager.DataManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class DataManagerTest {

    @Test
    public void testGetVariableValueAtTime() {
        DataManager dataManager = null;
        try {
            dataManager = new DataManager("path/to/test/file.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(dataManager);

        // Assuming time and variable type are valid for the test file
        double time = 1.0;
        String variableType = "T";
        assertNotNull(dataManager.getVariableValueAtTime(time, variableType));
    }

}
/*package tests;

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

}*/