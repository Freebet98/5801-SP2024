import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExtractDataCPL extends ExtractData{
    ExtractDataCPL(BufferedReader validFile, String header) throws IOException {
        this.validFile = validFile;
        this.header = header;
        String line;

        line = validFile.readLine();
        verifyLineIsDigit(line);
        int numSeats = Integer.parseInt(line);

        line = validFile.readLine();
        verifyLineIsDigit(line);
        int numBallots = Integer.parseInt(line);

        line = validFile.readLine();
        verifyLineIsDigit(line);
        int numParties = Integer.parseInt(line);

        HashMap<String, ArrayList<String>> partyCandidates = formatPartyInformation(numParties);
        ArrayList<ArrayList<Object>> partyVotes = new ArrayList<ArrayList<Object>>();
        ArrayList<ArrayList<Object>> candidateVotes = new ArrayList<ArrayList<Object>>();;
        formatBallotInformation(partyVotes, candidateVotes);


    }
    @Override
    protected void formatBallotInformation(ArrayList<ArrayList<Object>> partyVotes, ArrayList<ArrayList<Object>> candidateVotes) {

    }
}
