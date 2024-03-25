import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

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

    @Before
    public void setUp() {
        // FileData setup
        HashMap<String, ArrayList<String>> partyCandidates = new HashMap<>();
        partyCandidates.put("Dem", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));
        partyCandidates.put("Rep", new ArrayList<>(Arrays.asList("Craig", "Klein")));

        ArrayList<ArrayList<Object>> partyVotes = new ArrayList<>();
        partyVotes.add(new ArrayList<>(Arrays.asList("Dem", 750)));
        partyVotes.add(new ArrayList<>(Arrays.asList("Rep", 250)));

        ArrayList<ArrayList<Object>> candidateVotes = new ArrayList<>();
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
    }

    @Test
    public void testGetSeatAllocation() {
        //Test 1.a
        assertEquals(seatAlloc, test.getSeatAllocation());
    }

    @Test
    public void testGetRemainingVotes() {
        //Test 2.a
        assertEquals(remainVotes, test.getRemainingVotes());
    }

    @Test
    public void testGetFinalWinOrder() {
        ArrayList<ArrayList<Object>> finalWinOrder = new ArrayList<>();
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Dem", "Sarah", 1)));
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Dem", "Bob", 2)));
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Rep", "Craig", 3)));

        //Test 3.a
        assertEquals(finalWinOrder, test.getFinalWinOrder());
    }

    @Test
    public void testGetPartyWinOrder() {
        //Test 4.a
        assertEquals(partyWinOrder, test.getPartyWinOrder());
    }

    @Test
    public void testGetToString() {
        StringBuilder expected = new StringBuilder();
        expected.append(testFile.getElectionType() + " Election\n");
        expected.append(testFile.getNumberParties() + " Parties\n");
        expected.append(testFile.getNumberBallots() + " Ballots Cast\n");
        expected.append(testFile.getNumberSeats() + " Seats Avaliable\n");

        assertEquals("", test.toString()); //I expect this to be wrong
    }
}
