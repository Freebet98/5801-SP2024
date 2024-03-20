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
    ArrayList<ArrayList<Object>> partyVotes;
    ArrayList<ArrayList<Object>> candidateVotes;

    @Before
    public void setUp() throws IOException {
        // Ask teammates how to do this with a relative path
        String filePath = "C:\\Users\\betha\\OneDrive\\Documents\\GitHub\\repo-Team2\\Project1\\src\\test\\java\\InputFiles\\CPLInput01.txt";
        File file = new File(filePath);
        FileReader fileR = new FileReader(file);
        BufferedReader validFile01 = new BufferedReader(fileR);
        test01 = new ExtractDataCPL(validFile01, "CPL");

        filePath = "C:\\Users\\betha\\OneDrive\\Documents\\GitHub\\repo-Team2\\Project1\\src\\test\\java\\InputFiles\\CPLInput02.txt";
        File file2 = new File(filePath);
        FileReader fileR2 = new FileReader(file2);
        BufferedReader validFile2 = new BufferedReader(fileR2);
        test02 = new ExtractDataCPL(validFile2, "CPL");
        // File file3 = new File("CPLTest03.txt");
        // FileReader fileR3 = new FileReader(file3);
        // BufferedReader validFile3 = new BufferedReader(fileR3);
        // test03 = new ExtractDataCPL(validFile3, "CPL");
    }

    public void readToPointInFile(BufferedReader validFile, int numLines) throws IOException{
        for(int i = 0; i < numLines; i++){
            validFile.readLine();
        }
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
        readToPointInFile(validFile01, 4);

        assertEquals(partyCandidates, test01.formatPartyInformation(4, partyVotes, candidateVotes));

        // Test 2.b 
        //six parties but only 5 listed on ballot, formatBallotInformation will throw an error
        partyVotes = new ArrayList<>();
        candidateVotes = new ArrayList<>();
        partyCandidates = new HashMap<>();
        partyCandidates.put("Democratic", new ArrayList<>(Arrays.asList("Mary", "Jane", "Kim")));
        partyCandidates.put("Republican", new ArrayList<>(Arrays.asList("Allen", "Joe", "Sarah")));
        partyCandidates.put("Green", new ArrayList<>(Arrays.asList("Sally", "Nikki")));
        partyCandidates.put("Independant", new ArrayList<>(Arrays.asList("Mike")));
        partyCandidates.put("Grass", new ArrayList<>(Arrays.asList("Mars", "Jacob")));
    }

    @Test
    public void testFormatBallotInformation() throws IOException {
        // Test 3.a
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

        // Test 3.b
        partyVotes = new ArrayList<>();
        candidateVotes = new ArrayList<>();
        assertEquals(exception, test02.formatBallotInformation(partyVotes, candidateVotes));
        
    }

    @Test
    public void testExtractFromFile() throws IOException {
        FileData test = test01.extractFromFile();
        HashMap<String, ArrayList<String>> partyCandidates = new HashMap<>();
        partyCandidates.put("Democratic", new ArrayList<>(Arrays.asList("Mary", "Jane", "Kim")));
        partyCandidates.put("Republican", new ArrayList<>(Arrays.asList("Allen", "Joe", "Sarah")));
        partyCandidates.put("Green", new ArrayList<>(Arrays.asList("Sally", "Nikki")));
        partyCandidates.put("Independant", new ArrayList<>(Arrays.asList("Mike")));

        // Test 4.1a
        assertEquals("CPL", test.getElectionType());

        // Test 4.1b
        assertEquals(3, test.getNumberSeats());

        // Test 4.1c
        assertEquals(100000, test.getNumberBallots());

        // Test 4.1d
        assertEquals(4, test.getNumberParties());

        // Test 4.1e
        assertEquals(partyCandidates, test.getPartyCandidates());

        //Test 4.2a
        assertEquals(exception, test02.extractFromFile());

        
    }
}
