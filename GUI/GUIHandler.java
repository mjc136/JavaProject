package GUI;

import javax.swing.*;

import java.awt.*;

public class GUIHandler extends JFrame{
    
    LoginGUI loginGUI;
    static JPanel panel;

    public GUIHandler(){

        loginGUI = new LoginGUI();
        panel = loginGUI;

        // setting frame

        setSize(500, 500);
        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Purchases");
        pack();
        setVisible(true);

    }

    public static GUIHandler handler;

    public static void replacePanel(Container container, JPanel currentPanel, JPanel newPanel) {
        container.remove(currentPanel); // remove the current panel from the container
        container.add(newPanel); // add the new panel to the container
        container.revalidate(); // tell the container to re-layout its components
        container.repaint(); // tell the container to repaint itself
        panel = newPanel;
    }
    public static void main(String[] args){

        handler = new GUIHandler();

    }

}
