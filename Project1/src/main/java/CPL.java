import java.util.ArrayList;

/**
 * This class is used to run a CPL election
 * 
 * @author
 */
public class CPL extends Election {
    /**
     * TODO
     * 
     * @param fileData
     */
    CPL(FileData fileData) {
        this.fileData = fileData;
        availableSeats = fileData.getNumberSeats();

        this.remainingVotes = new ArrayList<ArrayList<Object>>();
        this.seatAllocation = new ArrayList<ArrayList<Object>>();
        this.winOrder = new ArrayList<String>();

        // largestRemainder calculation
        int ballots = fileData.getNumberBallots();
        this.largestRemainder = ballots / this.availableSeats;
    }

    /**
     * TODO
     * 
     * @return
     */
    @Override
    public ResultsData runElection() {
        firstAllocation();
        secondAllocation();
        this.results = new ResultsDataCPL(this.seatAllocation, this.remainingVotes,
                this.winOrder, this.fileData);
        return this.results;
    }
}
