package tokyslav;

import tokyslav.gui.GUI;

/**
 * Hello world!
 *
 */
public class SizeSeeker {

    private static final String myText = "HelloText";

    public static void main(String[] args) {
        System.out.println(myText);
        new GUI();
    }

    public static String getMyText() {
        return myText;
    }

}
