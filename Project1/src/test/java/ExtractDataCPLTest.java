import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ExtractDataCPLTest {
    ExtractDataCPL test01;
    ExtractDataCPL test02;
    ExtractDataCPL test03;
    BufferedReader validFile01;
    ArrayList<ArrayList<Object>> partyVotes;
    ArrayList<ArrayList<Object>> candidateVotes;

    @Before
    public void setUp() throws IOException {
        String filePath = "C:/Users/cs-apal/Documents/GitHub/repo-Team2/Project1/src/test/java/CPLInput01.txt";
        File file = new File(filePath);
        FileReader fileR = new FileReader(file);
        BufferedReader validFile01 = new BufferedReader(fileR);
        test01 = new ExtractDataCPL(validFile01, "CPL");
        readLineTillBallotInfo(validFile01);

        // File file2 = new File("CPLTest02.txt");
        // FileReader fileR2 = new FileReader(file2);
        // BufferedReader validFile2 = new BufferedReader(fileR2);
        // test02 = new ExtractDataCPL(validFile2, "CPL");
        // File file3 = new File("CPLTest03.txt");
        // FileReader fileR3 = new FileReader(file3);
        // BufferedReader validFile3 = new BufferedReader(fileR3);
        // test03 = new ExtractDataCPL(validFile3, "CPL");
    }

    public void readLineTillBallotInfo(BufferedReader validFile) throws IOException {
        for (int i = 0; i < 4; i++) {
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
                "Party: Democratic, Votes: 24936\nParty: Republican, Votes: 25093\nParty: Green, Votes: 25067\nParty: Independent, Votes: 24904\n",
                printPartyVotes(partyVotes));

        // Test 1.b
        partyVotes = new ArrayList<>();
        candidateVotes = new ArrayList<>();
        partyNames = new String[] { "Democratic", "Independent" };
    }
}
