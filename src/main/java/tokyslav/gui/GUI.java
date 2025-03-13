package tokyslav.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tokyslav.FileTypes;
import tokyslav.Fileobject;
import tokyslav.filereader.filereader;

public class GUI {

    private final int width = 800;
    private final int height = 600;
    private JFrame frame;
    private JLabel actualPathJLabel;
    private JPanel centerPanel;

    private String actualPath = "C:\\Users\\";
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
        centerPanel = centerJPanel();
        frame.add(centerPanel, BorderLayout.CENTER);
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
        // actualPath = " hihi ich ändere den Text hier";
        
        // Fileobject x = new Fileobject("Hallo", "700bytes", FileTypes.FILE);
        // System.out.println(Integer.parseInt(x.getSize()));
        
        
        //recreate CenterPanel
        setactualPathJLabelText(actualPath);
        frame.remove(centerPanel);
        centerPanel = centerJPanel();
        frame.add(centerPanel, BorderLayout.CENTER);
        
        // System.out.println(frame.getHeight());
    }
    
    private JPanel centerJPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setSize(frame.WIDTH, frame.HEIGHT - 100);
        // centerPanel.setPreferredSize(new Dimension(frame.WIDTH, (frame.HEIGHT - 100)));
        centerPanel.setBackground(Color.CYAN);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        

        List<Fileobject> fileobjectsLists = Arrays.asList(filereader.getInfoFromPath(actualPath));
        // Fileobject[] sortedFileobjectArray = GUILogic.sortItems(fileobjectsLists);
        // filereader.getInfoFromPath(actualPath);
        
        for (Fileobject i : filereader.getInfoFromPath(actualPath)) {
            centerPanel.add(createFileObjectPanel(i));
        }
        System.out.println("ich wurde gerufen: CENTER");
        return centerPanel;
    }

    private JPanel createFileObjectPanel(Fileobject tempFileobject) {
        JPanel fileObjectPanel = new JPanel();
        fileObjectPanel.setLayout(new BorderLayout());
        fileObjectPanel.setSize(frame.WIDTH, 50);
        fileObjectPanel.setPreferredSize(new Dimension(frame.WIDTH, 50));
        
        JButton buttonToPress = new JButton();
        buttonToPress.setOpaque(false);
        buttonToPress.setContentAreaFilled(false);
        buttonToPress.setBorderPainted(false);
        buttonToPress.setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon(getImagePath(tempFileobject.getFileType()));
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        JButton cornerButton = new JButton(icon);
        cornerButton.setSize(20, 20);
        cornerButton.setBorder(BorderFactory.createEmptyBorder());
        cornerButton.setContentAreaFilled(false);
        
        buttonToPress.add(cornerButton, BorderLayout.LINE_START);

        JLabel fileNameLabel = new JLabel(tempFileobject.getFileName());
        buttonToPress.add(fileNameLabel, BorderLayout.CENTER);

        JLabel fileSizeLabel = new JLabel(tempFileobject.getSize());
        buttonToPress.add(fileSizeLabel, BorderLayout.LINE_END);

        buttonToPress.addActionListener(e -> buttonFunctionFileObject(tempFileobject.getFileName()));
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
    private void buttonFunctionFileObject(String newPath){
        
        System.out.println(newPath);
        actualPath = newPath;
        setactualPathJLabelText(newPath);
        frame.remove(centerPanel);
        centerPanel = centerJPanel();
        frame.add(centerPanel, BorderLayout.CENTER);
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

}
