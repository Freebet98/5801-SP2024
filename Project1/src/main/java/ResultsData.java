import java.util.ArrayList;

/**
 * This class is used to store all the information obtained after running the
 * election
 * 
 * @author Bethany Freeman
 */
abstract public class ResultsData extends FileData {
    protected final ArrayList<ArrayList<Object>> seatAllocation;
    protected final ArrayList<ArrayList<Object>> remainingVotes;
    private final ArrayList<String> partyWinOrder;
    private final ArrayList<ArrayList<Object>> finalWinOrder;
    protected final FileData fileData;

    /**
     * This creates an object of ResultsData which is used to store the information
     * obtained after running the election,
     * inherits from FileData, so will have access to all of FileData's methods
     * 
     * @param seatAllocation This is a mapping of the name of each party to an array
     *                       that represents how many seats
     *                       a party got in the first allocation [0] and the second
     *                       allocation [1]. the actual
     *                       ArrayList will be of type ArrayList<ArrayList<String,
     *                       int[2]>>
     * @param remainingVotes This is a mapping of the name of each party to an int
     *                       that represents how many votes the
     *                       party has, this is only representative of how many
     *                       votes a party has at the end of the
     *                       first round of allocation. the actual ArrayList will be
     *                       of type
     *                       ArrayList<ArrayList<String, int>>
     * @param partyWinOrder  This is an Arraylist showing the ordered list of when a
     *                       party won a seat, this will
     *                       help determine which candidates get a seat
     * @param fileDate       this is the data from the original extraction from the
     *                       file
     */
    ResultsData(ArrayList<ArrayList<Object>> seatAllocation, ArrayList<ArrayList<Object>> remainingVotes,
            ArrayList<String> partyWinOrder, FileData fileDate) {
        super(fileDate.electionType, fileDate.numberSeats, fileDate.numberBallots, fileDate.numberParties,
                fileDate.partyCandidates, fileDate.partyVotes, fileDate.candidateVotes);
        this.seatAllocation = seatAllocation;
        this.remainingVotes = remainingVotes;
        this.partyWinOrder = partyWinOrder;
        this.fileData = fileDate;
        finalWinOrder = new ArrayList<ArrayList<Object>>();
        computeWinOrder();
    }

    /**
     * @return an ArrayList<ArrayList<Object>>
     */
    public ArrayList<ArrayList<Object>> getSeatAllocation() {
        return seatAllocation;
    }

    /**
     * @return an ArrayList<ArrayList<Object>>
     */
    public ArrayList<ArrayList<Object>> getRemainingVotes() {
        return remainingVotes;
    }

    /**
     * @return an ArrayList<ArrayList<Object>>
     */
    public ArrayList<ArrayList<Object>> getFinalWinOrder() {
        return finalWinOrder;
    }

    /**
     * @return an ArrayList<String>
     */
    public ArrayList<String> getPartyWinOrder() {
        return partyWinOrder;
    }

    /**
     * @return the output expected by the auditfile
     */
    abstract public String toString();

    /**
     * Allocates finalWinOrder based on winOrder and partyCandidates candidate
     * order. Uses a set to ensure that
     * no candidates are repeated
     */
    abstract protected void computeWinOrder();
}
