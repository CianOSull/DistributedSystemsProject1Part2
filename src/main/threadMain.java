package main;

import Observer.fileObservable;
import Observer.fileObserver;
import Threading.villainThread;

public class threadMain {
    public static void main(String [] args) {
        fileObservable fileOberv = new fileObservable();
        fileObserver fileOb = new fileObserver();
        // Add observer to the class
        fileOberv.addObserver(fileOb);
        villainThread villainTh = new villainThread();
        Thread vilThread = new Thread(villainTh);
        fileOberv.watchDirectory();
        System.out.println("Got past watchdirectory");
        vilThread.start();
    }
}