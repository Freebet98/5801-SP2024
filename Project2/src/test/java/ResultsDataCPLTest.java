import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class is used to test the ResultsDataCPL class
 * 
 * @author Bethany Freeman
 */
public class ResultsDataCPLTest {
    ArrayList<ArrayList<Object>> seatAlloc;
    ArrayList<ArrayList<Object>> remainVotes;
    ArrayList<String> partyWinOrder;
    ResultsDataCPL test;
    FileData testFile;
    ArrayList<ArrayList<Object>> finalWinOrder;
    ArrayList<ArrayList<Object>> partyVotes;
    HashMap<String, ArrayList<String>> partyCandidates;
    ArrayList<ArrayList<Object>> candidateVotes;

    @Before
    public void setUp() throws IOException {
        // FileData setup
        partyCandidates = new HashMap<>();
        partyCandidates.put("Dem", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));
        partyCandidates.put("Rep", new ArrayList<>(Arrays.asList("Craig", "Klein")));

        partyVotes = new ArrayList<>();
        partyVotes.add(new ArrayList<>(Arrays.asList("Dem", 750)));
        partyVotes.add(new ArrayList<>(Arrays.asList("Rep", 250)));

        candidateVotes = new ArrayList<>();
        candidateVotes.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Bob", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Jon", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Craig", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Klein", 0)));

        testFile = new FileData("CPL", 3, 1000, 2, partyCandidates, partyVotes, candidateVotes);

        // ResultsData setup
        seatAlloc = new ArrayList<>();
        seatAlloc.add(new ArrayList<>(Arrays.asList("Dem", new int[] { 2, 0 })));
        seatAlloc.add(new ArrayList<>(Arrays.asList("Rep", new int[] { 0, 1 })));

        remainVotes = new ArrayList<>();
        remainVotes.add(new ArrayList<>(Arrays.asList("Dem", 84)));
        remainVotes.add(new ArrayList<>(Arrays.asList("Rep", 250)));

        partyWinOrder = new ArrayList<>(Arrays.asList("Dem", "Dem", "Rep"));

        test = new ResultsDataCPL(seatAlloc, remainVotes, partyWinOrder, testFile);

        finalWinOrder = new ArrayList<>();
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Dem", "Sarah", 1)));
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Dem", "Bob", 2)));
        finalWinOrder.add(new ArrayList<>(Arrays.asList("Rep", "Craig", 3)));
    }

    private int[] getPercents(int index) {
        int[] seatAlloc = (int[]) this.seatAlloc.get(index).get(1);
        int totalSeats = seatAlloc[0] + seatAlloc[1];
        int[] percents = new int[2];

        int votes = (int) partyVotes.get(index).get(1);
        double perVote = ((double) votes / (double) testFile.getNumberBallots()) * 100;
        percents[0] = (int) Math.round(perVote);

        double perSeats = ((double) totalSeats / (double) testFile.getNumberSeats()) * 100;
        percents[1] = (int) Math.round(perSeats);

        return percents;
    }

    @Test
    public void testGetSeatAllocation() {
        // Test 1.a
        assertEquals(seatAlloc, test.getSeatAllocation());
    }

    @Test
    public void testGetRemainingVotes() {
        // Test 2.a
        assertEquals(remainVotes, test.getRemainingVotes());
    }

    @Test
    public void testGetFinalWinOrder() {
        // Test 3.a
        assertEquals(finalWinOrder, test.getFinalWinOrder());
    }

    @Test
    public void testGetPartyWinOrder() {
        // Test 4.a
        assertEquals(partyWinOrder, test.getPartyWinOrder());
    }

    @Test
    public void computeWinOrder() throws IOException{
        //Test 5.a partyWinOrder is empty
        partyWinOrder = new ArrayList<>();
        ArrayList<ArrayList<Object>> finalWinOrder = new ArrayList<>();
        test = new ResultsDataCPL(seatAlloc, remainVotes, partyWinOrder, testFile);

        assertEquals(finalWinOrder, test.getFinalWinOrder());

        //Test 5.b one party has more votes then supposed to
        partyWinOrder = new ArrayList<>(Arrays.asList("Rep", "Rep", "Rep"));
        
        assertThrows(IOException.class, () -> test = new ResultsDataCPL(seatAlloc, remainVotes, partyWinOrder, testFile));

        //Test 5.c partyWinOrder is normal
        partyWinOrder = new ArrayList<>(Arrays.asList("Dem", "Rep"));
        test = new ResultsDataCPL(seatAlloc, remainVotes, partyWinOrder, testFile);
        finalWinOrder = new ArrayList<>();
        finalWinOrder.add(new ArrayList<Object>(Arrays.asList("Dem", "Sarah", 1)));
        finalWinOrder.add(new ArrayList<Object>(Arrays.asList("Rep", "Craig", 2)));

        assertEquals(finalWinOrder, test.getFinalWinOrder());

        //Test 5.d partyWinOrder is has duplicate names in different parties
        partyCandidates = new HashMap<>();
        partyCandidates.put("Dem", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));
        partyCandidates.put("Rep", new ArrayList<>(Arrays.asList("Sarah", "Klein")));

        partyVotes = new ArrayList<>();
        partyVotes.add(new ArrayList<>(Arrays.asList("Dem", 750)));
        partyVotes.add(new ArrayList<>(Arrays.asList("Rep", 250)));

        candidateVotes = new ArrayList<>();
        candidateVotes.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Bob", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Jon", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Klein", 0)));

        testFile = new FileData("CPL", 3, 1000, 2, partyCandidates, partyVotes, candidateVotes);

        partyWinOrder = new ArrayList<>(Arrays.asList("Dem", "Rep"));
        finalWinOrder = new ArrayList<>();
        test = new ResultsDataCPL(seatAlloc, remainVotes, partyWinOrder, testFile);
        finalWinOrder.add(new ArrayList<Object>(Arrays.asList("Dem", "Sarah", 1)));
        finalWinOrder.add(new ArrayList<Object>(Arrays.asList("Rep", "Sarah", 2)));

        assertEquals(finalWinOrder, test.getFinalWinOrder()); 
        
        //Test 5.e partyWinOrder has duplicate names in the same party
        partyCandidates = new HashMap<>();
        partyCandidates.put("Dem", new ArrayList<>(Arrays.asList("Sarah", "Sarah", "Jon")));
        partyCandidates.put("Rep", new ArrayList<>(Arrays.asList("Sarah", "Klein")));
        
        candidateVotes = new ArrayList<>();
        candidateVotes.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Jon", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
        candidateVotes.add(new ArrayList<>(Arrays.asList("Klein", 0)));

        testFile = new FileData("CPL", 3, 1000, 2, partyCandidates, partyVotes, candidateVotes);

        partyWinOrder = new ArrayList<>(Arrays.asList("Dem", "Dem"));
        finalWinOrder = new ArrayList<>();
        test = new ResultsDataCPL(seatAlloc, remainVotes, partyWinOrder, testFile);
        finalWinOrder.add(new ArrayList<Object>(Arrays.asList("Dem", "Sarah", 1)));
        finalWinOrder.add(new ArrayList<Object>(Arrays.asList("Dem", "Sarah", 2)));

        assertEquals(finalWinOrder, test.getFinalWinOrder());
    }


    @Test
    public void testGetToString() {
        StringBuilder expected = new StringBuilder();
        expected.append(testFile.getElectionType() + " Election\n");
        expected.append(testFile.getNumberParties() + " Parties\n");
        expected.append(testFile.getNumberBallots() + " Ballots Cast\n");
        expected.append(testFile.getNumberSeats() + " Seats Avaliable\n");
        expected.append("----------------------------------------------------------------------\n");
        expected.append("  Party                       |  Candidates\n");
        expected.append("----------------------------------------------------------------------\n");

        int width = 26;
        for (int i = 0; i < partyVotes.size(); i++) {
            String partyName = (String) partyVotes.get(i).get(0);

            String format = String.format("  %-" + width + "s  |  ", partyName);
            expected.append(format);

            ArrayList<String> innerList = partyCandidates.get(partyName);
            for (int k = 0; k < innerList.size(); k++) {
                String candidateName = innerList.get(k);
                if (k == innerList.size() - 1) {
                    expected.append(candidateName + "\n");
                } else {
                    expected.append(candidateName + ", ");
                }
            }
        }

        expected.append("----------------------------------------------------------------------\n\n");
        expected.append(
                "-------------------------------------------------------------------------------------------------\n");
        expected.append(
                "                 |           |    First     |  Remaining  |    Second    |  Final  |  % of Vote\n");
        expected.append(
                "  Parties        |  Votes    |  Allocation  |    Votes    |  Allocation  |  Seat   |     to\n");
        expected.append(
                "                 |           |   Of Seats   |             |              |  Total  |  % of Seats\n");
        expected.append(
                "-------------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < partyVotes.size(); i++) {
            // Party Name
            String partyName = (String) partyVotes.get(i).get(0);
            width = 13;
            String format = String.format("  %-" + width + "s  |", partyName);
            expected.append(format);

            // Votes
            String votes = String.valueOf((int) partyVotes.get(i).get(1));
            width = 7;
            format = String.format("  %-" + width + "s  |", votes);
            expected.append(format);

            // Seats (1st alloc)
            int[] alloc = (int[]) seatAlloc.get(i).get(1);
            String firstAlloc = String.valueOf(alloc[0]);
            width = 10;
            format = String.format("  %-" + width + "s  |", firstAlloc);
            expected.append(format);

            // RemainingVotes
            width = 9;
            String remainVotes = String.valueOf((int) this.remainVotes.get(i).get(1));
            format = String.format("  %-" + width + "s  |", remainVotes);
            expected.append(format);

            // Seats (2nd alloc)
            width = 10;
            String secAlloc = String.valueOf(alloc[1]);
            format = String.format("  %-" + width + "s  |", secAlloc);
            expected.append(format);

            // Final seat
            width = 5;
            String finalAlloc = String.valueOf(alloc[0] + alloc[1]);
            format = String.format("  %-" + width + "s  |", finalAlloc);
            expected.append(format);

            // %vote to %seat
            int[] percents = getPercents(i);
            String votePer = String.valueOf(percents[0]) + "%";
            String seatPer = String.valueOf(percents[1] + "%");
            String out = votePer + "/" + seatPer;
            width = 10;
            format = String.format("  %-" + width + "s", out);
            expected.append(format + "\n");
        }
        expected.append(
                "-------------------------------------------------------------------------------------------------\n\n");

        // Winner Output
        expected.append("---------------------------------------------------\n");
        expected.append("  Winning        |  Seat           |  Seat\n");
        expected.append("  Parties        |  Winners        |  Won\n");
        expected.append("---------------------------------------------------\n");
        for (int i = 0; i < finalWinOrder.size(); i++) {
            // Party name
            width = 13;
            String partyName = (String) finalWinOrder.get(i).get(0);
            String format = String.format("  %-" + width + "s  |", partyName);
            expected.append(format);

            // Candidate name
            width = 13;
            String candidateName = (String) finalWinOrder.get(i).get(1);
            format = String.format("  %-" + width + "s  |", candidateName);
            expected.append(format);

            // Seat won
            width = 5;
            String seatNumber = String.valueOf(finalWinOrder.get(i).get(2));
            format = String.format("  %-" + width + "s", seatNumber);
            expected.append(format + "\n");
        }
        expected.append("---------------------------------------------------\n");

        //Test 6.a
        assertEquals(expected.toString(), test.toString());
    }


	@Test
	public void testGetPercents(){
   	 
    	//Test 7.a checking with index 0 on test

    	int index = 0;

    	int[] expectedPercents = {75, 67};

    	int[] actualPercents = test.getPercents(index);

    	assertEquals(expectedPercents[0], actualPercents[0]);
    	assertEquals(expectedPercents[1], actualPercents[1]);


    	//Test 7.b  checking with index 1 on test

    	index = 1;

    	expectedPercents[0] = 25;
    	expectedPercents[1] = 33;

    	actualPercents = test.getPercents(index);

    	assertEquals(expectedPercents[0], actualPercents[0]);
    	assertEquals(expectedPercents[1], actualPercents[1]);
	}


	@Test
	public void testPartySetUp(){

    	// test 8.a

    	String expectedOutput = "  Dem                    	|  Sarah, Bob, Jon\n" +
    	"  Rep                    	|  Craig, Klein\n";

    	String actualOutput = test.partySetUp();

    	expectedOutput = expectedOutput.replaceAll("\\s", "");
    	actualOutput = actualOutput.replaceAll("\\s", "");
    
    	assertEquals(expectedOutput.toString(), actualOutput);

    	test.partyVotes.clear();
    	test.partyVotes.add(new ArrayList<>(Arrays.asList("Dem", 750)));
    	test.partyCandidates.clear();
    	test.partyCandidates.put("Dem", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));
    
    	// test 8.b single party multiple candidates
    	actualOutput = test.partySetUp();
    
    	expectedOutput = "  Dem                    	|  Sarah, Bob, Jon\n";
    
    	// Normalize whitespace to ignore spacing differences
    	expectedOutput = expectedOutput.replaceAll("\\s", "");
    	actualOutput = actualOutput.replaceAll("\\s", "");
    
    	// Check if the actual output matches the expected output after removing whitespace
    	assertEquals(expectedOutput, actualOutput);
    
    
    	// 8.c empty party votes and party candidates
    	test.partyVotes.clear();
    	test.partyCandidates.clear();

    	actualOutput = test.partySetUp();

    	assertEquals("", actualOutput);


    	// 8.d multiple parties, single candidate
    	test.partyVotes.clear();
    	test.partyVotes.add(new ArrayList<>(Arrays.asList("Dem", 750)));
    	test.partyVotes.add(new ArrayList<>(Arrays.asList("Rep", 250)));
    	test.partyCandidates.clear();
    	test.partyCandidates.put("Dem", new ArrayList<>(Arrays.asList("Sarah")));
    	test.partyCandidates.put("Rep", new ArrayList<>(Arrays.asList("Craig")));

    	actualOutput = test.partySetUp();

    	expectedOutput = "  Dem                    	|  Sarah\n" +
                        	"  Rep                    	|  Craig\n";

    	expectedOutput = expectedOutput.replaceAll("\\s", "");
    	actualOutput = actualOutput.replaceAll("\\s", "");

    	assertEquals(expectedOutput, actualOutput);
	}

	@Test
	public void testElectionResultsSetUp(){
    	// test 9.a  empty data
    	test.partyVotes.clear();
    	test.seatAllocation.clear();
    	test.remainingVotes.clear();

    	String actualOutput = test.electionResultsSetUp();
    	assertEquals("", actualOutput);

    	// test 9.b  single party single seat

   	 

    	test.partyVotes.clear();
    	test.partyVotes.add(new ArrayList<>(Arrays.asList("Dem", 750)));

    	test.seatAllocation.clear();
    	test.seatAllocation.add(new ArrayList<>(Arrays.asList("Dem", new int[]{1, 0})));

    	test.remainingVotes.clear();
    	test.remainingVotes.add(new ArrayList<>(Arrays.asList("Dem", 84)));

    	actualOutput = test.electionResultsSetUp();
    	String expectedOutput = "  Dem     	|  750  |  1  |  84  |  0  |  1  |  75%/33%\n";

    	expectedOutput = expectedOutput.replaceAll("\\s", "");
    	actualOutput = actualOutput.replaceAll("\\s", "");
    	assertEquals(expectedOutput, actualOutput);

    	// test 9.c  multiple parties no seats allocated

    	test.partyVotes.clear();
    	test.partyVotes.add(new ArrayList<>(Arrays.asList("Dem", 750)));
    	test.partyVotes.add(new ArrayList<>(Arrays.asList("Rep", 250)));
    
    	test.seatAllocation.clear();
    	test.seatAllocation.add(new ArrayList<>(Arrays.asList("Dem", new int[]{0, 0})));
    	test.seatAllocation.add(new ArrayList<>(Arrays.asList("Rep", new int[]{0, 0})));
    
    	test.remainingVotes.clear();
    	test.remainingVotes.add(new ArrayList<>(Arrays.asList("Dem", 84)));
    	test.remainingVotes.add(new ArrayList<>(Arrays.asList("Rep", 250)));
    
    	actualOutput = test.electionResultsSetUp();
    	expectedOutput = "  Dem     	|  750  |  0  |  84  |  0  |  0  |  75%/0%\n" +
                            	"  Rep     	|  250  |  0  |  250  |  0  |  0  |  25%/0%\n";
    
    	expectedOutput = expectedOutput.replaceAll("\\s", "");
    	actualOutput = actualOutput.replaceAll("\\s", "");
    	assertEquals(expectedOutput, actualOutput);


    	// test 9.d

    	test.partyVotes.clear();
    	test.partyVotes.add(new ArrayList<>(Arrays.asList("Dem", 750)));
    	test.partyVotes.add(new ArrayList<>(Arrays.asList("Rep", 250)));

    	test.seatAllocation.clear();
    	test.seatAllocation.add(new ArrayList<>(Arrays.asList("Dem", new int[]{2, 0})));
    	test.seatAllocation.add(new ArrayList<>(Arrays.asList("Rep", new int[]{0, 1})));

    	test.remainingVotes.clear();
    	test.remainingVotes.add(new ArrayList<>(Arrays.asList("Dem", 84)));
    	test.remainingVotes.add(new ArrayList<>(Arrays.asList("Rep", 250)));

    	actualOutput = test.electionResultsSetUp();

    	expectedOutput = "  Dem     	|  750  |  2  |  84  |  0  |  2  |  75%/67%\n" +
                            	"  Rep     	|  250  |  0  |  250  |  1  |  1  |  25%/33%\n";

    	expectedOutput = expectedOutput.replaceAll("\\s", "");
    	actualOutput = actualOutput.replaceAll("\\s", "");

    	assertEquals(expectedOutput, actualOutput);
   	 
	}

	@Test
	public void testWinnerSetUp(){

    	//test 10.a multiple entries
    	test.finalWinOrder.clear();
    	test.finalWinOrder.add(new ArrayList<>(Arrays.asList("Dem", "Sarah", 1)));
    	test.finalWinOrder.add(new ArrayList<>(Arrays.asList("Rep", "Craig", 3)));

    	String actualOutput = test.winnerSetUp();
    	String expectedOutput = "  Dem     	|  Sarah   	|  1\n" +
                        	"  Rep     	|  Craig   	|  3\n";

    	expectedOutput = expectedOutput.replaceAll("\\s", "");
    	actualOutput = actualOutput.replaceAll("\\s", "");

    	assertEquals(expectedOutput, actualOutput);


    	// test 10.b
    	test.finalWinOrder.clear();
    	actualOutput = test.winnerSetUp();
    	assertEquals("", actualOutput);

    	// test 10.c  single entry
    	test.finalWinOrder.clear();
    	test.finalWinOrder.add(new ArrayList<>(Arrays.asList("Dem", "Sarah", 1)));

    	actualOutput = test.winnerSetUp();

    	expectedOutput = "  Dem     	|  Sarah   	|  1\n";

    	expectedOutput = expectedOutput.replaceAll("\\s", "");
    	actualOutput = actualOutput.replaceAll("\\s", "");

    	assertEquals(expectedOutput, actualOutput);
	}
}
