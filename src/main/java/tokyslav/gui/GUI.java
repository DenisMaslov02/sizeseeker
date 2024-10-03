package tokyslav.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tokyslav.filereader.filereader;

public class GUI {

    private filereader myFilereader;    
    private final int width = 800;
    private final int height = 600;


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
        headPanel.setBackground(Color.BLUE);
        return headPanel;
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
