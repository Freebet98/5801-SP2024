
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
 * @author Bethany Freeman
 */
public class ExtractDataCPLTest {
    ExtractDataCPL test01;
    // ExtractDataCPL test02;
    // ExtractDataCPL test03;
    BufferedReader validFile01;
    HashMap<String, ArrayList<String>> partyCandidates;
    ArrayList<ArrayList<Object>> partyVotes;
    ArrayList<ArrayList<Object>> candidateVotes;

    @Before
    public void setUp() throws IOException {
        test01 = new ExtractDataCPL(validFile01, "CPL");
    }

    @Test
    public void testVerifyLineIsDigit() {
        // Test 1.a
        String line = "";
        assertEquals(false, test01.verifyLineIsDigit(line));

        // Test 1.b
        line = "1";
        assertEquals(true, test01.verifyLineIsDigit(line));

        // Test 1.c
        line = " 1";
        assertEquals(true, test01.verifyLineIsDigit(line));

        // Test 1.d
        line = "abc";
        assertEquals(false, test01.verifyLineIsDigit(line));

        // Test 1.e
        line = "12b";
        assertEquals(false, test01.verifyLineIsDigit(line));
    }

    @Test
    public void testFormatPartyInformation() throws IOException {
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();

        // Test 2.a numParties = 6 which is the right number
        validFile01 = new BufferedReader(new FileReader(new File(
                "src/testing/java/InputFiles/CPLPartyInfo01.txt")));
        test01 = new ExtractDataCPL(validFile01, "CPL");
        partyCandidates = test01.formatPartyInformation(6, partyVotes, candidateVotes);
        HashMap<String, ArrayList<String>> expected = new HashMap<String, ArrayList<String>>();
        expected.put("Democratic", new ArrayList<>(Arrays.asList(" Mary", " Jane", " Kim")));
        expected.put("Republican", new ArrayList<>(Arrays.asList(" Allen", " Joe", " Sarah")));
        expected.put("Green", new ArrayList<>(Arrays.asList(" Sally", " Nikki")));
        expected.put("Independant", new ArrayList<>(Arrays.asList(" Mike")));
        expected.put("Grass", new ArrayList<>(Arrays.asList(" Mars", " Jacob")));
        expected.put("Pluto", new ArrayList<>(Arrays.asList(" Space", " Stars")));

        assertEquals(expected, partyCandidates);

        // Test 2.b numPatries = 0 which is the wrong number
        validFile01 = new BufferedReader(new FileReader(new File("src/testing/java/InputFiles/CPLPartyInfo02.txt")));
        test01 = new ExtractDataCPL(validFile01, "CPL");
        partyCandidates = test01.formatPartyInformation(0, partyVotes, candidateVotes);
        expected = new HashMap<String, ArrayList<String>>();

        assertEquals(expected, partyCandidates);

        // Test 2.c Independent has no candidates
        validFile01 = new BufferedReader(new FileReader(new File("src\\testing\\java\\InputFiles\\CPLPartyInfo03.txt")));
        test01 = new ExtractDataCPL(validFile01, "CPL");
        partyCandidates = test01.formatPartyInformation(6, partyVotes, candidateVotes);
        expected = new HashMap<String, ArrayList<String>>();
        expected.put("Democratic", new ArrayList<>(Arrays.asList(" Mary", " Jane", " Kim")));
        expected.put("Republican", new ArrayList<>(Arrays.asList(" Allen", " Joe", " Sarah")));
        expected.put("Green", new ArrayList<>(Arrays.asList(" Sally", " Nikki")));
        expected.put("Independant", new ArrayList<>());
        expected.put("Grass", new ArrayList<>(Arrays.asList(" Mars", " Jacob")));
        expected.put("Pluto", new ArrayList<>(Arrays.asList(" Space", " Stars")));

        assertEquals(expected, partyCandidates);

    }

    @Test
    public void testFormatBallotInformation() throws IOException {
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();

        // Test 3.a correct formatting
        validFile01 = new BufferedReader(new FileReader(new File("src/testing/java/InputFiles/CPLBallotTest01.txt")));
        test01 = new ExtractDataCPL(validFile01, "CPL");
        partyCandidates = test01.formatPartyInformation(3, partyVotes, candidateVotes);
        test01.formatBallotInformation(partyVotes, candidateVotes, partyCandidates);
        ArrayList<ArrayList<Object>> expectedPartyVotes = new ArrayList<>();
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Grass", 26646)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Pluto", 26742)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Republican", 26612)));

        assertEquals(expectedPartyVotes, partyVotes);

        // Test 3.b incorrect formatting on one of the votes
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();
        validFile01 = new BufferedReader(new FileReader(new File("src\\testing\\java\\InputFiles\\CPLBallotTest02.txt")));
        test01 = new ExtractDataCPL(validFile01, "CPL");
        partyCandidates = test01.formatPartyInformation(3, partyVotes, candidateVotes);

        assertThrows(IOException.class,
                () -> test01.formatBallotInformation(partyVotes, candidateVotes, partyCandidates));

        // 3.c line is null
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();
        validFile01 = new BufferedReader(new FileReader(new File("src\\testing\\java\\InputFiles\\CPLPartyInfo01.txt")));
        test01 = new ExtractDataCPL(validFile01, "CPL");
        partyCandidates = test01.formatPartyInformation(3, partyVotes, candidateVotes);

        assertThrows(IOException.class,
                () -> test01.formatBallotInformation(partyVotes, candidateVotes, partyCandidates));

        // 3.d no votes technically
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();
        validFile01 = new BufferedReader(new FileReader(new File("src\\testing\\java\\InputFiles\\CPLBallotTest03.txt")));
        test01 = new ExtractDataCPL(validFile01, "CPL");
        partyCandidates = test01.formatPartyInformation(3, partyVotes, candidateVotes);
        expectedPartyVotes = new ArrayList<>();
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Grass", 0)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Pluto", 0)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Republican", 0)));

        assertThrows(IOException.class,
                () -> test01.formatBallotInformation(partyVotes, candidateVotes, partyCandidates));

        // 3.e correct formatting two
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();
        validFile01 = new BufferedReader(new FileReader(new File("src\\testing\\java\\InputFiles\\CPLBallotTest04.txt")));
        test01 = new ExtractDataCPL(validFile01, "CPL");
        partyCandidates = test01.formatPartyInformation(4, partyVotes, candidateVotes);
        test01.formatBallotInformation(partyVotes, candidateVotes, partyCandidates);
        expectedPartyVotes = new ArrayList<>();
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Green", 22648)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Independant", 22613)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Grass", 22231)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Pluto", 22508)));

        assertEquals(expectedPartyVotes, partyVotes);

    }

    @Test
    public void testExtractDataCPLInput01() throws IOException {
        partyCandidates = new HashMap<String, ArrayList<String>>();
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();
        validFile01 = new BufferedReader(new FileReader(new File("src\\testing\\java\\InputFiles\\CPLInput01.txt")));
        String header = validFile01.readLine();
        test01 = new ExtractDataCPL(validFile01, header);
        FileData test = test01.extractFromFile();

        // Test 4.a Header = "CPL"
        assertEquals("CPL", test.getElectionType());

        // Test 4.b Number of Seats
        assertEquals(3, test.getNumberSeats());

        // Test 4.c Number of Ballots
        assertEquals(100000, test.getNumberBallots());

        // Test 4.e Number of Parties
        assertEquals(4, test.getNumberParties());

        // Test 4.e partyCandidates
        partyCandidates = new HashMap<>();
        partyCandidates.put("Democratic", new ArrayList<>(Arrays.asList("Mary", "Jane", "Kim")));
        partyCandidates.put("Republican", new ArrayList<>(Arrays.asList("Allen, Joe, Sarah")));
        partyCandidates.put("Green", new ArrayList<>(Arrays.asList("Sally", "Nikki")));
        partyCandidates.put("Independent", new ArrayList<>(Arrays.asList("Mike")));

        assertEquals(true, test.getPartyCandidates().containsKey("Democratic"));
        assertEquals(true, test.getPartyCandidates().containsKey("Republican"));
        assertEquals(true, test.getPartyCandidates().containsKey("Green"));
        assertEquals(true, test.getPartyCandidates().containsKey("Independant"));
        assertEquals("[ Mary,  Jane,  Kim]", test.getPartyCandidates().get("Democratic").toString());
        assertEquals("[ Allen,  Joe,  Sarah]", test.getPartyCandidates().get("Republican").toString());
        assertEquals("[ Sally,  Nikki]", test.getPartyCandidates().get("Green").toString());
        assertEquals("[ Mike]", test.getPartyCandidates().get("Independant").toString());

        // Test 4.f partyVotes
        partyVotes = new ArrayList<>();
        partyVotes.add(new ArrayList<>(Arrays.asList("Democratic", 24936)));
        partyVotes.add(new ArrayList<>(Arrays.asList("Republican", 25093)));
        partyVotes.add(new ArrayList<>(Arrays.asList("Green", 25067)));
        partyVotes.add(new ArrayList<>(Arrays.asList("Independant", 24904)));

        assertEquals(partyVotes, test.getPartyVotes());

        // Test 4.g candidateVotes
        candidateVotes = new ArrayList<>();
        candidateVotes.add(new ArrayList<>(Arrays.asList(" Mary", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList(" Jane", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList(" Kim", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList(" Allen", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList(" Joe", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList(" Sarah", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList(" Sally", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList(" Nikki", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList(" Mike", 0)));

        assertEquals(candidateVotes, test.getCandidateVotes());
    }

    @Test
    public void testExtractDataCPLInput02() throws IOException {
        //5.a
        validFile01 = new BufferedReader(new FileReader(new File("src\\testing\\java\\InputFiles\\CPLInput02.txt")));
        String header = validFile01.readLine();
        test01 = new ExtractDataCPL(validFile01, header);

        assertThrows(IOException.class, () -> test01.extractFromFile());
    }

    @Test
    public void testExtractDataCPLInput03() throws IOException {
        //6.a
        validFile01 = new BufferedReader(new FileReader(new File("src\\testing\\java\\InputFiles\\CPLInput03.txt")));
        String header = validFile01.readLine();
        test01 = new ExtractDataCPL(validFile01, header);

        assertThrows(IOException.class, () -> test01.extractFromFile());
    }
}
