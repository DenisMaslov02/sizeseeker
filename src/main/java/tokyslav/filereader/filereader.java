package tokyslav.filereader;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import tokyslav.FileTypes;
import tokyslav.Fileobject;
import tokyslav.Stringobject;
import tokyslav.multithread.myThread;

public class filereader {

    public static void main(String[] args) {
        // for (int i = 0; i <= 3; i++) {
        // myThread newThread = new myThread(i);
        // Thread myThread = new Thread(newThread);
        // myThread.start();
        // }
        File file = new File("C:\\Users\\Phill");
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            System.out.println(fileList[i]);
            System.out.println(i);
        }
        System.out.println(fileList.length);

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

    public static Stringobject[] getNameOfPath(String Path) {

        File file = new File(Path);
        File[] fileListName = file.listFiles();

        List<Stringobject> stringobjectList = new ArrayList<Stringobject>();
        for (int i = 0; i < fileListName.length; i++) {
            Path pathList = fileListName[i].toPath();
            Path pathLast = pathList.getName(pathList.getNameCount() - 1);
            String pathName = pathLast.toString();

            Stringobject singelStringobject = new Stringobject(pathName);
            stringobjectList.add(singelStringobject);
        }
        return stringobjectList.toArray(new Stringobject[0]);
    }

    public static Fileobject[] getInfoFromPath(String infoFromString) {

        File infoFromPath = new File(infoFromString);
        File[] fileListName = infoFromPath.listFiles();

        List<Fileobject> fileobjectlist = new ArrayList<Fileobject>();

        if (infoFromPath.exists() || infoFromPath.isDirectory()) {
            System.out.print("Path exists!");
            for (int i = 0; i < fileListName.length; i++) {
                File fileOfSize = fileListName[i];
                String filepath = fileListName[i].toString();
                long sizeOfPath = getSizeFromPath(fileOfSize);
                FileTypes typeOfPath = findFileType(fileListName[i]);

                Fileobject singleFileObject = new Fileobject(filepath, sizeOfPath, typeOfPath);
                fileobjectlist.add(singleFileObject);
            }
        } else {
            System.out.println("Path doesn´t exists!");
        }
        return fileobjectlist.toArray(new Fileobject[0]);
    }

    private static long getSizeFromPath(File infoFromPath) {

        long sizeOfFile = 0;
        if (infoFromPath == null)
            return sizeOfFile;
        if (infoFromPath.isDirectory()) {
            File[] filesDirectory = infoFromPath.listFiles();

            if (filesDirectory != null) {
                int n = filesDirectory.length;
                for (int i = 0; i < n / 5; i++) {
                    Runnable task1 = () -> getSizeFromPath(filesDirectory[i]);
                    Runnable task2 = () -> getSizeFromPath(filesDirectory[i + 1]);
                    Runnable task3 = () -> getSizeFromPath(filesDirectory[i + 2]);
                    Runnable task4 = () -> getSizeFromPath(filesDirectory[i + 3]);
                    Runnable task5 = () -> getSizeFromPath(filesDirectory[i + 4]);
                }
            }

            if (filesDirectory != null) {
                for (File subfile : filesDirectory) {
                    sizeOfFile += getSizeFromPath(subfile);
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