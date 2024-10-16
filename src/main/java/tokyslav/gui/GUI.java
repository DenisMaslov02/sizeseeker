package tokyslav.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

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

    private String actualAbsolutPath = " ";

    /**
     * @see 
     */
    public GUI(){
        //constructor of Gui
        frame = new JFrame("SizeSeeker");
        frame.setLayout(new BorderLayout());
        
        frame.setSize(width,height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createPanels();        
        frame.setVisible(true);
    }

    private void createPanels(){
        headJPanel();
        centerJPanel();
        southJPanel();
    }

    private void headJPanel(){
        JPanel headPanel = new JPanel();
        headPanel.setSize(width, 200);
        headPanel.setPreferredSize(new Dimension(width,30));
        headPanel.setBackground(Color.white);
        headPanel.setLayout(new BorderLayout());
        
        JButton goBackButton = new JButton();
        goBackButton.setText("Zurück"); //Zurück
        goBackButton.addActionListener(e -> goBackButtonFunction());
        headPanel.add(goBackButton,BorderLayout.LINE_START);
        
        actualPathJLabel = new JLabel();
        setactualPathOfWindow("1");
        headPanel.add(actualPathJLabel,BorderLayout.CENTER);
        
        frame.add(headPanel,BorderLayout.NORTH);
    }
    
    private void goBackButtonFunction(){
        actualAbsolutPath = "2";
        // actualAbsolutPath = filereader.getParent(actualAbsolutPath);
        // System.out.println(actualAbsolutPath);
        setactualPathOfWindow(actualAbsolutPath);
        frame.remove(centerPanel);
        centerJPanel();
    }
    private void setactualPathOfWindow(String p_path){
        actualAbsolutPath = p_path;
        actualPathJLabel.setText("Du befindest dich hier: " + p_path);
    }

    private void centerJPanel(){
        centerPanel = new JPanel();
        centerPanel.setSize(frame.WIDTH, frame.HEIGHT - 100);
        // centerPanel.setPreferredSize(new Dimension(frame.WIDTH, (frame.HEIGHT - 100)));
        centerPanel.setBackground(Color.CYAN);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        
        for (Fileobject i : filereader.getInfoFromPath(actualAbsolutPath)) {
            centerPanel.add(createFileObjectPanel(i));
        }
        frame.add(centerPanel,BorderLayout.CENTER);
    }
    private JPanel createFileObjectPanel(Fileobject tempFileobject){
        JPanel fileObjectPanel = new JPanel();
        fileObjectPanel.setLayout(new BorderLayout());
        fileObjectPanel.setSize(frame.WIDTH,50);
        
        //fileObjectPanel.setPreferredSize(new Dimension(frame.WIDTH, 50));
        
        ImageIcon icon = new ImageIcon(getImagePath(tempFileobject.getFileType()));  
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        JButton cornerButton = new JButton(icon);
        cornerButton.setSize(20,20);
        cornerButton.setBorder(BorderFactory.createEmptyBorder());
        cornerButton.setContentAreaFilled(false);
        // cornerButton.addActionListener(e -> goBackButtonFunction());
        fileObjectPanel.add(cornerButton,BorderLayout.LINE_START);
        
        JLabel fileNameLabel = new JLabel(tempFileobject.getFileName());
        fileObjectPanel.add(fileNameLabel,BorderLayout.CENTER);

        JLabel fileSizeLabel = new JLabel(tempFileobject.getSize());
        fileObjectPanel.add(fileSizeLabel,BorderLayout.LINE_END);

        return fileObjectPanel;
    }
    private String getImagePath(FileTypes type){
        String imgPath;
        switch(type){
            case DIRECTORY:
                imgPath = "src\\main\\java\\tokyslav\\gui\\Dir_Icon.png";
                break;
            case FILE:
                imgPath = "src\\main\\java\\tokyslav\\gui\\File_Icon.png";
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

    
    private void southJPanel(){
        JPanel southPanel = new JPanel();
        southPanel.setSize(width, 100);
        southPanel.setBackground(Color.GREEN);
        frame.add(southPanel,BorderLayout.SOUTH);
    }

}
