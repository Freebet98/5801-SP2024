import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used to extract data from the file for the MPO election
 * 
 * @author Rock Zgutowicz
 * @author Bethany Freeman
 */
public class ExtractDataMPO extends ExtractData{

    /**
     * This creates an object of the ExtractDataMPO class
     *
     * @param validFiles this is the Arrayist of bufferedReader objects so an
     *                   election can have multiple files
     * @param header     this is the header depicting which type of election it
     *                   is. This is found in the Main class and passed in
     */
    ExtractDataMPO(ArrayList<BufferedReader> validFiles, String header){
        this.validFiles = validFiles;
        this.header = header;
    }
    
    /**
     * this will format the ballots to update the number of votes in partyVotes, and
     * candidateVotes in the MPO election
     *
     * @param partyVotes      this is an ArrayList<ArrayList<Object>> which contains
     *                        inner mappings of a party name and the number of
     *                        corresponding
     *                        votes
     * @param candidateVotes  this is an ArrayList<ArrayList<Object>> whih contains
     *                        inner mappings of a candidate name and the number of
     *                        corresponding votes
     * @param partyCandidates this is a mapping of a party name to an ordered list
     *                        of their candidates
     * @throws IOException
     */
    @Override
    protected void formatBallotInformation(ArrayList<ArrayList<Object>> partyVotes,
            ArrayList<ArrayList<Object>> candidateVotes, HashMap<String, ArrayList<String>> partyCandidates)
            throws IOException {
        String line;
        char[] splitLine;
        int index = -1;
        int curCount = 0;
        int count = 0;

        // tempCount is used to count the number of votes in candidateVotes in the
        // current file being read
        ArrayList<Integer> tempCount = new ArrayList<>();
        for (int i = 0; i < candidateVotes.size(); i++) {
            tempCount.add(0);
        }

        // Check for the EOF
        while ((line = validFile.readLine()) != null) {
            line.trim();
            splitLine = line.toCharArray();

            // Check for correct file format
            if (line.indexOf('1') == -1 || line.indexOf(',') == -1) {
                throw new IOException("File format is not in the correct format");
            }

            // Gets index in splitLine and adds 1 vote to it
            for (int i = 0; i < splitLine.length; i++) {
                if (splitLine[i] == '1') {
                    index = i;
                    curCount = (int) tempCount.get(index);
                    curCount += 1;
                    tempCount.set(index, curCount);
                    count = (int) candidateVotes.get(index).get(1);
                    count += 1;
                    candidateVotes.get(index).set(1, count);
                }
            }

        }

        // Gathers the votes from candidateVotes and place in partyVotes
        for (int i = 0; i < candidateVotes.size(); i++) {
            String candidateName = (String) candidateVotes.get(i).get(0);
            putVotesInPartyVotes(partyVotes, candidateVotes, partyCandidates, candidateName, tempCount, i);
        }
    }
    
}