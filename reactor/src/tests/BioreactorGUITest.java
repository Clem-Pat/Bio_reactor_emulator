package tests;

import bioreactor.Bioreactor;
import bioreactor.BioreactorGUI;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/*

You specify the working directory in IntelliJ idea in your run/debug configuration. In your IntelliJ toolbar, look for the run icon (a green triangle). There should be a drop-down just to the left of the run icon. Click on the drop-down. Click edit configurations. Select your configuration and set the working directory to whatever you need.

When you run your program from IntelliJ (or start debugging), IntelliJ sets the working directory for your application to whatever you specified above. There is no way to stop IntelliJ from setting the directory. All relative paths in your program will be relative to this directory. Your program can find the directory using System.getProperty("user.dir").
*/

public class BioreactorGUITest {

    @Test
    public void testInitGUI() throws IOException {
        Bioreactor reactor = new Bioreactor("bioreactor\\DataManager\\2022-10-03-Act2-1.txt");
        BioreactorGUI gui = new BioreactorGUI(reactor);

        // Check if GUI components are initialized
        assertNotNull(gui.listPanelsCurrentParams);
        assertNotNull(gui.getContentPane());
    }

}