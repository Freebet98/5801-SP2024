import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Runs the program
 * 
 * @author
 * @author
 * @author
 * 
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "";
        String header;
        BufferedReader validFile;
        ExtractData extraction;
        FileData fileData;
        Election election;
        ResultsData results;
        AuditFile fileCreation;
        // get a valid file from the command line or user input

        /*
         * While the user has not inputted a valid file name, continue to prompt them
         * for a new
         * filename or for the letter 'q', the letter 'q' if entered will quit the
         * entire system
         */
        try {
            while (true) {
                File file = new File(fileName);

                if (fileName.equals("q")) {
                    System.exit(0);
                } else if (file.exists() && !file.isDirectory()) {
                    FileReader fileR = new FileReader(file);
                    validFile = new BufferedReader(fileR);
                    header = validFile.readLine();

                    // finish creating extraction depending on which type of election it is
                    if (header.equals("OPL")) {
                        extraction = new ExtractDataOPL(validFile, header);
                        break;
                    } else if (header.equals("CPL")) {
                        extraction = new ExtractDataCPL(validFile, header);
                        break;
                    }
                } else {
                    // prompt for new file name or "q"
                }
            }

            // Extracts information into fileData
            fileData = extraction.extractFromFile();

            // finish creating election depending on which election needs to run
            if (header.equals("OPL")) {
                election = new OPL(fileData);
            } else {
                election = new CPL(fileData);
            }

            results = election.runElection();

            // Finish creating the AuditFile object and print the formatted results to an
            // audit file
            fileCreation = new AuditFile(results);
            fileCreation.printToFile();

            // Display the results to the user
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

}