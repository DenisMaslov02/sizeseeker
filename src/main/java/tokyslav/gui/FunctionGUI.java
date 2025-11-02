package tokyslav.gui;

import javax.swing.JFrame;

import javax.swing.JPanel;

public class FunctionGUI {

    private static JFrame frameFROMGUI;

    boolean x = false;

    public void info() {
        System.out.println(frameFROMGUI);
    }

    public JFrame getFrame() {
        return frameFROMGUI;
    }

    public static void setFrame(JFrame tempFrame) {
        frameFROMGUI = tempFrame;
    }

    public static void removeContainerPanel() {
        if (frameFROMGUI == null) {
            System.out.println("Frame is null");
        }
        frameFROMGUI.getContentPane().removeAll();
        frameFROMGUI.revalidate();
        frameFROMGUI.repaint();
        frameFROMGUI.setVisible(true);
    }

    public static void addContainerPanelToFrame(JPanel tempJPanel) {
        frameFROMGUI.add(tempJPanel);
        frameFROMGUI.revalidate();
        frameFROMGUI.repaint();
    }

    public JFrame createFrame() {
        if (x == false) {
            x = true;
            frameFROMGUI = new FrameGUI();
        }
        return frameFROMGUI;
    }

}
