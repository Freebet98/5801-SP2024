import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @brief This class is used to store all the information obtained after running
 *        the election
 * @author Bethany Freeman
 */
public class ResultsDataMPO extends ResultsData {

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
     * This formats the winner information into a StringBuilder object to
     * display to the user
     * 
     * @return a string to display
     */
    @Override
    protected String display() {
        StringBuilder output = new StringBuilder();

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
     * This will take the ArrayList<String> winOrder that contains just partyNames
     * and format it to an ArrayList<ArrayList<Object>> with the partyName,
     * candidateName, which seat they won, and how many votes the candidate had
     * 
     * This can handle duplicate names in both opposing parties and the same party
     * 
     * @throws IOException if a candidate cannot be found in candidateVotes, handled
     *                     in main
     */
    @Override
    protected void computeWinOrder() throws IOException {
        // This is a set of allocatedCandidates
        HashSet<String> allocatedCandidates = new HashSet<>();
        ArrayList<String> currPartyArrayList;
        String candName;
        int seat = 1;
        try {
            // Go through every party in the winOrder
            for (String party : partyWinOrder) {
                currPartyArrayList = partyCandidates.get(party);
                int index = 0;
                // Infinite loop that breaks at specific points
                while (true) {
                    String candidate = currPartyArrayList.get(index);
                    candName = candidate + party + Integer.toString(index); // This ensures that candidates can have the
                                                                            // same name
                    if (!allocatedCandidates.contains(candName)) {
                        addCandidateToFinalWinOrder(allocatedCandidates, index, party, seat, candName, candidate);
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

    /**
     * Only gets called if a candidate is not already contained in the HashSet
     * will add that candidate to a new Inner ArrayList to add to finalWinOrder,
     * this will
     * contain the party name, candidates name, and how many votes they got.
     * 
     * @param allocatedCandidates the Hashset to look at
     * @param index               What index in the partyWinOrder are we actually
     *                            looking at
     * @param party               what party is this candidate in
     * @param seat                what seat did this candidate win
     * @param candName            the special string made to differentiate
     *                            candidates of the same name in the HashSet
     * @param candidate           the candidates name
     * @throws IOException
     */
    private void addCandidateToFinalWinOrder(HashSet<String> allocatedCandidates, int index, String party, int seat,
            String candName, String candidate) throws IOException {
        ArrayList<Object> innerList = new ArrayList<>();

        // adds to the HashSet the unique identifier
        allocatedCandidates.add(candName);

        // creates an inner arrayList to add to finalWinOrder
        innerList.add(party);
        innerList.add(candidate);
        innerList.add(seat);

        innerList.add(findCandidateVotes(candidate, party));

        finalWinOrder.add(innerList);
    }

    /**
     * This will retrieve the votes for a candidate
     * 
     * @param candidate name of the candidate
     * @param count     At which index of the name you can expect the candidates
     *                  votes
     * @return the votes of a candidate, -1 if the candidate name cannot be found
     * @throws IOException
     */
    private int findCandidateVotes(String candidate, String party) throws IOException {
        int index = checkForRepeatName(candidate, party);
        return (int) candidateVotes.get(index).get(1);
    }

    /**
     * This will find the index of where to access in candidate votes to obtain the
     * votes for the candidate
     * 
     * @param candidate the name of the candidate
     * @param party     the name of the party
     * @return an integer total of what index to retrieve from candidateVotes
     * @throws IOException
     */
    private int checkForRepeatName(String candidate, String party) throws IOException {
        int location = adjustIndexForPartyOrder(party);
        int additionalSame = checkFinalWinOrder(candidate, party);

        int totalIndex = location + additionalSame;

        if (totalIndex >= candidateVotes.size() || totalIndex < 0) {
            throw new IOException("Incorrect location calculation in checkForRepeatName");
        }

        return totalIndex;
    }

    /**
     * This will go through the current finalWinOrder update count if there already
     * exists a candidate in the same party with the same name
     * 
     * @param candidate the name of the candidate
     * @param party     the name of the party
     * @return a count
     */
    private int checkFinalWinOrder(String candidate, String party) {
        int count = 0;
        for (ArrayList<Object> innerList : finalWinOrder) {
            if (innerList.get(0).equals(party)) {
                count++;
            }
        }

        return count;
    }

    /**
     * This will add up the number of spots that are currently taken up in the
     * candidate Votes array ahead of the candidate we are trying to add to the
     * final win order
     * 
     * @param party this is the string of the party, passed in for
     *              checkPartyLocation
     * @return an interger total of how many are ahead
     */
    private int adjustIndexForPartyOrder(String party) {
        int count = checkPartyLocation(party);
        int tempCount = 0;
        int location = 0;
        String partyString;

        while (tempCount != count) {
            partyString = (String) partyVotes.get(tempCount).get(0);
            location += (int) partyCandidates.get(partyString).size();
            tempCount++;
        }

        return location;
    }

    /**
     * This will go through partyVotes and determine how many parties are before the
     * passed in party in the order
     * 
     * @param party this is a string of the partyName
     * @return an integer representing how many parties are ahead of it in the order
     */
    private int checkPartyLocation(String party) {
        int location = 0;
        for (ArrayList<Object> partyList : partyVotes) {
            if (partyList.get(0).equals(party)) {
                return location;
            } else {
                location++;
            }
        }

        return location;
    }

}
