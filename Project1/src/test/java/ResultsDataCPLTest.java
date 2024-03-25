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

        FileData testFile = new FileData("CPL", 3, 1000, 2, partyCandidates, partyVotes, candidateVotes);

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
        
    }

    @Test
    public void testGetRemainingVotes() {
        // TODO
    }

    @Test
    public void testGetFinalWinOrder() {
        // TODO
    }

    @Test
    public void testGetPartyWinOrder() {
        // TODO
    }

    @Test
    public void testGetToString() {
        // TODO
    }
}
