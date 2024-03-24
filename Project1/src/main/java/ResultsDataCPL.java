
/**
 * This class is used to store all the information obtained after running the election in a CPL election
 * @author Bethany Freeman
 */
import java.util.ArrayList;
import java.util.Collections;

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
     * @param fileData       this is the data from the original extraction from the
     *                       file
     */
    ResultsDataCPL(ArrayList<ArrayList<Object>> seatAllocation, ArrayList<ArrayList<Object>> remainingVotes,
            ArrayList<String> partyWinOrder, FileData fileData) {
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
        output.append(electionType + " Election\n");
        output.append(numberSeats + " Parties\n");
        output.append(numberBallots + " Ballots Cast\n");
        output.append(numberParties + " Seats Avaliable\n");
        output.append("---------------------------------------------------\n");
        output.append("     Party     |     Candidates\n");
        output.append("---------------------------------------------------\n");
        output.append(partySetUp());
        output.append("---------------------------------------------------\n\n");
        output.append("----------------------------------------------------------------------------------------------------------------------------------\n");
        output.append("                 |               |      First        |     Remaining     |       Second       |     Final     |     % of Vote\n");
        output.append("     Parties     |     Votes     |    Allocation     |       Votes       |     Allocation     |     Seat      |        to\n");
        output.append("                 |               |     Of Seats      |                   |      Of Seats      |     Total     |     % of Seats\n");
        output.append("----------------------------------------------------------------------------------------------------------------------------------\n");


        return output.toString();

    }

    /**
     * Formats the party candidates section of the output
     * @return a string to be appended to the toString()
     */
    private String partySetUp(){
        StringBuilder output = new StringBuilder();
        String currString = (String) partyVotes.get(0).get(0);
        int maxLength = currString.length();

        for(int i = 1; i < partyVotes.size(); i++){
            currString = (String) partyVotes.get(i).get(0);
            int tempLength = currString.length();
            if(tempLength > maxLength){
                maxLength = tempLength;
            }
        }

        for(int i = 0; i < partyVotes.size(); i++){
            String partyName = (String) partyVotes.get(i).get(0);
            
            output.append("  " + partyName);
            output.append(String.join("", Collections.nCopies((maxLength - partyName.length()) + 3, " ")));
            output.append("|      ");

            ArrayList<String> innerList = partyCandidates.get(partyName);
            for(int k = 0; k < innerList.size(); k++){
                String candidateName = innerList.get(k);
                if(k == innerList.size() -1){
                    output.append(candidateName + "\n");
                }
                output.append(candidateName + ", ");
            }
        }

        return output.toString();
    }

    private String electionResultsSetUp(){
        StringBuilder output = new StringBuilder();
        
    }
    /**
     * TODO
     */
    @Override
    protected void computeWinOrder() {

    }
}
