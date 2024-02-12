package banqueServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

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
				System.out.println(" Msg 2 Recu " + inputReq);
				String[] chaines = inputReq.split(" ");
				System.out.println(" Ordre Recu " + chaines[0]);
				if (chaines[0].contentEquals("retrait")) {
					int valeur = Integer.parseInt(chaines[1]);

					System.out.println(" valeur demandee  " + valeur);

					int valeurRetrait = monServeur.getBanque().demandeRetrait(valeur);

					String valeurExpediee = "" + valeurRetrait;
					System.out.println(" Retrait dans serveur " + valeurExpediee);

					os.println(valeurExpediee);

					System.out.println(monServeur);
				}
				if (chaines[0].contentEquals("depot")) {
					int valeur = Integer.parseInt(chaines[1]);

					System.out.println(" valeur demandee  " + valeur);

					int valeurDepot = monServeur.getBanque().demandeDepot(valeur);

					String valeurExpediee = "" + valeurDepot;
					System.out.println(" Depot dans serveur " + valeurExpediee);

					os.println(valeurExpediee);

					System.out.println(monServeur);
				}
			}
			clientSocket.close();
			os.close();
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
