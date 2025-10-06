package tokyslav.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

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

public class FolderGUI {

    private final int width = 800;
    private final int height = 800;
    private JFrame frame;
    private JScrollPane centerJPanel;
    private JScrollPane centerJScrollPanel;

    private JLabel actualPathJLabel;

    StartGUI myStartGUI = new StartGUI();

    public JPanel headJPanel(String tempString) {
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
        return headPanel;
    }

    public JScrollPane centerJScrollPane(String tempActualPath) {
        JPanel centerJPanel = new JPanel();
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
        deleteFrame();
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.02;
        gbc.anchor = GridBagConstraints.WEST;
        JPanel headPanel = headJPanel(newPath);
        frame.add(headPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.98;
        gbc.fill = GridBagConstraints.BOTH;

        centerJScrollPanel = centerJScrollPane(newPath);
        frame.add(centerJScrollPanel, gbc);

        frame.setVisible(true);
        // frame.getContentPane().remove(centerJScrollPanel);
        // frame.revalidate();
        // frame.repaint();
        // centerJPanel = centerJScrollPane(newPath);
        // frame.add(centerJPanel, BorderLayout.CENTER);
        // frame.repaint();
    }

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

    private void goBackButtonFunction(String tempActualPath) {

        if (filereader.backToHome(tempActualPath) == true) {
            deleteFrame();
            myStartGUI.startGUIJPanel(frame);
            frame.setVisible(true);
        } else {
            String newPath = filereader.getParent(tempActualPath);
            recreateCenterJPanel(newPath);
        }

    }

    public void getFrame(JFrame frame2) {
        frame = frame2;
    }

    private void deleteFrame() {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }
}
