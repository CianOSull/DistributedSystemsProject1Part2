package Threading;

import Factory.flyVillainFactory;
import PowerPeople.flyVillain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class villainThread implements  Runnable {
    private flyVillainFactory flyVillainFact = new flyVillainFactory(); // Create the factory for making villains
    private flyVillain flyVil = flyVillainFact.getVillain(); // Make hte villain
    private String absolutePath = "/home/cianosullivan/Desktop/CIT/3rd Year/Semester 1/Java projects" +
            "/DistributedSystemsProject1Part2/src/battleZones/battle.txt";
    private boolean checkFile;
    private File file = new File(absolutePath);

    public void run() {
        System.out.println("Villain Thread has started");

        createFile();

        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(absolutePath);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(flyVil);
            System.out.println("The villain has been placed into the battle zone");

            out.close();
            file.close();
        }
        catch (Exception e) {
            // printStackTrace method
            e.printStackTrace();
            // Prints what exception has been thrown
            System.out.println(e);
        }
    }

    // This creates the files that the villain will be put into
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

}
