
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
    AuditFile auditTestCPL;
    AuditFile auditTestOPL;

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

        ArrayList<ArrayList<Object>> partyVotesOpl = new ArrayList<>();
        partyVotesOpl.add(new ArrayList<>(Arrays.asList("Dem", 750)));
        partyVotesOpl.add(new ArrayList<>(Arrays.asList("Rep", 250)));

        ArrayList<ArrayList<Object>>candidateVotesOpl = new ArrayList<>();
        candidateVotesOpl.add(new ArrayList<>(Arrays.asList("Sarah", 300)));
        candidateVotesOpl.add(new ArrayList<>(Arrays.asList("Bob", 250)));
        candidateVotesOpl.add(new ArrayList<>(Arrays.asList("Jon", 200)));
        candidateVotesOpl.add(new ArrayList<>(Arrays.asList("Craig", 150)));
        candidateVotesOpl.add(new ArrayList<>(Arrays.asList("Klein", 100)));

        FileData testFileCpl = new FileData("CPL", 3, 1000, 2, partyCandidates, partyVotes, candidateVotes);
        FileData testFileOpl = new FileData("OPL",3,1000,5,partyCandidates, partyVotesOpl, candidateVotesOpl);

        // ResultsData setup
        ArrayList<ArrayList<Object>> seatAllocCpl = new ArrayList<>();
        seatAllocCpl.add(new ArrayList<>(Arrays.asList("Dem", new int[] { 2, 0 })));
        seatAllocCpl.add(new ArrayList<>(Arrays.asList("Rep", new int[] { 0, 1 })));

        ArrayList<ArrayList<Object>> seatAllocOpl = new ArrayList<>();
        seatAllocOpl.add(new ArrayList<>(Arrays.asList("Dem", new int[] { 2, 0 })));
        seatAllocOpl.add(new ArrayList<>(Arrays.asList("Rep", new int[] { 0, 1 })));

        ArrayList<ArrayList<Object>> remainVotesCpl = new ArrayList<>();
        remainVotesCpl.add(new ArrayList<>(Arrays.asList("Dem", 84)));
        remainVotesCpl.add(new ArrayList<>(Arrays.asList("Rep", 250)));

        ArrayList<ArrayList<Object>> remainVotesOpl = new ArrayList<>();
        remainVotesOpl.add(new ArrayList<>(Arrays.asList("Dem", 84)));
        remainVotesOpl.add(new ArrayList<>(Arrays.asList("Rep", 250)));

        ArrayList<String> partyWinOrderCpl = new ArrayList<>(Arrays.asList("Dem", "Dem", "Rep"));
        ArrayList<String> partyWinOrderOpl = new ArrayList<>(Arrays.asList("Dem", "Dem", "Rep"));

        ResultsData testCpl = new ResultsDataCPL(seatAllocCpl, remainVotesCpl, partyWinOrderCpl, testFileCpl);
        ResultsData testOpl = new ResultsDataOPL(seatAllocOpl, remainVotesOpl, partyWinOrderOpl, testFileOpl);

        ArrayList<ArrayList<Object>> finalWinOrderCpl = new ArrayList<>();
        finalWinOrderCpl.add(new ArrayList<>(Arrays.asList("Dem", "Sarah", 1)));
        finalWinOrderCpl.add(new ArrayList<>(Arrays.asList("Dem", "Bob", 2)));
        finalWinOrderCpl.add(new ArrayList<>(Arrays.asList("Rep", "Craig", 3)));

        ArrayList<ArrayList<Object>> finalWinOrderOpl = new ArrayList<>();
        finalWinOrderOpl.add(new ArrayList<>(Arrays.asList("Dem", "Sarah", 1, 300)));
        finalWinOrderOpl.add(new ArrayList<>(Arrays.asList("Dem", "Bob", 2, 250)));
        finalWinOrderOpl.add(new ArrayList<>(Arrays.asList("Rep", "Craig", 3, 150)));

        auditTestCPL = new AuditFile(testCpl);
        auditTestOPL = new AuditFile(testOpl);


    }

    @Test
    public void testPrintToFile() throws IOException {
        //Manual Testing - in Documents/AuditFiles
        //Test 1.a
        auditTestCPL.printToFile(); 

        //Test 1.b
        auditTestOPL.printToFile();
    }

}
