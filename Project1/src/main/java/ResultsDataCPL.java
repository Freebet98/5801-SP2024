/**
 * This class is used to store all the information obtained after running the election in a CPL election
 * @author Bethany Freeman
 */
import java.util.ArrayList;

public class ResultsDataCPL extends ResultsData {
    /**
     * Creates an Object of the ResultsDataCPL class
     * 
     * This stores all the information after running a CPL Election
     * @param seatAllocation This is an arraylist representing the seat allocation for each party for both the first
		and second round of allocation. The array is represented as shown <<Dem, [1,3]>, <Rep, [5,0>>
     * @param remainingVotes This is an arraylist to represent the remaining votes for each party after the first allocation of
		votes, this cannot be editted after the first allocation
     * @param partyWinOrder This is an arraylist representing the final win order of an election, CPL: <<Dem, Jon>, <Rep, Mary>>
     * @param fileDate FileData object, obtained from extracting information from the file
     */
    ResultsDataCPL(ArrayList<ArrayList<Object>> seatAllocation, ArrayList<ArrayList<Object>> remainingVotes,
                   ArrayList<String> partyWinOrder, FileData fileDate ){
        super(seatAllocation, remainingVotes, partyWinOrder, fileDate);
    }

    /**
     * TODO
     * @return
     */
    @Override
    public String toString() {
        return null;
    }

    /**
     * TODO
     */
    @Override
    protected void computeWinOrder() {

    }
}
