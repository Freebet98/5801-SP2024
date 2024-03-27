
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class is used to store all the information obtained after running the
 * election in an OPL election
 * 
 * @author Bethany Freeman
 */
public class ResultsDataOPL extends ResultsData {
    /**
     * Creates a ResultsDataOPL Object
     * This stores information from running an OPL election
     * inherits from ResultsData
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
    ResultsDataOPL(ArrayList<ArrayList<Object>> seatAllocation, ArrayList<ArrayList<Object>> remainingVotes,
            ArrayList<String> partyWinOrder, FileData fileDate) throws IOException {
        super(seatAllocation, remainingVotes, partyWinOrder, fileDate);
    }

    /**
     * Creates a String output based on the OPL ResultsData information
     * 
     * @return a String
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

        // Winner List
        output.append("----------------------------------------------------------\n");
        output.append("  Winning        |  Seat           |  Seat  |   Number\n");
        output.append("  Parties        |  Winners        |  Won   |  Of Votes\n");
        output.append("----------------------------------------------------------\n");
        output.append(winnerSetUp());
        output.append("----------------------------------------------------------\n");

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
     * This creates the formatted election results for the toString()
     * 
     * @return a string
     */
    private String electionResultsSetUp() {
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
    private String winnerSetUp() {
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
            width = 4;
            String seatNumber = String.valueOf(finalWinOrder.get(i).get(2));
            format = String.format("  %-" + width + "s  |", seatNumber);
            output.append(format);

            // Votes per candidate
            width = 6;
            String voteNumber = String.valueOf(finalWinOrder.get(i).get(3));
            format = String.format("  %-" + width + "s", voteNumber);
            output.append(voteNumber + "\n");
        }

        return output.toString();

    }

    
     /**
     * This calculates the percents for vote and seats for the given index
     * 
     * @param index used to indicate what party or candidate is being calculated for
     * @return an int[] for electionResultsSetUp to us to convert values to a string
     */
    private int[] getPercents(int index) {
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
     * This will take the ArrayList<String> winOrder that contains just partyNames
     * and format it to an ArrayList<ArrayList<Object>> with the partyName,
     * candidateName, which seat they won, and how many votes the candidate had
     * 
     * @throws IOException if a candidate cannot be found in candidateVotes, handled
     *                     in main
     */
    @Override
    protected void computeWinOrder() throws IOException{ //Runtime O(n^2 + k)
        // This is a set of allocatedCandidates, might need to change
        HashSet<String> allocatedCandidates = new HashSet<>();
        ArrayList<String> currPartyArrayList;
        int seat = 1;
        try {
            for (String party : partyWinOrder) { //O(n)
                currPartyArrayList = partyCandidates.get(party);
                int index = 0;
                while (true) { //O(k) where k is equal to the index
                    String candidate = currPartyArrayList.get(index);
                    ArrayList<Object> innerList = new ArrayList<>();
                    if (!allocatedCandidates.contains(candidate)) {
                        allocatedCandidates.add(candidate);
                        innerList.add(party);
                        innerList.add(candidate);
                        innerList.add(seat);
                        // Find the candidate votes by name
                        for(ArrayList<Object> candidateInfo: candidateVotes){ //O(n) worst case
                            if(candidateInfo.get(0).equals(candidate)){
                                innerList.add(candidateInfo.get(1));
                                break;
                            }
                        }
                        finalWinOrder.add(innerList);
                        seat++;
                        break;
                    } else {
                        index += 1;
                    }
                }
            }
        }catch (IndexOutOfBoundsException e) {
            throw new IOException("Parties did not get added to win order correctly");
        }
    }
}
