/**
 * This class is used to store all the information obtained after running the election in an OPL election
 * @author Bethany Freeman
 */
import java.util.ArrayList;

public class ResultsDataOPL extends ResultsData{
    /**
     * TODO
     * @param seatAllocation
     * @param remainingVotes
     * @param partyWinOrder
     * @param fileDate
     */
    ResultsDataOPL(ArrayList<ArrayList<Object>> seatAllocation, ArrayList<ArrayList<Object>> remainingVotes,
                   ArrayList<String> partyWinOrder, FileData fileDate){
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
