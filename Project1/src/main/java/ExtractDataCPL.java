import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExtractDataCPL extends ExtractData {
    /**
     * This creates an object of the ExtractDataCPL class
     *
     * @param validFile this the bufferedReader used to parse through the given
     *                  file
     * @param header    this is the header depicting which type of election it
     *                  is. This is found in the Main class and passed in
     * @throws IOException will happen if there is an error in reading the file
     */
    ExtractDataCPL(BufferedReader validFile, String header) throws IOException {
        this.validFile = validFile;
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
     */
    @Override
    protected void formatBallotInformation(ArrayList<ArrayList<Object>> partyVotes,
            ArrayList<ArrayList<Object>> candidateVotes) throws IOException {
        String line = validFile.readLine();
        char[] splitLine;
        int index = -1;
        int count = 0;

        // Check for the EOF
        while (line != null) {
            line.trim();
            splitLine = line.toCharArray();

            for (int i = 0; i < splitLine.length; i++) {
                if (splitLine[i] == '1') {
                    index = i;
                    count = (int) partyVotes.get(index).get(1);
                    count += 1;
                    partyVotes.get(index).set(1, count);
                }
            }

            line = validFile.readLine();
        }
    }
}
