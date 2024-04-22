import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.HashSet;

/**
 * @brief Performs computations specific to OPL elections. Inherits from Election
 * to provide base information which is carried through each type of election. 
 * Main interacts with this class in order to pass a FileData object into it 
 * for it to perform calculations on in order to produce election results.
 * 
 * @author Derrick Dischinger
 */
public class OPL extends Election {
    /**
     * Creates an Object of type OPL, which inherits from Election, this is
     * used to run the election of the passed in information from fileData
     *  
     *  @param fileData         A FileData object containing information from the
     *                          original file passed in
     *  
     *  @param availableSeats   An integer representing the number of available
     *                          seats in the election
     *  
     *  @param remainingVotes   An ArrayList<ArrayList<Object>> mapping of the name 
     *                          of each party to an integer which represents how many 
     *                          votes the party has
     *                          
     *                          This is only representative of how many votes a
     *                          party has at the end of the first round of 
     *                          allocation. The actual ArrayList will be
     *                          of type ArrayList<ArrayList<String, int>>
     *  
     *  @param seatAllocation   An ArrayList<ArrayList<Object>> mapping of the name
     *                          of each party to an array that represents how many
     *                          seats a party got in the first allocation [0] and the
     *                          second allocation [1]. The actual ArrayList will be of 
     *                          type ArrayList<ArrayList<String, int[2]>>
     *  
     *  @param winOrder         An ArrayList<String> showing the ordered list of when 
     *                          a party won a seat. This will help determine which
     *                          candidates get a seat. This is the data from the
     *                          original extraction from the file.
     *  
     *  @param ballots          An integer representing the number of ballots received
     *                          from the inputted fileData object
     *  
     *  @param largestRemainder An integer representing the number of votes required to
     *                          qualify for a seat within the function 'firstAllocation()'
     *  
     *  @throws ArithmeticException Throws an ArithmeticException if the number of
     *                              available seats is 0
     *  
     *                          If there are no available seats, largestRemainder cannot 
     *                          be initialized, and if there are no available seats, an
     *                          election need not take place
     */
    OPL(FileData fileData) {
        this.fileData = fileData;
        this.availableSeats = fileData.getNumberSeats();
        this.remainingVotes = this.deepCopyVotes(fileData.getPartyVotes());
        this.seatAllocation = initializeSeatAllocation();
        this.noCandidates = new HashSet<>();
        this.winOrder = new ArrayList<String>();
        int ballots = fileData.getNumberBallots();
        // assuming this does not need to be error checked as it was stated in clas there will never be an election run if no seats are availabke
        try {
            this.largestRemainder = ballots / availableSeats;
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Divide by 0 error; there are 0 available seats.");
        }
    }

    /**
     *  Runs an OPL election on the content contained in fileData
     *  
     *  @return a ResultsData object which contains all the information from
     *          FileData as well as all the information from running the 
     *          Election for an OPL system
     */ 
    @Override
    public ResultsData runElection() throws IOException {
        firstAllocation();
        secondAllocation();
        candidateRankings(fileData.getPartyCandidates(), fileData.getCandidateVotes());
        this.results = new ResultsDataOPL(this.seatAllocation, this.remainingVotes, this.winOrder, this.fileData);
        return results;
    }

    /**
     *  Ranks the candidates in their respective parties based on the number of 
     *  votes received
     *  
     *  Uses sortCandidates() helper function
     *  
     *  @param partyCandidates  The list of party candidate pairings in a HashMap
     *  
     *  @param candidateVotes   An arrayList of arraylists storing candidate names and
     *                          the number of votes they recieved
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
     *  Sorts the candidates using a custom comparator to order them based on the
     *  number of votes they recieved
     *  
     *  Uses java.util.Comparator and java.util.Collections and getVotes helper
     *  function to do so
     *  
     *  @param candidates       The list of candidates to be sorted by the helper
     *                          function
     *  
     *  @param candidateVotes   The list of votes corresponding to each candidate
     *                          that the candidates list will be sorted by
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
     *  Helper funtion to get the number of votes tied to a specific candidate 
     *  in candidateVotes
     *  
     *  @param candidateVotes The list of name vote pairings
     * 
     *  @param candidate      The name of the given candidate as a String
     * 
     *  @return an int representing the number of votes, or -1 if 
     *          the candidate is not found
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

