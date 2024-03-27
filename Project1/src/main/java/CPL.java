import java.io.IOException;
import java.util.ArrayList;
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
     * TODO
     * 
     * @param fileData
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
        this.largestRemainder = ballots / this.availableSeats;
    }

    /**
     * TODO
     * 
     * @return
     * @throws IOException 
     */
    @Override
    public ResultsData runElection() throws IOException {
        firstAllocation();
        secondAllocation();
        this.results = new ResultsDataCPL(this.seatAllocation, this.remainingVotes,
                this.winOrder, this.fileData);
        return this.results;
    }
}
