import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class ExtractData {
    protected BufferedReader validFile;
    protected FileData fileData;
    protected String header;

    protected void verifyLineIsDigit(String line){

    }

    protected HashMap<String, ArrayList<String>> formatPartyInformation(int numberParties){

        return null;
    }

    abstract protected void formatBallotInformation(ArrayList<ArrayList<Object>> partyVotes,
                                                    ArrayList<ArrayList<Object>> candidateVotes);

}
