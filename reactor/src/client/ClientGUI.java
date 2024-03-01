package client;

import client.IHMObjects.LabelParameter;
import client.IHMObjects.TextAreaParameter;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class ClientGUI extends JFrame implements PropertyChangeListener {
    public double order;
    public ClientGUI(Client client) {
        super();
        // On crée le client TCP
        ClientTCP serveurtcp = new ClientTCP("localhost", 6666);

        // On vient ensuite "écouter" l'automate (c'est la classe ClientGUI qui va
        // recevoir les notifications)
        client.getPropertyChangeSupport().addPropertyChangeListener(this);
        initGUI();
    }
    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            this.setVisible(true);
            this.setTitle("Client");
            {
                JPanel jPanelouest = new JPanel();
                getContentPane().add(jPanelouest, BorderLayout.WEST);
                jPanelouest.setPreferredSize(new java.awt.Dimension(392, 300));
                jPanelouest.setBackground(new java.awt.Color(255, 128, 64));
                {
                    JLabel panelTitle = new JLabel("Données en temps réel");
                    jPanelouest.add(panelTitle, BorderLayout.NORTH);
                    jPanelouest.setLayout(new GridLayout(3, 1));
                    panelTitle.setFont(new java.awt.Font("Tahoma", 1, 14));
                }
                {
                    JPanel jPanelParam = new JPanel();
                    jPanelouest.add(jPanelParam, BorderLayout.NORTH);
                    jPanelParam.setLayout(new GridLayout(3, 1));
                    jPanelParam.setBackground(new java.awt.Color(255, 128, 64));
                    {
                        LabelParameter panelParam1 = new LabelParameter(jPanelParam, "T : ", "00 °C");
                    }{
                    LabelParameter panelParam2 = new LabelParameter(jPanelParam, "O : ", "00 %");
                }{
                    LabelParameter panelParam3 = new LabelParameter(jPanelParam, "Ph : ", "00");
                }
                }
                JPanel jPanelest = new JPanel();
                getContentPane().add(jPanelest, BorderLayout.EAST);
                jPanelest.setPreferredSize(new java.awt.Dimension(392, 300));
                jPanelest.setBackground(new java.awt.Color(255, 128, 128));
                jPanelest.setLayout(new GridLayout(2, 1));
                {
                    JPanel panelTitle = new JPanel();
                    panelTitle.setBorder(BorderFactory.createEmptyBorder(60, 20, 60, 20));
                    jPanelest.add(panelTitle, BorderLayout.NORTH);
                    panelTitle.setLayout(new GridLayout(1, 2, 20, 20));
                    panelTitle.setBackground(new java.awt.Color(255, 128, 128));
                    {
                        JLabel paramTitle = new JLabel("Données à un instant t");
                        panelTitle.add(paramTitle, BorderLayout.NORTH);
                        paramTitle.setFont(new java.awt.Font("Tahoma", 1, 14));
                    }
                    {
                        TextAreaParameter textAreaParameter = new TextAreaParameter(this, panelTitle);
                    }
                }
                {
                    JPanel jPanelParam = new JPanel();
                    jPanelest.add(jPanelParam, BorderLayout.NORTH);
                    jPanelParam.setLayout(new GridLayout(3, 2));
                    jPanelParam.setBackground(new java.awt.Color(255, 128, 128));
                    {
                        LabelParameter panelParam1 = new LabelParameter(jPanelParam, "T : ", "00 °C");
                    }{
                    LabelParameter panelParam2 = new LabelParameter(jPanelParam, "O : ", "00 %");
                }{
                    LabelParameter panelParam3 = new LabelParameter(jPanelParam, "Ph : ", "00");
                }
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
