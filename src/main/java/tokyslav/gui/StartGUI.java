package tokyslav.gui;

import javax.swing.*;

import tokyslav.FileTypes;
import tokyslav.filereader.filereader;

import java.awt.*;
import java.io.File;

public class StartGUI {

    public JPanel startGUIJPanel() {

        JPanel startJPanelGUI = new JPanel();
        startJPanelGUI.setLayout(new GridBagLayout());
        GridBagConstraints layoutGBCStartGUI = new GridBagConstraints();

        layoutGBCStartGUI.fill = GridBagConstraints.BOTH;

        layoutGBCStartGUI.gridx = 0;
        layoutGBCStartGUI.gridy = 0;
        layoutGBCStartGUI.weightx = 1.0;
        layoutGBCStartGUI.weighty = 0.02;
        layoutGBCStartGUI.anchor = GridBagConstraints.WEST;

        JPanel startGUIHeadJPanel = startGUIHeadPanel();
        startJPanelGUI.add(startGUIHeadJPanel, layoutGBCStartGUI);

        layoutGBCStartGUI.gridx = 0;
        layoutGBCStartGUI.gridy = 1;
        layoutGBCStartGUI.weightx = 1.0;
        layoutGBCStartGUI.weighty = 0.96;
        layoutGBCStartGUI.fill = GridBagConstraints.BOTH;
        JPanel startGUICenterJPanel = startGUICenterJPanel();
        startJPanelGUI.add(startGUICenterJPanel, layoutGBCStartGUI);

        return startJPanelGUI;
    }

    private JPanel startGUIHeadPanel() {

        SettingGUI settingGUI = new SettingGUI();

        JPanel createStartGUIHeadPanel = new JPanel();
        createStartGUIHeadPanel.setLayout(new BorderLayout(8, 0));
        createStartGUIHeadPanel.setBackground(Color.WHITE);

        JButton headStartGUISettingButton = new JButton();
        headStartGUISettingButton.setText("Settings");
        // headStartGUISettingButton.addActionListener(e -> settingGUI.settingJPanel());
        createStartGUIHeadPanel.add(headStartGUISettingButton, BorderLayout.WEST);

        ImageIcon getImageIconPath = new ImageIcon(
                "src\\main\\java\\tokyslav\\gui\\IMAGE\\Loading_Icon_new.gif");
        Image setScaleImageForImageIcon = getImageIconPath.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon newImageIconLoadingGif = new ImageIcon(setScaleImageForImageIcon);

        JLabel creatingJLabel = new JLabel("Loading", newImageIconLoadingGif, SwingConstants.RIGHT);
        creatingJLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
        createStartGUIHeadPanel.add(creatingJLabel, BorderLayout.EAST);
        return createStartGUIHeadPanel;
    }

    private JPanel startGUICenterJPanel() {

        JPanel createStartGUICenterJPanel = new JPanel(new GridLayout(2, 1));

        File[] listOfFileRoots = filereader.getRoots();

        JPanel createStartGUIDiagrammJPanel = new JPanel(new GridLayout(1, 2));
        JPanel createStartGUIRootsJPanel = new JPanel(new GridLayout(listOfFileRoots.length, 1));

        for (int i = 0; i < listOfFileRoots.length; i++) {
            createStartGUIRootsJPanel.add(createStartGUIRootsJPanel(listOfFileRoots[i]));

        }

        createStartGUICenterJPanel.add(createStartGUIDiagrammJPanel);
        createStartGUICenterJPanel.add(createStartGUIRootsJPanel);

        return createStartGUICenterJPanel;
    }

    private JPanel createStartGUIRootsJPanel(File tempNameFile) {

        GetImagePath myGetImagePath = new GetImagePath();
        // TODO rename GetImagePath

        JPanel createStartGUIRootsJPanel = new JPanel();

        createStartGUIRootsJPanel.setBackground(Color.white);
        createStartGUIRootsJPanel.setLayout(new BorderLayout());

        JButton createStartGUIButtonForJPanel = new JButton();

        createStartGUIButtonForJPanel.setOpaque(false);
        createStartGUIButtonForJPanel.setContentAreaFilled(false);
        createStartGUIButtonForJPanel.setBorderPainted(false);
        createStartGUIButtonForJPanel.setLayout(new BorderLayout());
        createStartGUIButtonForJPanel.addActionListener(e -> startGUIGetInDriveCenterPanel(tempNameFile.toString()));

        JPanel createStartGUIJPanelForDrive = new JPanel();
        createStartGUIJPanelForDrive.setLayout(new BorderLayout());
        createStartGUIJPanelForDrive.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        ImageIcon driveIcon = new ImageIcon(myGetImagePath.getImagePath(FileTypes.DRIVE));
        Image scaledImg = driveIcon.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
        driveIcon = new ImageIcon(scaledImg);

        createStartGUIJPanelForDrive.setBackground(Color.white);
        createStartGUIJPanelForDrive.add(new JLabel(tempNameFile.toString(), driveIcon, JLabel.LEFT));

        createStartGUIButtonForJPanel.add(createStartGUIJPanelForDrive);

        createStartGUIRootsJPanel.add(createStartGUIButtonForJPanel);

        return createStartGUIRootsJPanel;
    }

    public void createLoadingAnimationImageIcon() {

    }

    private void startGUIGetInDriveCenterPanel(String tempDrivePath) {
        FolderGUI myFolderGUI = new FolderGUI();
        FunctionGUI.removeContainerPanel();

        FunctionGUI.addContainerPanelToFrame(myFolderGUI.createJPanelInToDrive(tempDrivePath));
    }
}
