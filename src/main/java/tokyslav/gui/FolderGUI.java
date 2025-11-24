package tokyslav.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
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
        fileObjectJPanel.setBackground(Color.WHITE);
        fileObjectJPanel.setLayout(new BorderLayout());

        // Hauptbutton
        JButton createFolderGUIButtonToPress = new JButton();
        createFolderGUIButtonToPress.setOpaque(false);
        createFolderGUIButtonToPress.setContentAreaFilled(false);
        createFolderGUIButtonToPress.setBorderPainted(false);
        createFolderGUIButtonToPress.setLayout(new BorderLayout());

        JPanel insadeButJPanel = new JPanel();
        insadeButJPanel.setLayout(new GridBagLayout());
        GridBagConstraints layoutGBCInsideButJPanel = new GridBagConstraints();

        layoutGBCInsideButJPanel.fill = GridBagConstraints.BOTH;

        layoutGBCInsideButJPanel.gridx = 0;
        layoutGBCInsideButJPanel.gridy = 0;
        layoutGBCInsideButJPanel.weightx = 0.2;
        layoutGBCInsideButJPanel.weighty = 1.0;

        // ICON LINKS
        JButton createFolderGUIIconButton = createIcon(tempFileobject);
        createFolderGUIIconButton.addActionListener(e -> openFileExplorer(tempFileobject.getFileName()));
        createFolderGUIButtonToPress.add(createFolderGUIIconButton, BorderLayout.WEST);

        insadeButJPanel.add(createFolderGUIButtonToPress, layoutGBCInsideButJPanel);

        // CENTER-PANEL → Name + Graphic
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        JLabel fileNameLabel = new JLabel(tempFileobject.getFileName());
        fileNameLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        centerPanel.add(fileNameLabel, BorderLayout.WEST);

        JLabel graphicJLabel = GUILogic.customJLabel("", percentageOfSize);
        graphicJLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        centerPanel.add(graphicJLabel, BorderLayout.EAST);

        layoutGBCInsideButJPanel.gridx = 1;
        layoutGBCInsideButJPanel.gridy = 0;
        layoutGBCInsideButJPanel.weightx = 0.6;
        layoutGBCInsideButJPanel.weighty = 1.0;

        insadeButJPanel.add(centerPanel, layoutGBCInsideButJPanel);

        // SIZE RECHTS
        JLabel fileSizeLabel = new JLabel(
                GUILogic.calculateSizeDisplayNumber(tempFileobject.getSize()));
        JPanel sizePanel = new JPanel();
        sizePanel.setBackground(Color.WHITE);
        sizePanel.add(fileSizeLabel);

        layoutGBCInsideButJPanel.gridx = 2;
        layoutGBCInsideButJPanel.gridy = 0;
        layoutGBCInsideButJPanel.weightx = 0.2;
        layoutGBCInsideButJPanel.weighty = 1.0;

        insadeButJPanel.add(sizePanel, layoutGBCInsideButJPanel);

        // Button Action
        createFolderGUIButtonToPress.addActionListener(e -> recreateCenterJPanel(tempFileobject.getFileName()));

        fileObjectJPanel.add(createFolderGUIButtonToPress, BorderLayout.CENTER);
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

    private void goBackButtonFunction(String tempActualPath) {

        StartGUI myStartGUI = new StartGUI();

        if (filereader.backToHome(tempActualPath) == true) {
            FunctionGUI.removeContainerPanel();
            FunctionGUI.addContainerPanelToFrame(myStartGUI.startGUIJPanel());
        } else {
            String newPath = filereader.getParent(tempActualPath);
            recreateCenterJPanel(newPath);
        }

    }
}