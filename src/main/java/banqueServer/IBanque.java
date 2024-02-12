
package banqueServer;

/**
 * API représentant la banque gérant un compte bancaire
 *
 */
public interface IBanque {

	int demandeDepot(int unDepot);

	int demandeRetrait(int unRetrait);

	int getDerniereOperation();

}
