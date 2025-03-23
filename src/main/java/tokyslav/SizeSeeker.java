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
        // System.out.println(myText);
        // // filereader.getParent("C:\\");
        // filereader.getNameOfPath("C:\\Users\\Public");
        // filereader.getInfoFromPath("C:\\Users\\Phill");

        // for (Fileobject x : filereader.getInfoFromPath("C:\\Users\\Phill")) {
        // System.out.println(x.getFileName());
        // System.out.println(x.getSize());
        // System.out.println(x.getFileType());
        // }
        // for (Stringobject x : filereader.getNameOfPath("C:\\Users\\Phill")) {
        // System.out.println(x.getPathName());
        // }
        new GUI();
        // filereader.getNameOfPath("C:\\Users\\Phill");
        // filereader.findFileType(C:\\Users\\Phill);
        // filereader.sendFileSizeBack("c:\\Users\\Phill\\Videos\\Captures");
        // testReaderFile.testGetSize("C:\\$SysReset");
    }

    public static String getMyText() {
        return myText;
    }

}
