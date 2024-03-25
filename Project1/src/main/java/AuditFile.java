import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is used to print all the information obtained after running the
 * election to a file
 * 
 * @author
 */
public class AuditFile {
    private final ResultsData results;

    /**
     * This creates a new AuditFile object
     * 
     * @param results is a ResultsData object, has all the information 
     * from extraction and the run of the election
     */
    AuditFile(ResultsData results) {
        this.results = results;
    }

    /**
     * This will print the results.toString() to a new file 
     * The file will be saved in a directory called AuditFiles
     * 
     * @throws IOException if an error occurs anywhere
     * These are handled in the main.java file
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
        File file = new File(directoryPath, filename);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(results.toString());

            // BufferedWrite is automatically closed at the end of the try-block
        } catch (IOException e) {
            throw new IOException("Something went wrong while trying to write to the file");
        }

    }

    /**
     * This checks to see if a directory path exists
     * 
     * @param directory the File object representing the directory for audit files
     * @return a boolean to indicate if it was successful or unneccesary
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
