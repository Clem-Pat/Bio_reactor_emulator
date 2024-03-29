package bioreactor;

import bioreactor.variableManager.Variable;
import client.IHMObjects.LabelParameter;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;

/**
 * Interface graphique du bioreacteur.
 */
public class BioreactorGUI extends JFrame implements PropertyChangeListener {
    public List<LabelParameter> listPanelsCurrentParams;
    public Bioreactor reactor;

    /**
     * Constructeur de la classe BioreactorGUI.
     *
     * @param initReactor Réacteur biologique à associer à l'interface graphique.
     */
    public BioreactorGUI(Bioreactor initReactor) {
        reactor = initReactor;
        reactor.getPropertyChangeSupport().addPropertyChangeListener(this);
        initGUI();
    }

    /**
     * Initialise l'interface graphique.
     */
    private void initGUI() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("Bioreactor");
        {
            JPanel jPanel = new JPanel();
            getContentPane().add(jPanel, BorderLayout.WEST);
            jPanel.setPreferredSize(new java.awt.Dimension(392, 300));
            jPanel.setBackground(new java.awt.Color(255, 128, 64));
            {
                JLabel panelTitle = new JLabel("Données en temps réel");
                jPanel.add(panelTitle, BorderLayout.NORTH);
                jPanel.setLayout(new GridLayout(3, 1));
                panelTitle.setFont(new java.awt.Font("Tahoma", 1, 14));
            }
            {
                JPanel jPanelParam = new JPanel();
                jPanel.add(jPanelParam, BorderLayout.NORTH);
                jPanelParam.setLayout(new GridLayout(3, 1));
                jPanelParam.setBackground(new java.awt.Color(255, 128, 64));
                LabelParameter panelCurrentParam1 = new LabelParameter(jPanelParam, "T : ", "00", " °C");
                LabelParameter panelCurrentParam2 = new LabelParameter(jPanelParam, "O : ", "00", " %");
                LabelParameter panelCurrentParam3 = new LabelParameter(jPanelParam, "Ph : ", "00", "");
                listPanelsCurrentParams = Arrays.asList(panelCurrentParam1, panelCurrentParam2, panelCurrentParam3);
            }
            pack();
        }
    }

    /**
     * Méthode appelée lorsqu'une propriété change.
     *
     * @param evt Événement de changement de propriété.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object newValue = evt.getNewValue();
        listPanelsCurrentParams.get(0).setValue(Double.toString(((Variable) ((List<?>) newValue).get(0)).getValue()));
        listPanelsCurrentParams.get(2).setValue(Double.toString(((Variable) ((List<?>) newValue).get(1)).getValue()));
        listPanelsCurrentParams.get(1).setValue(Double.toString(((Variable) ((List<?>) newValue).get(2)).getValue()));
    }
}