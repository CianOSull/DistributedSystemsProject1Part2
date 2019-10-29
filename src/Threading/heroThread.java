package Threading;

import Factory.flyHeroFactory;
import PowerPeople.flyHero;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class heroThread implements  Runnable {
    private flyHeroFactory flyHeroFact = new flyHeroFactory();

    public void run() {
        try {
            System.out.println("Hero Thread has started");

            //Saving of object in a file
            String absolutePath = "/home/cianosullivan/Desktop/CIT/3rd Year/Semester 1/Java projects" +
                    "/DistributedSystemsProject1Part2/src/battleZones/battle.txt";
            FileOutputStream file = new FileOutputStream(absolutePath);
            ObjectOutputStream out = new ObjectOutputStream(file);

            flyHero newHero = flyHeroFact.getHero();
            out.writeObject(newHero);

            System.out.println("The hero has been put in the battle file to stop the villain");
        }
        catch (Exception e) {
            // printStackTrace method
            e.printStackTrace();
            // Prints what exception has been thrown
            System.out.println(e);
        }
    }

}
