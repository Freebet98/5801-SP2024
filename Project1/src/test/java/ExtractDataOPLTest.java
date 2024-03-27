
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

                // Test 2.b numParties = 0 which is the wrong number
                validFile = new BufferedReader(new FileReader(new File("src/test/java/InputFiles/OPLPartyInfo01.txt")));
                test01 = new ExtractDataOPL(validFile, "OPL");
                partyCandidates = test01.formatPartyInformation(0, partyVotes, candidateVotes);
                expected = new HashMap<String, ArrayList<String>>();

                assertEquals(expected, partyCandidates);

                // Test 2.c Pluto has no candidates
                validFile = new BufferedReader(
                                new FileReader(new File("src\\test\\java\\InputFiles\\OPLPartyInfo02.txt")));
                test01 = new ExtractDataOPL(validFile, "OPL");

                assertThrows(IOException.class,
                                () -> partyCandidates = test01.formatPartyInformation(5, partyVotes, candidateVotes));

        }

        @Test
        public void testFormatBallotInformation() throws IOException {

                // Test 3.a correct formatting
                partyVotes = new ArrayList<ArrayList<Object>>();
                candidateVotes = new ArrayList<ArrayList<Object>>();
                validFile = new BufferedReader(
                                new FileReader(new File("src/test/java/InputFiles/OPLBallotTest01.txt")));
                test01 = new ExtractDataOPL(validFile, "OPL");
                partyCandidates = test01.formatPartyInformation(5, partyVotes,
                                candidateVotes);
                test01.formatBallotInformation(partyVotes, candidateVotes, partyCandidates);
                ArrayList<ArrayList<Object>> expectedPartyVotes = new ArrayList<>();
                expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Pluto", 40048)));
                expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Green", 59952)));

                ArrayList<ArrayList<Object>> expectedCandidateVotes = new ArrayList<>();
                expectedCandidateVotes.add(new ArrayList<>(Arrays.asList(" Becky", 20105)));
                expectedCandidateVotes.add(new ArrayList<>(Arrays.asList(" Jonah", 19943)));
                expectedCandidateVotes.add(new ArrayList<>(Arrays.asList(" Mariah", 19943)));
                expectedCandidateVotes.add(new ArrayList<>(Arrays.asList(" Radius", 20020)));
                expectedCandidateVotes.add(new ArrayList<>(Arrays.asList(" Louis", 19989)));

                assertEquals(expectedPartyVotes, partyVotes);
                assertEquals(expectedCandidateVotes, candidateVotes);

                // Test 3.b incorrect formatting on one of the votes
                partyVotes = new ArrayList<ArrayList<Object>>();
                candidateVotes = new ArrayList<ArrayList<Object>>();
                validFile = new BufferedReader(
                                new FileReader(new File("src\\test\\java\\InputFiles\\OPLBallotTest02.txt")));
                test01 = new ExtractDataOPL(validFile, "OPL");
                partyCandidates = test01.formatPartyInformation(5, partyVotes, candidateVotes);

                assertThrows(IOException.class,
                                () -> test01.formatBallotInformation(partyVotes, candidateVotes,
                                                partyCandidates));

                // Test 3.c no votes technically
                partyVotes = new ArrayList<ArrayList<Object>>();
                candidateVotes = new ArrayList<ArrayList<Object>>();
                validFile = new BufferedReader(
                                new FileReader(new File("src\\test\\java\\InputFiles\\OPLBallotTest03.txt")));
                test01 = new ExtractDataOPL(validFile, "OPL");
                partyCandidates = test01.formatPartyInformation(5, partyVotes,
                                candidateVotes);

                assertThrows(IOException.class,
                                () -> test01.formatBallotInformation(partyVotes, candidateVotes,
                                                partyCandidates));

                // Test 3.d correct formatting two
                partyVotes = new ArrayList<ArrayList<Object>>();
                candidateVotes = new ArrayList<ArrayList<Object>>();
                validFile = new BufferedReader(
                                new FileReader(new File("src\\test\\java\\InputFiles\\OPLBallotTest04.txt")));
                test01 = new ExtractDataOPL(validFile, "OPL");
                partyCandidates = test01.formatPartyInformation(5, partyVotes,
                                candidateVotes);
                test01.formatBallotInformation(partyVotes, candidateVotes, partyCandidates);
                expectedPartyVotes = new ArrayList<>();
                expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Pluto", 40052)));
                expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Green", 59948)));

                expectedCandidateVotes = new ArrayList<>();
                expectedCandidateVotes.add(new ArrayList<>(Arrays.asList(" Becky", 20132)));
                expectedCandidateVotes.add(new ArrayList<>(Arrays.asList(" Jonah", 20054)));
                expectedCandidateVotes.add(new ArrayList<>(Arrays.asList(" Mariah", 19920)));
                expectedCandidateVotes.add(new ArrayList<>(Arrays.asList(" Radius", 19826)));
                expectedCandidateVotes.add(new ArrayList<>(Arrays.asList(" Louis", 20068)));

                assertEquals(expectedPartyVotes, partyVotes);

        }

        @Test
        public void testExtractFromFile() throws IOException {
                partyCandidates = new HashMap<String, ArrayList<String>>();
                partyVotes = new ArrayList<ArrayList<Object>>();
                candidateVotes = new ArrayList<ArrayList<Object>>();
                validFile = new BufferedReader(new FileReader(new File("src\\test\\java\\InputFiles\\OPLInput01.txt")));
                String header = validFile.readLine();
                test01 = new ExtractDataOPL(validFile, header);
                FileData test = test01.extractFromFile();

                // Test 5.a Header = "CPL"
                assertEquals("OPL", test.getElectionType());

                // Test 5.b Number of Seats
                assertEquals(4, test.getNumberSeats());

                // Test 5.c Number of Ballots
                assertEquals(120000, test.getNumberBallots());

                // Test 5.e Number of Parties
                assertEquals(5, test.getNumberParties());

                // Test 5.e partyCandidates
                HashMap<String, ArrayList<String>> expected = new HashMap<String, ArrayList<String>>();
                expected.put("Pluto", new ArrayList<>(Arrays.asList(" Becky", " Mariah")));
                expected.put("Green", new ArrayList<>(Arrays.asList(" Jonah", " Radius", " Louis")));

                assertEquals(expected, test.getPartyCandidates());

                // Test 5.f partyVotes
                ArrayList<ArrayList<Object>> expectedPartyVotes = new ArrayList<>();
                expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Pluto", 48107)));
                expectedPartyVotes.add(new ArrayList<>(Arrays.asList("Green", 71893)));

                assertEquals(expectedPartyVotes, test.getPartyVotes());

                // Test 5.g candidateVotes
                ArrayList<ArrayList<Object>> expectedCandidateVotes = new ArrayList<>();
                expectedCandidateVotes.add(new ArrayList<>(Arrays.asList(" Becky", 23971)));
                expectedCandidateVotes.add(new ArrayList<>(Arrays.asList(" Jonah", 24014)));
                expectedCandidateVotes.add(new ArrayList<>(Arrays.asList(" Mariah", 24136)));
                expectedCandidateVotes.add(new ArrayList<>(Arrays.asList(" Radius", 24006)));
                expectedCandidateVotes.add(new ArrayList<>(Arrays.asList(" Louis", 23873)));

                assertEquals(expectedCandidateVotes, test.getCandidateVotes());

        }
}
