import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used to extract data from the file for the CPL election
 * 
 * @author Bethany Freeman
 */
public class ExtractDataCPL extends ExtractData {

    /**
     * This creates an object of the ExtractDataCPL class
     *
     * @param validFiles this is an ArrayList of BufferedReader Objects representing
     *                   the multiple files given by the user
     * @param header     this is the header depicting which type of election it
     *                   is. This is found in the Main class and passed in
     * @throws IOException will happen if there is an error in reading the file
     */
    ExtractDataCPL(ArrayList<BufferedReader> validFiles, String header) throws IOException {
        this.validFiles = validFiles;
        this.header = header;
    }

    /**
     * this will format the ballots to update the number of votes in partyVotes,
     * candidateVotes in the CPL election type does not get updated since all
     * candidates will have 0 votes personally
     *
     * @param partyVotes     this is an ArrayList<ArrayList<Object>> which contains
     *                       inner mappings of a party name and the number of
     *                       corresponding
     *                       votes
     * @param candidateVotes this is an ArrayList<ArrayList<Object>> whih contains
     *                       inner mappings of a candidate name and the number of
     *                       corresponding votes
     * @throws IOException
     * @throws Exception
     */
    @Override
    protected void formatBallotInformation(ArrayList<ArrayList<Object>> partyVotes,
            ArrayList<ArrayList<Object>> candidateVotes, HashMap<String, ArrayList<String>> partyCandidates, int numSeats)
            throws IOException {
        String line;
        char[] splitLine;
        int index = -1;
        int count = 0;

        // Runs while there are still ballots to be read
        while ((line = validFile.readLine()) != null) {
            line.trim();
            splitLine = line.toCharArray();

            // Checks if the file is in the correct format
            if (line.indexOf('1') == -1 || line.indexOf(',') == -1) {
                throw new IOException("File format is not in the correct format");
            }

            // Updates the number of votes in partyVotes
            for (int i = 0; i < splitLine.length; i++) {
                if (splitLine[i] == '1') {
                    index = i;
                    count = (int) partyVotes.get(index).get(1);
                    count += 1;
                    partyVotes.get(index).set(1, count);
                }
            }
        }
    }
}
