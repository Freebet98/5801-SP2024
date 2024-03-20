import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.*;

public class FileDataTest {
        // Create CPL input files
        FileData cplInput01;
        FileData cplInput02;
        FileData cplInput03;

        // Create OPL input files
        FileData oplInput04;
        FileData oplInput05;
        FileData oplInput06;

        @Before
        public void setUp() {

                // create partyCandidate lists for both CPL and OPL files
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
                partyCandidates05.put("Dem", new ArrayList<>(Arrays.asList("Sarah")));
                partyCandidates05.put("Grass", new ArrayList<>(Arrays.asList("Rain")));

                HashMap<String, ArrayList<String>> partyCandidates06 = new HashMap<>();
                partyCandidates06.put("Dem", new ArrayList<>(Arrays.asList("Sarah")));
                partyCandidates06.put("Grass", new ArrayList<>(Arrays.asList("Rain")));

                // create partyVotes lists for both CPL and OPL file
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

                ArrayList<ArrayList<Object>> partyVotes04 = new ArrayList<>();
                partyVotes04.add(new ArrayList<>(Arrays.asList("Dem", 6000)));
                partyVotes04.add(new ArrayList<>(Arrays.asList("Rep", 4000)));

                ArrayList<ArrayList<Object>> partyVotes05 = new ArrayList<>();
                partyVotes05.add(new ArrayList<>(Arrays.asList("Dem", 1500)));
                partyVotes05.add(new ArrayList<>(Arrays.asList("Grass", 500)));

                ArrayList<ArrayList<Object>> partyVotes06 = new ArrayList<>();
                partyVotes06.add(new ArrayList<>(Arrays.asList("Dem", 1900)));
                partyVotes06.add(new ArrayList<>(Arrays.asList("Grass", 100)));

                // create candidateVotes list for both CPL and OPL
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
                candidateVotes04.add(new ArrayList<>(Arrays.asList("Sarah", 4500)));
                candidateVotes04.add(new ArrayList<>(Arrays.asList("Bob", 2000)));
                candidateVotes04.add(new ArrayList<>(Arrays.asList("Jon", 3500)));

                ArrayList<ArrayList<Object>> candidateVotes05 = new ArrayList<>();
                candidateVotes05.add(new ArrayList<>(Arrays.asList("Sarah", 1500)));
                candidateVotes05.add(new ArrayList<>(Arrays.asList("Rain", 500)));

                ArrayList<ArrayList<Object>> candidateVotes06 = new ArrayList<>();
                candidateVotes06.add(new ArrayList<>(Arrays.asList("Sarah", 1900)));
                candidateVotes06.add(new ArrayList<>(Arrays.asList("Rain", 100)));

                // finish initializing CPL FileData
                cplInput01 = new FileData("CPL", 3, 10000, 4, partyCandidates01, partyVotes01, candidateVotes01);
                cplInput02 = new FileData("CPL", 0, 1000, 2, partyCandidates02, partyVotes02, candidateVotes02);
                cplInput03 = new FileData("CPL", 10, 2000, 1, partyCandidates03, partyVotes03, candidateVotes03);

                // finish initializing OPL FileData
                oplInput04 = new FileData("OPL", 2, 10000, 3, partyCandidates04, partyVotes04, candidateVotes04);
                oplInput05 = new FileData("OPL", 0, 2000, 2, partyCandidates05, partyVotes05, candidateVotes05);
                oplInput06 = new FileData("OPL", 10, 2000, 2, partyCandidates06, partyVotes06, candidateVotes06);
        }

        // Test start here

        @Test
        public void testCheckElectionType() {
                // Test 1.a
                assertEquals("CPL", cplInput01.getElectionType());

                // Test 1.b
                assertEquals("CPL", cplInput02.getElectionType());

                // Test 1.c
                assertEquals("CPL", cplInput03.getElectionType());

                // Test 1.d
                assertEquals("OPL", oplInput04.getElectionType());

                // Test 1.e
                assertEquals("OPL", oplInput05.getElectionType());

                // Test 1.f
                assertEquals("OPL", oplInput06.getElectionType());
        }

        @Test
        public void testCheckNumSeats() {
                // Test 2.a
                assertEquals(3, cplInput01.getNumberSeats());

                // Test 2.b
                assertEquals(0, cplInput02.getNumberSeats());

                // Test 2.c
                assertEquals(10, cplInput03.getNumberSeats());

                // Test 2.d
                assertEquals(2, oplInput04.getNumberSeats());

                // Test 2.e
                assertEquals(0, oplInput05.getNumberSeats());

                // Test 2.f
                assertEquals(10, oplInput06.getNumberSeats());
        }

        @Test
        public void testCheckNumBallots() {
                // Test 3.a
                assertEquals(10000, cplInput01.getNumberBallots());

                // Test 3.b
                assertEquals(1000, cplInput02.getNumberBallots());

                // Test 3.c
                assertEquals(2000, cplInput03.getNumberBallots());

                // Test 3.d
                assertEquals(10000, oplInput04.getNumberBallots());

                // Test 3.e
                assertEquals(2000, oplInput05.getNumberBallots());

                // Test 3.f
                assertEquals(2000, oplInput06.getNumberBallots());
        }

        @Test
        public void testCheckNumParties() {
                // Test 4.a
                assertEquals(4, cplInput01.getNumberParties());

                // Test 4.b
                assertEquals(2, cplInput02.getNumberParties());

                // Test 4.c
                assertEquals(1, cplInput03.getNumberParties());

                // Test 4.d
                assertEquals(3, oplInput04.getNumberParties());

                // Test 4.e
                assertEquals(2, oplInput05.getNumberParties());

                // Test 4.f
                assertEquals(2, oplInput06.getNumberParties());
        }

        @Test
        public void testCheckPartyCandidates01() {
                // Test 5.a1
                assertEquals(true, cplInput01.getPartyCandidates().containsKey("Dem"));

                // Test 5.a2
                assertEquals("[Sarah, Bob, Jon]", cplInput01.getPartyCandidates().get("Dem").toString());

                // Test 5.b1
                assertEquals(true, cplInput01.getPartyCandidates().containsKey("Rep"));

                // Test 5.b2
                assertEquals("[Craig, Klein]", cplInput01.getPartyCandidates().get("Rep").toString());

                // Test 5.c1
                assertEquals(true, cplInput01.getPartyCandidates().containsKey("Green"));

                // Test 5.c2
                assertEquals("[Rain, Water, Grass]", cplInput01.getPartyCandidates().get("Green").toString());

                // Test 5.d
                assertEquals(true, cplInput01.getPartyCandidates().containsKey("Lib"));

                // Test 5.d2
                assertEquals("[Ash, Matt]", cplInput01.getPartyCandidates().get("Lib").toString());

                // Test 5.e
                assertEquals(false, cplInput01.getPartyCandidates().containsKey("Grass"));
        }

        @Test
        public void testCheckPartyCandidates02() {
                // Test 6.a1
                assertEquals(true, cplInput02.getPartyCandidates().containsKey("Dem"));

                // Test 6.a2
                assertEquals("[Sarah, Bob, Jon]", cplInput01.getPartyCandidates().get("Dem").toString());

                // Test 6.b1
                assertEquals(true, cplInput02.getPartyCandidates().containsKey("Rep"));

                // Test 6.b2
                assertEquals("[Craig, Klein]", cplInput02.getPartyCandidates().get("Rep").toString());

                // Test 6.c
                assertEquals(false, cplInput02.getPartyCandidates().containsKey("Green"));

                // Test 6.d
                assertEquals(false, cplInput02.getPartyCandidates().containsKey("Lib"));

                // Test 6.e
                assertEquals(false, cplInput02.getPartyCandidates().containsKey("Grass"));
        }

        @Test
        public void testCheckPartyCandidates03() {
                // Test 7.a1
                assertEquals(true, cplInput03.getPartyCandidates().containsKey("Dem"));

                // Test 7.a2
                assertEquals("[Sarah, Bob, Jon]", cplInput03.getPartyCandidates().get("Dem").toString());

                // Test 7.b
                assertEquals(false, cplInput03.getPartyCandidates().containsKey("Rep"));

                // Test 7.c
                assertEquals(false, cplInput03.getPartyCandidates().containsKey("Green"));

                // Test 7.d
                assertEquals(false, cplInput03.getPartyCandidates().containsKey("Lib"));

                // Test 7.e
                assertEquals(false, cplInput03.getPartyCandidates().containsKey("Grass"));
        }

        @Test
        public void testCheckPartyCandidates04() {
                // Test 8.a1
                assertEquals(true, oplInput04.getPartyCandidates().containsKey("Dem"));

                // Test 8.a2
                assertEquals("[Sarah, Bob]", oplInput04.getPartyCandidates().get("Dem").toString());

                // Test 8.b1
                assertEquals(true, oplInput04.getPartyCandidates().containsKey("Rep"));

                // Test 8.b2
                assertEquals("[Craig]", oplInput04.getPartyCandidates().get("Rep").toString());

                // Test 8.c
                assertEquals(false, oplInput04.getPartyCandidates().containsKey("Green"));

                // Test 8.d
                assertEquals(false, oplInput04.getPartyCandidates().containsKey("Lib"));

                // Test 8.e
                assertEquals(false, oplInput04.getPartyCandidates().containsKey("Grass"));
        }

        @Test
        public void testCheckPartyCandidates05() {
                // Test 9.a1
                assertEquals(true, oplInput05.getPartyCandidates().containsKey("Dem"));

                // Test 9.a2
                assertEquals("[Sarah]", oplInput05.getPartyCandidates().get("Dem").toString());

                // Test 9.b1
                assertEquals(true, oplInput05.getPartyCandidates().containsKey("Grass"));

                // Test 9.b2
                assertEquals("[Rain]", oplInput05.getPartyCandidates().get("Grass").toString());

                // Test 9.c
                assertEquals(false, oplInput05.getPartyCandidates().containsKey("Green"));

                // Test 9.d
                assertEquals(false, oplInput05.getPartyCandidates().containsKey("Lib"));
        }

        @Test
        public void testCheckPartyCandidates06() {
                // Test 10.a1
                assertEquals(true, oplInput06.getPartyCandidates().containsKey("Dem"));

                // Test 10.a2
                assertEquals("[Sarah]", oplInput06.getPartyCandidates().get("Dem").toString());

                // Test 10.b1
                assertEquals(true, oplInput06.getPartyCandidates().containsKey("Grass"));

                // Test 10.b2
                assertEquals("[Rain]", oplInput06.getPartyCandidates().get("Grass").toString());

                // Test 10.c
                assertEquals(false, oplInput06.getPartyCandidates().containsKey("Green"));

                // Test 10.d
                assertEquals(false, oplInput06.getPartyCandidates().containsKey("Lib"));
        }

        @Test
        public void testCheckPartyVotes() {
                // Test 6.a
                assertEquals("[[Dem, 2500], [Rep, 3000], [Green, 2100], [Lib, 2400]]",
                                cplInput01.getPartyVotes().toString());

                // Test 6.b
                assertEquals("[[Dem, 750], [Rep, 250]]", cplInput02.getPartyVotes().toString());

                // Test 6.c
                assertEquals("[[Dem, 2000]]", cplInput03.getPartyVotes().toString());

                // Test 6.d
                assertEquals("[[Dem, 6000], [Rep, 4000]]", oplInput04.getPartyVotes().toString());

                // Test 6.e
                assertEquals("[[Dem, 1500], [Grass, 500]]", oplInput05.getPartyVotes().toString());

                // Test 6.f
                assertEquals("[[Dem, 1900], [Grass, 100]]", oplInput06.getPartyVotes().toString());
        }

        @Test
        public void testCheckCandidateVotes() {
                // Test 7.a
                assertEquals("[[Sarah, 0], [Bob, 0], [Jon, 0], [Craig, 0], [Klein, 0], [Rain, 0], [Water, 0], [Grass, 0], "
                                +
                                "[Ash, 0], [Matt, 0]]", cplInput01.getCandidateVotes().toString());

                // Test 7.b
                assertEquals("[[Sarah, 0], [Bob, 0], [Jon, 0], [Craig, 0], [Klein, 0]]",
                                cplInput02.getCandidateVotes().toString());

                // Test 7.c
                assertEquals("[[Sarah, 0], [Bob, 0], [Jon, 0]]", cplInput03.getCandidateVotes().toString());

                // Test 7.d
                assertEquals("[[Sarah, 4500], [Bob, 2000], [Jon, 3500]]", oplInput04.getCandidateVotes().toString());

                // Test 7.e
                assertEquals("[[Sarah, 1500], [Rain, 500]]", oplInput05.getCandidateVotes().toString());

                // Test 7.f
                assertEquals("[[Sarah, 1900], [Rain, 100]]", oplInput06.getCandidateVotes().toString());
        }

}
