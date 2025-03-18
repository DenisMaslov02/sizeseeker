package tokyslav.filereader;

import tokyslav.FileTypes;
import tokyslav.Fileobject;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class filereader {

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

    public static Fileobject[] getInfoFromPath(String infoFromString) {

        File infoFromPath = new File(infoFromString);
        File[] fileListName = infoFromPath.listFiles();

        List<Fileobject> fileobjectlist = new ArrayList<Fileobject>();

        if (infoFromPath.exists() || infoFromPath.isDirectory()) {
            System.out.print("Path exists!");
            for (int i = 0; i < fileListName.length; i++) {
                File fileOfSize = fileListName[i];
                fileobjectlist.add(
                        new Fileobject(fileListName[i].toString(), getInfoFromPath(fileOfSize),
                                findFileType(fileListName[i])));
            }
        } else {
            System.out.println("Path doesn´t exists!");
        }
        return fileobjectlist.toArray(new Fileobject[0]);
    }

    private static long getInfoFromPath(File infoFromPath) {

        long sizeOfFile = 0;
        if (infoFromPath == null)
            return sizeOfFile;
        if (infoFromPath.isDirectory()) {
            File[] filesDirectory = infoFromPath.listFiles();
            if (filesDirectory != null) {
                for (File subfile : filesDirectory) {
                    sizeOfFile += getInfoFromPath(subfile);
                }
            }
        } else {
            sizeOfFile += infoFromPath.length();
        }
        return sizeOfFile;
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