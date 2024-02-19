package bioreactor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Oxygen implements IVariable, PropertyChangeListener {

    BioreacteurGUI myGui;
    AllData leData;// pas sur, je sousentend l'existence ultérieure d'un seule endroit pour les données

    public Oxygen(AllData b, BioreacteurGUI bg) {
        this.leData = b;
        this.myGui = bg;
        b.getPropertyChangeSupport().addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // C'est couplé avec la futur GUI, a chaque fois que la variable soit changé il faut notifier la banque.

        // myGui.getTexteSommeBanque().setText(String.valueOf(laBanque.getLeCompte().getSomme()));
        // System.out.println("Somme mise a jour : " + laBanque.getLeCompte().getSomme());
        // myGui.getTexteSommeDemandee().setText(arg.toString());
    }

    public void getValue(){

    }
}
