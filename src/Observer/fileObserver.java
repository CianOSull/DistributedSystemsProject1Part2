package Observer;

import Factory.flyHeroFactory;
import Threading.heroThread;

import java.io.File;
import java.io.IOException;

public class fileObserver implements Observer, Runnable {
    private flyHeroFactory flyHeroFact = new flyHeroFactory();
    private String absolutePath = "/home/cianosullivan/Desktop/CIT/3rd Year/Semester 1/Java projects/" +
            "DistributedSystemsProject1Part2/src/battleZones/battle.txt";
    private boolean checkFile;
    private File file = new File(absolutePath);
    private int fileCounter = 1;
    private heroThread heroThread = new heroThread();

    public void run(){
        System.out.println("Observer Thread has started");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        update();
    }

    private void createFile(){
        try {
            checkFile = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(checkFile){
            System.out.println("Created a new file");
        }
        else{
            System.out.println("File already present at the specified location");
        }
    }

    private void update() {
        // this creates a thread that creates a hero and puts it into the file
        Thread threadCreateHero = new Thread(heroThread);
        threadCreateHero.start();
        // This should move the file to a new location
        moveFile();
    }

    private void moveFile(){
        checkFile = file.renameTo(new File("//home/cianosullivan/Desktop/CIT/3rd Year/Semester 1" +
                "/Java projects/DistributedSystemsProject1Part2/src/battleDone/battle" + fileCounter + ".txt"));
        if(checkFile){
            System.out.println("Was successful");
            // Create a new file to be used
            createFile();
            fileCounter++;
        }
        else {
            System.out.println("Failed");
        }
    }
}
