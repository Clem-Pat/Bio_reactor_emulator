package bioreactor;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BioreactorGUI extends JFrame implements PropertyChangeListener {
    public double order;

    public BioreactorGUI() {
        super();
        // On crée le client TCP
        ServeurTCP serveurtcp = new ServeurTCP(6666);

        // On vient ensuite "écouter" l'automate (c'est la classe ClientGUI qui va
        // recevoir les notifications)
//        automate.getPropertyChangeSupport().addPropertyChangeListener(this);

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        if (evt.getSource() instanceof Automate) {
//            if (evt.getPropertyName().equals("sommePoche")) {
//                jTextFieldSommenpoche.setText(Integer.toString(((Automate) evt.getSource()).getSommePoche()));
//            }
//        }
    }
}
