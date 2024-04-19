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
 * This class is used to test the MPO class
 *
 * @author Derrick Dischinger
 * @author Bethany Freeman
 */
public class MPOTests {

	// Create CPL input files
	MPO mpo01;
	MPO mpo02;
	MPO mpo03;
	MPO mpo04;
	MPO mpo05;
	MPO mpo06;
	MPO mpo07;
	FileData testFile;
	FileData testFile02;
	FileData testFile03;
	FileData testFile04;

	@Before
	public void setUp() {

    	// Create 4 ResultsDataOPL objects, 3 of which with proper outcomes for each
    	// cplInput,
    	// and 1 with incorrect outcomes for all 3.

    	// create partyCandidate lists for CPL file
    	HashMap<String, ArrayList<String>> partyCandidates01 = new HashMap<>();
    	partyCandidates01.put("D", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));
    	partyCandidates01.put("R", new ArrayList<>(Arrays.asList("Craig", "Klein")));
    	partyCandidates01.put("G", new ArrayList<>(Arrays.asList("Rain", "Water", "Grass")));
    	partyCandidates01.put("L", new ArrayList<>(Arrays.asList("Ash", "Matt")));

    	HashMap<String, ArrayList<String>> partyCandidates02 = new HashMap<>();
    	partyCandidates02.put("D", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));
    	partyCandidates02.put("R", new ArrayList<>(Arrays.asList("Craig", "Klein")));

    	HashMap<String, ArrayList<String>> partyCandidates03 = new HashMap<>();
    	partyCandidates03.put("D", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));

    	HashMap<String, ArrayList<String>> partyCandidates04 = new HashMap<>();
    	partyCandidates04.put("D", new ArrayList<>(Arrays.asList("Sarah", "Bob")));
    	partyCandidates04.put("R", new ArrayList<>(Arrays.asList("Craig")));

    	HashMap<String, ArrayList<String>> partyCandidates05 = new HashMap<>();
    	partyCandidates05.put("D", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));

    	HashMap<String, ArrayList<String>> partyCandidates06 = new HashMap<>();
    	partyCandidates06.put("D", new ArrayList<>(Arrays.asList("Sarah")));
    	partyCandidates06.put("R", new ArrayList<>(Arrays.asList("Craig", "Klein")));
   	 
    	HashMap<String, ArrayList<String>> partyCandidates07 = new HashMap<>();
    	partyCandidates07.put("D", new ArrayList<>(Arrays.asList("Sarah")));
    	partyCandidates07.put("R", new ArrayList<>(Arrays.asList("Craig", "Klein")));

    	// create partyVotes lists for MPO file
    	ArrayList<ArrayList<Object>> partyVotes01 = new ArrayList<>();
    	partyVotes01.add(new ArrayList<>(Arrays.asList("D", 170)));
    	partyVotes01.add(new ArrayList<>(Arrays.asList("R", 36)));
    	partyVotes01.add(new ArrayList<>(Arrays.asList("G", 99)));
    	partyVotes01.add(new ArrayList<>(Arrays.asList("L", 75)));

    	ArrayList<ArrayList<Object>> partyVotes02 = new ArrayList<>();
    	partyVotes02.add(new ArrayList<>(Arrays.asList("D", 700)));
    	partyVotes02.add(new ArrayList<>(Arrays.asList("R", 300)));

    	ArrayList<ArrayList<Object>> partyVotes03 = new ArrayList<>();
    	partyVotes03.add(new ArrayList<>(Arrays.asList("D", 2000)));

    	ArrayList<ArrayList<Object>> partyVotes04 = new ArrayList<>();
    	partyVotes04.add(new ArrayList<>(Arrays.asList("D", 6500)));
    	partyVotes04.add(new ArrayList<>(Arrays.asList("R", 3500)));

    	ArrayList<ArrayList<Object>> partyVotes05 = new ArrayList<>();
    	partyVotes05.add(new ArrayList<>(Arrays.asList("D", 2000)));

    	ArrayList<ArrayList<Object>> partyVotes06 = new ArrayList<>();
    	partyVotes06.add(new ArrayList<>(Arrays.asList("D", 1000)));
    	partyVotes06.add(new ArrayList<>(Arrays.asList("R", 2)));

    	ArrayList<ArrayList<Object>> partyVotes07 = new ArrayList<>();
    	partyVotes07.add(new ArrayList<>(Arrays.asList("D", 700)));
    	partyVotes07.add(new ArrayList<>(Arrays.asList("R", 700)));

    	// create candidateVotes list for MPO
    	ArrayList<ArrayList<Object>> candidateVotes01 = new ArrayList<>();
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Sarah", 50)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Bob", 100)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Jon", 20)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Craig", 34)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Klein", 2)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Rain", 15)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Water", 61)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Grass", 23)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Ash", 10)));
    	candidateVotes01.add(new ArrayList<>(Arrays.asList("Matt", 65)));

    	ArrayList<ArrayList<Object>> candidateVotes02 = new ArrayList<>();
    	candidateVotes02.add(new ArrayList<>(Arrays.asList("Sarah", 400)));
    	candidateVotes02.add(new ArrayList<>(Arrays.asList("Bob", 200)));
    	candidateVotes02.add(new ArrayList<>(Arrays.asList("Jon", 100)));
    	candidateVotes02.add(new ArrayList<>(Arrays.asList("Craig", 50)));
    	candidateVotes02.add(new ArrayList<>(Arrays.asList("Klein", 250)));

    	ArrayList<ArrayList<Object>> candidateVotes03 = new ArrayList<>();
    	candidateVotes03.add(new ArrayList<>(Arrays.asList("Sarah", 1250)));
    	candidateVotes03.add(new ArrayList<>(Arrays.asList("Bob", 750)));
    	candidateVotes03.add(new ArrayList<>(Arrays.asList("Jon", 500)));

    	ArrayList<ArrayList<Object>> candidateVotes04 = new ArrayList<>();
    	candidateVotes04.add(new ArrayList<>(Arrays.asList("Sarah", 4500)));
    	candidateVotes04.add(new ArrayList<>(Arrays.asList("Bob", 2000)));
    	candidateVotes04.add(new ArrayList<>(Arrays.asList("Craig", 3500)));

    	ArrayList<ArrayList<Object>> candidateVotes05 = new ArrayList<>();
    	candidateVotes05.add(new ArrayList<>(Arrays.asList("Sarah", 1250)));
    	candidateVotes05.add(new ArrayList<>(Arrays.asList("Bob", 750)));
    	candidateVotes05.add(new ArrayList<>(Arrays.asList("Jon", 500)));

    	ArrayList<ArrayList<Object>> candidateVotes06 = new ArrayList<>();
    	candidateVotes06.add(new ArrayList<>(Arrays.asList("Sarah", 1000)));
    	candidateVotes06.add(new ArrayList<>(Arrays.asList("Craig", 2)));
    	candidateVotes06.add(new ArrayList<>(Arrays.asList("Klein", 0)));

    	ArrayList<ArrayList<Object>> candidateVotes07 = new ArrayList<>();
    	candidateVotes07.add(new ArrayList<>(Arrays.asList("Sarah", 700)));
    	candidateVotes07.add(new ArrayList<>(Arrays.asList("Craig", 700)));
    	candidateVotes07.add(new ArrayList<>(Arrays.asList("Klein", 0)));

    	// finish initializing MPO FileData
    	mpo01 = new MPO(new FileData("MPO", 3, 380, 10, partyCandidates01, partyVotes01, candidateVotes01));
    	mpo02 = new MPO(new FileData("MPO", 1, 1000, 5, partyCandidates02, partyVotes02, candidateVotes02));
    	mpo03 = new MPO(new FileData("MPO", 2, 2000, 3, partyCandidates03, partyVotes03, candidateVotes03));
    	mpo04 = new MPO(new FileData("MPO", 2, 10000, 3, partyCandidates04, partyVotes04, candidateVotes04));
    	mpo05 = new MPO(new FileData("MPO", 1, 2000, 3, partyCandidates05, partyVotes05, candidateVotes05));
    	mpo06 = new MPO(new FileData("MPO", 2, 1002, 3, partyCandidates06, partyVotes06, candidateVotes06));
    	testFile = new FileData("MPO", 2, 1400, 3, partyCandidates07, partyVotes07, candidateVotes07);
		testFile02 = new FileData("MPO", 1, 1000, 5, partyCandidates02, partyVotes02, candidateVotes02);
		testFile03 = new FileData("MPO", 4, 380, 10, partyCandidates01, partyVotes01, candidateVotes01);
		//testFile04 = new FileData();

	}

	// Test starts here

	@Test
	public void testAdjustRemainingVotes() throws IOException {

    	// test 1.a adjustRemainingVotes correctly updates remainingVotes with a valid
    	// index

    	int index01 = 1;
    	ArrayList<ArrayList<Object>> expectedA = new ArrayList<>();
    	expectedA.add(new ArrayList<>(Arrays.asList("Sarah", 50)));
    	expectedA.add(new ArrayList<>(Arrays.asList("Bob", -26)));
    	expectedA.add(new ArrayList<>(Arrays.asList("Jon", 20)));
    	expectedA.add(new ArrayList<>(Arrays.asList("Craig", 34)));
    	expectedA.add(new ArrayList<>(Arrays.asList("Klein", 2)));
    	expectedA.add(new ArrayList<>(Arrays.asList("Rain", 15)));
    	expectedA.add(new ArrayList<>(Arrays.asList("Water", 61)));
    	expectedA.add(new ArrayList<>(Arrays.asList("Grass", 23)));
    	expectedA.add(new ArrayList<>(Arrays.asList("Ash", 10)));
        expectedA.add(new ArrayList<>(Arrays.asList("Matt", 65)));


    	mpo01.adjustRemainingVotes(index01);
    	assertEquals(mpo01.remainingVotes, expectedA);

    	// test 1.b index = -1 which is not within the valid range
    	// IOException is thrown

    	int index02 = -1;
    	// mpo01.adjustRemainingVotes(index02);
    	// assertEquals(mpo01.remainingVotes, expectedA);

    	assertThrows(IOException.class,
            	() -> mpo01.adjustRemainingVotes(index02));

    	// test 1.c index = 15 which is not within the valid range
    	// IOException is thrown

    	int index03 = 15;
    	assertThrows(IOException.class,
            	() -> mpo01.adjustRemainingVotes(index03));

    	// test 1.d ensure that the correct value is being subtracted from the index

    	int index04 = 3;
    	int expectedVal = (int) mpo01.remainingVotes.get(index04).get(1)
            	- ((int) mpo01.fileData.getNumberBallots() / (int) mpo01.fileData.getNumberSeats());
    	mpo01.adjustRemainingVotes(index04);
    	assertEquals(mpo01.remainingVotes.get(index04).get(1), expectedVal);
	}

	@Test
	public void testDeepCopyVotes() {

    	// test 2.a ensure both arrays lists are the same after the method is called

    	ArrayList<ArrayList<Object>> expected1 = new ArrayList<ArrayList<Object>>();
    	expected1.add(new ArrayList<>(Arrays.asList("Sarah", 50)));
    	expected1.add(new ArrayList<>(Arrays.asList("Joe", 20)));
    	expected1.add(new ArrayList<>(Arrays.asList("Bill", 13)));
    	expected1.add(new ArrayList<>(Arrays.asList("Timothey", 50)));

    	ArrayList<ArrayList<Object>> copied1 = mpo01.deepCopyVotes(expected1);

    	assertEquals(copied1, expected1);

    	// test 2.b ensure variables can be changed in original and not affect copy

    	expected1.get(1).set(1, 10); // changes Joe from 20 to 10
    	assertNotEquals(expected1, copied1);

    	// test 2.c situation where an empty list is passed in

    	ArrayList<ArrayList<Object>> expected2 = new ArrayList<ArrayList<Object>>();
    	ArrayList<ArrayList<Object>> copied2 = mpo01.deepCopyVotes(expected2);
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
    	expected1.add(new ArrayList<>(Arrays.asList("Sarah", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Bob", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Jon", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Craig", new int[] { 0, 0 })));
        expected1.add(new ArrayList<>(Arrays.asList("Klein", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Rain", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Water", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Grass", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Ash", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Matt", new int[] { 0, 0 })));

    	ArrayList<ArrayList<Object>> result1 = mpo01.initializeSeatAllocation();

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
    	float randomValue = mpo01.generateRandom();
    	assertTrue("Generated value should be >= 0", randomValue >= 0);
    	assertTrue("Generated  value should be < 10", randomValue < 10);

    	// test 3.b: Check if multiple calls to the method generate different values
    	// <---- should this be included, whats a better way to check if random
    	float previousRandomValue = mpo01.generateRandom();
    	boolean allDifferent = true;
    	for (int i = 0; i < 100; i++) {
        	float newRandomValue = mpo01.generateRandom();
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
        	float rv = mpo01.generateRandom();
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
    	// call the breakTie method using the MPO object
    	int result01 = mpo01.breakTie(numTie01);
    	assertTrue("resulting index should be between 0 and 4 inclusive", result01 >= 0 && result01 < numTie01);

    	// test 4.b: numTie is 0, not a valid tiebreak

    	int numTie02 = 0;
    	int result02 = mpo01.breakTie(numTie02);
    	assertEquals(result02, -1);

    	// test 4.c: numTie is 5, beyond range of number of parties

    	int numTie03 = 15;
    	int result03 = mpo01.breakTie(numTie03);
    	assertEquals(result03, -1);

    	// test 4.d: numTie is 1, should always return 0

    	int numTie04 = 1;
    	int result04 = mpo01.breakTie(numTie04);
    	assertEquals(result04, 0);

    	// we need more tests here. Im still unclear on what shana means by testing the
    	// the structure of the code
    	// how do i write a test for every conditional/loop in the method when it only
    	// usable value from an opl standpoint is the return value

	}

	@Test
	public void testAddWinner() throws IOException {

    	// test 5.a tests to see wether the list is empty on start and if calling it
    	// with a winner adds the correct winner

    	ArrayList<String> winOrderExcpected = new ArrayList<String>();
    	assertEquals(winOrderExcpected, mpo01.winOrder); // ensure they are equal before adding a winner
    	winOrderExcpected.add("Craig"); // manually add a winner to winOrderExpected
    	mpo01.addWinner(3); // add wainner to mpo01 using the index of "L" in seadAllocation to match
                        	// manual insertion
    	assertEquals(winOrderExcpected, mpo01.winOrder);

    	// test 5.b ensure an index that is too large will throw an exception

    	assertThrows(IOException.class,
            	() -> mpo01.addWinner(11));

    	// test 5.c ensure an index that is <0 will throw an exception

    	assertThrows(IOException.class,
            	() -> mpo01.addWinner(-1));
	}

	@Test
	public void testAdjustSeatAllocation() throws IOException {

    	// test 6.a test wether adding a seat at a specified index updates value to 1
    	// from empty seat allocation array

    	boolean firstRound = true;
    	int index01 = 1;
    	ArrayList<ArrayList<Object>> expected1 = new ArrayList<ArrayList<Object>>();
    	expected1.add(new ArrayList<>(Arrays.asList("Sarah", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Bob", new int[] { 1, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Jon", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Craig", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Klein", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Rain", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Water", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Grass", new int[] { 0, 0 })));
        expected1.add(new ArrayList<>(Arrays.asList("Ash", new int[] { 0, 0 })));
    	expected1.add(new ArrayList<>(Arrays.asList("Matt", new int[] { 0, 0 })));

    	mpo01.adjustSeatAllocation(index01, firstRound);

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
    	mpo01.adjustSeatAllocation(index01, firstRound);

    	assertEquals(expected1.size(), mpo01.seatAllocation.size());
    	for (int i = 0; i < expected1.size(); i++) {
        	assertEquals(expected1.get(i).size(), mpo01.seatAllocation.get(i).size());
        	assertEquals(expected1.get(i).get(0), mpo01.seatAllocation.get(i).get(0));
        	assertArrayEquals((int[]) expected1.get(i).get(1), (int[]) mpo01.seatAllocation.get(i).get(1));
    	}

    	// test 6.c test that the same index can be incremented twice

    	array[1]++;
    	mpo01.adjustSeatAllocation(index01, firstRound);

    	assertEquals(expected1.size(), mpo01.seatAllocation.size());
    	for (int i = 0; i < expected1.size(); i++) {
        	assertEquals(expected1.get(i).size(), mpo01.seatAllocation.get(i).size());
        	assertEquals(expected1.get(i).get(0), mpo01.seatAllocation.get(i).get(0));
        	assertArrayEquals((int[]) expected1.get(i).get(1), (int[]) mpo01.seatAllocation.get(i).get(1));
    	}

    	// 6.d testing with an index under accepted range IOException should be thrown

    	int index02 = -1;
    	assertThrows(IOException.class,
            	() -> mpo01.adjustSeatAllocation(index02, true));

    	// 6.e testing with an index over accepted range IOException should be thrown
    	int index03 = 10;
    	assertThrows(IOException.class,
            	() -> mpo01.adjustSeatAllocation(index03, true));
	}

	@Test
	public void testSeatAllocation() throws IOException {
    	// test 8.a seat should be given to top voted candidate
    	ArrayList<String> expectedWinOrder = new ArrayList<>();
    	expectedWinOrder.add("Sarah");
        mpo02.firstAllocation();
    	mpo02.secondAllocation();
    	assertEquals(expectedWinOrder, mpo02.winOrder);

    	// test 8.b Correctly allocates seats to the top 2 candidates
    	mpo04.firstAllocation();
    	mpo04.secondAllocation();
    	expectedWinOrder = new ArrayList<>();
    	expectedWinOrder.add("Sarah");
    	expectedWinOrder.add("Craig");
    	assertEquals(expectedWinOrder, mpo04.winOrder);

    	// test 8.c Correctly allocates seats to the top 2 candidates
    	mpo03.firstAllocation();
    	mpo03.secondAllocation();
    	expectedWinOrder = new ArrayList<>();
    	expectedWinOrder.add("Sarah");
    	expectedWinOrder.add("Bob");
    	assertEquals(expectedWinOrder, mpo03.winOrder);

    	// test 8.d second seat gets correctly allocated to party that has members available even if they didnt win votes
    	expectedWinOrder = new ArrayList<>();
    	expectedWinOrder.add("Sarah");
    	expectedWinOrder.add("Craig");
        mpo06.firstAllocation();
    	mpo06.secondAllocation();
    	assertEquals(expectedWinOrder, mpo06.winOrder);

    	// test 8.e there is a tie in seat allocation
    	int results = 0;
    	for(int i=0; i<1000; i++){
        	MPO mpo = new MPO(testFile);
        	mpo.secondAllocation();
        	// assertEquals(opl.winOrder.get(0),"D");
        	if(mpo.winOrder.get(0).equals("Sarah")){
            	results++;
        	}
    	}
    	assertTrue("Generated  value should be between 450 and 550", results<550);
    	assertTrue("Generated  value should be between 450 and 550", 450<results);
	}


	@Test
	public void testRunElection() throws IOException{

		// test 9.a normal opl election with one seat to allocate, no ties, 2 parties. 5 candidates
		MPO opl1 = new MPO(testFile02);
		ResultsData result1 = opl1.runElection();

		ArrayList<String> winOrder = new ArrayList<>();
		winOrder.add("Sarah");
		assertEquals(winOrder, result1.getPartyWinOrder());

		ArrayList<ArrayList<Object>> remainingVotes = new ArrayList<>();
		remainingVotes.add(new ArrayList<Object>(Arrays.asList("Sarah", 400)));
		remainingVotes.add(new ArrayList<Object>(Arrays.asList("Bob", 200)));
        remainingVotes.add(new ArrayList<Object>(Arrays.asList("Jon", 100)));
        remainingVotes.add(new ArrayList<Object>(Arrays.asList("Craig", 50)));
        remainingVotes.add(new ArrayList<Object>(Arrays.asList("Klein", 250)));


		assertEquals(remainingVotes, result1.getRemainingVotes());

		ArrayList<ArrayList<Object>> seatAllocation = new ArrayList<>();
		seatAllocation.add(new ArrayList<Object>(Arrays.asList(" D", new int[] {0,1})));
		seatAllocation.add(new ArrayList<Object>(Arrays.asList(" R", new int[] {0,0}))); 
		int[] temp = (int[]) result1.getSeatAllocation().get(0).get(1);
		int[] temp2 = (int[]) seatAllocation.get(0).get(1);
		assertEquals(Arrays.toString(temp2), Arrays.toString(temp));

		HashMap<String, ArrayList<String>> partyCandidatesTest = new HashMap<>();
		partyCandidatesTest.put("D", new ArrayList<>(Arrays.asList("Sarah", "Bob", "Jon")));
    	partyCandidatesTest.put("R", new ArrayList<>(Arrays.asList("Craig", "Klein")));

		assertEquals(partyCandidatesTest, result1.fileData.getPartyCandidates());

		// test 9.b normal mpo election with 3 seats to allocate, no ties, 4 parties, 10 candidates

		MPO mpo2;
        mpo2 = new MPO(testFile03);
        result1 = mpo2.runElection();

		winOrder = new ArrayList<>(Arrays.asList("Bob", "Matt", "Water", "Sarah"));

		seatAllocation = new ArrayList<>();
		seatAllocation.add(new ArrayList<Object>(Arrays.asList(" Bob", new int[] {0,1})));
		seatAllocation.add(new ArrayList<Object>(Arrays.asList(" Matt", new int[] {0,0}))); 
		seatAllocation.add(new ArrayList<Object>(Arrays.asList(" Water", new int[] {1,0}))); 
		seatAllocation.add(new ArrayList<Object>(Arrays.asList(" Sarah", new int[] {0,1}))); 
		temp = (int[]) result1.getSeatAllocation().get(0).get(1);
		temp2 = (int[]) seatAllocation.get(0).get(1);
		assertEquals(Arrays.toString(temp2), Arrays.toString(temp));

		partyCandidatesTest = new HashMap<>();
		partyCandidatesTest.put("D", new ArrayList<>(Arrays.asList("Sarah","Bob", "Jon")));
    	partyCandidatesTest.put("R", new ArrayList<>(Arrays.asList("Craig", "Klein")));
    	partyCandidatesTest.put("G", new ArrayList<>(Arrays.asList("Rain", "Water", "Grass")));
    	partyCandidatesTest.put("L", new ArrayList<>(Arrays.asList("Ash", "Matt")));

		assertEquals(partyCandidatesTest, result1.fileData.getPartyCandidates());

	}

}