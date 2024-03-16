package client.TCPManager;
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
    public PrintStream socOut; // ## link socOut
    private Socket socketServeur; // ## link socketServeur
    public RequestSender sender = new RequestSender(new RecurrentUpdateRequest());
    /**
     * Création d'un nouveau {@link ClientTCP} avec un nom de serveur et un numéro
     * de port
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
            return true;
        } catch (UnknownHostException e) {
            System.out.println("Serveur inconnu : " + e);
            return false;
        } catch (Exception e) {
            System.out.println("Exception:  " + e);
            return false;
        }
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
    public String performSending() {
        String msgServeur = "null";
        if (connexionServeur()) {
            try {
                sender.performSending(socOut);
                msgServeur = socIn.readLine();
                System.out.println("Reponse serveur : " + msgServeur);
                deconnexionServeur();
            } catch (Exception e) {
                System.err.println("Exception:  " + e);
            }
        }
        else {
//            On simule la connexion au serveur du Bioréacteur
            msgServeur = "error 1000000 1000000 1000000";
            System.out.println("Reponse serveur : " + msgServeur);
        }

        return msgServeur;
    }
}
