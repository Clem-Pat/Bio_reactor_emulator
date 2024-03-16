package bioreactor.TCPManager;

import bioreactor.Bioreactor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Représente un serveur TCP, qui écoute sur un numéro de port
 *
 *
 */
public class ServeurTCP extends Thread {

    private static int nbConnexions = 0;
    /** Maximum de connexions client autorisées */
    private int maxConnexions;
    private Socket clientSocket;
    private int numeroPort;
    private Bioreactor reactorCaller;

    public ServeurTCP(int unNumeroPort, Bioreactor reactor) {
        numeroPort = unNumeroPort;
        maxConnexions = 10000;
        reactorCaller = reactor;
        start();
    }
    /* l'ancienne methode go est remplacee par run */
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(numeroPort);
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + numeroPort + ", " + e);
            System.exit(1);
        }

        /* On autorise maxConnexions traitements */
        while (nbConnexions <= maxConnexions) {
            try {
                System.out.println(" Attente du serveur pour la communication d'un client ");
                clientSocket = serverSocket.accept();
                nbConnexions++;
                System.out.println("Nb de clients : " + nbConnexions);
            } catch (IOException e) {
                System.out.println("Accept failed: " + serverSocket.getLocalPort() + ", " + e);
                System.exit(1);
            }
            ServeurSpecifique st = new ServeurSpecifique(clientSocket, this);
            st.start();
        }
        System.out.println("Deja " + nbConnexions + " clients. Maximum autorisé atteint");

        try {
            serverSocket.close();
            nbConnexions--;
        } catch (IOException e) {
            System.out.println("Could not close");
        }

    }
    public Bioreactor getBioreactor(){
        return reactorCaller;
    }

}
