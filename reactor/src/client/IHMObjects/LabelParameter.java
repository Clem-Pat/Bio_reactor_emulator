package client.IHMObjects;

import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant un paramètre affiché sous forme de label.
 */
public class LabelParameter {
    public JLabel label1;
    public JLabel label2;
    public JLabel label3;

    /**
     * Constructeur de la classe LabelParameter.
     *
     * @param panel Panel auquel ajouter le paramètre.
     * @param title Titre du paramètre.
     * @param value Valeur du paramètre.
     * @param unit Unité du paramètre.
     */
    public LabelParameter(JPanel panel, String title, String value, String unit) {
        super();
        label1 = new JLabel(title);
        panel.add(label1, BorderLayout.NORTH);
        label1.setFont(new java.awt.Font("Tahoma", 1, 14));

        label2 = new JLabel(value);
        panel.add(label2, BorderLayout.NORTH);
        label2.setFont(new java.awt.Font("Tahoma", 1, 14));

        label3 = new JLabel(unit);
        panel.add(label3, BorderLayout.NORTH);
        label3.setFont(new java.awt.Font("Tahoma", 1, 14));
    }

    /**
     * Modifie le titre du label.
     *
     * @param text Nouveau texte pour le titre.
     */
    public void setTitle(String text){
        label1.setText(text);
    }

    /**
     * Modifie la valeur affichée dans le label.
     *
     * @param text Nouveau texte pour la valeur.
     */
    public void setValue(String text){
        label2.setText(text);
    }

    /**
     * Modifie l'unité affichée dans le label.
     *
     * @param text Nouveau texte pour l'unité.
     */
    public  void setUnit(String text){
        label3.setText(text);
    }
}