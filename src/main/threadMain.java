package main;

import Observer.fileObservable;
import Observer.fileObserver;
import Threading.villainThread;

public class threadMain {
    public static void main(String [] args) {
        // Observer and observable
        fileObservable fileOberv = new fileObservable();
        fileObserver fileOb = new fileObserver();

        // Villain Thread
        villainThread villainTh = new villainThread();

        // Add observer to the class
        fileOberv.addObserver(fileOb);

        // Creating threads
        Thread vilThread = new Thread(villainTh);
        Thread observeThread = new Thread(fileOberv);

        observeThread.start();
        vilThread.start();
    }
}