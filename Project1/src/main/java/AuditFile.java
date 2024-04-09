import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *  This class is used to print all the information obtained after running the
 *  election to a file
 *  
 *  @author Bethany Freeman
 */ 
public class AuditFile {
    private final ResultsData results;
    private File file;

    /**
     *  This creates a new AuditFile object
     *  
     *  @param results   ResultsData object which contains all of the 
     *                  information from extraction and the results of 
     *                  the election
     */
    AuditFile(ResultsData results) {
        this.results = results;
    }

    public String getFileName(){
        return file.getAbsolutePath();
    }

    /**
     *  Print the results of an election to a newly generated file
     *  
     *  The file will be saved in a directory called 'AuditFiles'
     *  
     *  @throws IOException If an error occurs anywhere. This would
     *                      be handled in Main.java
     */
    public void printToFile() throws IOException {
        // Directory stuff
        // Specify the directory to place file in
        String userHome = System.getProperty("user.home");

        // Specify relative path to AuditFiles directory
        String directoryPath = "Documents/AuditFiles/";

        // Create a file object to represent the documents directory
        File auditFileDir = new File(userHome, directoryPath);

        if (!checkDirectory(auditFileDir)) {
            throw new IOException("Directory doesn't exist and cannot be created");
        }

        // Get current system time in milliseconds
        long currentTimeMillis = System.currentTimeMillis();

        // Create a File Object with a FileName
        // electionType_Election_Results_Systime.ext
        String filename = results.getElectionType() + "_Election_Results_" + currentTimeMillis + ".txt";
        file = new File(auditFileDir, filename);
        
        if(!file.exists()){
            file.createNewFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(results.toString());
           // BufferedWrite is automatically closed at the end of the try-block
        } catch (IOException e) {
            throw new IOException("Something went wrong while trying to write to the file");
        }

    }

    /**
     *  Checks to see if a directory path exists
     *  
     *  @param directory File object representing the directory for audit files
     *  
     *  @return a boolean to indicate if it was successful or unneccesary
     */
    private boolean checkDirectory(File directory) {
        if (!directory.exists()) { // If the directory doesn't exist
            if (directory.mkdirs()) {
                return true; // directory created
            } else {
                return false; // directory not created, some problem
            }
        } else if (directory.exists()) { // directory already exists
            return true;
        }

        return false; // This is only here to make java happy
    }

}

       
