package tokyslav.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GUI {

    private final int width = 800;
    private final int height = 800;
    private JFrame frame;
    private JLabel actualPathJLabel;
    private JScrollPane centerJPanel;
    private JPanel startCenterJPanel;
    private JPanel startHeadJPanel;
    private JPanel headPanel;
    private JScrollPane centerJScroPanel;

    private int heightofHeadPanel = 35;
    private int heightofSouthPanel = 50;

    public GUI() {

        StartGUI myStartgui = new StartGUI();
        myStartgui.startGUIJPanel();

        frame.setVisible(true);
    }

}
