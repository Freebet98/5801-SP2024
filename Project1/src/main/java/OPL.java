/**
 * This class is used to run an OPL election
 * @Derrick 
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;

/**
 * This class is used to run an OPL election
 * 
 * @author Derrick Dischinger
 */
public class OPL extends Election {
    /**
     * Creates an Object of type OPL, which inherits from Election, this is
     * used to run the election of the passed in information from fileData
     * 
     * @param fileData a FileData object containing information from the
     *                 original file passed in
     */
    OPL(FileData fileData) {
        this.fileData = fileData;
        this.availableSeats = fileData.getNumberSeats();
        this.remainingVotes = new ArrayList<ArrayList<Object>>();
        this.seatAllocation = new ArrayList<ArrayList<Object>>();
        this.winOrder = new ArrayList<String>();
        int ballots = fileData.getNumberBallots();
        // assuming this does not need to be error checked as it was stated in clas there will never be an election run if no seats are availabke
        this.largestRemainder = ballots / availableSeats;
    }

    /**
     * Runs an OPL election on the content contained in fileData
     * 
     * @return ResultsData which contains all the information from FileData
     *         as well as all the information from running the Election for
     *         an OPL system
     * @throws IOException 
     */
    @Override
    public ResultsData runElection() {
        firstAllocation();
        secondAllocation();
        candidateRankings(fileData.getPartyCandidates(), fileData.getCandidateVotes());
        this.results = new ResultsDataOPL(this.seatAllocation, this.remainingVotes, this.winOrder, this.fileData);
        return results;
    }

    /**
     * ranks the candidates in thier respective parties based on their votes
     * recieved
     * uses sortCandidates() helper function
     * 
     * @param partyCandidates the list of party candidate pairings in a HashMap
     * @param candidateVotes  An arrayList of arraylists storing candidate names and
     *                        the number of votes they recieved
     */
    private void candidateRankings(HashMap<String, ArrayList<String>> partyCandidates,
            ArrayList<ArrayList<Object>> candidateVotes) {
        for (String party : partyCandidates.keySet()) {
            ArrayList<String> candidates = partyCandidates.get(party);
            sortCandidates(candidates, candidateVotes);
            partyCandidates.put(party, candidates);
        }
        return;
    }

    /**
     * Sorts the candidates using a custom comparator to order them based on the
     * number of votes
     * they recieved. Uses java.util.Comparator and java.util.Collections and
     * getVotes helper function to do so
     * 
     * @param candidateVotes the list of candidates to be sorted by the helper
     *                       function
     */
    private void sortCandidates(ArrayList<String> candidates, ArrayList<ArrayList<Object>> candidateVotes) {
        // Define custom comparator function to sort candidates based on number of votes
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String candidate1, String candidate2) {
                int votesCandidate1 = getVotes(candidateVotes, candidate1);
                int votesCandidate2 = getVotes(candidateVotes, candidate2);
                return votesCandidate2 - votesCandidate1;
            }
        };

        // Call custom comparator with sort
        Collections.sort(candidates, comparator);
    }

    /**
     * Helper funtion to get the number of votes tied to
     * a specific candidate in candidateVotes
     * 
     * @param candidateVotes the list of name vote pairings
     * @param candidate      the name of the given candidate as a String
     * @return returns the int (number of votes) or -1 if not found
     */
    private int getVotes(ArrayList<ArrayList<Object>> candidateVotes, String candidate) {
        for (ArrayList<Object> entry : candidateVotes) { // loop through entrys to compare to candidate name
            if (entry.get(0).equals(candidate)) {
                return (int) entry.get(1); // return the first index of the inner Arraylist as its the number of votes
                                           // for that candidate
            }
        }
        return -1; // if nothing is found then return a default value, -1 in this case since you
                   // can never have negative votes
    }
}
