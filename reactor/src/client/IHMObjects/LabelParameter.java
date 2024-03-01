package client.IHMObjects;

import javax.swing.*;
import java.awt.*;

public class LabelParameter {
    public JLabel label1;
    public JLabel label2;

    public LabelParameter(JPanel panel, String title, String text) {
        super();
        label1 = new JLabel(title);
        panel.add(label1, BorderLayout.NORTH);
        label1.setFont(new java.awt.Font("Tahoma", 1, 14));

        label2 = new JLabel(text);
        panel.add(label2, BorderLayout.NORTH);
        label2.setFont(new java.awt.Font("Tahoma", 1, 14));
    }

    public void changeText(String text){
        label2.setText(text);
    }
    public void changeTitle(String text){
        label1.setText(text);
    }
}
