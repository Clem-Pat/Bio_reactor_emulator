package banqueServer;

/**
 * Contient la méthode main()
 * 
 */
public class MainServeur {

	/**
	 * Méthode principale : lance le programme
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Banque banque = new Banque(10000);
		System.out.println("Creation de la banque: " + banque);

		// Création des deux interfaces graphiques
		MontantBanqueGUI montantBanqueGUI = new MontantBanqueGUI(banque);
		banque.addPropertyChangeListener(montantBanqueGUI);
		new DerniereOperationGUI(banque);

		// Ouverture de la banque
		banque.ouvrirBanque();
	}

}
