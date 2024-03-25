import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Runs the program
 * 
 * @author Bethany Freeman
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String fileName = "";
        String header;
        BufferedReader validFile;
        ExtractData extraction;
        FileData fileData;
        Election election;
        ResultsData results;
        AuditFile fileCreation;

        if (args.length == 0) {
            System.out.println("Please enter the name of the ballot file: ");
            fileName = scan.nextLine();

        } else if (args.length == 1) {
            fileName = args[0];
        } else {
            System.out.println("Too many arguments, cannot run program.");
            System.exit(0);
        }

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
                    scan.close();
                    System.exit(0);
                } else if (file.exists() && !file.isDirectory()) {
                    System.out.println("Generating Results...");
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
                    System.out.println("Error: Invalid file. Input file must be a valid CSV");
                    System.out.println("Please enter another file name or the letter \"q\" to quit");
                }
            }
            scan.close();

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