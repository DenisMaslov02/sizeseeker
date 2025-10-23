package tokyslav.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SettingGUI {
    private final int width = 800;
    private final int height = 800;
    private JFrame frame;
    private JPanel settingHeadJPanel;
    private JPanel settingCenterJPanel;

    public void settingJPanel() {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        createGridBagConstraints();
        frame.setVisible(true);
    }

    private JPanel createGridBagConstraints() {
        frame.setLayout(new GridLayout());
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel settingJPanel = new JPanel();
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.02;
        gbc.anchor = GridBagConstraints.WEST;

        settingHeadJPanel = settingHeadJPanel();
        frame.add(settingHeadJPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.98;
        // gbc.fill = GridBagConstraints.BOTH;
        settingCenterJPanel = settingCenterJPanel();
        frame.add(settingCenterJPanel, gbc);

        return settingJPanel;

        // Implementation for creating GridBagConstraints
    }

    private JPanel settingHeadJPanel() {

        StartGUI startGUI = new StartGUI();
        JPanel settingHeadPanel = new JPanel();
        settingHeadPanel.setBackground(java.awt.Color.white);
        settingHeadPanel.setLayout(new BorderLayout());

        JButton backButton = new JButton();
        backButton.setText("Back");
        backButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.revalidate();
            frame.repaint();
            // frame.add(startGUI.startGUIJPanel(frame));
            frame.setVisible(true);
        });

        JButton GraphicsButton = new JButton();
        GraphicsButton.setText("Graphics Settings");
        GraphicsButton.addActionListener(e -> {
            // frame.getContentPane().removeAll();
            // frame.revalidate();
            // frame.repaint();
            // frame.add(startGUI.startGUIJPanel(frame));
            // frame.setVisible(true);
        });
        settingHeadPanel.add(backButton, BorderLayout.WEST);
        settingHeadPanel.add(GraphicsButton, BorderLayout.EAST);

        return settingHeadPanel;
    }

    private JPanel settingCenterJPanel() {

        JPanel settingCenterJPanel = new JPanel(new GridLayout(2, 1));

        return settingCenterJPanel;
    }

    public void setFrame(JFrame frame2) {
        frame = frame2;
    }
}
