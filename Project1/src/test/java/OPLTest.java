
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;



import java.util.*;

/**
 * This class is used to test the OPL class
 * 
 * @author
 */
public class OPLTest{

    // Create CPL input files
    OPL opl01;
    OPL opl02;
    OPL opl03;

    @Before
    public void setUp() {

        // Create 4 ResultsDataCPL objects, 3 of which with proper outcomes for each cplInput,
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

        // create partyVotes lists for OPL file
        ArrayList<ArrayList<Object>> partyVotes01 = new ArrayList<>();
        partyVotes01.add(new ArrayList<>(Arrays.asList("Dem", 0)));
        partyVotes01.add(new ArrayList<>(Arrays.asList("Rep", 0)));
        partyVotes01.add(new ArrayList<>(Arrays.asList("Green", 0)));
        partyVotes01.add(new ArrayList<>(Arrays.asList("Lib", 0)));

        ArrayList<ArrayList<Object>> partyVotes02 = new ArrayList<>();
        partyVotes02.add(new ArrayList<>(Arrays.asList("Dem", 0)));
        partyVotes02.add(new ArrayList<>(Arrays.asList("Rep", 0)));

        ArrayList<ArrayList<Object>> partyVotes03 = new ArrayList<>();
        partyVotes03.add(new ArrayList<>(Arrays.asList("Dem", 0)));

        // create candidateVotes list for OPL 
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
        candidateVotes02.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
        candidateVotes02.add(new ArrayList<>(Arrays.asList("Bob", 0)));
        candidateVotes02.add(new ArrayList<>(Arrays.asList("Jon", 0)));
        candidateVotes02.add(new ArrayList<>(Arrays.asList("Craig", 0)));
        candidateVotes02.add(new ArrayList<>(Arrays.asList("Klein", 0)));

        ArrayList<ArrayList<Object>> candidateVotes03 = new ArrayList<>();
        candidateVotes03.add(new ArrayList<>(Arrays.asList("Sarah", 0)));
        candidateVotes03.add(new ArrayList<>(Arrays.asList("Bob", 0)));
        candidateVotes03.add(new ArrayList<>(Arrays.asList("Jon", 0)));

        // finish initializing OPL FileData
        opl01 = new OPL(new FileData("OPL", 3, 380, 4, partyCandidates01, partyVotes01, candidateVotes01));
        opl02 = new OPL(new FileData("OPL", 1, 1000, 2, partyCandidates02, partyVotes02, candidateVotes02));
        opl03 = new OPL(new FileData("OPL", 10, 2000, 1, partyCandidates03, partyVotes03, candidateVotes03));

    }

    // Test starts here

    @Test
    public void testAdjustRemainingVotes() {

        // test 1.a adjustRemainingVotes correctly updates remainingVotes with a valid index

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


        opl01.adjustRemainingVotes(index01);
        assertEquals(opl01.remainingVotes, expectedA);


        // test 1.b index = -1 which is not within the valid range
        // all fields of remainingVotes should remain unchanged

        int index02 = -1;
        opl01.adjustRemainingVotes(index02);
        assertEquals(opl01.remainingVotes, expectedA);

        // test 1.c index = 15 which is not within the valid range
        // all fields of remainingVotes should remain unchanged

        int index03 = 15;
        opl01.adjustRemainingVotes(index03);
        assertEquals(opl01.remainingVotes, expectedA);


        // test 1.d ensure that the correct value is being subtracted from the index

        int index04 = 3;
        int expectedVal = (int)opl01.remainingVotes.get(index04).get(1) - ((int)opl01.fileData.getNumberBallots()/(int)opl01.fileData.getNumberSeats());
        opl01.adjustRemainingVotes(index04);
        assertEquals(opl01.remainingVotes.get(index04).get(1), expectedVal);
    }

    @Test
    public void testDeepCopyVotes() {

        // test 2.a ensure both arrays lists are the same after the method is called

        ArrayList<ArrayList<Object>> expected1 = new ArrayList<ArrayList<Object>>();
        expected1.add(new ArrayList<>(Arrays.asList("Sarah", 50)));
        expected1.add(new ArrayList<>(Arrays.asList("Joe", 20)));
        expected1.add(new ArrayList<>(Arrays.asList("Bill", 13)));
        expected1.add(new ArrayList<>(Arrays.asList("Timothey", 50)));

        ArrayList<ArrayList<Object>> copied1 =  opl01.deepCopyVotes(expected1);

        assertEquals(copied1, expected1);


        // test 2.b ensure variables can be changed in original and not affect copy

        expected1.get(1).set(1 , 10); // changes Joe from 20 to 10
        assertNotEquals(expected1, copied1);

        // test 2.c situation where an empty list is passed in

        ArrayList<ArrayList<Object>> expected2 = new ArrayList<ArrayList<Object>>();
        ArrayList<ArrayList<Object>> copied2 =  opl01.deepCopyVotes(expected2);
        assertEquals(copied2, expected2);

        // test 2.d adding to one empty list will not affect the other

        expected2.add(new ArrayList<>(Arrays.asList("Sarah", 50)));
        assertNotEquals(expected1, copied1);
    }

    @Test
    public void testInitializeSeatAllocation() {

        // test 7.a ensure the resulting array has the correct default values in all fields
        
        ArrayList<ArrayList<Object>> expected1 = new ArrayList<ArrayList<Object>>();
        expected1.add(new ArrayList<>(Arrays.asList("Dem", new int[]{0, 0})));
        expected1.add(new ArrayList<>(Arrays.asList("Rep", new int[]{0, 0})));
        expected1.add(new ArrayList<>(Arrays.asList("Green", new int[]{0, 0})));
        expected1.add(new ArrayList<>(Arrays.asList("Lib", new int[]{0, 0})));

        ArrayList<ArrayList<Object>> result1 = opl01.initializeSeatAllocation();

        // maunally compare because assertEquals compares memory addresses for the int[]s
        assertEquals(expected1.size(), result1.size());
        for (int i = 0; i < expected1.size(); i++) {
            assertEquals(expected1.get(i).size(), result1.get(i).size());
            assertEquals(expected1.get(i).get(0), result1.get(i).get(0));
            assertArrayEquals((int[])expected1.get(i).get(1), (int[])result1.get(i).get(1));
        }





    }

    @Test
    public void testGenerateRandom() {
        // test 3.a: Check if generated random number is within the specified range
        float randomValue = opl01.generateRandom();
        assertTrue("Generated value should be >= 0", randomValue >= 0);
        assertTrue("Generated  value should be < 10", randomValue < 10);

        // test 3.b: Check if multiple calls to the method generate different values   <---- should this be included, whats a better way to check if random
        float previousRandomValue = opl01.generateRandom();
        boolean allDifferent = true;
        for (int i = 0; i < 100; i++) {
            float newRandomValue = opl01.generateRandom();
            if (newRandomValue == previousRandomValue) {
                allDifferent = false;
                break;
            }
            previousRandomValue = newRandomValue;
        }
        assertTrue("generateRandom() should generate different values (almost always)", allDifferent);
    }

    @Test
    public void testBreakTieMethod() {

        // test 4.a: numTie is 3, a valid number of ties

        int numTie01 = 3;
        // call the breakTie method using the OPL object
        int result01 = opl01.breakTie(numTie01);
        assertTrue("resulting index should be between 0 and 4 inclusive", result01 >= 0 && result01 < numTie01);


        // test 4.b: numTie is 0, not a valid tiebreak

        int numTie02 = 0;
        int result02 = opl01.breakTie(numTie02);
        assertEquals(result02, -1);


        // test 4.c: numTie is 5, beyond range of number of parties

        int numTie03 = 5;
        int result03 = opl01.breakTie(numTie03);
        assertEquals(result03, -1);


        // test 4.d: numTie is 1, should always return 0

        int numTie04 = 1;
        int result04 = opl01.breakTie(numTie04);
        assertEquals(result04, 0);

        // we need more tests here. Im still unclear on what shana means by testing the the structure of the code
        // how do i write a test for every conditional/loop in the method when it only usable value from an opl standpoint is the return value

    }
}