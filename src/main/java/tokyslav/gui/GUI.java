package tokyslav.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;

import javax.imageio.plugins.jpeg.JPEGQTable;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
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
    private JPanel startCenterJPanel;
    private JPanel startHeadJPanel;
    private JPanel headPanel;
    private JScrollPane centerJScroPanel;

    private int heightofHeadPanel = 35;
    private int heightofSouthPanel = 50;

    public GUI() {
        // constructor of Gui
        frame = new JFrame("SizeSeeker");
        frame.setLayout(new BorderLayout());

        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startGUIJPanel();

        frame.setVisible(true);
    }

    private JPanel startGUIJPanel() {

        JPanel startJPanel = new JPanel();
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.02;

        startHeadJPanel = startHeadPanel();
        frame.add(startHeadJPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.98;
        gbc.fill = GridBagConstraints.BOTH;
        startCenterJPanel = startCenterJPanel();
        frame.add(startCenterJPanel, gbc);
        return startJPanel;
    }

    private JPanel startHeadPanel() {

        JPanel startHeadPanel = new JPanel();

        Dimension d = new Dimension(width, heightofHeadPanel);
        startHeadPanel.setPreferredSize(d);
        startHeadPanel.setBackground(Color.white);
        startHeadPanel.setLayout(new BorderLayout());

        JButton settingButton = new JButton();

        settingButton.setText("Settings");
        settingButton.addActionListener(e -> settingJPanel());

        startHeadPanel.add(settingButton);

        return startHeadPanel;
    }

    private JPanel startCenterJPanel() {

        JPanel startCenterJPanel = new JPanel(new GridLayout(2, 1));

        File[] fileRoots = filereader.getRoots();

        JPanel startDiagrammJPanel = new JPanel(new GridLayout(1, 2));
        JPanel startRootsJPanel = new JPanel(new GridLayout(fileRoots.length, 1));

        for (int i = 0; i < fileRoots.length; i++) {
            startRootsJPanel.add(createRootsJPanel(fileRoots[i]));

        }

        startCenterJPanel.add(startDiagrammJPanel);
        startCenterJPanel.add(startRootsJPanel);

        return startCenterJPanel;
    }

    private JPanel createRootsJPanel(File tempNameFile) {
        JPanel rootsObjectPanel = new JPanel();

        // Visibel Object, after test delet
        // rootsObjectPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        // ...

        rootsObjectPanel.setBackground(Color.white);
        rootsObjectPanel.setLayout(new BorderLayout());

        JButton RenamButton = new JButton();

        RenamButton.setOpaque(false);
        RenamButton.setContentAreaFilled(false);
        RenamButton.setBorderPainted(false);
        RenamButton.setLayout(new BorderLayout());
        RenamButton.addActionListener(e -> getInDriveCenterPanel(tempNameFile.toString()));

        JPanel RenameJPanel = new JPanel();
        RenameJPanel.setLayout(new BorderLayout());

        RenameJPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        ImageIcon driveIcon = new ImageIcon(getImagePath(FileTypes.DRIVE));
        Image scaledImg = driveIcon.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
        driveIcon = new ImageIcon(scaledImg);

        RenameJPanel.setBackground(Color.white);
        RenameJPanel.add(new JLabel(tempNameFile.toString(), driveIcon, JLabel.LEFT));

        RenamButton.add(RenameJPanel);

        rootsObjectPanel.add(RenamButton);

        return rootsObjectPanel;
    }

    private void getInDriveCenterPanel(String tempDrivePath) {
        frame.remove(startHeadJPanel);
        frame.remove(startCenterJPanel);
        frame.repaint();

        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.02;
        gbc.anchor = GridBagConstraints.WEST;

        headPanel = headJPanel(tempDrivePath);
        frame.add(headPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.98;
        gbc.fill = GridBagConstraints.BOTH;

        centerJScroPanel = centerJScrollPane(tempDrivePath);
        frame.add(centerJScroPanel, gbc);

        frame.setVisible(true);
    }

    private void settingJPanel() {
        frame.remove(startHeadJPanel);
        frame.remove(startCenterJPanel);
        frame.repaint();
    }

    // TODO HEADPANEL
    private JPanel headJPanel(String tempString) {
        JPanel headPanel = new JPanel();
        headPanel.setSize(width, 200); // funktioniert irgendwie nicht, idk
        // Dimension d = new Dimension(width, 35);
        // headPanel.setPreferredSize(d);
        // headPanel.setBackground(Color.white);
        // headPanel.setLayout(new BorderLayout());

        JButton goBackButton = new JButton();
        goBackButton.setText("Zurück");// Zurück
        goBackButton.addActionListener(e -> goBackButtonFunction(tempString));
        headPanel.add(goBackButton, BorderLayout.LINE_START);

        actualPathJLabel = new JLabel();
        actualPathJLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        setactualPathJLabelText(tempString);
        headPanel.add(actualPathJLabel, BorderLayout.CENTER);
        testPrint();
        return headPanel;
    }

    // opens parent directory and lists all of the files there
    private void goBackButtonFunction(String tempActualPath) {

        if (filereader.backToHome(tempActualPath) == true) {
            frame.remove(headPanel);
            frame.remove(centerJScroPanel);
            frame.repaint();
            startGUIJPanel();
            frame.setVisible(true);

            return;
        }
        // else {
        // actualPath = filereader.getParent(tempActualPath);
        // recreateCenterJPanel(actualPath);
        // }
    }
    // TODO Start of ButtonThread

    private JScrollPane centerJScrollPane(String tempActualPath) {
        JPanel centerJPanel = new JPanel();
        // centerJPanel.setSize(frame.WIDTH, frame.HEIGHT - heightofSouthPanel -
        // heightofHeadPanel);
        // centerJPanel.setPreferredSize(new Dimension(frame.WIDTH, (frame.HEIGHT -
        // 100)));
        centerJPanel.setBackground(Color.CYAN);
        centerJPanel.setLayout(new BoxLayout(centerJPanel, BoxLayout.PAGE_AXIS));
        Fileobject[] fileobjectArray = filereader.getInfoFromPath(tempActualPath);
        int[] percentageOfSizeIntArray = GUILogic.calculatePercentage(fileobjectArray);

        for (int i = 0; i < fileobjectArray.length; i++) {
            centerJPanel.add(createFileObjectPanel(fileobjectArray[i], percentageOfSizeIntArray[i]));
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

        // ICON
        JButton iconButton = createIcon(tempFileobject);
        iconButton.addActionListener(e -> openFileExplorer(tempFileobject.getFileName()));
        buttonToPress.add(iconButton, BorderLayout.LINE_START);
        // NAME
        JLabel fileNameLabel = customJLabel(tempFileobject.getFileName(), percentageOfSize);
        fileNameLabel.setOpaque(false);
        buttonToPress.add(fileNameLabel, BorderLayout.CENTER);
        // GET SIZE
        long fileSize = tempFileobject.getSize();
        String fileSizeToDisplay = GUILogic.calculateSizeDisplayNumber(fileSize);
        JLabel fileSizeLabel = new JLabel(fileSizeToDisplay);
        buttonToPress.add(fileSizeLabel, BorderLayout.LINE_END);

        buttonToPress.addActionListener(e -> recreateCenterJPanel(tempFileobject.getFileName()));
        buttonToPress.setSize(fileObjectPanel.WIDTH, fileObjectPanel.HEIGHT);
        fileObjectPanel.add(buttonToPress);
        return fileObjectPanel;
    }

    private String getImagePath(FileTypes type) {
        String imgPath;
        switch (type) {
            case DIRECTORY:
                imgPath = "src\\main\\java\\tokyslav\\gui\\Drive_Icon_New.png";
                break;
            case FILE:
                imgPath = "src\\main\\java\\tokyslav\\gui\\File_Icon_New.png";
                break;
            case DRIVE:
                imgPath = "src\\main\\java\\tokyslav\\gui\\Drive_Start_Icon.png";
                break;
            default:
                imgPath = "src\\main\\java\\tokyslav\\gui\\Other_Icon.png";
                break;
        }
        return imgPath;
    }

    private JButton createIcon(Fileobject fileobjectForIcon) {
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

    private void recreateCenterJPanel(String newPath) {
        setactualPathJLabelText(newPath);
        frame.remove(centerJPanel);
        centerJPanel = centerJScrollPane(newPath);
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

    // every change of location can be set here
    private void setactualPathJLabelText(String textToSet) {
        String textToDisplay = "Du befindest dich hier: " + textToSet;
        actualPathJLabel.setText(textToDisplay);
    }

    private JLabel customJLabel(String textToDisplay, int percentageToFill) {
        JLabel jLabelWithCustomRect = new JLabel(textToDisplay) {
            @Override
            protected void paintComponent(Graphics g) {
                float a = percentageToFill;
                float b = a / 100;
                float c = b * getWidth();
                int fillWidth = (int) c;
                // Farbe für den Hintergrund festlegen (z.B. blau)
                g.setColor(GUILogic.evaluateColor(percentageToFill));
                // Rechteck füllen (von links beginnend)
                g.fillRect(getWidth() - fillWidth, 0, fillWidth, height);

                // Den Standard-Look (Text etc.) rendern
                super.paintComponent(g);
            }
        };
        return jLabelWithCustomRect;
    }

    private void openFileExplorer(String pathToOpen) {
        File file = new File(pathToOpen);
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        } catch (IOException ex) {
        }
    }

    private void testfunction() {

    }

    private void testPrint() {
        System.out.println("Test");
    }
}
