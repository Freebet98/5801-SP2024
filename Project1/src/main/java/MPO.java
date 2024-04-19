import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

/**
 * TODO: Write a description of what this class does here.
 * 
 * @author
 */
public class MPO extends Election {
    /**
     * TODO: Write a description of what this method does here.
     * 
     * @param fileData
     */
    MPO(FileData fileData) {
        // TODO: Pseudo code
        // filedate
        // availableSeats = fileData.getNumberSeats()
        // remainingVotes = deepCopy of fileData.getCandidateVotes() //The election
        // allocates seats to candidates not parties
        // winOrder = new ArrayList<string>
        // noCandidates = new HashSet

        // Calculate largestRemainder
        // Check for division by zero, tests for this are unneccesary though just a good
        // thing to have
    }

    /**
     * TODO: Write a description of what this method does here.
     */
    @Override
    public ResultsData runElection() throws IOException {
        // TODO: Pseudo code
        // firstAllocation()
        // secondAllocation()
        // results = new ResultsDataMPO
        // return results
        return null;
    }

    /**
     * TODO: Write a description of what this method does here.
     */
    @Override
    protected ArrayList<ArrayList<Object>> initializeSeatAllocation() {
        // TODO: Pseudo code
        // initialize ArrayList<ArrayList<Object>> initialized
        // for 0 to fileData.getCandidateVotes.size
        //  ArrayList innerList
        //  String candidateName = fileData.getCandidateVotes.get(i).get(0)
        //  innerList.add(candidateName)
        //  innerList.add(new int[2])
        //  initialized.add(innerList)

        // return initialized
        return null;
    }

}
