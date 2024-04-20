import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used to store all the information extracted from the file
 * 
 * @author Bethany Freeman
 */
public class FileData {
    protected final String electionType;
    protected final int numberSeats;
    protected final int numberBallots;
    protected final int numberParties;
    protected final HashMap<String, ArrayList<String>> partyCandidates;
    protected final ArrayList<ArrayList<Object>> partyVotes;
    protected final ArrayList<ArrayList<Object>> candidateVotes; // Not utilized within a CPL election

    /**
     * This creates an object of FileData, this is used to store all the information
     * extracted from the file passed
     * in by the user
     * 
     * @param electionType    this is a string representing what type of election is
     *                        being run on the file
     * @param numberSeats     this how many seats are up for election
     * @param numberBallots   this is how many ballots are on the file
     * @param numberParties   this is how many parties are participating in a CPL
     *                        election or the number of candidates
     *                        in an OPL election
     * @param partyCandidates this is a mapping of a party name to an ordered list
     *                        of their candidates
     * @param partyVotes      this is a mapping of the party name to the number of
     *                        votes that party has, the actual
     *                        ArrayList will be of type ArrayList<ArrayList<String,
     *                        int>>
     * @param candidateVotes  this a mapping of each candidate and the amount of
     *                        votes they have, not utilized in a CPL
     *                        election, the actual ArrayList will be of type
     *                        ArrayList<ArrayList<String, int>>
     */
    FileData(String electionType, int numberSeats, int numberBallots, int numberParties,
            HashMap<String, ArrayList<String>> partyCandidates, ArrayList<ArrayList<Object>> partyVotes,
            ArrayList<ArrayList<Object>> candidateVotes) {
        this.electionType = electionType;
        this.numberSeats = numberSeats;
        this.numberBallots = numberBallots;
        this.numberParties = numberParties;
        this.partyCandidates = partyCandidates;
        this.partyVotes = partyVotes;
        this.candidateVotes = candidateVotes;
    }

    /**
     * @return the electionType of the election
     */
    public String getElectionType() {
        return electionType;
    }

    /**
     * @return the number of seats available
     */
    public int getNumberSeats() {
        return numberSeats;
    }

    /**
     * @return the number of ballots that were on the file
     */
    public int getNumberBallots() {
        return numberBallots;
    }

    /**
     * @return In a CPL election, the number of parties running
     *         In an OPL election, the number of candidates running
     */
    public int getNumberParties() {
        return numberParties;
    }

    /**
     * @return a mapping of the party name to its list of candidates
     */
    public HashMap<String, ArrayList<String>> getPartyCandidates() {
        return partyCandidates;
    }

    /**
     * @return a mapping of the party name and the number of votes it has
     */
    public ArrayList<ArrayList<Object>> getPartyVotes() {
        return partyVotes;
    }

    /**
     * @return a mapping of the candidate name and the number of votes they have,
     *         not utilized in a CPL election
     */
    public ArrayList<ArrayList<Object>> getCandidateVotes() {
        return candidateVotes;
    }
}
