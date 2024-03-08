package bioreactor;

import java.io.IOException;
/**
 * Contient la méthode main()
 *
 */
public class MainBioreactor {

    /**
     * Méthode principale : lance le programme
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // Instancie l'interface graphique du client
        BioreactorSimulator reactor = new BioreactorSimulator();
        new BioreactorGUI(reactor);
        System.out.println("attente interface graphique");
    }
}

