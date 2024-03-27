import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class is used to test the ResultsDataCPL class
 * 
 * @author Bethany Freeman
 */
public class ResultsDataCPLTest {
    ArrayList<ArrayList<Object>> seatAlloc;
    ArrayList<ArrayList<Object>> remainVotes;
    ArrayList<String> partyWinOrder;
    ResultsDataCPL test;
    FileData testFile;
    ArrayList<ArrayList<Object>> finalWinOrder;
    ArrayList<ArrayList<Object>> partyVotes;
    HashMap<String, ArrayList<String>> partyCandidates;
    ArrayList<ArrayList<Object>> candidateVotes;

    @Before
    public void setUp() throws IOException {
        // FileData setup
        partyCandidates = new HashMap<>();
        partyCandidates.put("Dem", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));
        partyCandidates.put("Rep", new ArrayList<>(Arrays.asList("Craig", "Klein")));

        partyVotes = new ArrayList<>();
        partyVotes.add(new ArrayList<>(Arrays.asList("Dem", 750)));
        partyVotes.add(new ArrayList<>(Arrays.asList("Rep", 250)));

        candidateVotes = new ArrayList<>();
        candidateVotes.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Bob", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Jon", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Craig", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Klein", 0)));

        testFile = new FileData("CPL", 3, 1000, 2, partyCandidates, partyVotes, candidateVotes);

        // ResultsData setup
        seatAlloc = new ArrayList<>();
        seatAlloc.add(new ArrayList<>(Arrays.asList("Dem", new int[] { 2, 0 })));
        seatAlloc.add(new ArrayList<>(Arrays.asList("Rep", new int[] { 0, 1 })));

        remainVotes = new ArrayList<>();
        remainVotes.add(new ArrayList<>(Arrays.asList("Dem", 84)));
        remainVotes.add(new ArrayList<>(Arrays.asList("Rep", 250)));

        partyWinOrder = new ArrayList<>(Arrays.asList("Dem", "Dem", "Rep"));

        test = new ResultsDataCPL(seatAlloc, remainVotes, partyWinOrder, testFile);

        finalWinOrder = new ArrayList<>();
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Dem", "Sarah", 1)));
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Dem", "Bob", 2)));
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Rep", "Craig", 3)));
    }

    private int[] getPercents(int index) {
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

    @Test
    public void testGetSeatAllocation() {
        // Test 1.a
        assertEquals(seatAlloc, test.getSeatAllocation());
    }

    @Test
    public void testGetRemainingVotes() {
        // Test 2.a
        assertEquals(remainVotes, test.getRemainingVotes());
    }

    @Test
    public void testGetFinalWinOrder() {
        // Test 3.a
        assertEquals(finalWinOrder, test.getFinalWinOrder());
    }

    @Test
    public void testGetPartyWinOrder() {
        // Test 4.a
        assertEquals(partyWinOrder, test.getPartyWinOrder());
    }

    @Test
    public void computeWinOrder() throws IOException{
        //Test 5.a partyWinOrder is empty
        partyWinOrder = new ArrayList<>();
        ArrayList<ArrayList<Object>> finalWinOrder = new ArrayList<>();
        test = new ResultsDataCPL(seatAlloc, remainVotes, partyWinOrder, testFile);

        assertEquals(finalWinOrder, test.getFinalWinOrder());

        //Test 5.b one party has more votes then supposed to
        partyWinOrder = new ArrayList<>(Arrays.asList("Rep", "Rep", "Rep"));
        
        assertThrows(IOException.class, () -> test = new ResultsDataCPL(seatAlloc, remainVotes, partyWinOrder, testFile));

        //Test 5.c partyWinOrder is normal
        partyWinOrder = new ArrayList<>(Arrays.asList("Dem", "Rep"));
        test = new ResultsDataCPL(seatAlloc, remainVotes, partyWinOrder, testFile);
        finalWinOrder = new ArrayList<>();
        finalWinOrder.add(new ArrayList<Object>(Arrays.asList("Dem", "Sarah", 1)));
        finalWinOrder.add(new ArrayList<Object>(Arrays.asList("Rep", "Craig", 2)));

        assertEquals(finalWinOrder, test.getFinalWinOrder());

        //Test 5.d partyWinOrder is has duplicate names in different parties
        partyCandidates = new HashMap<>();
        partyCandidates.put("Dem", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));
        partyCandidates.put("Rep", new ArrayList<>(Arrays.asList("Sarah", "Klein")));

        partyWinOrder = new ArrayList<>(Arrays.asList("Dem", "Rep"));
        finalWinOrder = new ArrayList<>();
        test = new ResultsDataCPL(seatAlloc, remainVotes, partyWinOrder, testFile);
        finalWinOrder.add(new ArrayList<Object>(Arrays.asList("Dem", "Sarah", 1)));
        finalWinOrder.add(new ArrayList<Object>(Arrays.asList("Rep", "Sarah", 2)));

        assertEquals(finalWinOrder, test.getFinalWinOrder()); //This is meant to fail
        
        

    }


    @Test
    public void testGetToString() {
        StringBuilder expected = new StringBuilder();
        expected.append(testFile.getElectionType() + " Election\n");
        expected.append(testFile.getNumberParties() + " Parties\n");
        expected.append(testFile.getNumberBallots() + " Ballots Cast\n");
        expected.append(testFile.getNumberSeats() + " Seats Avaliable\n");
        expected.append("----------------------------------------------------------------------\n");
        expected.append("  Party                       |  Candidates\n");
        expected.append("----------------------------------------------------------------------\n");

        int width = 26;
        for (int i = 0; i < partyVotes.size(); i++) {
            String partyName = (String) partyVotes.get(i).get(0);

            String format = String.format("  %-" + width + "s  |  ", partyName);
            expected.append(format);

            ArrayList<String> innerList = partyCandidates.get(partyName);
            for (int k = 0; k < innerList.size(); k++) {
                String candidateName = innerList.get(k);
                if (k == innerList.size() - 1) {
                    expected.append(candidateName + "\n");
                } else {
                    expected.append(candidateName + ", ");
                }
            }
        }

        expected.append("----------------------------------------------------------------------\n\n");
        expected.append(
                "-------------------------------------------------------------------------------------------------\n");
        expected.append(
                "                 |           |    First     |  Remaining  |    Second    |  Final  |  % of Vote\n");
        expected.append(
                "  Parties        |  Votes    |  Allocation  |    Votes    |  Allocation  |  Seat   |     to\n");
        expected.append(
                "                 |           |   Of Seats   |             |              |  Total  |  % of Seats\n");
        expected.append(
                "-------------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < partyVotes.size(); i++) {
            // Party Name
            String partyName = (String) partyVotes.get(i).get(0);
            width = 13;
            String format = String.format("  %-" + width + "s  |", partyName);
            expected.append(format);

            // Votes
            String votes = String.valueOf((int) partyVotes.get(i).get(1));
            width = 7;
            format = String.format("  %-" + width + "s  |", votes);
            expected.append(format);

            // Seats (1st alloc)
            int[] alloc = (int[]) seatAlloc.get(i).get(1);
            String firstAlloc = String.valueOf(alloc[0]);
            width = 10;
            format = String.format("  %-" + width + "s  |", firstAlloc);
            expected.append(format);

            // RemainingVotes
            width = 9;
            String remainVotes = String.valueOf((int) this.remainVotes.get(i).get(1));
            format = String.format("  %-" + width + "s  |", remainVotes);
            expected.append(format);

            // Seats (2nd alloc)
            width = 10;
            String secAlloc = String.valueOf(alloc[1]);
            format = String.format("  %-" + width + "s  |", secAlloc);
            expected.append(format);

            // Final seat
            width = 5;
            String finalAlloc = String.valueOf(alloc[0] + alloc[1]);
            format = String.format("  %-" + width + "s  |", finalAlloc);
            expected.append(format);

            // %vote to %seat
            int[] percents = getPercents(i);
            String votePer = String.valueOf(percents[0]) + "%";
            String seatPer = String.valueOf(percents[1] + "%");
            String out = votePer + "/" + seatPer;
            width = 10;
            format = String.format("  %-" + width + "s", out);
            expected.append(format + "\n");
        }
        expected.append(
                "-------------------------------------------------------------------------------------------------\n\n");

        // Winner Output
        expected.append("---------------------------------------------------\n");
        expected.append("  Winning        |  Seat           |  Seat\n");
        expected.append("  Parties        |  Winners        |  Won\n");
        expected.append("---------------------------------------------------\n");
        for (int i = 0; i < finalWinOrder.size(); i++) {
            // Party name
            width = 13;
            String partyName = (String) finalWinOrder.get(i).get(0);
            String format = String.format("  %-" + width + "s  |", partyName);
            expected.append(format);

            // Candidate name
            width = 13;
            String candidateName = (String) finalWinOrder.get(i).get(1);
            format = String.format("  %-" + width + "s  |", candidateName);
            expected.append(format);

            // Seat won
            width = 5;
            String seatNumber = String.valueOf(finalWinOrder.get(i).get(2));
            format = String.format("  %-" + width + "s", seatNumber);
            expected.append(format + "\n");
        }
        expected.append("---------------------------------------------------\n");

        //Test 6.a
        assertEquals(expected.toString(), test.toString());
    }
}
