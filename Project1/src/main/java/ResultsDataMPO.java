import java.io.IOException;
import java.util.ArrayList;

public class ResultsDataMPO extends ResultsData{

    /**
     * * This creates an object of ResultsDataCPL which is used to store the
     * information obtained after running the election,
     * inherits from ResultsData, so will have access to all of ResultsData's
     * methods
     * 
     * @param seatAllocation This is a mapping of the name of each party to an array
     *                       that represents how many seats
     *                       a party got in the first allocation [0] and the second
     *                       allocation [1]. the actual
     *                       ArrayList will be of type ArrayList<ArrayList<String,
     *                       int[2]>>
     *  
     * @param remainingVotes This is a mapping of the name of each party to an int
     *                       that represents how many votes the
     *                       party has, this is only representative of how many
     *                       votes a party has at the end of the
     *                       first round of allocation. the actual ArrayList will be
     *                       of type
     *                       ArrayList<ArrayList<String, int>>
     * 
     * @param partyWinOrder  This is an Arraylist showing the ordered list of when a
     *                       party won a seat, this will
     *                       help determine which candidates get a seat
     * 
     * @param fileData       this is the data from the original extraction from the
     *                       file
     */
    ResultsDataMPO(ArrayList<ArrayList<Object>> seatAllocation, ArrayList<ArrayList<Object>> remainingVotes,
            ArrayList<String> partyWinOrder, FileData fileData) throws IOException {
        super(seatAllocation, remainingVotes, partyWinOrder, fileData);
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "";
    }

    @Override
    protected String display() {
        // TODO Auto-generated method stub
        return "";
    }

    @Override
    protected void computeWinOrder() throws IOException {
        // TODO Auto-generated method stub
    }
    
}
