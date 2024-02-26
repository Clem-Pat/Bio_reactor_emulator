package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.EventHandler;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import static javax.swing.JComponent.WHEN_FOCUSED;

@SuppressWarnings("serial")
public class ClientGUI extends JFrame implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        if (evt.getSource() instanceof Automate) {
//            if (evt.getPropertyName().equals("sommePoche")) {
//                jTextFieldSommenpoche.setText(Integer.toString(((Automate) evt.getSource()).getSommePoche()));
//            }
//        }
    }
}
