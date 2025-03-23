package tokyslav.multithread;

public class myThread implements Runnable {
    private int threadNumber;

    public myThread(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(i + "from thread" + threadNumber);
            try {
                Thread.sleep((1000));
            } catch (InterruptedException e) {

            }
        }
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