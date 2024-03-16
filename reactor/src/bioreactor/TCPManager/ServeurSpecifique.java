package bioreactor.TCPManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

/**
 * Représente la gestion de la connexion d'un client avec le serveur. Cette
 * gestion repose sur une {@link Socket} et s'effectue dans un {@link Thread}
 *
 *
 */
class ServeurSpecifique extends Thread {

    private final Socket clientSocket;
    private final ServeurTCP monServeur;

    public ServeurSpecifique(Socket uneSocket, ServeurTCP unServeur) {
        super("ServeurThread");

        clientSocket = uneSocket;
        monServeur = unServeur;
    }

    @Override
    public void run() {
        String inputReq;

        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintStream os = new PrintStream(clientSocket.getOutputStream());
            System.out.println("Serveur avec  Client ");

            if ((inputReq = is.readLine()) != null) {
                System.out.println(" Msg Recu " + inputReq);
                String[] chaines = inputReq.split(" ");
                System.out.println(" Ordre Recu " + chaines[0]);
                if (chaines[0].contentEquals("get")) {
                    int valeur = Integer.parseInt(chaines[1]);
                    if (valeur > 0){
                        System.out.println(" parametres demandés à " + valeur);
                        List<Double> parametersToSend = monServeur.getBioreactor().getParametersAtTime(valeur);
                        sendAnswer("get ", os, parametersToSend);
                    }
                }
                if (chaines[0].contentEquals("update")) {
                    System.out.println(" derniers paramètres demandés ");
                    List<Double> parametersToSend = monServeur.getBioreactor().getCurrentParameters();
                    sendAnswer("update ", os, parametersToSend);
                }
            }
            clientSocket.close();
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendAnswer(String initialOrder, PrintStream os, List<Double> parametersToSend){
        String reponse = initialOrder;
        for (int i = 0; i<parametersToSend.toArray().length; i++){
            reponse += parametersToSend.get(i) + " ";
        }
        System.out.println("Réponse du serveur : " + reponse);
        os.println(reponse);
    }
}