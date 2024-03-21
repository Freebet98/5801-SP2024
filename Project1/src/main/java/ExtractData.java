/**
 * This class is used to extract data from the file
 * @author Bethany Freeman
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class ExtractData {
    protected BufferedReader validFile;
    protected FileData fileData;
    protected String header;

    /**
     * This is used to extract data from the File, assumes all checks done within
     * functions called work
     *
     * @return fileData, this is a FileData object containing the information from
     *         the extracted file
     * @throws IOException 
     * @throws Exception 
     */
    protected FileData extractFromFile() throws IOException {
        // represents the string obtained from the BufferedReader while reading a line
        String line;

        // Looks at the second line of the file, if it's an integer, numSeats is set to
        // it
        line = validFile.readLine();
        if(!verifyLineIsDigit(line)){
            return null;
        }
        int numSeats = Integer.parseInt(line);

        // Looks at the third line of the file, if it's an integer, numBallots is set to
        // it
        line = validFile.readLine();
        if(!verifyLineIsDigit(line)){
            return null;
        }
        int numBallots = Integer.parseInt(line);

        // Looks at the fourth line of the file, if it's an integer, numParties is set
        // to it
        line = validFile.readLine();
        if(!verifyLineIsDigit(line)){
            return null;
        }
        int numParties = Integer.parseInt(line);

        // Create two new ArrayList<ArrayList<Object>> to store partyVotes and
        // candidateVotes
        // Initialize partyCandidates with formatPartyInformation
        ArrayList<ArrayList<Object>> partyVotes = new ArrayList<>();
        ArrayList<ArrayList<Object>> candidateVotes = new ArrayList<>();
        HashMap<String, ArrayList<String>> partyCandidates = formatPartyInformation(numParties, partyVotes,
                candidateVotes);

        // Values for these get set in formatBallotInformation
        formatBallotInformation(partyVotes, candidateVotes, partyCandidates);

        fileData = new FileData(header, numSeats, numBallots, numParties, partyCandidates, partyVotes, candidateVotes);
        return fileData;
    }

    /**
     * This will look through the given string, if it finds a character that is not
     * a digit a message will
     * be given to the user and the program will close with a system.exit()
     *
     * @param line represents a string of the line from the file. This line should
     *             only be digits
     */
    protected boolean verifyLineIsDigit(String line) {
        if (line == "" || line == null) {
            return false;
        }
        line = line.trim();

        // Goes through every character in line, looks to see if it's a digit
        for (int i = 0; i < line.length(); i++) {
            if (!Character.isDigit(line.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method will read as many lines as there are number of parties in the
     * file, the first word
     * in the line will be used as the key in the HashMap and the rest beyond the
     * first , will be
     * added to the Arraylist of string, which represents the list of candidates for
     * a party
     *
     * @param numParties this represents the number of parties that are listed in
     *                   the given file
     * @return HashMap<String, ArrayList < String>> that represents a key value of a
     *         party name
     *         to a list of candidate names
     * @throws IOException if there is an error while reading the validFile
     */
    protected HashMap<String, ArrayList<String>> formatPartyInformation(int numParties,
            ArrayList<ArrayList<Object>> partyVotes,
            ArrayList<ArrayList<Object>> candidateVotes)
            throws IOException {
        HashMap<String, ArrayList<String>> partyCandidates = new HashMap<>();
        String line;
        String[] splitLine;
        String partyName;
        ArrayList<Object> partyInner;
        ArrayList<Object> candidateInner;
        ArrayList<String> candidates;

        /*
         * This for loop reads a single line from validFile and uses the information
         * in this string to get the matching of a party to its candidates list
         * as well as initialize partyVotes and candidateVotes with the names and
         * the number 0
         */
        for (int i = 0; i < numParties; i++) {
            // reads a file and splits the words into a string[] and also trims the
            // whitespace
            line = validFile.readLine();
            splitLine = line.trim().split(",");
            partyName = splitLine[0];

            // Gets the candidates from splitLine and adds them to the candidate
            // arraylist, also adds them to candidateInner which is used for
            // candidateVotes
            candidates = new ArrayList<>();
            for (int k = 1; k < splitLine.length; k++) {
                candidates.add(splitLine[k]);

                // Initialize candidateVotes with an arrayList of candidateName and 0
                candidateInner = new ArrayList<>();
                candidateInner.add(splitLine[k]);
                candidateInner.add(0);
                candidateVotes.add(candidateInner);
            }

            // Initialize partyVotes with an arraylist of partyName and 0
            partyInner = new ArrayList<>();
            partyInner.add(partyName);
            partyInner.add(0);
            partyVotes.add(partyInner);

            partyCandidates.put(partyName, candidates);
        }

        return partyCandidates;
    }

    /**
     * This is an abstract method that will be initialized in ExtractDataOPL and
     * ExtractDataCPL. When initialized, this method will format the ballots to
     * update the number of votes in partyVotes and in OPL will also update the
     * number of votes in candidateVotes
     *
     * @param partyVotes     is a mapping of multiple party names to their
     *                       corresponding
     *                       number of votes
     * @param candidateVotes is a mapping of multiple candidate names to their
     *                       corresponing number of votes
     * @param partyCandidates 
     * @throws Exception 
     */
    abstract protected void formatBallotInformation(ArrayList<ArrayList<Object>> partyVotes,
            ArrayList<ArrayList<Object>> candidateVotes, HashMap<String, ArrayList<String>> partyCandidates) throws IOException;

}
