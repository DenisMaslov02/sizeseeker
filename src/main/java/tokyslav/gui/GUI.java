package tokyslav.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GUI {

    private JFrame frame;
    private JLabel actualPathJLabel;
    private JScrollPane centerJPanel;
    private JPanel startCenterJPanel;
    private JPanel startHeadJPanel;
    private JPanel headPanel;
    private JScrollPane centerJScroPanel;

    public GUI() {

        frame = new JFrame();
        frame.setTitle("SizeSeeker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);

        StartGUI myStartgui = new StartGUI();
        frame.add(myStartgui.startGUIJPanel(frame));
        frame.setVisible(true);
    }

    public JFrame removeAllJpanel(JFrame frame) {
        return frame;
    }

}
