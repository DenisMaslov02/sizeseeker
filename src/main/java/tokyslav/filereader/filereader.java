package tokyslav.filereader;

import tokyslav.FileTypes;
import tokyslav.Fileobject;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class filereader {

    public static Fileobject[] getInfoFromPath(String infoFromString) {

        File infoFromPath = new File(infoFromString);

        List<Fileobject> fileobjectlist = new ArrayList<Fileobject>();

        if (infoFromPath.exists() || infoFromPath.isDirectory()) {
            System.out.print("Path exists!");
            fileobjectlist.add(new Fileobject(infoFromString, getInfoFromPath(infoFromPath), null));
            // fileobjectlist = createMethodeFileObjects(infoFromPath);
            // if (checkroots(infoFromPath)) {
            // createFileObjects(infoFromPath, true);
            // } else {
            // createFileObjects(infoFromPath, false);
            // }
        } else {
            System.out.println("Path doesn´t exists!");
        }
        return fileobjectlist.toArray(new Fileobject[0]);
    }

    // public static FileObject[] createListOfObject(File HeadFile, List<Fileobject>
    // fileobjectlist) {

    // File[] nameFilesList = HeadFile.listFiles();

    // fileobjectlist.getFileName().add(HeadFile.toString());
    // for (int fileListIndex = 0; fileListIndex < nameFilesList.length;
    // fileListIndex++) {
    // fileobjectlist.getFileName[fileListIndex] +=
    // nameFilesList[fileListIndex].toString();
    // }
    // return FileObject[];
    // }

    // public static List<Fileobject> createMethodeFileObjects(String Path) {

    // File getFile = new File(Path);
    // File[] getFileList = getFile.listFiles();

    // List<Fileobject> fileobjectlist = new ArrayList<Fileobject>();
    // for (File file : getFileList) {
    // fileobjectlist.add(new Fileobject(file.getAbsolutePath(),
    // sendFileSizeBack(file.toString()),
    // findFileType(file)));
    // }

    // return fileobjectlist;
    // }

    private static int getInfoFromPath(File infoFromPath) {
        int x = 0;

        return x;
    }

    public static String getParent(String backpath) {

        File[] roots = File.listRoots();

        boolean foundRoots = false;

        for (int rootsIndex = 0; rootsIndex < roots.length; rootsIndex++) {

            if (backpath != roots[rootsIndex].toString()) {
                foundRoots = false;
                System.out.println("You can go Back!");
            } else {
                foundRoots = true;
                System.out.println("You can not go Back!");
            }
        }
        if (foundRoots == false) {
            Path getPath = Path.of(backpath);
            String parentPath = getPath.getParent().toString();
            backpath = parentPath;
        }
        return backpath;
    }

    public static String sendFileSizeBack(String infoFromPath) {

        File getfileSize = new File(infoFromPath);
        File[] getFileList = getfileSize.listFiles();
        int arrayInt = getFileList.length;
        int fileSize = 0;
        if (arrayInt == 0) {
            fileSize += infoFromPath.length();
        } else {
            for (int x = 0; x < getFileList.length; x++) {
                fileSize += getFileList[x].length();
            }
        }
        return fileSize + "bytes";

    }

    public static long getSize(File file) {
        long size = 0;

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; files != null && i < file.length(); i++) {
                size += getSize(files[i]);
            }
        } else {
            size += file.length();
        }
        return size;
    }

    public static FileTypes findFileType(File fileTypee) {

        FileTypes type;

        if (fileTypee.isFile()) {
            type = FileTypes.FILE;
        } else if (!fileTypee.isFile() && !fileTypee.isDirectory()) {
            type = FileTypes.OTHER;
        } else if (fileTypee.isDirectory()) {
            type = FileTypes.DIRECTORY;
        } else {
            type = FileTypes.NOTEXIST;
        }
        return type;
    }

    public static File[] getRoots() {
        File[] roots = File.listRoots();
        return roots;
    }
    // ######################## Das war eine Methode von mir um Anzeige zu testen,
    // no worries ##########################
    /*
     * public static Fileobject[] getInfoFromPath(String p_pathToSearch){
     * List<Fileobject> fileobjectlist = new ArrayList<Fileobject>();
     * 
     * if("1".equals(p_pathToSearch)){
     * fileobjectlist.add(new Fileobject("C:\\","8326583", FileTypes.DRIVE));
     * fileobjectlist.add(new Fileobject("D:\\","6542345", FileTypes.DRIVE));
     * fileobjectlist.add(new Fileobject("E:\\","3949757656", FileTypes.DRIVE));
     * }
     * if("2".equals(p_pathToSearch)){
     * fileobjectlist.add(new Fileobject("Dir_1","28", FileTypes.DIRECTORY));
     * fileobjectlist.add(new Fileobject("Dir_2","45", FileTypes.DIRECTORY));
     * fileobjectlist.add(new Fileobject("config.xml","64", FileTypes.OTHER));
     * fileobjectlist.add(new Fileobject("MeinText.txt","3434", FileTypes.FILE));
     * }
     * if("3".equals(p_pathToSearch)){
     * fileobjectlist.add(new Fileobject("Dir_1","64792", FileTypes.DIRECTORY));
     * fileobjectlist.add(new Fileobject("MeinText.txt","5", FileTypes.FILE));
     * fileobjectlist.add(new Fileobject("MeinText2.txt","6482", FileTypes.FILE));
     * fileobjectlist.add(new Fileobject("Bachelor.txt","1", FileTypes.FILE));
     * }
     * 
     * return fileobjectlist.toArray(new Fileobject[0]);
     * }
     */
}

// Windows: C:\Dev\SizeSeeker_old\src\META-INF
// Mac/Linx: /Dev/SizeSeeker_old/src/

/*
 * Implementiere in dieser KLasse die Funktionalität aus einem gegebenen
 * Pfad(Windows) alle
 * darunter liegenden Ordner/Dateien zu lesen und dessen Größe herauszufinden
 * 
 */

// TODO exists() für search folder/data

// SystemDATA: 1,6,8,9,10,11,14,16,21,23,24

// 16 ist das Gleiche wie 18