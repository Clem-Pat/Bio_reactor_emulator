package bioreactor;

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
    private IContext contexte;
    private IProtocole protocole;
    private int numeroPort;
    private BioreactorSimulator reactorCaller;

    public ServeurTCP(int unNumeroPort, BioreactorSimulator reactor) {
        numeroPort = unNumeroPort;
        maxConnexions = 10;
        reactorCaller = reactor;
        start();
    }
    public ServeurTCP(IContext b, IProtocole p, int port, BioreactorSimulator reactor) {
        this(port, reactor);
        contexte = b;
        protocole = p;
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
    public BioreactorSimulator getBioreactor(){
        return reactorCaller;
    }
    public IProtocole getProtocole() {
        return protocole;
    }
    public IContext getContexte() {
        return contexte;
    }

}
