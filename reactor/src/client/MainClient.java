package client;
/**
 * Contient la méthode main()
 *
 */
public class MainClient {

    /**
     * Méthode principale : lance le programme
     *
     * @param args
     */
    public static void main(String[] args) {
        // Instancie l'interface graphique du client
        new ClientGUI();
        System.out.println("attente interface graphique");
    }

}