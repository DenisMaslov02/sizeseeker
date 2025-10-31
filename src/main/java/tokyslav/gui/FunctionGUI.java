package tokyslav.gui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.PanelUI;

import tokyslav.Fileobject;

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
        // JPanel addingJPanelToFrame = tempJPanel;

        // frameFROMGUI.setLayout(new GridLayout());
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
