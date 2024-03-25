
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
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
        partyVotes01.add(new ArrayList<>(Arrays.asList("Dem", 2500)));
        partyVotes01.add(new ArrayList<>(Arrays.asList("Rep", 3000)));
        partyVotes01.add(new ArrayList<>(Arrays.asList("Green", 2100)));
        partyVotes01.add(new ArrayList<>(Arrays.asList("Lib", 2400)));

        ArrayList<ArrayList<Object>> partyVotes02 = new ArrayList<>();
        partyVotes02.add(new ArrayList<>(Arrays.asList("Dem", 750)));
        partyVotes02.add(new ArrayList<>(Arrays.asList("Rep", 250)));

        ArrayList<ArrayList<Object>> partyVotes03 = new ArrayList<>();
        partyVotes03.add(new ArrayList<>(Arrays.asList("Dem", 2000)));

        // create candidateVotes list for OPL 
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

        // finish initializing OPL FileData
        opl01 = new OPL(new FileData("CPL", 3, 10000, 4, partyCandidates01, partyVotes01, candidateVotes01));
        opl02 = new OPL(new FileData("CPL", 0, 1000, 2, partyCandidates02, partyVotes02, candidateVotes02));
        opl03 = new OPL(new FileData("CPL", 10, 2000, 1, partyCandidates03, partyVotes03, candidateVotes03));



        int RVlargestRemainder01 = 5;
        int RVindex01 = 2;
    }

    // Test starts here

    @Test
    public void testAdjustRemainingVotes() {
        excpected = 
        opl01.adjustRemainingVotes(1);


    }


}