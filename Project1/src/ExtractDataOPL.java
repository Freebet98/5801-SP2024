import java.io.BufferedReader;
import java.util.ArrayList;

public class ExtractDataOPL extends ExtractData{
    /**
     * TODO
     * @param validFile
     * @param header
     */
    ExtractDataOPL(BufferedReader validFile, String header){
        this.validFile = validFile;
        this.header = header;
    }

    /**
     * TODO
     * @param partyVotes
     * @param candidateVotes
     */
    @Override
    protected void formatBallotInformation(ArrayList<ArrayList<Object>> partyVotes, ArrayList<ArrayList<Object>> candidateVotes) {

    }
}
