import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class ExtractData {
    protected BufferedReader validFile;
    protected FileData fileData;
    protected String header;

    /**
     * This is used to extract data from the File, assumes all checks done within functions called work
     * @return fileData, this is a FileData object containing the information from the extracted file
     * @throws IOException will throw this exception from readLine() if an error has occurred while
     *                      reading the file
     */
    protected FileData extractFromFile() throws IOException {
        //represents the string obtained from the BufferedReader while reading a line
        String line;

        //Looks at the second line of the file, if it's an integer, numSeats is set to it
        line = validFile.readLine();
        verifyLineIsDigit(line);
        int numSeats = Integer.parseInt(line);

        //Looks at the third line of the file, if it's an integer, numBallots is set to it
        line = validFile.readLine();
        verifyLineIsDigit(line);
        int numBallots = Integer.parseInt(line);

        //Looks at the fourth line of the file, if it's an integer, numParties is set to it
        line = validFile.readLine();
        verifyLineIsDigit(line);
        int numParties = Integer.parseInt(line);

        HashMap<String, ArrayList<String>> partyCandidates = formatPartyInformation(numParties);

        //Create two new ArrayList<ArrayList<Object>> to store partyVotes and candidateVotes,
        //Values for these get set in formatBallotInformation
        ArrayList<ArrayList<Object>> partyVotes = new ArrayList<>();
        ArrayList<ArrayList<Object>> candidateVotes = new ArrayList<>();
        formatBallotInformation(partyVotes, candidateVotes);

        fileData = new FileData(header, numSeats, numBallots, numParties, partyCandidates, partyVotes, candidateVotes);
        return fileData;
    }

    /**
     * TODO
     * @param line
     */
    protected void verifyLineIsDigit(String line){

    }

    /**
     * TODO
     * @param numParties
     * @return
     */
    protected HashMap<String, ArrayList<String>> formatPartyInformation(int numParties){

        return null;
    }

    /**
     * TODO
     * @param partyVotes
     * @param candidateVotes
     */
    abstract protected void formatBallotInformation(ArrayList<ArrayList<Object>> partyVotes,
                                                    ArrayList<ArrayList<Object>> candidateVotes);

}
