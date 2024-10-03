package tokyslav;

import tokyslav.filereader.filereader;
import tokyslav.gui.GUI;


/**
 * Hello world!
 *
 */
public class SizeSeeker {

    private static final String myText = "HelloText";

    public static void main(String[] args) {
        System.out.println(myText);
        new GUI(new filereader());
    }

    public static String getMyText() {
        return myText;
    }

}
