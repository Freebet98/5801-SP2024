import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Performs computations specific to CPL elections. Inherits from Election
 * to provide base information which is carried through each type of election.
 * Main interacts with this class in order to pass a FileData object into it
 * for it to perform calculations on in order to produce election results.
 * 
 * @author Rock Zgutowicz
 */
public class CPL extends Election {
    /**
     * Creates an Object of type CPL, which inherits from Election, this is
     * used to run the election of the passed in information from fileData
     * 
     * @param fileData         A FileData object containing information from the
     *                         original file passed in
     * 
     * @param availableSeats   An integer representing the number of available
     *                         seats in the election
     * 
     * @param remainingVotes   An ArrayList<ArrayList<Object>> mapping of the name
     *                         of each party to an integer which represents how many
     *                         votes the party has
     * 
     *                         This is only representative of how many votes a
     *                         party has at the end of the first round of
     *                         allocation. The actual ArrayList will be
     *                         of type ArrayList<ArrayList<String, int>>
     * 
     * @param seatAllocation   An ArrayList<ArrayList<Object>> mapping of the name
     *                         of each party to an array that represents how many
     *                         seats a party got in the first allocation [0] and the
     *                         second allocation [1]. The actual ArrayList will be
     *                         of
     *                         type ArrayList<ArrayList<String, int[2]>>
     * 
     * @param winOrder         An ArrayList<String> showing the ordered list of when
     *                         a party won a seat. This will help determine which
     *                         candidates get a seat. This is the data from the
     *                         original extraction from the file.
     * 
     * @param ballots          An integer representing the number of ballots
     *                         received
     *                         from the inputted fileData object
     * 
     * @param largestRemainder An integer representing the number of votes required
     *                         to
     *                         qualify for a seat within the function
     *                         'firstAllocation()'
     * 
     * @throws ArithmeticException Throws an ArithmeticException if the number of
     *                             available seats is 0
     * 
     *                             If there are no available seats, largestRemainder
     *                             cannot
     *                             be initialized, and if there are no available
     *                             seats, an
     *                             election need not take place
     */
    CPL(FileData fileData) {
        this.fileData = fileData;
        this.availableSeats = fileData.getNumberSeats();

        this.remainingVotes = this.deepCopyVotes(fileData.getPartyVotes());
        this.seatAllocation = initializeSeatAllocation();
        this.winOrder = new ArrayList<String>();
        this.noCandidates = new HashSet<>();

        // largestRemainder calculation
        int ballots = fileData.getNumberBallots();
        // assuming this does not need to be error checked as it was stated in class
        // there will never be an election run if no seats are availabke
        try {
            this.largestRemainder = ballots / availableSeats;
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Divide by 0 error; there are 0 available seats.");
        }
    }

    /**
     * Runs an CPL election on the content contained in fileData
     * 
     * @return a ResultsData object which contains all the information from
     *         FileData as well as all the information from running the
     *         Election for an CPL system
     */
    @Override
    public ResultsData runElection() throws IOException {
        firstAllocation();
        secondAllocation();
        this.results = new ResultsDataCPL(this.seatAllocation, this.remainingVotes, this.winOrder, this.fileData);
        return results;
    }

}
