import java.util.ArrayList;

abstract public class Election {
    FileData fileData;
    ResultsData results;
    int largestRemainder;
    int availableSeats;
    ArrayList<ArrayList<Object>> remainingVotes;
    ArrayList<ArrayList<Object>> seatAllocation;
    ArrayList<String> winOrder;

    /**
     * TODO
     * @return
     */
    abstract public ResultsData runElection();

    /**
     * TODO
     * @param index
     */
    protected void adjustRemainingVotes(int index){

    }

    /**
     * TODO
     * @param index
     */
    protected void adjustSeatAllocation(int index){

    }

    /**
     * TODO
     * @param index
     */
    protected void addWinner(int index){

    }

    /**
     * TODO
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
     * TODO
     * @param numTie
     * @return
     */
    protected int breakTie(int numTie){
        return 0;
    }
}
