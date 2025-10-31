package tokyslav.gui;

import javax.swing.JFrame;

public class GUI {

    private JFrame frameGUI;

    public GUI() {

        FunctionGUI FunctionGUI = new FunctionGUI();

        frameGUI = FunctionGUI.createFrame();

        StartGUI myStartgui = new StartGUI();
        frameGUI.add(myStartgui.startGUIJPanel());
        frameGUI.setVisible(true);
        FunctionGUI.setFrame(frameGUI);
    }

}
