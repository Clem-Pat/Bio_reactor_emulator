package client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Représente un client TCP : cette classe s'appuie principalement sur un objet
 * {@link Socket}, et s'initialise par un nom de serveur et un numéro de port
 */
public class ClientTCP {
    private int numeroPort; // ## attribute numeroPort
    private String nomServeur; // ## attribute nomServeur
    private BufferedReader socIn; // ## link socIn
    private PrintStream socOut; // ## link socOut
    private Socket socketServeur; // ## link socketServeur
    /**
     * Création d'un nouveau {@link ClientTCP} avec un nom de serveur et un numéro
     * de port
     *
     * @param unNomServeur
     * @param unNumero
     */
    public ClientTCP(String unNomServeur, int unNumero) {
        numeroPort = unNumero;
        nomServeur = unNomServeur;
    }
    public boolean connexionServeur() {
        try {
            socketServeur = new Socket(nomServeur, numeroPort);
            socOut = new PrintStream(socketServeur.getOutputStream());
            socIn = new BufferedReader(new InputStreamReader(socketServeur.getInputStream()));

        } catch (UnknownHostException e) {
//            System.err.println("Serveur inconnu : " + e);
            return false;
        } catch (Exception e) {
//            System.err.println("Exception:  " + e);
            return false;
        }
        return true;
    }
    public void deconnexionServeur() {
        try {
            socOut.close();
            socIn.close();
            socketServeur.close();
        } catch (Exception e) {
            System.err.println("Exception:  " + e);
        }
    }
    public String transmettreChaine(String uneChaine) {
        String msgServeur = null;
        if (connexionServeur()) {
            try {
                System.out.println("Client " + uneChaine);
                socOut.println(uneChaine);
                socOut.flush();
                msgServeur = socIn.readLine();
                System.out.println("Reponse serveur : " + msgServeur);
//                deconnexionServeur();
            } catch (Exception e) {
                System.err.println("Exception:  " + e);
            }
        }
        else {
            msgServeur = "0.5 4 5.6";
        }

        return msgServeur;
    }
}
