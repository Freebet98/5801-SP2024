import java.util.ArrayList;
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
    protected ArrayList<ArrayList<Object>> remainingVotes;
    protected ArrayList<ArrayList<Object>> seatAllocation;
    protected ArrayList<String> winOrder;

    /**
     * Runs the election and populates a ResultsData object, will be implemented by
     * classes that inherit from this abstract class
     * 
     * @return returns the ResultsData object created and populated within the
     *         method containing the election results
     */
    abstract public ResultsData runElection();

    /**
     * This will access into the given index of the arraylist remainingvotes
     * and subtract largestRemainder from it
     * returns void
     * 
     * @param index
     */
    protected void adjustRemainingVotes(int index) {
        if (index < 0) {
            // error, invalid index
        }
        int val = (int) this.remainingVotes.get(index).get(1);
        val -= this.largestRemainder;
        this.remainingVotes.get(index).set(1, val);
    }

    /**
     * This will access into the given index of the arraylist
     * seatAllocation and add 1 to the seat total
     * returns void
     * 
     * @param index      which index this references in seatAllocation
     * @param firstRound true if allocating for the firstRound, false otherwise
     */
    protected void adjustSeatAllocation(int index, boolean firstRound) {
        int[] val = (int[]) this.seatAllocation.get(index).get(1);

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
     * @param index
     */
    protected void addWinner(int index) {
        String winner = (String) this.seatAllocation.get(index).get(0);
        this.winOrder.add(winner);
    }

    /**
     * Generates 1000 random floats and takes the 1001th as the return value to
     * circumvent the pseudorandomness of the Random object
     * 
     * @return returns the 1001th float
     */
    protected float generateRandom() {
        Random rand = new Random();
        // generates 100 random floats before the retval is calculated to eliminate some
        // psuedo randomness
        for (int i = 0; i <= 1000; i++) {
            rand.nextFloat();
        }
        // calculates a random float and makes in a range of
        float retVal = rand.nextFloat() * 10;
        return retVal;
    }

    /**
     * Preforms the first round of seat allocation.
     * Runs a while loop until all seat are allocated or all parties are under the
     * remainder.
     * Adds winners if votes>=largestRemainder otherwise increments the number under
     * the remainder
     */
    protected void firstAllocation() {
        int i = 0;
        int underRemain = 0;
        // run until there are no more seats to allocate or all parties are under the
        // remainder
        while (true) {
            int votes = (int) this.remainingVotes.get(i).get(1);
            if (votes >= largestRemainder) {
                adjustRemainingVotes(i);
                adjustSeatAllocation(i, true);
                addWinner(i);
                availableSeats--; // a winner was added so a seat should be removed
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
     */
    protected void secondAllocation() {
        // creates a new ArrayList<ArrayList<Object>> which is a copy of remainingVote
        ArrayList<ArrayList<Object>> remainVotesNew = new ArrayList<>();
        for (int i = 0; i < remainingVotes.size(); i++) {
            ArrayList<Object> innerList = new ArrayList<>(remainingVotes.get(i));
            remainVotesNew.add(innerList);
        }
        while (availableSeats > 0) {
            int max = (int) remainVotesNew.get(0).get(1);
            int index = 0;
            // finds the candidate with the highest number of votes
            for (int i = 1; i < remainingVotes.size(); i++) {
                int current = (int) remainingVotes.get(i).get(1);

                if (current > max) {
                    max = current;
                    index = i;
                }
            }
            // check to see if there are any ties
            ArrayList<Integer> indexTie = new ArrayList<>();
            for (int i = 0; i < remainingVotes.size(); i++) {
                if (i == index)
                    continue;

                int current = (int) remainingVotes.get(i).get(1);
                if (current == max) {
                    indexTie.add(i);
                }
            }

            // resolve ties if any
            if (!indexTie.isEmpty()) {
                int location = breakTie(indexTie.size());
                index = indexTie.get(location);
            }

            // allocate seat to the candidate with the highest score
            adjustSeatAllocation(index, false);
            int value = (int) remainingVotes.get(index).get(1);
            value -= largestRemainder;
            remainingVotes.get(index).set(1, value);
            availableSeats--; // a winner was added so a seat should be removed
        }
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
        for (int i = 1; i < ties.length; i++) {
            if (Math.abs(compval - ties[i]) < curSmallest) {
                curSmallest = Math.abs(compval - ties[i]);
                index = i;
                isSame = false;
            } else if (Math.abs(compval - ties[i]) == curSmallest) {
                isSame = true;
            }
        }
        if (isSame) {
            return breakTie(numTie);
        }
        return index;
    }
}
