import java.util.ArrayList;

abstract public class Election {
    protected FileData fileData;
    protected ResultsData results;
    protected int largestRemainder;
    protected int availableSeats;
    protected ArrayList<ArrayList<Object>> remainingVotes;
    protected ArrayList<ArrayList<Object>> seatAllocation;
    protected ArrayList<String> winOrder;

    /**
     * TODO
     * @return
     */
    abstract public ResultsData runElection();

    /**
     * This will access into the given index of the arraylist remainingvotes
     * and subtract largestRemainder from it
     * returns void
     * @param index
     */
    protected void adjustRemainingVotes(int index){
        if(index < 0) {
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
     * @param index
     */
    protected void adjustSeatAllocation(int index){
        int val =(int) this.seatAllocation.get(index).get(1);
        val++;
        this.seatAllocation.get(index).set(1, val);
    }

    /**
     * This will add the string of the party from seatAllocation
     * at the given index to finalWinOrder
     * returns void
     * @param index
     */
    protected void addWinner(int index){
        String winner = (String) this.seatAllocation.get(index).get(0);
        this.winOrder.add(winner);
    }

    /**
     * Derrick did this
     * @return
     */
    protected float generateRandom(){
        return 0;
    }

    /**
     * TODO
     */
    protected void firstAllocation(){

    }

    /**
     * TODO
     */
    protected void secondAllocation(){

    }

    /**
     * Derrick did this
     * @param numTie
     * @return
     */
    protected int breakTie(int numTie){
        return 0;
    }
}
