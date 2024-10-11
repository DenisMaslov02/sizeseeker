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
        // for (Fileobject x : filereader.getObject()) {
        // System.out.println(x.getFileName());
        // System.out.println(x.getSize());
        // System.out.println(x.getFileType());
        // }
        // System.out.println(filereader.getRoots()[0].toString());
        filereader.getParent("C:\\");
        // filereader.getObject();
        // try {
        // filereader.getFileNames();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // new GUI();
    }

    public static String getMyText() {
        return myText;
    }

}
