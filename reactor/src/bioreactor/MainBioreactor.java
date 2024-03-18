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
        Bioreactor reactor = new Bioreactor();
        new BioreactorGUI(reactor);
    }
}

