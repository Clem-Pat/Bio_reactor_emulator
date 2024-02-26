package bioreactor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static javax.swing.JComponent.WHEN_FOCUSED;

public class BioreactorGUI extends JFrame implements PropertyChangeListener {
    private double order;

    public BioreactorGUI() {
        super();
        // On crée le client TCP
        ServeurTCP serveurtcp = new ServeurTCP(6666);

        // On vient ensuite "écouter" l'automate (c'est la classe ClientGUI qui va
        // recevoir les notifications)
//        automate.getPropertyChangeSupport().addPropertyChangeListener(this);

        // On initialise ensuite l'interface graphique proprement dite
        initGUI();
    }

    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setForeground(new java.awt.Color(255, 0, 128));
            this.setFont(new java.awt.Font("Antique Olive", 0, 10));
            this.setVisible(true);
            this.setTitle("Client");
            {
                JPanel jPanelouest = new JPanel();
                getContentPane().add(jPanelouest, BorderLayout.WEST);
                jPanelouest.setPreferredSize(new java.awt.Dimension(392, 300));
                jPanelouest.setBackground(new java.awt.Color(255, 128, 64));

                JPanel jPanelest = new JPanel();
                getContentPane().add(jPanelest, BorderLayout.EAST);
                jPanelest.setPreferredSize(new java.awt.Dimension(392, 300));
                jPanelest.setBackground(new java.awt.Color(255, 128, 128));
//                jPanelest.setLayout(new GridLayout(3, 0));
                {
                    JLabel panelTitle = new JLabel("Données à un instant t");
                    jPanelest.add(panelTitle, BorderLayout.NORTH);
                    panelTitle.setFont(new java.awt.Font("Tahoma", 1, 14));
                }
                {
                    JTextArea jTextArea1 = new JTextArea();
                    jPanelest.add(jTextArea1, BorderLayout.NORTH);
                    jTextArea1.setText("Choix de l'instant");
                    jTextArea1.setFont(new java.awt.Font("Tahoma", 1, 14));
                    jTextArea1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                    jTextArea1.setWrapStyleWord(true);
                    jTextArea1.setPreferredSize(new java.awt.Dimension(183, 20));
                    jTextArea1.setBackground(new java.awt.Color(230, 230, 230));
                    InputMap inputMap = jTextArea1.getInputMap(WHEN_FOCUSED);
                    ActionMap actionMap = jTextArea1.getActionMap();
                    KeyStroke enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
                    inputMap.put(enterStroke, enterStroke.toString());
                    actionMap.put(enterStroke.toString(), new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            String text = jTextArea1.getText();
                            System.out.println(text);
                            order = Double.parseDouble(text);
                        }
                    });
                }
                {
                    JLabel panelParam1 = new JLabel("T : ");
                    jPanelest.add(panelParam1, BorderLayout.NORTH);
                    panelParam1.setFont(new java.awt.Font("Tahoma", 1, 14));
                }
                {
                    JLabel panelParam1Res = new JLabel("00 °C");
                    jPanelest.add(panelParam1Res, BorderLayout.NORTH);
                    panelParam1Res.setFont(new java.awt.Font("Tahoma", 1, 14));
                }
                {
                    JLabel panelParam1 = new JLabel("Ph : ");
                    jPanelest.add(panelParam1, BorderLayout.NORTH);
                    panelParam1.setFont(new java.awt.Font("Tahoma", 1, 14));
                }
                {
                    JLabel panelParam1Res = new JLabel("00");
                    jPanelest.add(panelParam1Res, BorderLayout.NORTH);
                    panelParam1Res.setFont(new java.awt.Font("Tahoma", 1, 14));
                }
            }
            pack();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
