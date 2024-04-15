import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used to extract data from the file for the MPO election
 * 
 * @author TODO
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
        // TODO
    }
    
}
