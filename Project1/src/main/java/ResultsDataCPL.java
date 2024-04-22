import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @brief This class is used to store all the information obtained after running the election in a CPL election
 * @author Bethany Freeman
 */
public class ResultsDataCPL extends ResultsData {
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
    ResultsDataCPL(ArrayList<ArrayList<Object>> seatAllocation, ArrayList<ArrayList<Object>> remainingVotes,
            ArrayList<String> partyWinOrder, FileData fileData) throws IOException {
        super(seatAllocation, remainingVotes, partyWinOrder, fileData);
    }

    /**
     * Creates a String output based on the CPL ResultsData information
     * 
     * @return a String
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        // Header portion
        output.append(electionType + " Election\n");
        output.append(numberParties + " Parties\n");
        output.append(numberBallots + " Ballots Cast\n");
        output.append(numberSeats + " Seats Avaliable\n");

        // Party and Candidate List
        output.append("----------------------------------------------------------------------\n");
        output.append("  Party                       |  Candidates\n");
        output.append("----------------------------------------------------------------------\n");
        output.append(partySetUp());
        output.append("----------------------------------------------------------------------\n\n");

        // Election Output
        output.append(
                "-------------------------------------------------------------------------------------------------\n");
        output.append(
                "                 |           |    First     |  Remaining  |    Second    |  Final  |  % of Vote\n");
        output.append("  Parties        |  Votes    |  Allocation  |    Votes    |  Allocation  |  Seat   |     to\n");
        output.append(
                "                 |           |   Of Seats   |             |              |  Total  |  % of Seats\n");
        output.append(
                "-------------------------------------------------------------------------------------------------\n");
        output.append(electionResultsSetUp());
        output.append(
                "-------------------------------------------------------------------------------------------------\n\n");

        // Winner Output
        output.append("---------------------------------------------------\n");
        output.append("  Winning        |  Seat           |  Seat\n");
        output.append("  Parties        |  Winners        |  Won\n");
        output.append("---------------------------------------------------\n");
        output.append(winnerSetUp());
        output.append("---------------------------------------------------\n");

        return output.toString();
    }

    /**
     * This formats the winner information into a StringBuilder object to
     * display to the user
     * 
     * @return a string to display
     */
    protected String display(){
        StringBuilder output = new StringBuilder();
        
        // Winner Output
        output.append("---------------------------------------------------\n");
        output.append("  Winning        |  Seat           |  Seat\n");
        output.append("  Parties        |  Winners        |  Won\n");
        output.append("---------------------------------------------------\n");
        output.append(winnerSetUp());
        output.append("---------------------------------------------------\n");

        return output.toString();
    }

    /**
     * This will take the ArrayList<String> winOrder that contains just partyNames
     * and format it to an ArrayList<ArrayList<Object>> with the partyName,
     * candidateName, and which seat they won
     * 
     * This can handle duplicate names in both opposing parties and the same party
     * 
     * @throws IOException
     */
    @Override
    protected void computeWinOrder() throws IOException {
        // This is a set of allocatedCandidates, might need to change
        HashSet<String> allocatedCandidates = new HashSet<>();
        ArrayList<String> currPartyArrayList;
        ArrayList<Object> innerList;
        String candidate;
        String candName;
        int index;
        int seat = 1;
        try {
            for (String party : partyWinOrder) {
                currPartyArrayList = partyCandidates.get(party);
                innerList = new ArrayList<>();
                index = 0;
                while (true) {
                    candidate = currPartyArrayList.get(index);
                    candName = candidate + party + Integer.toString(index);
                    if (!allocatedCandidates.contains(candName)) {
                        allocatedCandidates.add(candName);
                        innerList.add(party);
                        innerList.add(candidate);
                        innerList.add(seat);
                        finalWinOrder.add(innerList);

                        seat++;
                        break;
                    } else {
                        index += 1;
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IOException("Parties did not get added to win order correctly");
        }

    }
}
