package client.IHMObjects;

import client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static javax.swing.JComponent.WHEN_FOCUSED;

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
                String text = getText();
                System.out.println(text);
                try {
                    GUI.sendOrder(Double.parseDouble(text));
                }catch (Exception e){
                    setText("Choix de l'instant");
                }
            }
        });
    }
}
