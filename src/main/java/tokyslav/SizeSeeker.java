package tokyslav;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tokyslav.gui.GUI;

import tokyslav.filereader.filereader;

/**
 * Hello world!
 *
 */
public class SizeSeeker {

    private static final String myText = "HelloText";

    public static void main(String[] args) {
        // System.out.println(myText);
        // createMyWindow();
        filereader.getRoots();
        filereader.getPathDrive();
        // try {
        // filereader.getFileNames();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }

    public static String getMyText() {
        return myText;
    }

}
