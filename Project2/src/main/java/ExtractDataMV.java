import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used to extract data from the file for the MV election
 * 
 * @author TODO
 * @author Bethany Freeman
 */
public class ExtractDataMV extends ExtractData {
    /**
     * This creates an object of the ExtractDataMV class
     *
     * @param validFiles this is the Arrayist of bufferedReader objects so an
     *                   election can have multiple files
     * @param header     this is the header depicting which type of election it
     *                   is. This is found in the Main class and passed in
     */
    ExtractDataMV(ArrayList<BufferedReader> validFiles, String header) {
        this.validFiles = validFiles;
        this.header = header;
    }

    /**
     * this will format the ballots to update the number of votes in partyVotes, and
     * candidateVotes in the MV election
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
        // TODO: pseudo code
        //String line
        //while(!EOF):
        //line = validFile.readLine();
        //int count = StringUtils.countMatchs(line, '1');
        //if(count > numSeats || count < 1):
        //    throw new exception

        //String[] splitLine = line.trim().split(,)
        //int tempCount = 0
        //for splitLine.length() to 0:
        //    if i != splitLine.length() -1: 
        //        if previousIndex == '1'
        //            continue
        //        else:
        //            if index == '1':
        //                Allocate votes properly
        //                add one to tempCount, break from the for loop if tempCount = count 
        //    else if i == '1':
        //        allocate last index votes

        //follow steps from MPO ExtractData or OPL ExtractData for the rest
    }
}
