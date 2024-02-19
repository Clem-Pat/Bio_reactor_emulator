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
        manager = new DataManager("C:\\Users\\Clément Patrizio\\Desktop\\2022-10-03-Act2-2.txt");
    }

    @Test
    public void represent_specific_time() throws IOException {
        String line = manager.br.readLine();
        System.out.println(line.substring(0, Math.min(line.length(), 15)));
        System.out.println(manager.repr("03/10/2022 18:15"));
    }
}