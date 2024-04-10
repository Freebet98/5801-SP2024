
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class is used to test the ResultsDataOPL class
 * 
 * @author Bethany Freeman
 */
public class ResultsDataOPLTest {
    ArrayList<ArrayList<Object>> seatAlloc;
    ArrayList<ArrayList<Object>> remainVotes;
    ArrayList<String> partyWinOrder;
    ResultsDataOPL test;
    FileData testFile;
    ArrayList<ArrayList<Object>> finalWinOrder;
    ArrayList<ArrayList<Object>> partyVotes;
    HashMap<String, ArrayList<String>> partyCandidates;
    ArrayList<ArrayList<Object>> candidateVotes;

    @Before
    public void setUp() throws IOException {
        // FileData setup
        partyCandidates = new HashMap<>();
        partyCandidates.put("Pluto", new ArrayList<>(Arrays.asList(" Becky", " Mariah")));
        partyCandidates.put("Green", new ArrayList<>(Arrays.asList(" Jonah", " Radius", " Louis")));

        partyVotes = new ArrayList<>();
        partyVotes.add(new ArrayList<>(Arrays.asList("Pluto", 40048)));
        partyVotes.add(new ArrayList<>(Arrays.asList("Green", 59952)));

        candidateVotes = new ArrayList<>();
        candidateVotes.add(new ArrayList<>(Arrays.asList(" Becky", 20105)));
        candidateVotes.add(new ArrayList<>(Arrays.asList(" Jonah", 19943)));
        candidateVotes.add(new ArrayList<>(Arrays.asList(" Mariah", 19943)));
        candidateVotes.add(new ArrayList<>(Arrays.asList(" Radius", 20020)));
        candidateVotes.add(new ArrayList<>(Arrays.asList(" Louis", 19989)));

        testFile = new FileData("OPL", 3, 100000, 5, partyCandidates, partyVotes, candidateVotes);

        // ResultsData setup
        seatAlloc = new ArrayList<>();
        seatAlloc.add(new ArrayList<>(Arrays.asList("Pluto", new int[] { 1, 0 })));
        seatAlloc.add(new ArrayList<>(Arrays.asList("Green", new int[] { 1, 1 })));

        remainVotes = new ArrayList<>();
        remainVotes.add(new ArrayList<>(Arrays.asList("Pluto", 6715)));
        remainVotes.add(new ArrayList<>(Arrays.asList("Rep", 26619)));

        partyWinOrder = new ArrayList<>(Arrays.asList("Green", "Pluto", "Green"));

        test = new ResultsDataOPL(seatAlloc, remainVotes, partyWinOrder, testFile);

        finalWinOrder = new ArrayList<>();
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Green", " Jonah", 1, 19943)));
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Pluto", " Becky", 2, 20105)));
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Green", " Radius", 3, 20020)));
    }

    public String partySetUp(){
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

    public String electionResultsSetUp(){
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
            int[] alloc = (int[]) this.seatAlloc.get(i).get(1);
            String firstAlloc = String.valueOf(alloc[0]);
            width = 10;
            format = String.format("  %-" + width + "s  |", firstAlloc);
            output.append(format);

            // RemainingVotes
            width = 9;
            String remainVotes = String.valueOf((int) this.remainVotes.get(i).get(1));
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

    public int[] getPercents(int index){
        int[] seatAlloc = (int[]) this.seatAlloc.get(index).get(1);
        int totalSeats = seatAlloc[0] + seatAlloc[1];
        int[] percents = new int[2];

        int votes = (int) partyVotes.get(index).get(1);
        double perVote = ((double) votes / (double) testFile.getNumberBallots()) * 100;
        percents[0] = (int) Math.round(perVote);

        double perSeats = ((double) totalSeats / (double) testFile.getNumberSeats()) * 100;
        percents[1] = (int) Math.round(perSeats);

        return percents;
    }

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

    @Test
    public void testGetSeatAllocation() {
        //Test 1.a
        assertEquals(seatAlloc, test.getSeatAllocation());
    }

    @Test
    public void testGetRemainingVotes() {
        // Test 2.a
        assertEquals(remainVotes, test.getRemainingVotes());
    }

    @Test
    public void testGetFinalWinOrder() {
        // Test 3.a private String winnerSetUp() {
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
        assertEquals(finalWinOrder, test.getFinalWinOrder());
    }

    @Test
    public void testGetPartyWinOrder() {
        // Test 4.a
        assertEquals(partyWinOrder, test.getPartyWinOrder());
    }

    @Test
    public void testComputeWinOrder() throws IOException {
        //Test 5.a partyWinOrder is empty

        partyWinOrder = new ArrayList<>();
        ArrayList<ArrayList<Object>> finalWinOrder = new ArrayList<>();
        test = new ResultsDataOPL(seatAlloc, remainVotes, partyWinOrder, testFile);

        assertEquals(finalWinOrder, test.getFinalWinOrder());

        //Test 5.b one party has more votes then supposed to
        ArrayList<ArrayList <Object>> expected = new ArrayList<>();

        expected.add(new ArrayList<>(Arrays.asList("Pluto", " Becky", 1, 20105)));
        expected.add(new ArrayList<>(Arrays.asList("Pluto", " Mariah", 2, 19943)));
        expected.add(new ArrayList<>(Arrays.asList("Green", " Jonah", 3, 19943)));

        assertThrows(IOException.class, () -> test = new ResultsDataOPL(seatAlloc, remainVotes, new ArrayList<>(Arrays.asList("Pluto", "Pluto", "Pluto")), testFile));

        //Test 5.c partyWinOrder is normal
        expected = new ArrayList<>();
        ArrayList<String> winOrder = new ArrayList<>(Arrays.asList("Pluto", "Pluto", "Green"));

        expected.add(new ArrayList<>(Arrays.asList("Pluto", " Becky", 1, 20105)));
        expected.add(new ArrayList<>(Arrays.asList("Pluto", " Mariah", 2, 19943)));
        expected.add(new ArrayList<>(Arrays.asList("Green", " Jonah", 3, 19943)));
        test = new ResultsDataOPL(seatAlloc, remainVotes, winOrder, test);

        assertEquals(expected, test.getFinalWinOrder());

        // Test 5.d partyWinOrder has duplicate names in different parties
        partyCandidates = new HashMap<>();
        partyCandidates.put("Dem", new ArrayList<>(Arrays.asList("Clyde", "Becky", "John")));
        partyCandidates.put("Rep", new ArrayList<>(Arrays.asList("Clyde", "Klaus")));

        partyVotes = new ArrayList<>();
        partyVotes.add(new ArrayList<>(Arrays.asList("Dem", 500)));
        partyVotes.add(new ArrayList<>(Arrays.asList("Rep", 505)));

        candidateVotes = new ArrayList<>();
        candidateVotes.add(new ArrayList<>(Arrays.asList("Clyde", 300)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Becky", 100)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("John", 100)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Clyde", 400)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Klaus", 105)));

        testFile = new FileData("OPL", 3, 1005, 2, partyCandidates, partyVotes, candidateVotes);
        partyWinOrder = new ArrayList<>(Arrays.asList("Dem", "Rep"));
        finalWinOrder = new ArrayList<>();
        test = new ResultsDataOPL(seatAlloc, remainVotes, partyWinOrder, testFile);
        finalWinOrder.add(new ArrayList<Object>(Arrays.asList("Dem", "Clyde", 1, 300)));
        finalWinOrder.add(new ArrayList<Object>(Arrays.asList("Rep", "Clyde", 2, 400)));

        assertEquals(finalWinOrder, test.getFinalWinOrder()); 

        // Test 5.e partyWinOrder has duplicate names in the same party
        partyCandidates = new HashMap<>();
        partyCandidates.put("Dem", new ArrayList<>(Arrays.asList("Clyde", "Clyde", "John")));
        partyCandidates.put("Rep", new ArrayList<>(Arrays.asList("Clyde", "Klaus")));

        partyVotes = new ArrayList<>();
        partyVotes.add(new ArrayList<>(Arrays.asList("Dem", 500)));
        partyVotes.add(new ArrayList<>(Arrays.asList("Rep", 505)));

        candidateVotes = new ArrayList<>();
        candidateVotes.add(new ArrayList<>(Arrays.asList("Clyde", 300)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Clyde", 150)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("John", 50)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Clyde", 400)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Klaus", 105)));

        testFile = new FileData("OPL", 3, 1005, 2, partyCandidates, partyVotes, candidateVotes);
        partyWinOrder = new ArrayList<>(Arrays.asList("Dem", "Dem"));
        finalWinOrder = new ArrayList<>();
        test = new ResultsDataOPL(seatAlloc, remainVotes, partyWinOrder, testFile);
        finalWinOrder.add(new ArrayList<Object>(Arrays.asList("Dem", "Clyde", 1, 300)));
        finalWinOrder.add(new ArrayList<Object>(Arrays.asList("Dem", "Clyde", 2, 150)));

        assertEquals(finalWinOrder, test.getFinalWinOrder()); 
    }

    @Test
    public void testToString(){
        StringBuilder output = new StringBuilder();

        // Header Portion
        output.append(test.fileData.getElectionType() + " Election\n");
        output.append(partyCandidates.size() + " Parties\n");
        output.append(test.fileData.numberBallots + " Ballots Cast\n");
        output.append(test.fileData.getNumberSeats() + " Seats Avaliable\n");

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

        assertEquals(output.toString(), test.toString());
    }

}
