package tokyslav.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tokyslav.Fileobject;
import tokyslav.filereader.filereader;

public class FolderGUI {

    StartGUI myStartGUI = new StartGUI();

    public JPanel createJPanelInToDrive(String tempDrivePath) {

        JPanel createFolderGUISetDriveJPanel = new JPanel();

        createFolderGUISetDriveJPanel.setLayout(new GridBagLayout());
        GridBagConstraints layoutGBCFolderGUI = new GridBagConstraints();

        layoutGBCFolderGUI.fill = GridBagConstraints.NONE;

        layoutGBCFolderGUI.gridx = 0;
        layoutGBCFolderGUI.gridy = 0;
        layoutGBCFolderGUI.weightx = 1.0;
        layoutGBCFolderGUI.weighty = 0.02;
        layoutGBCFolderGUI.anchor = GridBagConstraints.WEST;
        JPanel createFolderGUIHeadJPaneltemp = headFolderGUIJPanel(tempDrivePath);
        createFolderGUISetDriveJPanel.add(createFolderGUIHeadJPaneltemp, layoutGBCFolderGUI);

        layoutGBCFolderGUI.gridx = 0;
        layoutGBCFolderGUI.gridy = 1;
        layoutGBCFolderGUI.weightx = 1.0;
        layoutGBCFolderGUI.weighty = 0.98;
        layoutGBCFolderGUI.fill = GridBagConstraints.BOTH;

        JScrollPane createFolderGUIJScrollPanetemp = centerJScrollPanel(tempDrivePath);
        createFolderGUISetDriveJPanel.add(createFolderGUIJScrollPanetemp, layoutGBCFolderGUI);

        return createFolderGUISetDriveJPanel;
    }

    public JPanel headFolderGUIJPanel(String tempString) {
        JPanel createFolderGUIHeadJPanel = new JPanel();
        createFolderGUIHeadJPanel.setLayout(new BorderLayout());

        JButton goBackButton = new JButton();
        goBackButton.setText("Zurück");
        goBackButton.addActionListener(e -> goBackButtonFunction(tempString));
        createFolderGUIHeadJPanel.add(goBackButton, BorderLayout.LINE_START);

        JLabel createFolderGUIActualPathJLabel = new JLabel();
        createFolderGUIActualPathJLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        setActualPathJLabelText(tempString, createFolderGUIActualPathJLabel);
        createFolderGUIHeadJPanel.add(createFolderGUIActualPathJLabel, BorderLayout.CENTER);
        return createFolderGUIHeadJPanel;
    }

    public JScrollPane centerJScrollPanel(String tempActualPath) {
        System.out.println("Drive path: " + tempActualPath);
        JPanel createFolderGUIcenterJPanel = new JPanel();
        createFolderGUIcenterJPanel.setBackground(Color.CYAN);
        createFolderGUIcenterJPanel.setLayout(new BoxLayout(createFolderGUIcenterJPanel, BoxLayout.PAGE_AXIS));
        Fileobject[] fileobjectArray = filereader.getInfoFromPath(tempActualPath);
        int[] percentageOfSizeIntArray = GUILogic.calculatePercentage(fileobjectArray);

        for (int i = 0; i < fileobjectArray.length; i++) {
            createFolderGUIcenterJPanel.add(createFileObjectPanel(fileobjectArray[i], percentageOfSizeIntArray[i]));
        }
        JScrollPane createFolderGUIScrollPanel = new JScrollPane(createFolderGUIcenterJPanel);
        return createFolderGUIScrollPanel;
    }

    private JPanel createFileObjectPanel(Fileobject tempFileobject, int percentageOfSize) {
        JPanel fileObjectJPanel = new JPanel();
        fileObjectJPanel.setLayout(new BorderLayout());
        fileObjectJPanel.setSize(800, 50);
        fileObjectJPanel.setPreferredSize(new Dimension(800, 50));

        JButton createFolderGUIButtonToPress = new JButton();
        createFolderGUIButtonToPress.setOpaque(false);
        createFolderGUIButtonToPress.setContentAreaFilled(false);
        createFolderGUIButtonToPress.setBorderPainted(false);
        createFolderGUIButtonToPress.setLayout(new BorderLayout());

        // ICON
        JButton createFolderGUIIconButton = createIcon(tempFileobject);
        createFolderGUIIconButton.addActionListener(e -> openFileExplorer(tempFileobject.getFileName()));
        createFolderGUIButtonToPress.add(createFolderGUIIconButton, BorderLayout.LINE_START);
        // NAME
        JLabel fileNameLabel = customJLabel(tempFileobject.getFileName(), percentageOfSize);
        fileNameLabel.setOpaque(false);
        createFolderGUIButtonToPress.add(fileNameLabel, BorderLayout.CENTER);
        // GET SIZE
        long fileSize = tempFileobject.getSize();
        String fileSizeToDisplay = GUILogic.calculateSizeDisplayNumber(fileSize);
        JLabel fileSizeLabel = new JLabel(fileSizeToDisplay);
        createFolderGUIButtonToPress.add(fileSizeLabel, BorderLayout.LINE_END);

        createFolderGUIButtonToPress.addActionListener(e -> recreateCenterJPanel(tempFileobject.getFileName()));
        createFolderGUIButtonToPress.setSize(fileObjectJPanel.WIDTH, fileObjectJPanel.HEIGHT);
        fileObjectJPanel.add(createFolderGUIButtonToPress);
        return fileObjectJPanel;
    }

    private void recreateCenterJPanel(String tempString) {

        FunctionGUI.removeContainerPanel();

        JPanel createFunctionGUIrecreateCenterJPanel = new JPanel();
        createFunctionGUIrecreateCenterJPanel.setLayout(new GridBagLayout());

        GridBagConstraints layoutGBCFolderGUICenterJPanel = new GridBagConstraints();

        layoutGBCFolderGUICenterJPanel.fill = GridBagConstraints.NONE;

        layoutGBCFolderGUICenterJPanel.gridx = 0;
        layoutGBCFolderGUICenterJPanel.gridy = 0;
        layoutGBCFolderGUICenterJPanel.weightx = 1.0;
        layoutGBCFolderGUICenterJPanel.weighty = 0.02;
        layoutGBCFolderGUICenterJPanel.anchor = GridBagConstraints.WEST;
        JPanel headPanel = headFolderGUIJPanel(tempString);
        createFunctionGUIrecreateCenterJPanel.add(headPanel, layoutGBCFolderGUICenterJPanel);

        layoutGBCFolderGUICenterJPanel.gridx = 0;
        layoutGBCFolderGUICenterJPanel.gridy = 1;
        layoutGBCFolderGUICenterJPanel.weightx = 1.0;
        layoutGBCFolderGUICenterJPanel.weighty = 0.98;
        layoutGBCFolderGUICenterJPanel.fill = GridBagConstraints.BOTH;

        JScrollPane centerJScrollPanel = centerJScrollPanel(tempString);
        createFunctionGUIrecreateCenterJPanel.add(centerJScrollPanel, layoutGBCFolderGUICenterJPanel);
        FunctionGUI.addContainerPanelToFrame(createFunctionGUIrecreateCenterJPanel);
    }

    private JLabel setActualPathJLabelText(String textToSet, JLabel actualPathJLabel) {
        String textToDisplay = "Du befindest dich hier: " + textToSet;
        actualPathJLabel.setText(textToDisplay);
        return actualPathJLabel;
    }

    private void openFileExplorer(String pathToOpen) {
        File file = new File(pathToOpen);
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        } catch (IOException ex) {
        }
    }

    public JButton createIcon(Fileobject fileobjectForIcon) {

        GetImagePath myGetImagePath = new GetImagePath();

        ImageIcon icon = new ImageIcon(myGetImagePath.getImagePath(fileobjectForIcon.getFileType()));
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        JButton cornerButton = new JButton(icon);
        cornerButton.setSize(20, 20);
        cornerButton.setBorder(BorderFactory.createEmptyBorder());
        cornerButton.setContentAreaFilled(false);
        return cornerButton;
    }

    public JLabel customJLabel(String textToDisplay, int percentageToFill) {
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
                g.fillRect(getWidth() - fillWidth, 0, fillWidth, 800);

                // Den Standard-Look (Text etc.) rendern
                super.paintComponent(g);
            }
        };
        return jLabelWithCustomRect;
    }

    private void goBackButtonFunction(String tempActualPath) {

        StartGUI myStartGUI = new StartGUI();

        if (filereader.backToHome(tempActualPath) == true) {
            FunctionGUI.removeContainerPanel();
            FunctionGUI.addContainerPanelToFrame(myStartGUI.startGUIJPanel());
        } else {
            String newPath = filereader.getParent(tempActualPath);
            FunctionGUI.removeContainerPanel();
            recreateCenterJPanel(newPath);
        }

    }
}
