package GUI;

import javax.swing.*;

import java.awt.*;

public class GUIHandler extends JFrame{
    
    static JPanel panel;
    public static GUIHandler handler;
    public static int user;

    public GUIHandler(){

        panel = new LoginGUI();

        // setting frame
        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Purchases");
        pack();
        setVisible(true);

    }


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
