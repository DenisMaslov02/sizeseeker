package tokyslav.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tokyslav.filereader.filereader;

public class GUI {
    
    private filereader myFilereader;    
    private final int width = 800;
    private final int height = 600;

    private String actualPath = " ";


    public GUI(filereader p_filrereader){
        //constructor of Gui
        myFilereader = p_filrereader;

        JFrame frame = new JFrame("SizeSeeker");
        frame.setLayout(new BorderLayout());
        
        frame.setSize(width,height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(headJPanel(),BorderLayout.NORTH);
        frame.add(centerJPanel(),BorderLayout.CENTER);
        frame.add(southJPanel(),BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }

    private JPanel headJPanel(){
        JPanel headPanel = new JPanel();
        headPanel.setSize(width, 200);
        Dimension d = new Dimension(width,30);
        headPanel.setPreferredSize(d);
        headPanel.setBackground(Color.white);
        headPanel.setLayout(new BorderLayout());
    
        JButton goBackButton = new JButton();
        goBackButton.setText("DU HURENSOHN");//Zurück
        goBackButton.addActionListener(e -> goBackButtonFunction());
        headPanel.add(goBackButton,BorderLayout.LINE_START);
        
        JLabel actualPathLabel = new JLabel();
        String textToDisplay = "Du befindest dich hier:" + actualPath;
        actualPathLabel.setText(textToDisplay);
        headPanel.add(actualPathLabel,BorderLayout.CENTER);
        
        return headPanel;
    }
    
    private void goBackButtonFunction(){
        //System.out.println("Hello u Son of bitch");
        // opens parent directory and lists all of the files there
    }

    private JPanel centerJPanel(){
        JPanel centerPanel = new JPanel();
        centerPanel.setSize(width, 400);
        centerPanel.setBackground(Color.CYAN);
        return centerPanel;
    }
    
    private JPanel southJPanel(){
        JPanel southPanel = new JPanel();
        southPanel.setSize(width, 100);
        southPanel.setBackground(Color.GREEN);
        return southPanel;
    }

}
