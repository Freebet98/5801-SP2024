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
    * This class is used to test the ExtractDataMV class
    * 
    * @author Rock Zgutowicz
    * @Preconditions:
    *                 The ExtractDataMV class must be created
    *                 The abstract ExtractData class be created
    *                 The FileData class must be created
    *                 All test files must be created and properly formatted
*/
public class ExtractDataMVTest {
    ExtractDataMV test;
    ArrayList<BufferedReader> validFile;
    HashMap<String, ArrayList<String>> partyCandidates;
    ArrayList<ArrayList<Object>> partyVotes;
    ArrayList<ArrayList<Object>> candidateVotes;

    @Before
    public void setUp() throws IOException {
        test = new ExtractDataMV(validFile, "MV");
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
            "src/test/java/InputFiles/MVPartyInfo01.txt")))));
        test = new ExtractDataMV(validFile, "MV");
        test.validFile = validFile.get(0);
        partyCandidates = test.formatPartyInformation(partyVotes, candidateVotes, false);
        HashMap<String, ArrayList<String>> expected = new HashMap<String, ArrayList<String>>();

        expected.put("D", new ArrayList<>(Arrays.asList("Pike", "Foster")));
        expected.put("R", new ArrayList<>(Arrays.asList("Deutsch", "Borg", "Jones")));
        expected.put("I", new ArrayList<>(Arrays.asList("Smith")));

        assertEquals(expected, partyCandidates);

        // Test 2.b
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MVPartyInfo02.txt")))));
        test = new ExtractDataMV(validFile, "MV");
        test.validFile = validFile.get(0);
        partyCandidates = test.formatPartyInformation(partyVotes, candidateVotes, false);
        expected = new HashMap<String, ArrayList<String>>();
        expected.put("D", new ArrayList<>(Arrays.asList("Pike", "Foster")));
        expected.put("R", new ArrayList<>(Arrays.asList("Deutsch", "Borg", "Jones")));
        expected.put("", new ArrayList<>(Arrays.asList("Smith")));

        assertEquals(expected, partyCandidates);

        // test 2.c
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MVPartyInfo03.txt")))));
        test = new ExtractDataMV(validFile, "MV");
        test.validFile = validFile.get(0);
        partyCandidates = test.formatPartyInformation(partyVotes, candidateVotes, true);
        expected = new HashMap<String, ArrayList<String>>();
        expected.put("D", new ArrayList<>(Arrays.asList("Pike", "Foster")));
        expected.put("R", new ArrayList<>(Arrays.asList("Deutsch", "Borg", "Jones")));
        expected.put("I", new ArrayList<>(Arrays.asList("")));

        assertEquals(expected, partyCandidates);
    }


    @Test
    public void testFormatBallotInformation() throws IOException {
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();

        // Test 3.a correct formatting
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MVBallotTest01.txt")))));
        test = new ExtractDataMV(validFile, "MV");
        test.validFile = validFile.get(0);
        partyCandidates = test.formatPartyInformation(partyVotes, candidateVotes, false);
        test.formatBallotInformation(partyVotes, candidateVotes, partyCandidates, 3);
        ArrayList<ArrayList<Object>> expectedPartyVotes = new ArrayList<>();
        ArrayList<ArrayList<Object>> expectedCandidateVotes = new ArrayList<>();

        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Pike", 4)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Foster", 3)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Deutsch", 4)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Borg", 4)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Jones", 4)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Smith", 3)));

        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("D", 7)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("R", 12)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("I", 3)));

        assertEquals(expectedPartyVotes, partyVotes);
        assertEquals(expectedCandidateVotes, candidateVotes);


        // Test 3.b l instead of 1

        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MVBallotTest02.txt")))));
        test = new ExtractDataMV(validFile, "MV");
        test.validFile = validFile.get(0);
        partyCandidates = test.formatPartyInformation(partyVotes, candidateVotes, false);

        assertThrows(IOException.class,
                () -> test.formatBallotInformation(partyVotes, candidateVotes, partyCandidates, 3));

        // // test 3.c line is null
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MVPartyInfo01.txt")))));
        test = new ExtractDataMV(validFile, "MV");
        test.validFile = validFile.get(0);
        partyCandidates = test.formatPartyInformation(partyVotes, candidateVotes, false);

        // 3.d no votes technically
        partyVotes = new ArrayList<ArrayList<Object>>();
        candidateVotes = new ArrayList<ArrayList<Object>>();
        validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MVBallotTest03.txt")))));
        test = new ExtractDataMV(validFile, "MV");
        test.validFile = validFile.get(0);
        partyCandidates = test.formatPartyInformation(partyVotes, candidateVotes, true);
        expectedPartyVotes = new ArrayList<>();
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Pike", 0)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Foster", 0)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Deutsch", 0)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Borg", 0)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Jones", 0)));
        expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Smith", 0)));

        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("D", 0)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("R", 0)));
        expectedPartyVotes.add(new ArrayList<>(Arrays.asList("I", 0)));

        assertThrows(IOException.class,
                () -> test.formatBallotInformation(partyVotes, candidateVotes, partyCandidates, 3));
    }

}
