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
    ArrayList<ArrayList<Object>> partyVotes;
    ArrayList<ArrayList<Object>> candidateVotes;

    @Before
    public void setUp() throws IOException {
        // Ask teammates how to do this with a relative path
        String filePath = "C:\\Users\\bethany\\Documents\\GitHub\\repo-Team2\\Project1\\src\\test\\java\\CPLInput01.txt";
        File file = new File(filePath);
        FileReader fileR = new FileReader(file.getAbsolutePath());
        BufferedReader validFile01 = new BufferedReader(fileR);
        test01 = new ExtractDataCPL(validFile01, "CPL");

        // File file2 = new File("CPLTest02.txt");
        // FileReader fileR2 = new FileReader(file2);
        // BufferedReader validFile2 = new BufferedReader(fileR2);
        // test02 = new ExtractDataCPL(validFile2, "CPL");
        // File file3 = new File("CPLTest03.txt");
        // FileReader fileR3 = new FileReader(file3);
        // BufferedReader validFile3 = new BufferedReader(fileR3);
        // test03 = new ExtractDataCPL(validFile3, "CPL");
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
        String line = "12";
        assertEquals(true, test01.verifyLineIsDigit(line));

        // Test 1.b
        line = " 1";
        assertEquals(true, test01.verifyLineIsDigit(line));

        // Test 1.c
        line = "abc";
        assertEquals(false, test01.verifyLineIsDigit(line));

        // Test 1.d
        line = " 1a2";
        assertEquals(false, test01.verifyLineIsDigit(line));

        // Test 1.e
        line = "1a2";
        assertEquals(false, test01.verifyLineIsDigit(line));
    }

    @Test
    public void testFormatPartyInformation() throws IOException {
        // Test 2.a
        ArrayList<ArrayList<Object>> partyVotes = new ArrayList<>();
        ArrayList<ArrayList<Object>> candidateVotes = new ArrayList<>();
        HashMap<String, ArrayList<String>> partyCandidates = new HashMap<>();
        partyCandidates.put("Democratic", new ArrayList<>(Arrays.asList("Mary", "Jane", "Kim")));
        partyCandidates.put("Republican", new ArrayList<>(Arrays.asList("Allen", "Joe", "Sarah")));
        partyCandidates.put("Green", new ArrayList<>(Arrays.asList("Sally", "Nikki")));
        partyCandidates.put("Independant", new ArrayList<>(Arrays.asList("Mike")));

        assertEquals(partyCandidates, test01.formatPartyInformation(4, partyVotes, candidateVotes));

    }

    @Test
    public void testFormatBallotInformation() throws IOException {
        // Test 1.a
        partyVotes = new ArrayList<>();
        String[] partyNames = { "Democratic", "Republican", "Green", "Independent" };
        for (String partyName : partyNames) {
            ArrayList<Object> innerList = new ArrayList<>();
            innerList.add(partyName);
            innerList.add(0); // Assuming initial votes are 0
            partyVotes.add(innerList);
        }
        candidateVotes = new ArrayList<>();
        test01.formatBallotInformation(partyVotes, candidateVotes);
        assertEquals(
                "Party: Democratic, Votes: 24937\nParty: Republican, Votes: 25093\nParty: Green, Votes: 25067\nParty: Independent, Votes: 24904\n",
                printPartyVotes(partyVotes));

        // Test 1.b
        partyVotes = new ArrayList<>();
        candidateVotes = new ArrayList<>();
        partyNames = new String[] { "Democratic", "Independent" };
    }

    @Test
    public void testExtractFromFile() throws IOException {
        FileData test = test01.extractFromFile();
        HashMap<String, ArrayList<String>> partyCandidates = new HashMap<>();
        partyCandidates.put("Democratic", new ArrayList<>(Arrays.asList("Mary", "Jane", "Kim")));
        partyCandidates.put("Republican", new ArrayList<>(Arrays.asList("Allen", "Joe", "Sarah")));
        partyCandidates.put("Green", new ArrayList<>(Arrays.asList("Sally", "Nikki")));
        partyCandidates.put("Independant", new ArrayList<>(Arrays.asList("Mike")));

        // Test a
        assertEquals("CPL", test.getElectionType());

        // Test b
        assertEquals(3, test.getNumberSeats());

        // Test c
        assertEquals(100000, test.getNumberBallots());

        // Test d
        assertEquals(4, test.getNumberParties());

        // Test e
        assertEquals(partyCandidates, test.getPartyCandidates());
    }
}
