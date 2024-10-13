package tokyslav.filereader;

import java.util.*;

import javax.sound.sampled.SourceDataLine;

import com.google.common.io.Files;

import scala.collection.StringOps.StringIterator;
import tokyslav.FileTypes;
import tokyslav.Fileobject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class filereader {

    public static Fileobject[] getInfoFromPath(String infoFromPath) {

        File getFile = new File(infoFromPath);
        File[] getFileList = getFile.listFiles();

        List<Fileobject> fileobjectlist = new ArrayList<Fileobject>();

        if (infoFromPath == getParent(infoFromPath)) {
            fileobjectlist.add(new Fileobject(infoFromPath, sendFileSizeBack(getFile),
                    FileTypes.DRIVE));
            for (File file : getFileList) {
                fileobjectlist.add(new Fileobject(file.getAbsolutePath(),
                        sendFileSizeBack(file),
                        findFileType(file)));
            }
        }
        if (infoFromPath != getParent(infoFromPath)) {
            for (File file : getFileList) {
                fileobjectlist.add(new Fileobject(file.getAbsolutePath(),
                        sendFileSizeBack(file),
                        findFileType(file)));
            }
        }
        return fileobjectlist.toArray(new Fileobject[0]);
    }

    public static String getParent(String backpath) {

        File[] roots = File.listRoots();

        boolean foundRoots = false;

        for (int rootsInd = 0; rootsInd < roots.length; rootsInd++) {

            if (backpath == roots[rootsInd].toString()) {
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

    public static String sendFileSizeBack(File infoFromPath) {

        return infoFromPath.length() + "bytes";

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

    // public static Fileobject[] getPathDrive() {

    // List<Fileobject> fileobjectlist = new ArrayList<Fileobject>();
    // for (File file : getRoots()) {
    // fileobjectlist.add(new Fileobject(file.getAbsolutePath(), null,
    // FileTypes.DRIVE));
    // }
    // return fileobjectlist.toArray(new Fileobject[0]);
    // }

    public static void openHardDrive() {

        File[] roots = File.listRoots();
        if (roots != null && roots.length > 0) {

        }
    }

    public static void getBackPath() {

    }

    @SuppressWarnings("unchecked")
    public static <Interger> void getFileNames() throws IOException {

        List myDataList = new LinkedList<Interger>();
        List myDataTyp = new LinkedList<Interger>();
        List myDataSize = new LinkedList<Interger>();

        File root = new File("C:\\");
        File[] folderRoot = root.listFiles();

        File path = new File("\\");
        File[] folderSizeList = path.listFiles();

        if (null != root) {
            for (int folderIntList = 0; folderIntList < folderRoot.length; folderIntList++) {

                String folder = folderRoot[folderIntList].toString();
                String fileExt = Files.getFileExtension(folderRoot[folderIntList].toString());

                Path pathsize = Paths.get(folderSizeList[folderIntList].toString());
                FileChannel fileChannel;
                // Path folderPath = Paths.get(folder[folderIntList].toString);
                // long sizefile = Files.size(folderRoot[folderIntList]);
                if (null != folder && folder.length() > 0) {
                    myDataList.add(folder.substring(folder.lastIndexOf("\\") + 1, folder.length()));
                    myDataTyp.add(fileExt);
                    // try {
                    // fileChannel = FileChannel.open(pathsize);
                    // long filesize = fileChannel.size();
                    // System.out.println(filesize + "bytes");
                    // fileChannel.close();
                    // } catch (IOException e) {
                    // e.printStackTrace();
                    // }
                }
                // myDataSize.add(pathsize.size());
                // System.out.println("File:" + (folderIntList + 1) + ": "
                // + folder.substring(pathsize.lastIndexOf("\\") + 1, pathsize.length()));
                // for print!!! Don´t delete
            }
        }
        System.out.println(myDataList);
        System.out.println(myDataTyp);
        // System.out.println(myDataSize);
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