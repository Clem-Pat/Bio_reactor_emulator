package client.IHMObjects;

import javax.swing.*;
import java.awt.*;

public class LabelParameter {
    public JLabel label1;
    public JLabel label2;
    public JLabel label3;

    public LabelParameter(JPanel panel, String title, String value, String unit) {
        super();
//        Title
        label1 = new JLabel(title);
        panel.add(label1, BorderLayout.NORTH);
        label1.setFont(new java.awt.Font("Tahoma", 1, 14));
//        Value
        label2 = new JLabel(value);
        panel.add(label2, BorderLayout.NORTH);
        label2.setFont(new java.awt.Font("Tahoma", 1, 14));
//        Unit
        label3 = new JLabel(unit);
        panel.add(label3, BorderLayout.NORTH);
        label3.setFont(new java.awt.Font("Tahoma", 1, 14));
    }
    public void setTitle(String text){
        label1.setText(text);
    }
    public void setValue(String text){
        label2.setText(text);
    }
    public  void setUnit(String text){
        label3.setText(text);
    }
}
