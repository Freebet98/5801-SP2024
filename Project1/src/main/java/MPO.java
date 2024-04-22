import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @brief Performs computations specific to MPO elections. Inherits from Election
 * to provide base information which is carried through each type of election. 
 * Main interacts with this class in order to pass a FileData object into it 
 * for it to perform calculations on in order to produce election results.
 * 
 * @author Derrick Dischinger
 */
public class MPO extends Election {
    /**
     * Creates an Object of type MPO, which inherits from Election, this is
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
     *                          of each candidate to an array that represents if they
     *                          got a seat in the first allocation [0] or the
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
    MPO(FileData fileData) {

        // Pseudo code
        // filedate
        // availableSeats = fileData.getNumberSeats()
        // remainingVotes = deepCopy of fileData.getCandidateVotes() //The election
        // allocates seats to candidates not parties
        // winOrder = new ArrayList<string>
        // noCandidates = new HashSet

        // Calculate largestRemainder
        // Check for division by zero, tests for this are unneccesary though just a good
        // thing to have

        this.fileData = fileData;
        this.availableSeats = fileData.getNumberSeats();
        this.remainingVotes = this.deepCopyVotes(fileData.getCandidateVotes());
        this.seatAllocation = initializeSeatAllocation();
        this.noCandidates = new HashSet<>();  // never used just makes it work well with election.java
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
     *  Runs an MPO election on the content contained in fileData
     *  
     *  @return a ResultsData object which contains all the information from
     *          FileData as well as all the information from running the 
     *          Election for an MPO system
     */ 
    @Override
    public ResultsData runElection() throws IOException {
        // Pseudo code
        firstAllocation();
        secondAllocation();
        this.results = new ResultsDataMPO(this.seatAllocation, this.remainingVotes, this.winOrder, this.fileData);
        return results;
    }

    /**
     * initializes the seat allocation array to have default values of 0
     * puts candidate names in the array rather than party names as parties do not matter form MPO seat allocation
     * 
     * @return returns initialized ArrayList<ArrayList<Object>>
     */

    @Override
    protected ArrayList<ArrayList<Object>> initializeSeatAllocation() {
        // Pseudo code
        // initialize ArrayList<ArrayList<Object>> initialized
        // for 0 to fileData.getCandidateVotes.size
        //  ArrayList innerList
        //  String candidateName = fileData.getCandidateVotes.get(i).get(0)
        //  innerList.add(candidateName)
        //  innerList.add(new int[2])
        //  initialized.add(innerList)
        // return initialized

        ArrayList<ArrayList<Object>> initialized = new ArrayList<ArrayList<Object>>();
        for (int i = 0; i < this.fileData.getCandidateVotes().size(); i++) {
            ArrayList<Object> innerList = new ArrayList<Object>();
            String candidateName = (String) this.fileData.getCandidateVotes().get(i).get(0);
            innerList.add(candidateName);
            innerList.add(new int[2]);
            initialized.add(innerList);
        }
        return initialized;
    }

    /**
     * Overridden checkNoCandidates in election as party affiliation doesnt
     * matter in MPO. Added a remainingVotes set to -1 as there is no need for remaining
     * votes either as each person is idividual. This ensures people cannot get 2 seats
     */

    @Override
    protected void checkNoCandidates(int index) throws IOException {
        remainingVotes.get(index).set(1, -1);
    }

}
