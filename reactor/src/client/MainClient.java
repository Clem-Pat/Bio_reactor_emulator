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
        Client client = new Client();
        // Instancie l'interface graphique du client
        new ClientGUI(client);
    }

}