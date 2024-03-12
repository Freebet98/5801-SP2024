import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class OPL extends Election{
    /**
     * TODO
     * @param fileData
     */
    OPL(FileData fileData){
        this.fileData = fileData;
    }

    /**
     * TODO
     * @return
     */
    @Override
    public ResultsData runElection() {
        return null;
    }

    /**
     * TODO
     * @param partyCandidates
     * @param candidateVotes
     */
    private void candidateRankings(HashMap<String, ArrayList<String>> partyCandidates,
                                   ArrayList<ArrayList<Object>> candidateVotes){

    }

    /**
     * TODO
     * @param candidateVotes
     */
    private void sortCandidates(ArrayList<String>, ArrayList<ArrayList<Object>> candidateVotes){

    }

    /**
     * TODO
     * @param candidateVotes
     * @param candidate
     * @return
     */
    private int getVotes(ArrayList<ArrayList<Object>> candidateVotes, String candidate){
        return 0;
    }
}
