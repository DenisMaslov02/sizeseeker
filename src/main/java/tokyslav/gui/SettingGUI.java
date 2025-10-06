package tokyslav.gui;

import javax.swing.JFrame;

public class SettingGUI {
    public JFrame frame = new JFrame("Settings");

    public void settingJPanel() {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }
}
