/**
 * This class is used to run an OPL election
 * @author 
 */

import java.util.ArrayList;
import java.util.HashMap;

public class OPL extends Election{
    /**
     * Creates an Object of type OPL, which inherits from Election, this is
	 * used to run the election of the passed in information from fileData
     * @param fileData a FileData object containing information from the
	 * original file passed in
     */
    OPL(FileData fileData){
        this.fileData = fileData;
    }

    /**
     * Runs an OPL election on the content contained in fileData
     * @return ResultsData which contains all the information from FileData
	 * 			as well as all the information from running the Election for
     * 			an OPL system
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
    private void sortCandidates(ArrayList<String> candidates, ArrayList<ArrayList<Object>> candidateVotes){

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
