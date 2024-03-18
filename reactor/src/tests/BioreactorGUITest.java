package tests;

import bioreactor.Bioreactor;
import bioreactor.BioreactorGUI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.io.IOException;

public class BioreactorGUITest {

    @Test
    public void testInitGUI() throws IOException {
        Bioreactor reactor = new Bioreactor();
        BioreactorGUI gui = new BioreactorGUI(reactor);

        // Check if GUI components are initialized
        assertNotNull(gui.listPanelsCurrentParams);
        assertNotNull(gui.getContentPane());
    }

}