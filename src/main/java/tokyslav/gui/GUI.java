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

import tokyslav.Fileobject;
import tokyslav.filereader.filereader;

public class GUI {

    private final int width = 800;
    private final int height = 600;
    private JFrame frame;

    private String actualPath = " ";

    public GUI() {
        // constructor of Gui
        frame = new JFrame("SizeSeeker");
        frame.setLayout(new BorderLayout());

        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(headJPanel(), BorderLayout.NORTH);
        frame.add(centerJPanel(), BorderLayout.CENTER);
        frame.add(southJPanel(), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel headJPanel() {
        JPanel headPanel = new JPanel();
        headPanel.setSize(width, 200);
        Dimension d = new Dimension(width, 30);
        headPanel.setPreferredSize(d);
        headPanel.setBackground(Color.white);
        headPanel.setLayout(new BorderLayout());

        JButton goBackButton = new JButton();
        goBackButton.setText("DU HURENSOHN");// Zurück
        goBackButton.addActionListener(e -> goBackButtonFunction());
        headPanel.add(goBackButton, BorderLayout.LINE_START);

        JLabel actualPathLabel = new JLabel();
        String textToDisplay = "Du befindest dich hier:" + actualPath;
        actualPathLabel.setText(textToDisplay);
        headPanel.add(actualPathLabel, BorderLayout.CENTER);

        return headPanel;
    }

    private void goBackButtonFunction() {
        // System.out.println("Hello u Son of bitch");
        // opens parent directory and lists all of the files there
        System.out.println(frame.WIDTH);
    }

    private JPanel centerJPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setSize(frame.WIDTH, frame.HEIGHT - 100);
        centerPanel.setBackground(Color.CYAN);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));

        // for (Fileobject i : filereader.getInfoFromPath("hehe")) {
        // centerPanel.add(createFileObjectPanel(i));
        // }
        return centerPanel;
    }

    private JPanel createFileObjectPanel(Fileobject tempFileobject) {
        JPanel fileObjectPanel = new JPanel();
        fileObjectPanel.setLayout(new BorderLayout());
        fileObjectPanel.setSize(frame.WIDTH, 50);

        // fileObjectPanel.setPreferredSize(new Dimension(frame.WIDTH, 50));

        ImageIcon icon = new ImageIcon("C:\\Users\\denis\\Desktop\\Dir_Icon.png");
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        JButton cornerButton = new JButton(icon);
        cornerButton.setSize(20, 20);
        cornerButton.setBorder(BorderFactory.createEmptyBorder());
        cornerButton.setContentAreaFilled(false);
        fileObjectPanel.add(cornerButton, BorderLayout.LINE_START);

        JLabel fileNameLabel = new JLabel(tempFileobject.getFileName());
        fileObjectPanel.add(fileNameLabel, BorderLayout.CENTER);

        JLabel fileSizeLabel = new JLabel(tempFileobject.getSize());
        fileObjectPanel.add(fileSizeLabel, BorderLayout.LINE_END);

        return fileObjectPanel;
    }

    private JPanel southJPanel() {
        JPanel southPanel = new JPanel();
        southPanel.setSize(width, 100);
        southPanel.setBackground(Color.GREEN);
        return southPanel;
    }

}
