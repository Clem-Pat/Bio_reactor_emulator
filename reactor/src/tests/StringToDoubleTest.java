package tests;

import client.Client;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StringToDoubleTest {

    @Test
    public void StringToDouble() {
        // Test conversion of string list to double list
        Client client = new Client();
        List<String> stringList = Arrays.asList("1.0", "2.0", "3.0");
        List<Double> doubleList = client.convertStringListTodoubleList(stringList, Double::parseDouble);

        // Check if the conversion is correct
        assertEquals(3, doubleList.size());
        assertEquals(1.0, doubleList.get(0), 0.0); // Use delta for double comparison
        assertEquals(2.0, doubleList.get(1), 0.0); // Use delta for double comparison
        assertEquals(3.0, doubleList.get(2), 0.0); // Use delta for double comparison
    }

}