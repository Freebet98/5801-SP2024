import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * This is the abstract class for Elections opl and cpl to inherit
 * 
 * @author Rock Zgutowicz
 * @author Derrick Dischinger
 */
abstract public class Election {
    protected FileData fileData;
    protected ResultsData results;
    protected int largestRemainder;
    protected int availableSeats;
    protected HashSet<String> noCandidates;
    protected ArrayList<ArrayList<Object>> remainingVotes;
    protected ArrayList<ArrayList<Object>> seatAllocation;
    protected ArrayList<String> winOrder;

    /**
     * Runs the election and populates a ResultsData object, will be implemented by
     * classes that inherit from this abstract class
     * 
     * @return returns the ResultsData object created and populated within the
     *         method containing the election results
     * @throws IOException
     */
    abstract public ResultsData runElection() throws IOException;

    /**
     * This will access into the given index of the arraylist remainingvotes
     * and subtract largestRemainder from it
     * returns void
     * 
     * @param index
     * @throws IOException
     */
    protected void adjustRemainingVotes(int index) throws IOException {
        // Check if index is valid
        if (index < 0 || index >= this.remainingVotes.size()) {
            throw new IOException("invalid index passed into adjustRemainingVotes");
        }

        // Adjust remaining votes
        int val = (int) this.remainingVotes.get(index).get(1);
        val -= this.largestRemainder;
        this.remainingVotes.get(index).set(1, val);
    }

    /**
     * Creates a deep copy of a votes ArrayList to create a modifiable version
     * sed to set remainingVotes
     * 
     * @param votes the ArrayList<ArrayList<Object>> to be copied
     * 
     * @return returns the deep copied ArrayList<ArrayList<Object>>
     */

    protected ArrayList<ArrayList<Object>> deepCopyVotes(ArrayList<ArrayList<Object>> votes) {
        ArrayList<ArrayList<Object>> copy = new ArrayList<ArrayList<Object>>(votes.size());

        for (ArrayList<Object> innerList : votes) {
            ArrayList<Object> innerListCopy = new ArrayList<Object>(2);
            innerListCopy.add(new String((String) innerList.get(0))); // this is the string containing the party name
            innerListCopy.add(new Integer((Integer) innerList.get(1))); // this is the int representing num votes
            copy.add(innerListCopy);
        }

        return copy;
    }

    /**
     * initializes the seat allocation array to have default values of 0
     * 
     * @return returns initialized ArrayList<ArrayList<Object>>
     */

    protected ArrayList<ArrayList<Object>> initializeSeatAllocation() {
        ArrayList<ArrayList<Object>> initialized = new ArrayList<ArrayList<Object>>();

        // for each party, add a new ArrayList<Object> with the party name and [0, 0]
        for (int i = 0; i < this.fileData.getPartyVotes().size(); i++) {
            ArrayList<Object> innerList = new ArrayList<Object>();
            String partyName = (String) this.fileData.getPartyVotes().get(i).get(0);
            innerList.add(partyName);
            innerList.add(new int[2]);
            initialized.add(innerList);
        }

        return initialized;
    }

    /**
     * This will access into the given index of the arraylist
     * seatAllocation and add 1 to the seat total
     * returns void
     * 
     * @param index      which index this references in seatAllocation
     * @param firstRound true if allocating for the firstRound, false otherwise
     * @throws IOException
     */
    protected void adjustSeatAllocation(int index, boolean firstRound) throws IOException {
        // Check if index is valid
        if (index < 0 || index >= this.seatAllocation.size()) {
            throw new IOException("Index less than 0");
        }

        int[] val = (int[]) this.seatAllocation.get(index).get(1);

        // Adjust seat allocation
        if (firstRound) {
            val[0]++;
        } else {
            val[1]++;
        }

        this.seatAllocation.get(index).set(1, val);
    }

    /**
     * This will add the string of the party from seatAllocation
     * at the given index to finalWinOrder
     * returns void
     * 
     * @param index index to determine which party to check in seatAllocation
     * @throws IOException
     */
    protected void addWinner(int index) throws IOException {
        // Check if index is valid
        if (index < 0 || index >= this.seatAllocation.size()) {
            throw new IOException("Index less than 0");
        }

        // Add winner
        String winner = (String) this.seatAllocation.get(index).get(0);
        this.winOrder.add(winner);
    }

    /**
     * Checks whether there are no candidates in a party given an index
     * Adds them to the noCandidates hashset if true
     * 
     * @param index index to determine which party to check in seatAllocation
     * 
     */
    protected void checkNoCandidates(int index) throws IOException {
        // Check if index is valid
        if (index < 0 || index >= this.seatAllocation.size()) {
            throw new IOException("Index less than 0");
        }

        int[] currentAmt = (int[]) this.seatAllocation.get(index).get(1);
        String party = (String) this.seatAllocation.get(index).get(0);
        ArrayList<String> inner = this.fileData.getPartyCandidates().get(party);

        // Check if party has no candidates available
        if (currentAmt[0] == inner.size()) {
            noCandidates.add(party);
        }
    }

    /**
     * Preforms the first round of seat allocation.
     * Runs a while loop until all seat are allocated or all parties are under the
     * remainder.
     * Adds winners if votes>=largestRemainder otherwise increments the number under
     * the remainder
     * 
     * @throws IOException
     */
    protected void firstAllocation() throws IOException {
        int i = 0;
        int underRemain = 0;

        // run until there are no more seats to allocate or all parties are under the
        // remainder
        while (true) {
            int votes = (int) this.remainingVotes.get(i).get(1);
            String party = (String) this.remainingVotes.get(i).get(0);
            // Check if party has no candidates
            if (noCandidates.contains(party)) {
                underRemain++;
            // Check if a parties votes are greater than or equal to the largest remainder
            } else if (votes >= largestRemainder) {
                adjustRemainingVotes(i);
                adjustSeatAllocation(i, true);
                addWinner(i);
                checkNoCandidates(i);
                availableSeats--; // a winner was added so a seat should be removed
            // increment the number under the remainder
            } else {
                underRemain++; // underRemain is a number that indicates how many parties are currently under
                               // the largest remainder
            }

            i++;
            if (availableSeats <= 0) { // there are no seats, break from loop
                break;
            }
            if (underRemain >= remainingVotes.size()) { // Are all of the parties under the remainder
                break; // move to second allocation
            }
            if (i >= remainingVotes.size()) { // start round robin over
                underRemain = 0;
                i = 0;
            }
        }
    }

    /**
     * Preforms the second round of allocation.
     * Iterates through a list of remaining votes and allocates seats
     * based on the votes associated with each candidate.
     * Resolves ties if multiple candidates have the same highest score.
     * 
     * @throws IOException
     */
    protected void secondAllocation() throws IOException {
        // creates a new ArrayList<ArrayList<Object>> which is a copy of remainingVote
        ArrayList<ArrayList<Object>> remainVotesNew = deepCopyVotes(remainingVotes);
        
        // run until there are no more seats to allocate
        while (availableSeats > 0) {
            int max = (int) remainVotesNew.get(0).get(1);
            int index = 0;
            String party = (String) remainVotesNew.get(0).get(0);
            
            // Check if party has no candidates
            if (noCandidates.contains(party)) {
                max = -1;
            }

            // finds the candidate with the highest number of votes
            for (int i = 1; i < remainingVotes.size(); i++) {
                int current = (int) remainVotesNew.get(i).get(1);

                if (current > max) {
                    max = current;
                    index = i;
                }
            }

            // check to see if there are any ties
            ArrayList<Integer> indexTie = new ArrayList<>();
            for (int i = 0; i < remainingVotes.size(); i++) {
                if (i == index) {
                    indexTie.add(i);
                    continue;
                }

                int current = (int) remainVotesNew.get(i).get(1);
                if (current == max) {
                    indexTie.add(i);
                }
            }

            // resolve ties if any
            if (!(indexTie.size() == 1)) {
                int location = breakTie(indexTie.size());
                index = indexTie.get(location);
            }

            // allocate seat to the candidate with the highest score
            adjustSeatAllocation(index, false);
            addWinner(index);
            int value = (int) remainVotesNew.get(index).get(1);
            value -= largestRemainder;
            remainVotesNew.get(index).set(1, value);
            availableSeats--; // a winner was added so a seat should be removed
        }
    }

    /**
     * Generates 1000 random floats and takes the 1001th as the return value to
     * circumvent the pseudorandomness of the Random object
     * 
     * @return returns the 1001th float
     */
    protected float generateRandom() {
        Random rand = new Random();
        
        // generates 1000 random floats before the retval is calculated to eliminate some
        // psuedo randomness
        for (int i = 0; i <= 1000; i++) {
            rand.nextFloat();
        }

        // calculates a random float and makes in a range of
        float retVal = rand.nextFloat() * 10;
        return retVal;
    }

    /**
     * Breaks the tie based on a number of tied people, if there is a tie in the tie
     * break
     * it will rerun breakTie. Determines the winner by generating a random number
     * via generateRandom()
     * method and comparing to numTie random numbers (also generated via
     * generateRandom()). The closest
     * number to the compare value is the winner
     * 
     * @param numTie the number of tied candidates/parties
     * @return returns the index of the winner
     */
    protected int breakTie(int numTie) {
        // if numTie is less than or equal to 0 or greater than the number of parties, return -1
        if (numTie <= 0 || numTie > this.fileData.getNumberParties()) {
            return -1;
        }

        // the number used to determine the winner
        float compval = generateRandom();

        // an array
        float[] ties = new float[numTie];
        for (int i = 0; i < ties.length; i++) {
            ties[i] = generateRandom();
        }

        float curSmallest = Math.abs(compval - ties[0]);
        int index = 0;
        boolean isSame = false;

        // compares to find the index of the number closest to the compVal
        for (int i = 1; i < ties.length; i++) {
            if (Math.abs(compval - ties[i]) < curSmallest) {
                curSmallest = Math.abs(compval - ties[i]);
                index = i;
                isSame = false;
            } else if (Math.abs(compval - ties[i]) == curSmallest) {
                isSame = true;
            }
        }

        // if there are 2 of the same, run breakTie again
        if (isSame) {
            return breakTie(numTie);
        }
        
        return index;
    }
}
