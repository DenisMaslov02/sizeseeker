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
        // filereader.getParent("C:\\");
        for (Fileobject x : filereader.getInfoFromPath("C:\\Users\\Phill")) {
            System.out.println(x.getFileName());
            System.out.println(x.getSize());
            System.out.println(x.getFileType();
        }
        // new GUI();
        // filereader.findFileType(C:\\Users\\Phill);
        // filereader.sendFileSizeBack("c:\\Users\\Phill\\Videos\\Captures");
        // testReaderFile.testGetSize("C:\\$SysReset");
    }

    public static String getMyText() {
        return myText;
    }

}
