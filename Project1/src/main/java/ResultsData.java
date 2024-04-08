import java.io.IOException;
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
    protected final ArrayList<String> partyWinOrder;
    protected final ArrayList<ArrayList<Object>> finalWinOrder;
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
     * @throws IOException
     */
    ResultsData(ArrayList<ArrayList<Object>> seatAllocation, ArrayList<ArrayList<Object>> remainingVotes,
            ArrayList<String> partyWinOrder, FileData fileDate) throws IOException {
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
     * This calculates the percents for vote and seats for the given index
     * 
     * @param index used to indicate what party or candidate is being calculated for
     * @return an int[] for electionResultsSetUp to us to convert values to a string
     */
    protected int[] getPercents(int index) {
        int[] seatAlloc = (int[]) this.seatAllocation.get(index).get(1);
        int totalSeats = seatAlloc[0] + seatAlloc[1];
        int[] percents = new int[2];

        int votes = (int) partyVotes.get(index).get(1);
        double perVote = ((double) votes / (double) numberBallots) * 100;
        percents[0] = (int) Math.round(perVote);

        double perSeats = ((double) totalSeats / (double) numberSeats) * 100;
        percents[1] = (int) Math.round(perSeats);

        return percents;
    }

    /**
     * Formats the party candidates section of the output
     * 
     * @return a string to be appended to the toString()
     */
    protected String partySetUp() {
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
     * This creates the formatted election results for the toString()
     * 
     * @return a string
     */
    protected String electionResultsSetUp() {
        StringBuilder output = new StringBuilder();
        int width;

        for (int i = 0; i < partyVotes.size(); i++) {
            // Party Name
            String partyName = (String) partyVotes.get(i).get(0);
            width = 13;
            String format = String.format("  %-" + width + "s  |", partyName);
            output.append(format);

            // Votes
            String votes = String.valueOf((int) partyVotes.get(i).get(1));
            width = 7;
            format = String.format("  %-" + width + "s  |", votes);
            output.append(format);

            // Seats (1st alloc)
            int[] alloc = (int[]) this.seatAllocation.get(i).get(1);
            String firstAlloc = String.valueOf(alloc[0]);
            width = 10;
            format = String.format("  %-" + width + "s  |", firstAlloc);
            output.append(format);

            // RemainingVotes
            width = 9;
            String remainVotes = String.valueOf((int) remainingVotes.get(i).get(1));
            format = String.format("  %-" + width + "s  |", remainVotes);
            output.append(format);

            // Seats (2nd alloc)
            width = 10;
            String secAlloc = String.valueOf(alloc[1]);
            format = String.format("  %-" + width + "s  |", secAlloc);
            output.append(format);

            // Final seat
            width = 5;
            String finalAlloc = String.valueOf(alloc[0] + alloc[1]);
            format = String.format("  %-" + width + "s  |", finalAlloc);
            output.append(format);

            // %vote to %seat
            int[] percents = getPercents(i);
            String votePer = String.valueOf(percents[0]) + "%";
            String seatPer = String.valueOf(percents[1] + "%");
            String out = votePer + "/" + seatPer;
            width = 10;
            format = String.format("  %-" + width + "s", out);
            output.append(format + "\n");
        }

        return output.toString();

    }

    /**
     * Format a string with the finalWinOrder information
     * 
     * @return a string with the formatted winner information
     */
    protected String winnerSetUp() {
        StringBuilder output = new StringBuilder();
        int width;
        String partyName;
        String candidateName;

        for (int i = 0; i < finalWinOrder.size(); i++) {
            // Party name
            width = 13;
            partyName = (String) finalWinOrder.get(i).get(0);
            String format = String.format("  %-" + width + "s  |", partyName);
            output.append(format);

            // Candidate name
            width = 13;
            candidateName = (String) finalWinOrder.get(i).get(1);
            format = String.format("  %-" + width + "s  |", candidateName);
            output.append(format);

            // Seat won
            width = 5;
            String seatNumber = String.valueOf(finalWinOrder.get(i).get(2));
            format = String.format("  %-" + width + "s", seatNumber);
            output.append(format + "\n");
        }

        return output.toString();

    }

    /**
     * @return the output expected by the auditfile
     */
    abstract public String toString();

    /**
     * This formats the winner information into a StringBuilder object to
     * display to the user
     * 
     * @return a string to display
     */
    abstract protected String display();

    /**
     * Allocates finalWinOrder based on winOrder and partyCandidates candidate
     * order. Uses a set to ensure that
     * no candidates are repeated
     * 
     * @throws IOException
     */
    abstract protected void computeWinOrder() throws IOException;
}
