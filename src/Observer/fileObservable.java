package Observer;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class fileObservable implements Observable {
    private fileObserver fileOb;
    private File battleFile = new File("/home/cianosullivan/Desktop/CIT/3rd Year/Semester 1/Java projects" +
            "/DistributedSystemsProject1Part2/src/battleZones/battle.txt");
    private Thread threadObserver;
    private boolean checkFile;

    public void addObserver(fileObserver observer) {
        this.fileOb = observer;
        threadObserver = new Thread(fileOb);
    }

    private void notifyObservs() {
        threadObserver.start();
    }

    private void createFile(){
        try {
            checkFile = battleFile.createNewFile();
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

    public void watchDirectory() {
        // This watch directory will only be called once so it is fine for the start up
        // Creates a file on startup just to make sure there is something in the directory it can watch
        createFile();
        //checkForFile();
        try {
            // This part here creates the watch service and connects it to the file system
            WatchService watchService = FileSystems.getDefault().newWatchService();

            // This gets the path to the directory being monitored
            Path path = Paths.get("/home/cianosullivan/Desktop/CIT/3rd Year/Semester 1/Java projects" +
                    "/DistributedSystemsProject1Part2/src/battleZones");

            path.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    //process
                    System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
                    if(event.kind() == StandardWatchEventKinds.ENTRY_MODIFY){
                        // False so it makes a villain
                        notifyObservs();
                        //break;
                    }
                    // This creates a hero

                    break;
                }
                key.reset();
            }
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}