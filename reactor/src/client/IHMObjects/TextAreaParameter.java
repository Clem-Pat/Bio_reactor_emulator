package client.IHMObjects;

import client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.Socket;

/**
 * Boîte texte intelligente : lorsque l'on tape un chiffre puis qu'on fait entrée, elle demande au Client d'interroger le serveur du bioreacteur via le ClientTCP
 *
 */
public class TextAreaParameter extends JTextArea{

    public TextAreaParameter(ClientGUI GUI, JPanel panel){
        panel.add(this, BorderLayout.NORTH);
        setText("Choix de l'instant");
        setFont(new java.awt.Font("Tahoma", 1, 14));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setWrapStyleWord(true);
        setPreferredSize(new java.awt.Dimension(183, 20));
        setBackground(new java.awt.Color(230, 230, 230));
        InputMap inputMap = getInputMap(WHEN_FOCUSED);
        ActionMap actionMap = getActionMap();
        KeyStroke enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        inputMap.put(enterStroke, enterStroke.toString());
        actionMap.put(enterStroke.toString(), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    GUI.sendOrder(Integer.parseInt(getText()));
                }catch (NumberFormatException e){
                    setText("Choix de l'instant");
                }
            }
        });
    }
}
