import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main{
    public static void main(String[] args) throws FileNotFoundException {
        String fileName;
        String header;
        BufferedReader validFile;
        ExtractData extraction;
        FileData fileData;
        Election election;
        ResultsData results;
        AuditFile fileCreation;
        //get a valid file from the command line or user input

        while(true){
            File file = new File(fileName);

            if(fileName.equals('q')){
                System.exit(0);
            }
            else if(file.exists() && !file.isDirectory()){
                FileReader fileR = new FileReader(file);
                validFile =
            }
        }

    }
}