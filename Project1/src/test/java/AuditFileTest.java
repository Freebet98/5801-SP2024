
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class is used to test the AuditFile
 * 
 * @author
 */
public class AuditFileTest {
    AuditFile auditTest;

    @Before
    public void setUp() throws IOException {
        // FileData setup
        HashMap<String, ArrayList<String>> partyCandidates = new HashMap<>();
        partyCandidates.put("Dem", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));
        partyCandidates.put("Rep", new ArrayList<>(Arrays.asList("Craig", "Klein")));

        ArrayList<ArrayList<Object>> partyVotes = new ArrayList<>();
        partyVotes.add(new ArrayList<>(Arrays.asList("Dem", 750)));
        partyVotes.add(new ArrayList<>(Arrays.asList("Rep", 250)));

        ArrayList<ArrayList<Object>>candidateVotes = new ArrayList<>();
        candidateVotes.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Bob", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Jon", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Craig", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Klein", 0)));

        FileData testFile = new FileData("CPL", 3, 1000, 2, partyCandidates, partyVotes, candidateVotes);

        // ResultsData setup
        ArrayList<ArrayList<Object>> seatAlloc = new ArrayList<>();
        seatAlloc.add(new ArrayList<>(Arrays.asList("Dem", new int[] { 2, 0 })));
        seatAlloc.add(new ArrayList<>(Arrays.asList("Rep", new int[] { 0, 1 })));

        ArrayList<ArrayList<Object>> remainVotes = new ArrayList<>();
        remainVotes.add(new ArrayList<>(Arrays.asList("Dem", 84)));
        remainVotes.add(new ArrayList<>(Arrays.asList("Rep", 250)));

        ArrayList<String> partyWinOrder = new ArrayList<>(Arrays.asList("Dem", "Dem", "Rep"));

        ResultsData test = new ResultsDataCPL(seatAlloc, remainVotes, partyWinOrder, testFile);

        ArrayList<ArrayList<Object>> finalWinOrder = new ArrayList<>();
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Dem", "Sarah", 1)));
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Dem", "Bob", 2)));
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Rep", "Craig", 3)));

        auditTest = new AuditFile(test);
    }

    @Test
    public void testPrintToFile() throws IOException {
        //Test 1.a
        auditTest.printToFile(); 
    }

}
