package tokyslav.filereader;

import java.io.File;

import tokyslav.Fileobject;

public class myThread implements Runnable {
    private Fileobject aFileObject;
    private File fileToCheck;

    public myThread(File p_fileToCheck) {
        fileToCheck = p_fileToCheck;
    }

    @Override
    public void run() {
        aFileObject = filereader.createsingleFileObject(fileToCheck);
    }

    public Fileobject getFileobject() {
        return aFileObject;
    }
}

// public class ThreadInfo {
// public static void main(String[] args) {
// System.out.println("1. Anzahl aktiver Threads: " + Thread.activeCount());

// int totalThreads = Thread.getAllStackTraces().keySet().size();
// System.out.println("2. Gesamte Anzahl laufender Threads: " + totalThreads);

// System.out.println("3. Liste aller Threads:");
// for (Thread t : Thread.getAllStackTraces().keySet()) {
// System.out.println(" - " + t.getName() + " | Status: " + t.getState());
// }

// // ThreadPool erstellen und Threads zählen
// ThreadPoolExecutor executor = (ThreadPoolExecutor)
// Executors.newFixedThreadPool(5);
// System.out.println("4. Aktive Threads im ThreadPool: " +
// executor.getActiveCount());

// executor.shutdown(); // Beendet den Thread-Pool
// }
// }