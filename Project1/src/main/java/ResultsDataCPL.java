
/**
 * This class is used to store all the information obtained after running the election in a CPL election
 * @author Bethany Freeman
 */
import java.util.ArrayList;
import java.util.Collections;

public class ResultsDataCPL extends ResultsData {
    ArrayList<ArrayList<Object>> finalWinOrder;

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
        this.finalWinOrder = new ArrayList<>();
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

        // List of party and Candidates
        output.append("---------------------------------------------------\n");
        output.append("     Party     |     Candidates\n");
        output.append("---------------------------------------------------\n");
        output.append(partySetUp());
        output.append("---------------------------------------------------\n\n");

        // List of Election Information
        output.append(
                "----------------------------------------------------------------------------------------------------------------------------------\n");
        output.append(
                "                 |               |      First        |     Remaining     |       Second       |     Final     |     % of Vote\n");
        output.append(
                "     Parties     |     Votes     |    Allocation     |       Votes       |     Allocation     |     Seat      |        to\n");
        output.append(
                "                 |               |     Of Seats      |                   |      Of Seats      |     Total     |     % of Seats\n");
        output.append(
                "----------------------------------------------------------------------------------------------------------------------------------\n");
        output.append(electionResultsSetUp());
        output.append(
                "----------------------------------------------------------------------------------------------------------------------------------\n\n");

        //List of Winners


        return output.toString();

    }

    /**
     * Formats the party candidates section of the output
     * 
     * @return a string to be appended to the toString()
     */
    private String partySetUp() {
        StringBuilder output = new StringBuilder();

        int maxLength = findMaxLength();

        for (int i = 0; i < partyVotes.size(); i++) {
            String partyName = (String) partyVotes.get(i).get(0);

            output.append("  " + partyName);
            output.append(String.join("", Collections.nCopies((maxLength - partyName.length()) + 3, " ")));
            output.append("|      ");

            ArrayList<String> innerList = partyCandidates.get(partyName);
            for (int k = 0; k < innerList.size(); k++) {
                String candidateName = innerList.get(k);
                if (k == innerList.size() - 1) {
                    output.append(candidateName + "\n");
                }
                output.append(candidateName + ", ");
            }
        }

        return output.toString();
    }

    /**
     * This creates the formatted election results for the toString()
     * @return a string
     */
    private String electionResultsSetUp() {
        StringBuilder output = new StringBuilder();
        int maxLength = findMaxLength();
        int maxSpace;

        for (int i = 0; i < partyVotes.size(); i++) {
            // Party Name
            String partyName = (String) partyVotes.get(i).get(0);

            output.append("  " + partyName);
            output.append(String.join("", Collections.nCopies((maxLength - partyName.length()) + 3, " ")));
            output.append("|");

            // Votes
            maxSpace = 11;
            String votes = String.valueOf((int) partyVotes.get(i).get(1));
            output.append(String.join("", Collections.nCopies(maxSpace - votes.length(), " ")));
            output.append(votes + "    |");

            // Seats (1st alloc)
            int[] alloc = (int[]) this.seatAllocation.get(i).get(1);
            String firstAlloc = String.valueOf(alloc[0]);
            maxSpace = 10;
            output.append(String.join("", Collections.nCopies(maxSpace - firstAlloc.length(), " ")));
            output.append(firstAlloc + "         |");

            // RemainingVotes
            maxSpace = 13;
            String remainVotes = String.valueOf((int) remainingVotes.get(i).get(1));
            output.append(String.join("", Collections.nCopies(maxSpace - remainVotes.length(), " ")));
            output.append(remainVotes + "      |");

            // Seats (2nd alloc)
            maxSpace = 11;
            String secAlloc = String.valueOf(alloc[1]);
            output.append(String.join("", Collections.nCopies(maxSpace - secAlloc.length(), " ")));
            output.append(secAlloc + "         |");

            // Final seat
            maxSpace = 8;
            String finalAlloc = String.valueOf(alloc[0] + alloc[1]);
            output.append(String.join("", Collections.nCopies(maxSpace - finalAlloc.length(), " ")));
            output.append(finalAlloc + "       |");

            // %vote to %seat
            int[] percents = getPercents(i);
            String votePer = String.valueOf(percents[0]) + "%";
            String seatPer = String.valueOf(percents[1] + "%");
            maxSpace = 9;
            output.append(String.join("", Collections.nCopies(maxSpace - votePer.length(), " ")));
            output.append(votePer + "/" + seatPer + "\n");
        }

        return output.toString();

    }

    /**
     * This calculates the percents for vote and seats for the given index
     * @param index used to indicate what party or candidate is being calculated for
     * @return an int[] for electionResultsSetUp to us to convert values to a string
     */
    private int[] getPercents(int index) {
        int[] seatAlloc = (int[]) this.seatAllocation.get(index).get(1);
        int totalSeats = seatAlloc[0] + seatAlloc[1];
        int[] percents = new int[2];

        int votes = (int) partyVotes.get(index).get(1);
        double perVote = (votes / numberBallots) * 100;
        percents[0] = (int) Math.round(perVote);

        double perSeats = (totalSeats / numberSeats) * 100;
        percents[1] = (int) Math.round(perSeats);

        return percents;
    }

    /**
     * Goes through the partyNames and finds the longest one
     * 
     * @return the length of the longest partyName
     */
    private int findMaxLength() {
        String currString = (String) partyVotes.get(0).get(0);
        int maxLength = currString.length();

        for (int i = 1; i < partyVotes.size(); i++) {
            currString = (String) partyVotes.get(i).get(0);
            int tempLength = currString.length();
            if (tempLength > maxLength) {
                maxLength = tempLength;
            }
        }

        return maxLength;
    }

    /**
     * TODO
     */
    @Override
    protected void computeWinOrder() {

    }
}
