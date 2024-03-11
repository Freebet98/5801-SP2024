import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExtractDataCPL extends ExtractData{
    /**
     * TODO
     * @param validFile
     * @param header
     * @throws IOException
     */
    ExtractDataCPL(BufferedReader validFile, String header) throws IOException {
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
