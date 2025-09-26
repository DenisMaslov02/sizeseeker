package tokyslav.filereader;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import tokyslav.FileTypes;
import tokyslav.Fileobject;

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
            } else {
                foundRoots = true;
            }
        }
        if (foundRoots == false) {
            Path getPath = Path.of(backpath);
            String parentPath = getPath.getParent().toString();
            backpath = parentPath;
        }
        return backpath;
    }

    public static String getNameOfPath(String Path) {

        File file = new File(Path);
        Path pathList = file.toPath();
        Path pathLast = pathList.getName(pathList.getNameCount() - 1);
        String pathName = pathLast.toString();

        return pathName;
    }

    public static Fileobject[] getInfoFromPath(String infoFromString) {
        
        File infoFromPath = new File(infoFromString);
        File[] fileListName = infoFromPath.listFiles();
        infoFromPath.list();

        List<Fileobject> fileobjectlist = new ArrayList<Fileobject>();
        List<myThread> myThreadList = new ArrayList<myThread>();
        List<Thread> threadList = new ArrayList<Thread>();

        if (infoFromPath.exists() || infoFromPath.isDirectory()) {
            for (int i = 0; i < fileListName.length; i++) {
                myThread oneTask = new myThread(fileListName[i]);
                myThreadList.add(oneTask);
                Thread oneThread = new Thread(oneTask);
                oneThread.start();
                threadList.add(oneThread);
            }
        }
        boolean isStillChecking = true;
        while (isStillChecking) {
            boolean stillrunning = false;
            for (Thread t : threadList) {
                if (t.isAlive()) {
                    stillrunning = true;
                }
            }
            if (!stillrunning) {
                isStillChecking = false;
            }
        }
        for (myThread oneTask : myThreadList) {
            fileobjectlist.add(oneTask.getFileobject());
        }
        // checken das alle Tasks fertig sind und hinzufügen
        return fileobjectlist.toArray(new Fileobject[0]);
    }

    static Fileobject createsingleFileObject(File singleFile) {
        String filepath = singleFile.toString();
        long sizeOfPath = getSizeFromPath(singleFile);
        FileTypes typeOfPath = findFileType(singleFile);
        return new Fileobject(filepath, sizeOfPath, typeOfPath);
    }

    private static long getSizeFromPath(File infoFromPath) {
        long sizeOfFile = 0;
        if (infoFromPath == null) {
            return sizeOfFile;
        }
        // if it's a file
        if (!infoFromPath.isDirectory()) {
            sizeOfFile += infoFromPath.length();
        } else {
            File[] filesDirectory = infoFromPath.listFiles();
            if (filesDirectory == null) {
                return sizeOfFile;
            }
            for (File subfile : filesDirectory) {
                sizeOfFile += getSizeFromPath(subfile);
            }
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