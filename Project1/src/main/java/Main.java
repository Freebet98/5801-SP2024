import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs the program
 * 
 * @author Bethany Freeman
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String relativePath = "Project1/src/BallotFile/";
        Scanner scan = new Scanner(System.in);
        String fileName = "";
        String filePath = "";
        String header;
        BufferedReader validFile;
        ExtractData extraction;
        FileData fileData;
        Election election;
        ResultsData results;
        AuditFile fileCreation;
        ArrayList<BufferedReader> files = new ArrayList<>();
        // Ballot files must be placed in a folder named BallotFiles within the
        // Project1/src

        if (args.length == 0) {
            System.out.println("Please enter the name of the ballot file: ");
            fileName = scan.nextLine();

        } else if (args.length > 0) {
            for(int i = 0; i < args.length; i++){
                fileName = relativePath + args[i];

                //Create a new file Object
                File tempFile = new File(fileName);
                if(!tempFile.exists() || tempFile.isDirectory()){
                    System.out.println("One of the files entered was incorrect, cannot finish running program");
                    System.exit(0);
                }
                else{
                    FileReader tempFileR = new FileReader(tempFile);
                    BufferedReader tempFileB = new BufferedReader(tempFileR);
                    files.add(tempFileB);
                }
            }

            System.out.println("If you have more ballot files to add please add them");
            System.out.println("Otherwise type \"q\" to quit or \"d\" to indicate you are done entering files");
            fileName = scan.nextLine();
        } else {
            System.out.println("Error, cannot run program.");
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
                filePath = relativePath + fileName;
                File file = new File(filePath);

                if (fileName.equals("q")) {
                    scan.close();
                    System.exit(0);
                } else if(fileName.equals("d")){
                    System.out.println("Generating Results...");

                    if(!files.isEmpty()){
                        validFile = files.get(0);
                        header = validFile.readLine();

                        //Check for matching headers
                        for(int i = 1; i < files.size(); i++){
                            validFile = files.get(i);
                            if(!header.equals(validFile.readLine())){
                                throw new IOException("Two or more files given with non-matching headers");
                            }
                        }

                        if (header.equals("OPL")) {
                            extraction = new ExtractDataOPL(files, header);
                            break;
                        } else if (header.equals("CPL")) {
                            extraction = new ExtractDataCPL(files, header);
                            break;
                        }
                    }
                    else{
                        throw new IOException("Empty files ArrayList, no valid files given");
                    }
                    
                }
                else if (file.exists() && !file.isDirectory()) {
                    FileReader fileR = new FileReader(file);
                    validFile = new BufferedReader(fileR);
                    files.add(validFile);
                } else {
                    System.out.println("Error: Invalid file. Input file must be a valid CSV");
                    System.out.println("Please enter another file name or the letter \"q\" to quit");
                    fileName = scan.nextLine();
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
            System.out.println(results.display());
            System.out.println("Audit File Saved: " + fileCreation.getFileName());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}