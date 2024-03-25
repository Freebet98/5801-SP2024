
import java.util.ArrayList;

/**
 * This class is used to store all the information obtained after running the
 * election in an OPL election
 * 
 * @author Bethany Freeman
 */
public class ResultsDataOPL extends ResultsData {
    /**
     * TODO
     * 
     * @param seatAllocation
     * @param remainingVotes
     * @param partyWinOrder
     * @param fileDate
     */
    ResultsDataOPL(ArrayList<ArrayList<Object>> seatAllocation, ArrayList<ArrayList<Object>> remainingVotes,
            ArrayList<String> partyWinOrder, FileData fileDate) {
        super(seatAllocation, remainingVotes, partyWinOrder, fileDate);
    }

    /**
     * TODO
     * 
     * @return
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        // Header Portion
        output.append(electionType + " Election\n");
        output.append(partyCandidates.size() + " Parties\n");
        output.append(numberBallots + " Ballots Cast\n");
        output.append(numberSeats + " Seats Avaliable\n");

        // Party and Candidate List
        output.append("----------------------------------------------------------------------\n");
        output.append("  Party                       |  Candidates\n");
        output.append("----------------------------------------------------------------------\n");
        output.append(partySetUp());
        output.append("----------------------------------------------------------------------\n\n");

        return output.toString();
    }

    /**
     * Formats the party candidates section of the output
     * 
     * @return a string to be appended to the toString()
     */
    private String partySetUp() {
        StringBuilder output = new StringBuilder();
        int width = 26;
        for (int i = 0; i < partyVotes.size(); i++) {
            String partyName = (String) partyVotes.get(i).get(0);

            String format = String.format("  %-" + width + "s  |  ", partyName);
            output.append(format);

            ArrayList<String> innerList = partyCandidates.get(partyName);
            for (int k = 0; k < innerList.size(); k++) {
                String candidateName = innerList.get(k);
                if (k == innerList.size() - 1) {
                    output.append(candidateName + "\n");
                } else {
                    output.append(candidateName + ", ");
                }
            }
        }

        return output.toString();
    }

    /**
     * TODO
     */
    @Override
    protected void computeWinOrder() {

    }
}
