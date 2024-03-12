import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FileDataTest{

    @Before
    public void setUp(){
        //Create CPL input files
        FileData cplInput01;
        FileData cplInput02;
        FileData cplInput03;

        //Create OPL input files
        FileData oplInput04;
        FileData oplInput05;
        FileData oplInput06;

        //create partyCandidate lists for both CPL and OPL files
        HashMap<String, ArrayList<String>> partyCandidates01 = setUpPartyCandidates(["Dem", "Rep", "Green", "Lib"], [["Sarah", "Bob", "Jon"], ["Craig", "Klein"], ["Rain", "Water", "Grass"], ["Ash", "Matt"]]);
        HashMap<String, ArrayList<String>> partyCandidates02 = setUpPartyCandidates(["Dem", "Rep"], [["Sarah", "Bob", "Jon"], ["Craig", "Klein"]]);
        HashMap<String, ArrayList<String>> partyCandidates03 = setUpPartyCandidates(["Dem"], [["Sarah", "Bob", "Jon"]]);
        HashMap<String, ArrayList<String>> partyCandidates04 = setUpPartyCandidates(["Dem", "Dem", "Rep"], [["Sarah"], ["Bob"], ["Craig"]]);
        HashMap<String, ArrayList<String>> partyCandidates05 = setUpPartyCandidates(["Dem", "Grass"], [["Sarah"], ["Rain"]]);
        HashMap<String, ArrayList<String>> partyCandidates06 = setUpPartyCandidates(["Dem", "Grass"], [["Sarah"], ["Rain"]]);

        //create partyVotes lists for both CPL and OPL file
        ArrayList<ArrayList<Object>> partyVotes01 = setUpVotes(["Dem", "Rep", "Green", "Lib"], [2500, 3000, 2100, 2400]);
        ArrayList<ArrayList<Object>> partyVotes02 = setUpVotes(["Dem", "Rep"], [750, 250]);
        ArrayList<ArrayList<Object>> partyVotes03 = setUpVotes(["Dem"], [2000]);
        ArrayList<ArrayList<Object>> partyVotes04 = setUpVotes(["Dem", "Rep"], [6000, 4000]);
        ArrayList<ArrayList<Object>> partyVotes05 = setUpVotes(["Dem", "Grass"], [1500, 500]);
        ArrayList<ArrayList<Object>> partyVotes06 = setUpVotes(["Dem", "Grass"], [1900, 100]);

        //create candidateVotes list for both CPL and OPL
        ArrayList<ArrayList<Object>> candidateVotes01 = setUpVotes(["Sarah", "Bob", "Jon", "Craig", "Klein", "Rain", "Water", "Grass", "Ash", "Matt"], [0,0,0,0,0,0,0,0,0,0]);
        ArrayList<ArrayList<Object>> candidateVotes02 = setUpVotes(["Sarah", "Bob", "Jon", "Craig", "Klein"], [0,0,0,0,0]);
        ArrayList<ArrayList<Object>> candidateVotes03 = setUpVotes(["Sarah", "Bob", "Jon"], [0,0,0]);
        ArrayList<ArrayList<Object>> candidateVotes04 = setUpVotes(["Sarah", "Bob", "Jon"], [4500, 2000, 3500]);
        ArrayList<ArrayList<Object>> candidateVotes05 = setUpVotes(["Sarah, Rain"], [1500, 500]);
        ArrayList<ArrayList<Object>> candidateVotes06 = setUpVotes(["Sarah", "Rain"], [1900, 100]);

        //finish initializing CPL FileData
        cplInput01 = new FileData("CPL", 3, 10000, 4, partyCandidates01, partyVotes01, candidateVotes01);
        cplInput02 = new FileData("CPL", 0, 1000, 2, partyCandidates02, partyVotes02, candidateVotes02);
        cplInput03 = new FileData("CPL", 10, 2000, 1, partyCandidates03, partyVotes03, candidateVotes03);

        //finish initializing OPL FileData
        oplInput04 = new FileData("OPL", 2, 10000, 3, partyCandidates04, partyVotes04, candidateVotes04);
        oplInput05 = new FileData("OPL", 0, 2000, 2, partyCandidates05, partyVotes05, candidateVotes05);
        oplInput06 = new FileData("OPL", 10, 2000, 2, partyCandidates06, partyVotes06, candidateVotes06);
    }

    public HashMap<String, ArrayList<String>> setUpPartyCandidates(String[] partyNames, String[][] names){
        HashMap<String, ArrayList<String>> returnVal = new HashMap<>();
        for(int i = 0; i < names.length(); i++){
            String key = partyList[i];
            ArrayList<String> value = new ArrayList<>();
            for(int k = 0; k < names[i].length(); k++){
                value.add(k)
            }
            returnVal.put(key, value);
            value.clear();
        }

        return returnVal;
    }

    public ArrayList<ArrayList<Object>> setUpVotes(String[] names, int[] votes){
        ArrayList<ArrayList<Object>> returnVal = new ArrayList<>();

        for(int i = 0; i < names.length(); i++){
            ArrayList<Object> value = new ArrayList<>();
            value.add(names[i]);
            value.add(votes[i]);
            returnVal.add(value);
            value.clear();
        }

        return returnVal;
    }

    //Test start here

    @Test
    public void testCheckElectionType(){
        //Test 1.a
        assertEquals("CPL", cplInput01.getElectionType(), "ElectionType is wrong for CPL Input file 01 - test 1.a");

        //Test 1.b
        assertEquals("CPL", cplInput02.getElectionType(), "ElectionType is wrong for CPL Input file 02 - test 1.b");

        //Test 1.c
        assertEquals("CPL", cplInput03.getElectionType(), "ElectionType is wrong for CPL Input file 03 - test 1.c");

        //Test 1.d
        assertEquals("OPL", oplInput04.getElectionType(), "ElectionType is wrong for OPL Input file 04 - test 1.d");

        //Test 1.e
        assertEquals("OPL", oplInput05.getElectionType(), "ElectionType is wrong for OPL Input file 05 - test 1.e");

        //Test 1.f
        assertEquals("OPL", oplInput06.getElectionType(), "ElectionType is wrong for OPL Input file 06 - test 1.f");    
    }

    @Test
    public void testCheckNumSeats(){
        //Test 2.a
        assertEquals(3, cplInput01.getNumberSeats(), "Number Seats is wrong for CPL Input file 01 - Test 2.a");

        //Test 2.b
        assertEquals(0, cplInput02.getNumberSeats(), "Number Seats is wrong for CPL Input file 02 - Test 2.b");

        //Test 2.c
        assertEquals(10, cplInput03.getNumberSeats(), "Number Seats is wrong for CPL Input file 03 - Test 2.c");

        //Test 2.d
        assertEquals(2, oplInput04.getNumberSeats(), "Number Seats is wrong for OPL Input file 04 - Test 2.d");

        //Test 2.e
        assertEquals(0, oplInput05.getNumberSeats(), "Number Seats is wrong for OPL Input file 05 - Test 2.e");

        //Test 2.f
        assertEquals(10, oplInput06.getNumberSeats(), "Number Seats is wrong for OPL Input file 06 - Test 2.f");
    }

    @Test
    public void testCheckNumBallots(){
        //Test 3.a
        assertEquals(10000, cplInput01.getNumberBallots(), "Number Ballots is wrong for CPL Input file 01 - Test 3.a");

        //Test 3.b
        assertEquals(1000, cplInput02.getNumberBallots(), "Number Ballots is wrong for CPL Input file 02 - Test 3.b");

        //Test 3.c
        assertEquals(2000, cplInput03.getNumberBallots(), "Number Ballots is wrong for CPL Input file 03 - Test 3.c");

        //Test 3.d
        assertEquals(10000, oplInput04.getNumberBallots(), "Number Ballots is wrong for OPL Input file 04 - Test 3.d");

        //Test 3.e
        assertEquals(2000, oplInput05.getNumberBallots(), "Number Ballots is wrong for OPL Input file 05 - Test 3.e");

        //Test 3.f
        assertEquals(2000, oplInput06.getNumberBallots(), "Number Ballots is wrong for OPL Input file 06 - Test 3.f");
    }

    
}
  
