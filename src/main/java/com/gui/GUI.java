package com.gui;

import com.steelseries.GameEvent;
import com.steelseries.RemoveGame;
import com.steelseries.RemoveGameEvent;
import com.utilities.HttpRequester;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;

public class GUI extends JFrame{

    private HttpRequester server;
    private String appName;
    private String eventName;

    /**
     * Create the application.
     *
     * @param appName name of the application
     * @param eventName name of the event
     */
    public GUI(HttpRequester server, String appName, String eventName) {
        this.server = server;
        this.appName = appName;
        this.eventName = eventName;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.getContentPane().setBackground(new Color(230, 230, 250));
        this.setBackground(Color.WHITE);
        this.setTitle("StudyPortals busy indicator");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/icons/steelseries.png")));
        this.setBounds(100, 100, 293, 107);
        this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Allocate an anonymous instance of an anonymous inner class
        // that extends WindowAdapter.
        // "super" Frame adds the instance as WindowEvent listener.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                RemoveGameEvent rmGE = new RemoveGameEvent(appName, eventName);
                try {
                    rmGE.send(server);
                }
                catch(Exception e){
                    showErrorDialog(e.getMessage());
                }
                RemoveGame rmG = new RemoveGame(appName);
                try {
                    rmG.send(server);
                }
                catch(Exception e){
                    showErrorDialog(e.getMessage());
                }
                System.exit(0);  // Terminate the program
            }
        });

        JToggleButton btnNewButton = new JToggleButton("");
        btnNewButton.setIcon(new ImageIcon(GUI.class.getResource("/icons/button_green.png")));
        btnNewButton.setBackground(Color.GREEN);
        btnNewButton.setSize(50, 50);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                disableOthers(GUI.this.getContentPane().getComponents(), (Component)arg0.getSource());
                GameEvent ge = new GameEvent(
                        appName,
                        eventName,
                        1
                );
                try {
                    ge.send(server);
                }
                catch(Exception e){
                    showErrorDialog(e.getMessage());
                }
            }
        });
        this.getContentPane().add(btnNewButton);

        JToggleButton btnNewButton_1 = new JToggleButton("");
        btnNewButton_1.setIcon(new ImageIcon(GUI.class.getResource("/icons/button_yellow.png")));
        btnNewButton_1.setBackground(Color.ORANGE);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                disableOthers(GUI.this.getContentPane().getComponents(), (Component)arg0.getSource());
                GameEvent ge = new GameEvent(
                        appName,
                        eventName,
                        2
                );
                try {
                    ge.send(server);
                }
                catch(Exception e){
                    showErrorDialog(e.getMessage());
                }
            }
        });
        this.getContentPane().add(btnNewButton_1);

        JToggleButton btnNewButton_2 = new JToggleButton("");
        btnNewButton_2.setIcon(new ImageIcon(GUI.class.getResource("/icons/button_red.png")));
        btnNewButton_2.setBackground(Color.RED);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                disableOthers(GUI.this.getContentPane().getComponents(), (Component)arg0.getSource());
                GameEvent ge = new GameEvent(
                        appName,
                        eventName,
                        3
                );
                try {
                    ge.send(server);
                }
                catch(Exception e){
                    showErrorDialog(e.getMessage());
                }
            }
        });
        this.getContentPane().add(btnNewButton_2);

    }

    private void disableOthers(Component[] components, Component currentCmp){
        for(int i=0; i<components.length; i++){
            if(components[i] != currentCmp){
                if(components[i] instanceof JToggleButton){
                    ((JToggleButton)components[i]).setSelected(false);
                }
            }
        }
    }

    private void showErrorDialog(String message){
        JOptionPane.showMessageDialog(this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

}
