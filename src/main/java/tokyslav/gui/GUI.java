package tokyslav.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tokyslav.FileTypes;
import tokyslav.Fileobject;
import tokyslav.filereader.filereader;

public class GUI {

    private final int width = 800;
    private final int height = 800;
    private JFrame frame;
    private JLabel actualPathJLabel;
    private JScrollPane centerJPanel;

    private String actualPath = "C:\\Users";
    private int heightofHeadPanel = 35;
    private int heightofSouthPanel = 50;

    public GUI() {
        // constructor of Gui
        frame = new JFrame("SizeSeeker");
        frame.setLayout(new BorderLayout());  

        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(headJPanel(), BorderLayout.NORTH);
        centerJPanel = centerJScrollPane();
        frame.add(centerJPanel, BorderLayout.CENTER);
        frame.add(southJPanel(), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel headJPanel() {
        JPanel headPanel = new JPanel();
        // headPanel.setSize(width, 200); //funktioniert irgendwie nicht, idk
        Dimension d = new Dimension(width, heightofHeadPanel);
        headPanel.setPreferredSize(d);
        headPanel.setBackground(Color.white);
        headPanel.setLayout(new BorderLayout());

        JButton goBackButton = new JButton();
        goBackButton.setText("Zurück");// Zurück
        goBackButton.addActionListener(e -> goBackButtonFunction());
        headPanel.add(goBackButton, BorderLayout.LINE_START);

        actualPathJLabel = new JLabel();
        setactualPathJLabelText(actualPath);
        headPanel.add(actualPathJLabel, BorderLayout.CENTER);

        return headPanel;
    }

    // opens parent directory and lists all of the files there
    private void goBackButtonFunction() {
        // actualPath = filereader.getParent(actualPath);  //aktuell kaputt, Phillip klären
        recreateCenterJPanel(actualPath);
    }
    
    private JScrollPane centerJScrollPane() {
        JPanel centerJPanel = new JPanel();
        // centerJPanel.setSize(frame.WIDTH, frame.HEIGHT - heightofSouthPanel - heightofHeadPanel);
        // centerJPanel.setPreferredSize(new Dimension(frame.WIDTH, (frame.HEIGHT - 100)));
        centerJPanel.setBackground(Color.CYAN);
        centerJPanel.setLayout(new BoxLayout(centerJPanel, BoxLayout.PAGE_AXIS));

        Fileobject[] fileobjectArray = filereader.getInfoFromPath(actualPath);
        // int[] percentageOfSizeIntArray = GUILogic.calculatePercentage(fileobjectArray);
        int[] percentageOfSizeIntArray = {0,20,30,50,100,35,75,95};
        
        for (int i = 0; i < fileobjectArray.length; i++) {
            centerJPanel.add(createFileObjectPanel(fileobjectArray[i],percentageOfSizeIntArray[i]));
        }
        JScrollPane scrollpanel = new JScrollPane(centerJPanel);
        return scrollpanel;
    }

    private JPanel createFileObjectPanel(Fileobject tempFileobject, int percentageOfSize) {
        JPanel fileObjectPanel = new JPanel();
        fileObjectPanel.setLayout(new BorderLayout());
        fileObjectPanel.setSize(frame.WIDTH, 50);
        fileObjectPanel.setPreferredSize(new Dimension(frame.WIDTH, 50));
        
        JButton buttonToPress = new JButton();
        buttonToPress.setOpaque(false);
        buttonToPress.setContentAreaFilled(false);
        buttonToPress.setBorderPainted(false);
        buttonToPress.setLayout(new BorderLayout());

        JButton iconButton = createIcon(tempFileobject); 
        buttonToPress.add(iconButton, BorderLayout.LINE_START);

        JLabel fileNameLabel = customJLabel(tempFileobject.getFileName(),percentageOfSize);
        fileNameLabel.setOpaque(false);
        buttonToPress.add(fileNameLabel, BorderLayout.CENTER);

        JLabel fileSizeLabel = new JLabel(tempFileobject.getSize());
        buttonToPress.add(fileSizeLabel, BorderLayout.LINE_END);

        buttonToPress.addActionListener(e -> recreateCenterJPanel(tempFileobject.getFileName()));
        buttonToPress.setSize(fileObjectPanel.WIDTH, fileObjectPanel.HEIGHT);
        fileObjectPanel.add(buttonToPress);
        return fileObjectPanel;
    }
    private String getImagePath(FileTypes type){
        String imgPath;
        switch(type){
            case DIRECTORY:
                imgPath = "src\\main\\java\\tokyslav\\gui\\Drive_Icon_New.png";
                break;
            case FILE:
                imgPath = "src\\main\\java\\tokyslav\\gui\\File_Icon_New.png";
                break;
            case DRIVE:
                imgPath = "src\\main\\java\\tokyslav\\gui\\Drive_Icon.png";
                break;
            default:
                imgPath = "src\\main\\java\\tokyslav\\gui\\Other_Icon.png";
                break;
            }
        return imgPath;
    }

    private JButton createIcon(Fileobject fileobjectForIcon){
        ImageIcon icon = new ImageIcon(getImagePath(fileobjectForIcon.getFileType()));
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        JButton cornerButton = new JButton(icon);
        cornerButton.setSize(20, 20);
        cornerButton.setBorder(BorderFactory.createEmptyBorder());
        cornerButton.setContentAreaFilled(false);
        return cornerButton;
    }
    private void recreateCenterJPanel(String newPath){
        actualPath = newPath;
        setactualPathJLabelText(newPath);
        frame.remove(centerJPanel);
        centerJPanel = centerJScrollPane();
        frame.add(centerJPanel, BorderLayout.CENTER);
    }

    private JPanel southJPanel() {
        JPanel southPanel = new JPanel();
        // southPanel.setSize(width, 100); //funktioniert irgendwie nicht, idk
        Dimension d = new Dimension(width, heightofSouthPanel);
        southPanel.setPreferredSize(d);
        southPanel.setBackground(Color.magenta);
        return southPanel;
    }

    //every change of location can be set here
    private void setactualPathJLabelText(String textToSet){
        String textToDisplay = "Du befindest dich hier: " + textToSet;
        actualPathJLabel.setText(textToDisplay);
    }

    private JLabel customJLabel(String textToDisplay, int percentageToFill){
        JLabel jLabelWithCustomRect = new JLabel(textToDisplay) {
            @Override
            protected void paintComponent(Graphics g) {
                float a = percentageToFill;
                float b = a/100;
                float c = b * getWidth();
                int fillWidth = (int) c;
                // Farbe für den Hintergrund festlegen (z.B. blau)
                g.setColor(Color.cyan);
                // Rechteck füllen (von links beginnend)
                g.fillRect(0, 0, fillWidth, height);
        
                // Den Standard-Look (Text etc.) rendern
                super.paintComponent(g);
            }
        };
        return jLabelWithCustomRect; 
    }
}
