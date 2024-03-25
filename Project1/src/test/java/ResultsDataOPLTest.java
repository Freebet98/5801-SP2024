
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
    ResultsDataCPL test;
    FileData testFile;
    ArrayList<ArrayList<Object>> finalWinOrder;
    ArrayList<ArrayList<Object>> partyVotes;
    HashMap<String, ArrayList<String>> partyCandidates;
    ArrayList<ArrayList<Object>> candidateVotes;

    @Before
    public void setUp() {
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

        test = new ResultsDataCPL(seatAlloc, remainVotes, partyWinOrder, testFile);

        finalWinOrder = new ArrayList<>();
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Green", " Jonah", 1)));
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Pluto", " Becky", 2)));
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Green", " Radius", 3)));
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
        // Test 3.a
        assertEquals(finalWinOrder, test.getFinalWinOrder());
    }

    @Test
    public void testGetPartyWinOrder() {
        // Test 4.a
        assertEquals(partyWinOrder, test.getPartyWinOrder());
    }

}
