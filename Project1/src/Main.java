import java.io.*;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main{
    public static void main(String[] args) throws IOException {
        String fileName = "";
        String header;
        BufferedReader validFile;
        ExtractData extraction;
        FileData fileData;
        Election election;
        ResultsData results;
        AuditFile fileCreation;
        //get a valid file from the command line or user input

        /*While the user has not inputted a valid file name, continue to prompt them for a new
        * filename or for the letter 'q', the letter 'q' if entered will quit the entire system
        */
        while(true){
            File file = new File(fileName);

            if(fileName.equals("q")){
                System.exit(0);
            }
            else if(file.exists() && !file.isDirectory()){
                FileReader fileR = new FileReader(file);
                validFile = new BufferedReader(fileR);
                header = validFile.readLine();

                //finish creating extraction depending on which type of election it is
                if(header.equals("OPL")){
                    extraction = new ExtractDataOPL(validFile, header);
                    break;
                }
                else if(header.equals("CPL")){
                    extraction = new ExtractDataCPL(validFile, header);
                    break;
                }
            }
            else{
                //prompt for new file name or "q"
            }
        }

        //Extracts information into fileData
        fileData = extraction.extractFromFile();

        //finish creating election depending on which election needs to run
        if(header.equals("OPL")){
            election = OPL(fileData);
        }
        else{
            election = CPL(fileData);
        }

        results = election.runElection();

        //Finish creating the AuditFile object and print the formatted results to an audit file
        fileCreation = AuditFile(results);
        fileCreation.PrintToFile();

        //Display the results to the user
    }
}