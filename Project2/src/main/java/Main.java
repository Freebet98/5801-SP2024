import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs the program
 * 
 * @author Bethany Freeman
 */
public class Main {
    public static void main(String[] args) {
        // Relative path to where all ballot files are kept
        String relativePath = "Project1/src/BallotFile/";
        Scanner scan = new Scanner(System.in);
        String fileName = "";
        ExtractData extraction = null;
        // Empty ArrayList of bufferedReader objects for multiple files
        ArrayList<BufferedReader> files = new ArrayList<>();

        // try catch block for user input, will catch all IOExceptions
        try {
            if (args.length == 0) { // No arguments given in the command line
                System.out.println("Please enter the name of the ballot file: ");
                fileName = scan.nextLine();
            } else { // Arguments given in the command line
                for (String arg : args) {
                    String filePath = relativePath + arg;
                    File file = new File(filePath);
                    if (!file.exists() || file.isDirectory()) { // A file given in arguments does not properly exist
                        System.out.println("One of the files entered was incorrect, cannot finish running program");
                        System.exit(0);
                    } else { // A file given in arguments does properly exist
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        files.add(reader);
                    }
                }
                System.out.println("If you have more ballot files to add please add them");
                System.out.println("Otherwise type \"q\" to quit or \"d\" to indicate you are done entering files");
                fileName = scan.nextLine();
            }

            while (!fileName.equals("q")) { // While the user does not want to quit
                if (fileName.equals("d")) { // The user no longer has files to add
                    System.out.println("Generating Results...");
                    if (!files.isEmpty()) { // If there are files to read
                        BufferedReader firstFile = files.get(0);
                        firstFile.mark(10);
                        String header = firstFile.readLine();

                        int index = 0;
                        for (BufferedReader file: files) { //Go through every file in the arraylist and check for matching headers
                            if(index != 0){
                                if (!header.equals(file.readLine())) {
                                    throw new IOException("Two or more files given with non-matching headers");
                                } 
                            }
                            
                            index++;
                        }
                        
                        firstFile.reset();

                        if (header.equals("OPL")) {
                            extraction = new ExtractDataOPL(files, header);
                            break;
                        } else if (header.equals("CPL")) {
                            extraction = new ExtractDataCPL(files, header);
                            break;
                        } else if (header.equals("MPO")) {
                            extraction = new ExtractDataMPO(files, header);
                            break;
                        } else {
                            throw new IOException("Header does not correspond to an Election type");
                        }
                    } else {
                        throw new IOException("Empty files ArrayList, no valid files given");
                    }
                } else {
                    String filePath = relativePath + fileName;
                    File file = new File(filePath);
                    if (file.exists() && !file.isDirectory()) {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        files.add(reader);
                    } else {
                        System.out.println("Error: Invalid file. Input file must be a valid CSV");
                    }
                }
                System.out.println(
                        "Please enter another file name, the letter \"q\" to quit, or the letter \"d\" to indicate you have no more files to input");
                fileName = scan.nextLine();
            }

            scan.close();

            if (extraction != null) {
                FileData fileData;
                if(extraction.header.equals("MPO") || extraction.header.equals("MV")) {
                    fileData = extraction.extractFromFile(true);
                }
                else if(extraction.header.equals("OPL") || extraction.header.equals("CPL")){
                    fileData = extraction.extractFromFile();
                }
                else {
                    throw new IOException("Header does not correspond to an Election type");
                }
                Election election;
                if (extraction.header.equals("OPL")) {
                    election = new OPL(fileData);
                } else if (extraction.header.equals("CPL")) {
                    election = new CPL(fileData);
                } else if (extraction.header.equals("MPO")) {
                    election = new MPO(fileData);
                } //else if (extraction.header.equals("MV")) {
                    //election = new MV(fileData);
                //}
                else {
                    throw new IOException("Header does not correspond to an Election type");
                }

                ResultsData results = election.runElection();
                AuditFile fileCreation = new AuditFile(results);
                fileCreation.printToFile();

                System.out.println(results.display());
                System.out.println("Audit File Saved: " + fileCreation.getFileName());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            // Closing all buffered readers
            for (BufferedReader br : files) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}