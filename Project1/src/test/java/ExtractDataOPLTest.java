/**
 * This class is used to test the ExtractDataOPL class
 * @author Bethany Freeman
 */
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class is used to test the ExtractDataOPL class
 * 
 * @author Bethany Freeman
 * @Preconditions:
 *                 The ExtractDataOPL class must be created
 *                 The abstract ExtractData class be created
 *                 The FileData class must be created
 *                 All test files must be created and properly formatted
 */
public class ExtractDataOPLTest {
    ExtractDataOPL test01;
    // ExtractDataOPL test02;
    // ExtractDataOPL test03;
    BufferedReader validFile;
    HashMap<String, ArrayList<String>> partyCandidates;
    ArrayList<ArrayList<Object>> partyVotes;
    ArrayList<ArrayList<Object>> candidateVotes;

    @Before
    public void setUp() throws IOException {
        test01 = new ExtractDataOPL(validFile, "OPL");
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
        validFile = new BufferedReader(new FileReader(new File(
                "src/test/java/InputFiles/OPLPartyInfo01.txt")));
        test01 = new ExtractDataOPL(validFile, "OPL");
        partyCandidates = test01.formatPartyInformation(5, partyVotes, candidateVotes);
        HashMap<String, ArrayList<String>> expected = new HashMap<String, ArrayList<String>>();
        expected.put("Pluto", new ArrayList<>(Arrays.asList(" Becky", " Mariah")));
        expected.put("Green", new ArrayList<>(Arrays.asList(" Jonah", " Radius", " Louis")));

        assertEquals(expected, partyCandidates);

        // Test 2.b numPatries = 0 which is the wrong number
        validFile = new BufferedReader(new FileReader(new File("src/test/java/InputFiles/OPLPartyInfo01.txt")));
        test01 = new ExtractDataOPL(validFile, "OPL");
        partyCandidates = test01.formatPartyInformation(0, partyVotes, candidateVotes);
        expected = new HashMap<String, ArrayList<String>>();

        assertEquals(expected, partyCandidates);

        // Test 2.c Pluto has no candidates
        validFile = new BufferedReader(new FileReader(new File("src\\test\\java\\InputFiles\\OPLPartyInfo02.txt")));
        test01 = new ExtractDataOPL(validFile, "OPL");
        partyCandidates = test01.formatPartyInformation(5, partyVotes, candidateVotes);
        expected = new HashMap<String, ArrayList<String>>();
        expected.put("Green", new ArrayList<>(Arrays.asList(" Jonah", " Radius", " Louis")));
        expected.put("Pluto", new ArrayList<>(Arrays.asList()));

        assertEquals(expected, partyCandidates);

    }

    @Test
    public void testPutVotesInPartyVotes() {

    }

    // @Test
    // public void testFormatBallotInformation() throws IOException {
    // partyVotes = new ArrayList<ArrayList<Object>>();
    // candidateVotes = new ArrayList<ArrayList<Object>>();

    // // Test 4.a correct formatting
    // validFile01 = new BufferedReader(new FileReader(new
    // File("src/test/java/InputFiles/CPLBallotTest01.txt")));
    // test01 = new ExtractDataCPL(validFile01, "CPL");
    // partyCandidates = test01.formatPartyInformation(3, partyVotes,
    // candidateVotes);
    // test01.formatBallotInformation(partyVotes, candidateVotes, partyCandidates);
    // ArrayList<ArrayList<Object>> expectedPartyVotes = new ArrayList<>();
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Grass", 26646)));
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Pluto", 26742)));
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Republican", 26612)));

    // assertEquals(expectedPartyVotes, partyVotes);

    // // Test 4.b incorrect formatting on one of the votes
    // partyVotes = new ArrayList<ArrayList<Object>>();
    // candidateVotes = new ArrayList<ArrayList<Object>>();
    // validFile01 = new BufferedReader(new FileReader(new
    // File("src\\test\\java\\InputFiles\\CPLBallotTest02.txt")));
    // test01 = new ExtractDataCPL(validFile01, "CPL");
    // partyCandidates = test01.formatPartyInformation(3, partyVotes,
    // candidateVotes);
    // expectedPartyVotes = new ArrayList<>();
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Grass", 16862)));
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Pluto", 16640)));
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Republican", 16498)));

    // assertThrows(IOException.class,
    // () -> test01.formatBallotInformation(partyVotes, candidateVotes,
    // partyCandidates));

    // // Test 4.c line is null
    // partyVotes = new ArrayList<ArrayList<Object>>();
    // candidateVotes = new ArrayList<ArrayList<Object>>();
    // validFile = new BufferedReader(new FileReader(new
    // File("src\\test\\java\\InputFiles\\CPLPartyInfo01.txt")));
    // test01 = new ExtractDataOPL(validFile, "OPL");
    // partyCandidates = test01.formatPartyInformation(3, partyVotes,
    // candidateVotes);
    // expectedPartyVotes = new ArrayList<>();
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Grass", 0)));
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Pluto", 0)));
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Republican", 0)));

    // assertThrows(IOException.class,
    // () -> test01.formatBallotInformation(partyVotes, candidateVotes,
    // partyCandidates));

    // // Test 4.d no votes technically
    // partyVotes = new ArrayList<ArrayList<Object>>();
    // candidateVotes = new ArrayList<ArrayList<Object>>();
    // validFile = new BufferedReader(new FileReader(new
    // File("src\\test\\java\\InputFiles\\CPLBallotTest03.txt")));
    // test01 = new ExtractDataOPL(validFile, "OPL");
    // partyCandidates = test01.formatPartyInformation(3, partyVotes,
    // candidateVotes);
    // test01.formatBallotInformation(partyVotes, candidateVotes, partyCandidates);
    // expectedPartyVotes = new ArrayList<>();
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Grass", 0)));
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Pluto", 0)));
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Republican", 0)));

    // assertEquals(expectedPartyVotes, partyVotes);

    // // Test 4.e correct formatting two
    // partyVotes = new ArrayList<ArrayList<Object>>();
    // candidateVotes = new ArrayList<ArrayList<Object>>();
    // validFile01 = new BufferedReader(new FileReader(new
    // File("src\\test\\java\\InputFiles\\CPLBallotTest04.txt")));
    // test01 = new ExtractDataCPL(validFile01, "CPL");
    // partyCandidates = test01.formatPartyInformation(4, partyVotes,
    // candidateVotes);
    // test01.formatBallotInformation(partyVotes, candidateVotes, partyCandidates);
    // expectedPartyVotes = new ArrayList<>();
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Green", 22648)));
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Independant", 22613)));
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Grass", 22231)));
    // expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Pluto", 22508)));

    // assertEquals(expectedPartyVotes, partyVotes);

    // }
}
