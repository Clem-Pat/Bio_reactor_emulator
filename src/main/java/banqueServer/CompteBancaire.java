package banqueServer;

/**
 * Représente un compte bancaire: ici on gère uniquement la somme présente sur
 * le compte, comme un entier
 *
 */
public class CompteBancaire {

	private int somme = 0;

	public CompteBancaire(int somme) {
		this.somme = somme;
	}

	public int getSomme() {
		return somme;
	}

	public void setSomme(int somme) {
		this.somme = somme;
	}

}
