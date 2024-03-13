import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class ExtractData {
    protected BufferedReader validFile;
    protected FileData fileData;
    protected String header;

    /**
     * This is used to extract data from the File, assumes all checks done within functions called work
     * @return fileData, this is a FileData object containing the information from the extracted file
     * @throws IOException will throw this exception from readLine() if an error has occurred while
     *                      reading the file
     */
    protected FileData extractFromFile() throws IOException {
        //represents the string obtained from the BufferedReader while reading a line
        String line;

        //Looks at the second line of the file, if it's an integer, numSeats is set to it
        line = validFile.readLine();
        verifyLineIsDigit(line);
        int numSeats = Integer.parseInt(line);

        //Looks at the third line of the file, if it's an integer, numBallots is set to it
        line = validFile.readLine();
        verifyLineIsDigit(line);
        int numBallots = Integer.parseInt(line);

        //Looks at the fourth line of the file, if it's an integer, numParties is set to it
        line = validFile.readLine();
        verifyLineIsDigit(line);
        int numParties = Integer.parseInt(line);

        //Create two new ArrayList<ArrayList<Object>> to store partyVotes and candidateVotes
        //Initialize partyCandidates with formatPartyInformation
        ArrayList<ArrayList<Object>> partyVotes = new ArrayList<>();
        ArrayList<ArrayList<Object>> candidateVotes = new ArrayList<>();
        HashMap<String, ArrayList<String>> partyCandidates = formatPartyInformation(numParties);

        //Values for these get set in formatBallotInformation
        formatBallotInformation(partyVotes, candidateVotes);

        fileData = new FileData(header, numSeats, numBallots, numParties, partyCandidates, partyVotes, candidateVotes);
        return fileData;
    }

    /**
     * This will look through the given string, if it finds a character that is not a digit a message will
     * be given to the user and the program will close with a system.exit()
     * @param line represents a string of the line from the file. This line should only be digits
     */
    protected void verifyLineIsDigit(String line){
        //Goes through every character in line, looks to see if it's a digit
        for(int i = 0; i < line.length(); i++){
            if(!Character.isDigit(line.charAt(i))){
                System.out.println("Election File is invalid, the program will close now.");
                System.exit(0);
            }
        }
    }

    /**
     * This method will read as many lines as there are number of parties in the file, the first word
     * in the line will be used as the key in the HashMap and the rest beyond the first , will be
     * added to the Arraylist of string, which represents the list of candidates for a party
     * @param numParties this represents the number of parties that are listed in the given file
     * @return HashMap<String, ArrayList<String>> that represents a key value of a party name
     *          to a list of candidate names
     * @throws IOException if there is an error while reading the validFile
     */
    protected HashMap<String, ArrayList<String>> formatPartyInformation(int numParties) throws IOException {
        HashMap<String, ArrayList<String>> partyCandidates = new HashMap<>();
        String line;
        String[] splitLine;

        //Reads a new line for the number of parties there are
        for(int i = 0; i < numParties; i++){
            //reads a file and splits the words into a string[] and also trims the whitespace
            line = validFile.readLine();
            splitLine = line.trim().split(",");

            //Create new arraylist to act as an inner arrayList for partyVotes
            //adds the party name and the number 0 to the Arraylist partyVotes
            ArrayList<Object> partyInner = new ArrayList<>();
            partyInner.add(splitLine[0]);
            partyInner.add(0);

            //Create new arraylist for list of candidates in a party
            ArrayList<String> candidates = new ArrayList<>();


        }

        return partyCandidates;
    }

    /**
     * TODO
     * @param partyVotes
     * @param candidateVotes
     */
    abstract protected void formatBallotInformation(ArrayList<ArrayList<Object>> partyVotes,
                                                    ArrayList<ArrayList<Object>> candidateVotes);

}
