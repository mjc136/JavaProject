package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class test extends JFrame implements ActionListener {
    
    private JPanel cards;
    private JButton button1, button2;
    private JLabel label1, label2;
    
    public test() {
        super("My GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the first page
        JPanel page1 = new JPanel();
        label1 = new JLabel("This is page 1");
        button1 = new JButton("Go to page 2");
        button1.addActionListener(this);
        page1.add(label1);
        page1.add(button1);
        
        // Create the second page
        JPanel page2 = new JPanel();
        label2 = new JLabel("This is page 2");
        button2 = new JButton("Go to page 1");
        button2.addActionListener(this);
        page2.add(label2);
        page2.add(button2);
        
        // Create the card layout and add the pages to it
        cards = new JPanel(new CardLayout());
        cards.add(page1, "Page 1");
        cards.add(page2, "Page 2");
        
        // Add the cards to the frame
        getContentPane().add(cards);
        
        // Display the frame
        pack();
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        if (e.getSource() == button1) {
            cl.show(cards, "Page 2");
        } else if (e.getSource() == button2) {
            cl.show(cards, "Page 1");
        }
    }
    
    public static void main(String[] args) {
        new test();
    }
}
