
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class is used to test the ExtractDataCPL class
 * 
 * @author Derrick Dischinger
 */
public class ExtractDataMPOTest {
    ExtractDataMPO test;
    // ExtractDataCPL test02;
    // ExtractDataCPL test03;
    ArrayList<BufferedReader> validFile;
    HashMap<String, ArrayList<String>> partyCandidates;
    ArrayList<ArrayList<Object>> partyVotes;
    ArrayList<ArrayList<Object>> candidateVotes;
    
    @Before
    public void setUp() throws IOException {
        test = new ExtractDataMPO(validFile, "MPO");
    }

    @Test
    public void testVerifyLineIsDigit() {
        // Test 1.a
        String line = "";
        assertEquals(false, test.verifyLineIsDigit(line));

        // Test 1.b
        line = "1";
        assertEquals(true, test.verifyLineIsDigit(line));

        // Test 1.c
        line = " 1";
        assertEquals(true, test.verifyLineIsDigit(line));

        // Test 1.d
        line = "abc";
        assertEquals(false, test.verifyLineIsDigit(line));

        // Test 1.e
        line = "12b";
        assertEquals(false, test.verifyLineIsDigit(line));
    }

    @Test
    public void testFormatPartyInformation() throws IOException {

        //test 2.a

        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File(
            "src/test/java/InputFiles/MPOPartyInfo01.txt")))));
        test = new ExtractDataMPO(validFile, "MPO");
        test.validFile = validFile.get(0);
        partyCandidates = test.formatPartyInformation(partyVotes, candidateVotes, true);
        HashMap<String, ArrayList<String>> expected = new HashMap<String, ArrayList<String>>();

        expected.put("D", new ArrayList<>(Arrays.asList("Shana", "Risako")));
        expected.put("R", new ArrayList<>(Arrays.asList("Jon")));
        expected.put("G", new ArrayList<>(Arrays.asList("Chris")));
        expected.put("I", new ArrayList<>(Arrays.asList("Daniel")));

        assertEquals(expected, partyCandidates);

        // Test 2.b numPatries = 0 which is the wrong number
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MPOPartyInfo02.txt")))));
        test = new ExtractDataMPO(validFile, "MPO");
        test.validFile = validFile.get(0);
        partyCandidates = test.formatPartyInformation(partyVotes, candidateVotes, true);
        expected = new HashMap<String, ArrayList<String>>();
        expected.put("D", new ArrayList<>(Arrays.asList("Shana", "Risako")));
        expected.put("", new ArrayList<>(Arrays.asList("Jon")));
        expected.put("G", new ArrayList<>(Arrays.asList("Chris")));
        expected.put("I", new ArrayList<>(Arrays.asList("Daniel")));

        assertEquals(expected, partyCandidates);

        // test 2.c
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MPOPartyInfo03.txt")))));
        test = new ExtractDataMPO(validFile, "MPO");
        test.validFile = validFile.get(0);
        partyCandidates = test.formatPartyInformation(partyVotes, candidateVotes, true);
        expected = new HashMap<String, ArrayList<String>>();
        expected.put("D", new ArrayList<>(Arrays.asList("Shana", "Risako")));
        expected.put("R", new ArrayList<>(Arrays.asList("")));
        expected.put("G", new ArrayList<>(Arrays.asList("Chris")));
        expected.put("I", new ArrayList<>(Arrays.asList("Daniel")));

        assertEquals(expected, partyCandidates);
    }

    @Test
    public void testFormatBallotInformation() throws IOException {
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();

        // Test 3.a correct formatting
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MPOBallotTest01.txt")))));
        test = new ExtractDataMPO(validFile, "MPO");
        test.validFile = validFile.get(0);
        partyCandidates = test.formatPartyInformation(partyVotes, candidateVotes, true);
        test.formatBallotInformation(partyVotes, candidateVotes, partyCandidates);
        ArrayList<ArrayList<Object>> expectedPartyVotes = new ArrayList<>();
        ArrayList<ArrayList<Object>> expectedCandidateVotes = new ArrayList<>();

        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Jon", 150224)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Shana", 150437)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Risako", 149660)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Chris", 150129)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Daniel", 149550)));

        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("R", 150224)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("D", 300097)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("G", 150129)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("I", 149550)));

        assertEquals(expectedPartyVotes, partyVotes);
        assertEquals(expectedCandidateVotes, candidateVotes);


        // Test 3.a l instead of 1

        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MPOBallotTest02.txt")))));
        test = new ExtractDataMPO(validFile, "MPO");
        test.validFile = validFile.get(0);
        partyCandidates = test.formatPartyInformation(partyVotes, candidateVotes, true);

        assertThrows(IOException.class,
                () -> test.formatBallotInformation(partyVotes, candidateVotes, partyCandidates));

        // test 3.c line is null
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MPOPartyInfo01.txt")))));
        test = new ExtractDataMPO(validFile, "MPO");
        test.validFile = validFile.get(0);
        partyCandidates = test.formatPartyInformation(partyVotes, candidateVotes, true);

        // 3.d no votes technically
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MPOBallotTest03.txt")))));
        test = new ExtractDataMPO(validFile, "MPO");
        test.validFile = validFile.get(0);
        partyCandidates = test.formatPartyInformation(partyVotes, candidateVotes, true);
        expectedPartyVotes = new ArrayList<>();
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Jon", 0)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Shana", 0)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Risako", 0)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Chris", 0)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Daniel", 0)));

        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("R", 0)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("D", 0)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("G", 0)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("I", 0)));

        assertThrows(IOException.class,
                () -> test.formatBallotInformation(partyVotes, candidateVotes, partyCandidates));

    }

    @Test
    public void testExtractDataMPOInput01() throws IOException {
        partyCandidates = new HashMap<String, ArrayList<String>>();
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MPOInput001.txt")))));
        String header = "MPO";
        test = new ExtractDataMPO(validFile, header);
        FileData t = test.extractFromFile(true);

        // Test 4.a Header = "MPO"
        assertEquals("MPO", t.getElectionType());

        // Test 4.b Number of Seats
        assertEquals(2, t.getNumberSeats());

        // Test 4.c Number of Ballots
        assertEquals(120000, t.getNumberBallots());

        // Test 4.e Number of Parties
        assertEquals(5, t.getNumberParties());

        partyCandidates = new HashMap<>();
        partyCandidates.put("D", new ArrayList<>(Arrays.asList("Shana","Daniel")));
        partyCandidates.put("R", new ArrayList<>(Arrays.asList("Carl","Risako","Chris")));


        assertEquals(true, t.getPartyCandidates().containsKey("D"));
        assertEquals(true, t.getPartyCandidates().containsKey("R"));
        assertEquals("[Shana, Daniel]", t.getPartyCandidates().get("D").toString());
        assertEquals("[Carl, Risako, Chris]", t.getPartyCandidates().get("R").toString());


        // Test 4.f partyVotes
        partyVotes = new ArrayList<>();
        partyVotes.add(new ArrayList<>(Arrays.asList("D", 47991)));
        partyVotes.add(new ArrayList<>(Arrays.asList("R", 72009)));


        assertEquals(partyVotes, t.getPartyVotes());

        // Test 4.g candidateVotes
        candidateVotes = new ArrayList<>();
        candidateVotes.add(new ArrayList<>(Arrays.asList("Shana", 23948)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Daniel", 24043)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Carl", 23722)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Risako", 24289)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Chris", 23998)));

        assertEquals(candidateVotes, t.getCandidateVotes());
    }

    @Test
    public void testExtractDataMPOInput04() throws IOException {
        partyCandidates = new HashMap<String, ArrayList<String>>();
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MPOInputMulti01.txt")))));
        String header = "MPO";
        test = new ExtractDataMPO(validFile, header);
        FileData t = test.extractFromFile(true);

        // Test 4.a Header = "MPO"
        assertEquals("MPO", t.getElectionType());

        // Test 4.b Number of Seats
        assertEquals(4, t.getNumberSeats());

        // Test 4.c Number of Ballots
        assertEquals(1000000, t.getNumberBallots());

        // Test 4.e Number of Parties
        assertEquals(6, t.getNumberParties());

        partyCandidates = new HashMap<>();
        partyCandidates.put("D", new ArrayList<>(Arrays.asList("Shana","Risako")));
        partyCandidates.put("R", new ArrayList<>(Arrays.asList("Jon","Carl")));
        partyCandidates.put("G", new ArrayList<>(Arrays.asList("Chris")));
        partyCandidates.put("I", new ArrayList<>(Arrays.asList("Daniel")));


        assertEquals(true, t.getPartyCandidates().containsKey("D"));
        assertEquals(true, t.getPartyCandidates().containsKey("R"));
        assertEquals(true, t.getPartyCandidates().containsKey("G"));
        assertEquals(true, t.getPartyCandidates().containsKey("I"));
        assertEquals("[Shana, Risako]", t.getPartyCandidates().get("D").toString());
        assertEquals("[Jon, Carl]", t.getPartyCandidates().get("R").toString());
        assertEquals("[Chris]", t.getPartyCandidates().get("G").toString());
        assertEquals("[Daniel]", t.getPartyCandidates().get("I").toString());

        // Test 4.f candidateVotes
        candidateVotes = new ArrayList<>();
        candidateVotes.add(new ArrayList<>(Arrays.asList("Jon", 166483)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Shana", 166680)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Risako", 166996)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Chris", 166385)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Carl", 166630)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Daniel", 166826)));


        assertEquals(candidateVotes, t.getCandidateVotes());
    }

    @Test
    public void testExtractDataMPOInput02() throws IOException {
        //5.a
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MPOInput02.txt")))));
        String header = "MPO";
        test.validFile = validFile.get(0);
        test = new ExtractDataMPO(validFile, header);

        assertThrows(IOException.class, () -> test.extractFromFile());
    }

    @Test
    public void testExtractDataCPLMultipleFiles() throws IOException {
        validFile = new ArrayList<BufferedReader>();
        validFile.add(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MPOInputMulti01.txt"))));
        validFile.add(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MPOInputMulti02.txt"))));
        validFile.add(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MPOInputMulti03.txt"))));

        String header = "MPO";
        test = new ExtractDataMPO(validFile, header);
        FileData t = test.extractFromFile(true);

        // Test 7.a Checks Header
        assertEquals("MPO", t.getElectionType());

        // Test 7.b Number of Seats
        assertEquals(4, t.getNumberSeats());

        // Test 7.c Number of Ballots
        assertEquals(2102000, t.getNumberBallots());

        // Test 7.d Number of Parties
        assertEquals(6, t.getNumberParties());

        // Test 7.e Party Candidates
        partyCandidates = new HashMap<>();
        partyCandidates.put("D", new ArrayList<>(Arrays.asList("Shana", "Risako")));
        partyCandidates.put("R", new ArrayList<>(Arrays.asList("Jon", "Carl")));
        partyCandidates.put("G", new ArrayList<>(Arrays.asList("Chris")));
        partyCandidates.put("I", new ArrayList<>(Arrays.asList("Daniel")));

        assertEquals(true, t.getPartyCandidates().containsKey("D"));
        assertEquals(true, t.getPartyCandidates().containsKey("R"));
        assertEquals(true, t.getPartyCandidates().containsKey("G"));
        assertEquals(true, t.getPartyCandidates().containsKey("I"));
        assertEquals("[Shana, Risako]", t.getPartyCandidates().get("D").toString());
        assertEquals("[Jon, Carl]", t.getPartyCandidates().get("R").toString());
        assertEquals("[Chris]", t.getPartyCandidates().get("G").toString());
        assertEquals("[Daniel]", t.getPartyCandidates().get("I").toString());

        // Test 7.f Candidate Votes
        candidateVotes = new ArrayList<>();
        candidateVotes.add(new ArrayList<>(Arrays.asList("Jon", 350468)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Shana", 350413)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Risako", 350357)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Chris", 349958)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Carl", 350539)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Daniel", 350265)));

        assertEquals(candidateVotes, t.getCandidateVotes());
    }

}