package tokyslav.gui;

import javax.swing.JFrame;

public class FrameGUI extends JFrame {

    public FrameGUI() {

        super("SizeSeeker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
