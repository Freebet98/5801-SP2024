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

}
