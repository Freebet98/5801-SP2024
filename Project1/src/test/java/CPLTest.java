import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import java.util.*;

/**
 * This class is used to test the CPL class
 *
 * @author Derrick Dischinger
 * @author Bethany Freeman
 */
public class CPLTest {

	// Create CPL input files
	CPL cpl01;
	CPL cpl02;
	CPL cpl03;
	CPL cpl04;
	CPL cpl05;
	CPL cpl06;
	CPL cpl07;
	FileData testFile;
	FileData testFile02;

	@Before
	public void setUp() {

    	// Create 4 ResultsDataCPL objects, 3 of which with proper outcomes for each
    	// cplInput,
    	// and 1 with incorrect outcomes for all 3.

    	// create partyCandidate lists for CPL file
    	HashMap<String, ArrayList<String>> partyCandidates01 = new HashMap<>();
    	partyCandidates01.put("Dem", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));
    	partyCandidates01.put("Rep", new ArrayList<>(Arrays.asList("Craig", "Klein")));
    	partyCandidates01.put("Green", new ArrayList<>(Arrays.asList("Rain", "Water", "Grass")));
    	partyCandidates01.put("Lib", new ArrayList<>(Arrays.asList("Ash", "Matt")));

    	HashMap<String, ArrayList<String>> partyCandidates02 = new HashMap<>();
    	partyCandidates02.put("Dem", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));
    	partyCandidates02.put("Rep", new ArrayList<>(Arrays.asList("Craig", "Klein")));

    	HashMap<String, ArrayList<String>> partyCandidates03 = new HashMap<>();
    	partyCandidates03.put("Dem", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));

    	HashMap<String, ArrayList<String>> partyCandidates04 = new HashMap<>();
    	partyCandidates04.put("Dem", new ArrayList<>(Arrays.asList("Sarah", "Bob")));
    	partyCandidates04.put("Rep", new ArrayList<>(Arrays.asList("Craig")));

    	HashMap<String, ArrayList<String>> partyCandidates05 = new HashMap<>();
    	partyCandidates05.put("Dem", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));

    	HashMap<String, ArrayList<String>> partyCandidates06 = new HashMap<>();
    	partyCandidates06.put("Dem", new ArrayList<>(Arrays.asList("Sarah")));
    	partyCandidates06.put("Rep", new ArrayList<>(Arrays.asList("Craig", "Klein")));
   	 
    	HashMap<String, ArrayList<String>> partyCandidates07 = new HashMap<>();
    	partyCandidates07.put("Dem", new ArrayList<>(Arrays.asList("Sarah")));
    	partyCandidates07.put("Rep", new ArrayList<>(Arrays.asList("Craig", "Klein")));

    	// create partyVotes lists for CPL file
    	ArrayList<ArrayList<Object>> partyVotes01 = new ArrayList<>();
    	partyVotes01.add(new ArrayList<>(Arrays.asList("Dem", 170)));
    	partyVotes01.add(new ArrayList<>(Arrays.asList("Rep", 36)));
    	partyVotes01.add(new ArrayList<>(Arrays.asList("Green", 99)));
    	partyVotes01.add(new ArrayList<>(Arrays.asList("Lib", 75)));

    	ArrayList<ArrayList<Object>> partyVotes02 = new ArrayList<>();
    	partyVotes02.add(new ArrayList<>(Arrays.asList("Dem", 700)));
    	partyVotes02.add(new ArrayList<>(Arrays.asList("Rep", 300)));

    	ArrayList<ArrayList<Object>> partyVotes03 = new ArrayList<>();
    	partyVotes03.add(new ArrayList<>(Arrays.asList("Dem", 2000)));

    	ArrayList<ArrayList<Object>> partyVotes04 = new ArrayList<>();
    	partyVotes04.add(new ArrayList<>(Arrays.asList("Dem", 6500)));
    	partyVotes04.add(new ArrayList<>(Arrays.asList("Rep", 3500)));

    	ArrayList<ArrayList<Object>> partyVotes05 = new ArrayList<>();
    	partyVotes05.add(new ArrayList<>(Arrays.asList("Dem", 2000)));

    	ArrayList<ArrayList<Object>> partyVotes06 = new ArrayList<>();
    	partyVotes06.add(new ArrayList<>(Arrays.asList("Dem", 1000)));
    	partyVotes06.add(new ArrayList<>(Arrays.asList("Rep", 2)));

    	ArrayList<ArrayList<Object>> partyVotes07 = new ArrayList<>();
    	partyVotes07.add(new ArrayList<>(Arrays.asList("Dem", 700)));
    	partyVotes07.add(new ArrayList<>(Arrays.asList("Rep", 700)));

    	// create candidateVotes list for CPL
    	ArrayList<ArrayList<Object>> candidateVotes01 = new ArrayList<>();
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Bob", 0)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Jon", 0)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Craig", 0)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Klein", 0)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Rain", 0)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Water", 0)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Grass", 0)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Ash", 0)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Matt", 0)));

    	ArrayList<ArrayList<Object>> candidateVotes02 = new ArrayList<>();
    	candidateVotes02.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
    	candidateVotes02.add(new ArrayList<>(Arrays.asList("Bob", 0)));
    	candidateVotes02.add(new ArrayList<>(Arrays.asList("Jon", 0)));
    	candidateVotes02.add(new ArrayList<>(Arrays.asList("Craig", 0)));
    	candidateVotes02.add(new ArrayList<>(Arrays.asList("Klein", 0)));

    	ArrayList<ArrayList<Object>> candidateVotes03 = new ArrayList<>();
    	candidateVotes03.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
    	candidateVotes03.add(new ArrayList<>(Arrays.asList("Bob", 0)));
    	candidateVotes03.add(new ArrayList<>(Arrays.asList("Jon", 0)));

    	ArrayList<ArrayList<Object>> candidateVotes04 = new ArrayList<>();
    	candidateVotes04.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
    	candidateVotes04.add(new ArrayList<>(Arrays.asList("Bob", 0)));
    	candidateVotes04.add(new ArrayList<>(Arrays.asList("Craig", 0)));

    	ArrayList<ArrayList<Object>> candidateVotes05 = new ArrayList<>();
    	candidateVotes05.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
    	candidateVotes05.add(new ArrayList<>(Arrays.asList("Bob", 0)));
    	candidateVotes05.add(new ArrayList<>(Arrays.asList("Jon", 0)));

    	ArrayList<ArrayList<Object>> candidateVotes06 = new ArrayList<>();
    	candidateVotes06.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
    	candidateVotes06.add(new ArrayList<>(Arrays.asList("Craig", 0)));
    	candidateVotes06.add(new ArrayList<>(Arrays.asList("Klein", 0)));

    	ArrayList<ArrayList<Object>> candidateVotes07 = new ArrayList<>();
    	candidateVotes07.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
    	candidateVotes07.add(new ArrayList<>(Arrays.asList("Craig", 0)));
    	candidateVotes07.add(new ArrayList<>(Arrays.asList("Klein", 0)));

    	// finish initializing CPL FileData
    	cpl01 = new CPL(new FileData("CPL", 3, 380, 10, partyCandidates01, partyVotes01, candidateVotes01));
    	cpl02 = new CPL(new FileData("CPL", 1, 1000, 5, partyCandidates02, partyVotes02, candidateVotes02));
    	cpl03 = new CPL(new FileData("CPL", 2, 2000, 3, partyCandidates03, partyVotes03, candidateVotes03));
    	cpl04 = new CPL(new FileData("CPL", 2, 10000, 3, partyCandidates04, partyVotes04, candidateVotes04));
    	cpl05 = new CPL(new FileData("CPL", 1, 2000, 3, partyCandidates05, partyVotes05, candidateVotes05));
    	cpl06 = new CPL(new FileData("CPL", 2, 1002, 3, partyCandidates06, partyVotes06, candidateVotes06));
    	testFile = new FileData("CPL", 2, 1400, 3, partyCandidates07, partyVotes07, candidateVotes07);
		testFile02 = new FileData("CPL", 1, 1000, 5, partyCandidates02, partyVotes02, candidateVotes02);

	}

	// Test starts here

	@Test
	public void testAdjustRemainingVotes() throws IOException {

    	// test 1.a adjustRemainingVotes correctly updates remainingVotes with a valid
    	// index

    	int index01 = 1;
    	ArrayList<ArrayList<Object>> expectedA = new ArrayList<>();
    	expectedA.add(new ArrayList<>(Arrays.asList("Dem", 170)));
    	expectedA.add(new ArrayList<>(Arrays.asList("Rep", -90)));
    	expectedA.add(new ArrayList<>(Arrays.asList("Green", 99)));
    	expectedA.add(new ArrayList<>(Arrays.asList("Lib", 75)));

    	cpl01.adjustRemainingVotes(index01);
    	assertEquals(cpl01.remainingVotes, expectedA);

    	// test 1.b index = -1 which is not within the valid range
    	// IOException is thrown

    	int index02 = -1;
    	// cpl01.adjustRemainingVotes(index02);
    	// assertEquals(cpl01.remainingVotes, expectedA);

    	assertThrows(IOException.class,
            	() -> cpl01.adjustRemainingVotes(index02));

    	// test 1.c index = 15 which is not within the valid range
    	// IOException is thrown

    	int index03 = 15;
    	assertThrows(IOException.class,
            	() -> cpl01.adjustRemainingVotes(index03));

    	// test 1.d ensure that the correct value is being subtracted from the index

    	int index04 = 3;
    	int expectedVal = (int) cpl01.remainingVotes.get(index04).get(1)
            	- ((int) cpl01.fileData.getNumberBallots() / (int) cpl01.fileData.getNumberSeats());
    	cpl01.adjustRemainingVotes(index04);
    	assertEquals(cpl01.remainingVotes.get(index04).get(1), expectedVal);
	}

	@Test
	public void testDeepCopyVotes() {

    	// test 2.a ensure both arrays lists are the same after the method is called

    	ArrayList<ArrayList<Object>> expected1 = new ArrayList<ArrayList<Object>>();
    	expected1.add(new ArrayList<>(Arrays.asList("Sarah", 50)));
    	expected1.add(new ArrayList<>(Arrays.asList("Joe", 20)));
    	expected1.add(new ArrayList<>(Arrays.asList("Bill", 13)));
    	expected1.add(new ArrayList<>(Arrays.asList("Timothey", 50)));

    	ArrayList<ArrayList<Object>> copied1 = cpl01.deepCopyVotes(expected1);

    	assertEquals(copied1, expected1);

    	// test 2.b ensure variables can be changed in original and not affect copy

    	expected1.get(1).set(1, 10); // changes Joe from 20 to 10
    	assertNotEquals(expected1, copied1);

    	// test 2.c situation where an empty list is passed in

    	ArrayList<ArrayList<Object>> expected2 = new ArrayList<ArrayList<Object>>();
    	ArrayList<ArrayList<Object>> copied2 = cpl01.deepCopyVotes(expected2);
    	assertEquals(copied2, expected2);

    	// test 2.d adding to one empty list will not affect the other

    	expected2.add(new ArrayList<>(Arrays.asList("Sarah", 50)));
    	assertNotEquals(expected1, copied1);
	}

	@Test
	public void testInitializeSeatAllocation() {

    	// test 7.a ensure the resulting array has the correct default values in all
    	// fields

    	ArrayList<ArrayList<Object>> expected1 = new ArrayList<ArrayList<Object>>();
    	expected1.add(new ArrayList<>(Arrays.asList("Dem", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Rep", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Green", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Lib", new int[] { 0, 0 })));

    	ArrayList<ArrayList<Object>> result1 = cpl01.initializeSeatAllocation();

    	// maunally compare because assertEquals compares memory addresses for the
    	// int[]s
    	assertEquals(expected1.size(), result1.size());
    	for (int i = 0; i < expected1.size(); i++) {
        	assertEquals(expected1.get(i).size(), result1.get(i).size());
        	assertEquals(expected1.get(i).get(0), result1.get(i).get(0));
        	assertArrayEquals((int[]) expected1.get(i).get(1), (int[]) result1.get(i).get(1));
    	}

	}

	@Test
	public void testGenerateRandom() {
    	// test 3.a: Check if generated random number is within the specified range
    	float randomValue = cpl01.generateRandom();
    	assertTrue("Generated value should be >= 0", randomValue >= 0);
    	assertTrue("Generated  value should be < 10", randomValue < 10);

    	// test 3.b: Check if multiple calls to the method generate different values
    	// <---- should this be included, whats a better way to check if random
    	float previousRandomValue = cpl01.generateRandom();
    	boolean allDifferent = true;
    	for (int i = 0; i < 100; i++) {
        	float newRandomValue = cpl01.generateRandom();
        	if (newRandomValue == previousRandomValue) {
            	allDifferent = false;
            	break;
        	}
        	previousRandomValue = newRandomValue;
    	}
    	assertTrue("generateRandom() should generate different values (almost always)", allDifferent);

    	// test 3.c test if the random numbers are actually distributed evenly over a
    	// large set and not psuedorandom testing for 90% accuracy
    	int between0and1 = 0;
    	int between1and2 = 0;
    	int between2and3 = 0;
    	int between3and4 = 0;
    	int between4and5 = 0;
    	int between5and6 = 0;
    	int between6and7 = 0;
    	int between7and8 = 0;
    	int between8and9 = 0;
    	int between9and10 = 0;
    	for (int i = 0; i < 10000; i++) {
        	float rv = cpl01.generateRandom();
        	if (rv < 1) {
            	between0and1++;
        	} else if (rv < 2) {
            	between1and2++;
        	} else if (rv < 3) {
            	between2and3++;
        	} else if (rv < 4) {
            	between3and4++;
        	} else if (rv < 5) {
            	between4and5++;
        	} else if (rv < 6) {
            	between5and6++;
        	} else if (rv < 7) {
            	between6and7++;
        	} else if (rv < 8) {
            	between7and8++;
        	} else if (rv < 9) {
            	between8and9++;
        	} else if (rv < 10) {
            	between9and10++;
        	}
    	}
    	assertTrue("at least 800 should be in the range 0 to 1", between0and1 >= 900);
    	assertTrue("at least 800 should be in the range 1 to 2", between1and2 >= 900);
    	assertTrue("at least 800 should be in the range 2 to 3", between2and3 >= 900);
    	assertTrue("at least 800 should be in the range 3 to 4", between3and4 >= 900);
    	assertTrue("at least 800 should be in the range 4 to 5", between4and5 >= 900);
    	assertTrue("at least 800 should be in the range 5 to 6", between5and6 >= 900);
    	assertTrue("at least 800 should be in the range 6 to 7", between6and7 >= 900);
    	assertTrue("at least 800 should be in the range 7 to 8", between7and8 >= 900);
    	assertTrue("at least 800 should be in the range 8 to 9", between8and9 >= 900);
    	assertTrue("at least 800 should be in the range 9 to 10", between9and10 >= 900);

	}

	@Test
	public void testBreakTie() {

    	// test 4.a: numTie is 3, a valid number of ties

    	int numTie01 = 3;
    	// call the breakTie method using the CPL object
    	int result01 = cpl01.breakTie(numTie01);
    	assertTrue("resulting index should be between 0 and 4 inclusive", result01 >= 0 && result01 < numTie01);

    	// test 4.b: numTie is 0, not a valid tiebreak

    	int numTie02 = 0;
    	int result02 = cpl01.breakTie(numTie02);
    	assertEquals(result02, -1);

    	// test 4.c: numTie is 5, beyond range of number of parties

    	int numTie03 = 15;
    	int result03 = cpl01.breakTie(numTie03);
    	assertEquals(result03, -1);

    	// test 4.d: numTie is 1, should always return 0

    	int numTie04 = 1;
    	int result04 = cpl01.breakTie(numTie04);
    	assertEquals(result04, 0);

    	// we need more tests here. Im still unclear on what shana means by testing the
    	// the structure of the code
    	// how do i write a test for every conditional/loop in the method when it only
    	// usable value from an cpl standpoint is the return value

	}

	@Test
	public void testAddWinner() throws IOException {

    	// test 5.a tests to see wether the list is empty on start and if calling it
    	// with a winner adds the correct winner

    	ArrayList<String> winOrderExcpected = new ArrayList<String>();
    	assertEquals(winOrderExcpected, cpl01.winOrder); // ensure they are equal before adding a winner
    	winOrderExcpected.add("Lib"); // manually add a winner to winOrderExpected
    	cpl01.addWinner(3); // add wainner to cpl01 using the index of "Lib" in seadAllocation to match
                        	// manual insertion
    	assertEquals(winOrderExcpected, cpl01.winOrder);

    	// test 5.b ensure an index that is too large will throw an exception

    	assertThrows(IOException.class,
            	() -> cpl01.addWinner(5));

    	// test 5.c ensure an index that is <0 will throw an exception

    	assertThrows(IOException.class,
            	() -> cpl01.addWinner(-1));
	}

	@Test
	public void testAdjustSeatAllocation() throws IOException {

    	// test 6.a test wether adding a seat at a specified index updates value to 1
    	// from empty seat allocation array

    	boolean firstRound = true;
    	int index01 = 1;
    	ArrayList<ArrayList<Object>> expected1 = new ArrayList<ArrayList<Object>>();
    	expected1.add(new ArrayList<>(Arrays.asList("Dem", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Rep", new int[] { 1, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Green", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Lib", new int[] { 0, 0 })));

    	cpl01.adjustSeatAllocation(index01, firstRound);

    	assertEquals(expected1.size(), expected1.size());
    	for (int i = 0; i < expected1.size(); i++) {
        	assertEquals(expected1.get(i).size(), expected1.get(i).size());
        	assertEquals(expected1.get(i).get(0), expected1.get(i).get(0));
        	assertArrayEquals((int[]) expected1.get(i).get(1), (int[]) expected1.get(i).get(1));
    	}

    	// test 6.b test that adding in a second round to the same object works

    	int[] array = (int[]) expected1.get(1).get(1);
    	array[1]++;
    	firstRound = false;
    	cpl01.adjustSeatAllocation(index01, firstRound);

    	assertEquals(expected1.size(), cpl01.seatAllocation.size());
    	for (int i = 0; i < expected1.size(); i++) {
        	assertEquals(expected1.get(i).size(), cpl01.seatAllocation.get(i).size());
        	assertEquals(expected1.get(i).get(0), cpl01.seatAllocation.get(i).get(0));
        	assertArrayEquals((int[]) expected1.get(i).get(1), (int[]) cpl01.seatAllocation.get(i).get(1));
    	}

    	// test 6.c test that the same index can be incremented twice

    	array[1]++;
    	cpl01.adjustSeatAllocation(index01, firstRound);

    	assertEquals(expected1.size(), cpl01.seatAllocation.size());
    	for (int i = 0; i < expected1.size(); i++) {
        	assertEquals(expected1.get(i).size(), cpl01.seatAllocation.get(i).size());
        	assertEquals(expected1.get(i).get(0), cpl01.seatAllocation.get(i).get(0));
        	assertArrayEquals((int[]) expected1.get(i).get(1), (int[]) cpl01.seatAllocation.get(i).get(1));
    	}

    	// 6.d testing with an index under accepted range IOException should be thrown

    	int index02 = -1;
    	assertThrows(IOException.class,
            	() -> cpl01.adjustSeatAllocation(index02, true));

    	// 6.e testing with an index over accepted range IOException should be thrown
    	int index03 = 10;
    	assertThrows(IOException.class,
            	() -> cpl01.adjustSeatAllocation(index03, true));
	}

	@Test
	public void testFirstAllocation() throws IOException {
    	// test 7.a first allocation test where only 1 seat is available
    	cpl02.firstAllocation();
    	// ensure it breaks after 1 loop adding no winners as they are all below the
    	// remainder single winner
    	ArrayList<String> expectedWinOrder = new ArrayList<String>();
    	assertEquals(expectedWinOrder, cpl02.winOrder);

    	// test 7.b one seat is allocated in the round of allocation
    	cpl04.firstAllocation();
    	expectedWinOrder = new ArrayList<>();
    	expectedWinOrder.add("Dem");
   	 
    	assertEquals(expectedWinOrder, cpl04.winOrder);   

    	// test 7.c two seats are allocated in the first round of allocation
    	cpl03.firstAllocation();
    	expectedWinOrder = new ArrayList<>();
    	expectedWinOrder.add("Dem");
    	expectedWinOrder.add("Dem");
    	int[] expected = new int[]{2,0};

    	assertEquals(expectedWinOrder, cpl03.winOrder);
    	int[] temp = (int[]) cpl03.seatAllocation.get(0).get(1);
    	assertEquals(Arrays.toString(expected), Arrays.toString(temp));

    	// test 7.d all seats allocated in first allocation, should end
    	cpl05.firstAllocation();
    	expectedWinOrder = new ArrayList<>();
    	expectedWinOrder.add("Dem");
    	assertEquals(expectedWinOrder, cpl05.winOrder);

    	// test 7.e 2 seats arent allocated to winning party if theres only 1 member
    	cpl06.firstAllocation();
    	expectedWinOrder = new ArrayList<>();
    	expectedWinOrder.add("Dem");
    	assertEquals(expectedWinOrder, cpl06.winOrder);
	}

	@Test
	public void testSecondAllocation() throws IOException {
    	// test 8.a no seat given in first allocation, assigns seat in second to highest party vote count\
    	cpl02.firstAllocation();
    	cpl02.secondAllocation();
    	ArrayList<String> expectedWinOrder = new ArrayList<>();
    	expectedWinOrder.add("Dem");
    	assertEquals(expectedWinOrder, cpl02.winOrder);

    	// test 8.b shoud go to republicans in the second allocation, each party has a seat
    	cpl04.firstAllocation();
    	cpl04.secondAllocation();
    	expectedWinOrder = new ArrayList<>();
    	expectedWinOrder.add("Dem");
    	expectedWinOrder.add("Rep");
    	assertEquals(expectedWinOrder, cpl04.winOrder);

    	// test 8.c Should not allocate any seats in the second round
    	cpl03.firstAllocation();
    	cpl03.secondAllocation();
    	expectedWinOrder = new ArrayList<>();
    	expectedWinOrder.add("Dem");
    	expectedWinOrder.add("Dem");
    	assertEquals(expectedWinOrder, cpl03.winOrder);

    	// test 8.d second seat gets correctly allocated to party that has members available even if they didnt win votes
    	cpl06.firstAllocation();
    	cpl06.secondAllocation();
    	expectedWinOrder = new ArrayList<>();
    	expectedWinOrder.add("Dem");
    	expectedWinOrder.add("Rep");
    	assertEquals(expectedWinOrder, cpl06.winOrder);

    	// test 8.c there is a tie in the second allocation
    	int results = 0;
    	for(int i=0; i<1000; i++){
        	CPL cpl = new CPL(testFile);
        	cpl.secondAllocation();
        	// assertEquals(cpl.winOrder.get(0),"Dem");
        	if(cpl.winOrder.get(0).equals("Dem")){
            	results++;
        	}
    	}
    	assertTrue("Generated  value should be between 450 and 550", results<550);
    	assertTrue("Generated  value should be between 450 and 550", 450<results);
	}
	
	@Test
	public void testRunElection() throws IOException{
		CPL cpl1 = new CPL(testFile02);
		ResultsData result1 = cpl1.runElection();

		ArrayList<String> winOrder = new ArrayList<>();
		winOrder.add("Dem");
		assertEquals(winOrder, result1.getPartyWinOrder());

		ArrayList<ArrayList<Object>> remainingVotes = new ArrayList<>();
		remainingVotes.add(new ArrayList<Object>(Arrays.asList("Dem", 700)));
		remainingVotes.add(new ArrayList<Object>(Arrays.asList("Rep", 300)));
		assertEquals(remainingVotes, result1.getRemainingVotes());

		ArrayList<ArrayList<Object>> seatAllocation = new ArrayList<>();
		seatAllocation.add(new ArrayList<Object>(Arrays.asList(" Dem", new int[] {0,1})));
		seatAllocation.add(new ArrayList<Object>(Arrays.asList(" Rep", new int[] {0,0}))); 
		int[] temp = (int[]) result1.getSeatAllocation().get(0).get(1);
		int[] temp2 = (int[]) seatAllocation.get(0).get(1);
		assertEquals(Arrays.toString(temp2), Arrays.toString(temp));


	}
}
