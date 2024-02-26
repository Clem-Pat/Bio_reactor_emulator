package bioreactor;

import client.ClientGUI;
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
    public static void main(String[] args) {
        // Instancie l'interface graphique du client
        new BioreactorGUI();
        System.out.println("attente interface graphique");
    }
}

