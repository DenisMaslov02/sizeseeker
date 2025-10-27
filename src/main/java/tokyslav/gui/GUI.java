package tokyslav.gui;

import javax.swing.JFrame;

import tokyslav.Settings.SettingFunctions;

public class GUI {

    private JFrame frame;

    public GUI() {
        frame = new FrameGUI();

        StartGUI myStartgui = new StartGUI();
        frame.add(myStartgui.startGUIJPanel(frame));
        frame.setVisible(true);

        SettingFunctions myFunctions = new SettingFunctions();
        myFunctions.createJSONFile();
    }

}
