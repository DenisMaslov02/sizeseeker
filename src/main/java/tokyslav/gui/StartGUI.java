package tokyslav.gui;

import javax.swing.*;

import tokyslav.FileTypes;
import tokyslav.filereader.filereader;

import java.awt.*;
import java.io.File;

public class StartGUI {

    private JFrame frame;
    private JPanel startJPanel;
    private JPanel startHeadJPanel;
    private JPanel startCenterJPanel;
    private JPanel headPanel;
    private JScrollPane centerJScrollPanel;

    public JPanel startGUIJPanel(JFrame tempFrame) {

        frame = tempFrame;
        startJPanel = new JPanel();
        startJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.02;
        gbc.anchor = GridBagConstraints.WEST;

        startHeadJPanel = startHeadPanel();
        startJPanel.add(startHeadJPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.98;
        gbc.fill = GridBagConstraints.BOTH;
        startCenterJPanel = startCenterJPanel();
        startJPanel.add(startCenterJPanel, gbc);

        return startJPanel;
    }

    private JPanel startHeadPanel() {

        SettingGUI settingGUI = new SettingGUI();
        settingGUI.pullFrame(frame);

        JPanel startHeadPanel = new JPanel();

        startHeadPanel.setBackground(Color.white);
        startHeadPanel.setLayout(new BorderLayout());

        JButton settingButton = new JButton();

        settingButton.setText("Settings");
        settingButton.addActionListener(e -> settingGUI.settingJPanel());

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

        GetImagePath myGetImagePath = new GetImagePath();

        JPanel rootsObjectPanel = new JPanel();

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

        ImageIcon driveIcon = new ImageIcon(myGetImagePath.getImagePath(FileTypes.DRIVE));
        Image scaledImg = driveIcon.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
        driveIcon = new ImageIcon(scaledImg);

        RenameJPanel.setBackground(Color.white);
        RenameJPanel.add(new JLabel(tempNameFile.toString(), driveIcon, JLabel.LEFT));

        RenamButton.add(RenameJPanel);

        rootsObjectPanel.add(RenamButton);

        return rootsObjectPanel;
    }

    private JFrame getInDriveCenterPanel(String tempDrivePath) {
        FolderGUI myFolderGUI = new FolderGUI();

        frame.remove(startJPanel);

        JPanel inToDriveJPanel = new JPanel();

        inToDriveJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.02;
        gbc.anchor = GridBagConstraints.WEST;
        headPanel = myFolderGUI.headJPanel(tempDrivePath);
        inToDriveJPanel.add(headPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.98;
        gbc.fill = GridBagConstraints.BOTH;

        centerJScrollPanel = myFolderGUI.centerJScrollPane(tempDrivePath);
        inToDriveJPanel.add(centerJScrollPanel, gbc);
        frame.add(inToDriveJPanel);
        myFolderGUI.pullFrame(frame);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
        return frame;
    }
}
