
/**
 * This class is used to test the ExtractDataCPL class
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

public class ExtractDataCPLTest {
    ExtractDataCPL test01;
    ExtractDataCPL test02;
    ExtractDataCPL test03;
    BufferedReader validFile01;
    HashMap<String, ArrayList<String>> partyCandidates;
    ArrayList<ArrayList<Object>> partyVotes;
    ArrayList<ArrayList<Object>> candidateVotes;

    @Before
    public void setUp() throws IOException {
        test01 = new ExtractDataCPL(validFile01, "CPL");
    }

    public String printPartyVotes(ArrayList<ArrayList<Object>> partyVotes) {
        String line = "";
        for (ArrayList<Object> tuple : partyVotes) {
            String partyName = (String) tuple.get(0);
            int votes = (int) tuple.get(1);
            line += ("Party: " + partyName + ", Votes: " + votes + "\n");
        }

        return line;
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

        // Test 1.a numParties = 6 which is the right number
        validFile01 = new BufferedReader(new FileReader(new File(
                "C:/Users/cs-apal/Documents/GitHub/repo-Team2/Project1/src/test/java/InputFiles/CPLPartyInfo01.txt")));
        test01 = new ExtractDataCPL(validFile01, "CPL");
        partyCandidates = test01.formatPartyInformation(6, partyVotes, candidateVotes);
        HashMap<String, ArrayList<String>> expected = new HashMap<String, ArrayList<String>>();
        partyCandidates.put("Democratic", new ArrayList<>(Arrays.asList("Mary", "Jane", "Kim")));
        partyCandidates.put("Republican", new ArrayList<>(Arrays.asList("Allen", "Joe", "Sarah")));
        partyCandidates.put("Green", new ArrayList<>(Arrays.asList("Sally", "Nikki")));
        partyCandidates.put("Independant", new ArrayList<>(Arrays.asList("Mike")));
        partyCandidates.put("Grass", new ArrayList<>(Arrays.asList("Mars", "Jacob")));
        partyCandidates.put("Pluto", new ArrayList<>(Arrays.asList("Space", "Stars")));

        assertEquals(expected, partyCandidates);

        // Test 1.b numPatries = 0 which is the wrong number
        validFile01 = new BufferedReader(new FileReader(new File("C:/Users/cs-apal/Documents/GitHub/repo-Team2/Project1/src/test/java/InputFiles/CPLPartyInfo02.txt")));
        test01 = new ExtractDataCPL(validFile01, "CPL");
        partyCandidates = test01.formatPartyInformation(0, partyVotes, candidateVotes);
        HashMap<String, ArrayList<String>> expected = new HashMap<String, ArrayList<String>>();

        assertEquals(expected, partyCandidates);

        // Test 1.c Independent has no candidates
        validFile01 = new BufferedReader(new FileReader(new File("C:/Users/cs-apal/Documents/GitHub/repo-Team2/Project1/src/test/java/InputFiles/CPLPartyInfo03.txt")));
        test01 = new ExtractDataCPL(validFile01, "CPL");
        partyCandidates = test01.formatPartyInformation(0, partyVotes, candidateVotes);
        expected = new HashMap<String, ArrayList<String>>();
        partyCandidates.put("Democratic", new ArrayList<>(Arrays.asList("Mary", "Jane", "Kim")));
        partyCandidates.put("Republican", new ArrayList<>(Arrays.asList("Allen", "Joe", "Sarah")));
        partyCandidates.put("Green", new ArrayList<>(Arrays.asList("Sally", "Nikki")));
        partyCandidates.put("Independant", new ArrayList<>());
        partyCandidates.put("Grass", new ArrayList<>(Arrays.asList("Mars", "Jacob")));
        partyCandidates.put("Pluto", new ArrayList<>(Arrays.asList("Space", "Stars")));

        assertEquals(expected, partyCandidates);

    }

    @Test
    public void testExtractDataCPLInput01() throws IOException {
        partyCandidates = new HashMap<String, ArrayList<String>>();
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();
        validFile01 = new BufferedReader(new FileReader(new File("CPLInput01.txt")));
        test01 = new ExtractDataCPL(validFile01, "CPL");
        test01.formatBallotInformation(partyVotes, candidateVotes, partyCandidates);

        // Test 1.a

        //
    }
}
