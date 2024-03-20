import java.util.ArrayList;

/**
 * This class is used to store all the information obtained after running the election
 * @author Bethany Freeman
 */
abstract public class ResultsData extends FileData{
    private final ArrayList<ArrayList<Object>> seatAllocation;
    private final ArrayList<ArrayList<Object>> remainingVotes;
    private final ArrayList<String> partyWinOrder;
    private final ArrayList<ArrayList<Object>> finalWinOrder;

    /**
     * This creates an object of ResultsData which is used to store the information obtained after running the election,
     * inherits from FileData, so will have access to all of FileData's methods
     * @param seatAllocation This is a mapping of the name of each party to an array that represents how many seats
     *                       a party got in the first allocation [0] and the second allocation [1]. the actual
     *                       ArrayList will be of type ArrayList<ArrayList<String, int[2]>>
     * @param remainingVotes This is a mapping of the name of each party to an int that represents how many votes the
     *                       party has, this is only representative of how many votes a party has at the end of the
     *                       first round of allocation. the actual ArrayList will be of type
     *                       ArrayList<ArrayList<String, int>>
     * @param partyWinOrder  This is an Arraylist showing the ordered list of when a party won a seat, this will
     *                       help determine which candidates get a seat
     * @param fileDate this is the data from the original extraction from the file
     */
    ResultsData(ArrayList<ArrayList<Object>> seatAllocation, ArrayList<ArrayList<Object>> remainingVotes,
                ArrayList<String> partyWinOrder, FileData fileDate){
        super(fileDate.electionType, fileDate.numberSeats, fileDate.numberBallots, fileDate.numberParties,
                fileDate.partyCandidates, fileDate.partyVotes, fileDate.candidateVotes);
        this.seatAllocation = seatAllocation;
        this.remainingVotes = remainingVotes;
        this.partyWinOrder = partyWinOrder;
        finalWinOrder = new ArrayList<ArrayList<Object>>();
        computeWinOrder();
    }

	/**
	* TODO
	*/
    public ArrayList<ArrayList<Object>> getSeatAllocation(){
        return seatAllocation;
    }

	/**
	* TODO
	*/
    public ArrayList<ArrayList<Object>> getRemainingVotes(){
        return remainingVotes;
    }

	/**
	* TODO
	*/
    public ArrayList<ArrayList<Object>> getFinalWinOrder(){
        return finalWinOrder;
    }

	/**
	* TODO
	*/
    public ArrayList<String> getPartyWinOrder(){
        return partyWinOrder;
    }

    //Will be used to override the Object toString()
	/**
	* TODO
	*/
    abstract public String toString();

    /**
     * Allocates finalWinOrder based on winOrder and partyCandidates candidate order. Uses a set to ensure that
     * no candidates are repeated
     */
    abstract protected void computeWinOrder();
}
