import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;

/**
 * This class is used to extract data from the file for the MV election
 * 
 * @author Rock Zgutowicz
 * @author Bethany Freeman
 */
public class ExtractDataMV extends ExtractData {
    /**
     * This creates an object of the ExtractDataMV class
     *
     * @param validFiles this is the Arrayist of bufferedReader objects so an
     *                   election can have multiple files
     * @param header     this is the header depicting which type of election it
     *                   is. This is found in the Main class and passed in
     */
    ExtractDataMV(ArrayList<BufferedReader> validFiles, String header) {
        this.validFiles = validFiles;
        this.header = header;
    }

    /**
     * this will format the ballots to update the number of votes in partyVotes, and
     * candidateVotes in the MV election
     *
     * @param partyVotes      this is an ArrayList<ArrayList<Object>> which contains
     *                        inner mappings of a party name and the number of
     *                        corresponding
     *                        votes
     * @param candidateVotes  this is an ArrayList<ArrayList<Object>> whih contains
     *                        inner mappings of a candidate name and the number of
     *                        corresponding votes
     * @param partyCandidates this is a mapping of a party name to an ordered list
     *                        of their candidates
     * @throws IOException
     */
    @Override
    protected void formatBallotInformation(ArrayList<ArrayList<Object>> partyVotes,
            ArrayList<ArrayList<Object>> candidateVotes, HashMap<String, ArrayList<String>> partyCandidates,
            int numSeats) throws IOException {
        // TODO: pseudo code
        String line;
        char[] onesCount;
        String[] splitLine;
        int count;
        int curCount = 0;


        // tempCount is used to count the numbre of votes in candidateVotes in the 
        // current file being read, may not be necessary
        ArrayList<Integer> tempCount = new ArrayList<>();
        for (int i = 0; i < candidateVotes.size(); i++) {
            tempCount.add(0);
        }

        while((line = validFile.readLine()) != null) {
            onesCount = line.toCharArray();

            // Find how many times '1' occures in the line
            count = 0;
            for (int i = 0; i < onesCount.length; i++) {
                if (onesCount[i] == '1') {
                    count++;
                }
            }

            if (count > numSeats || count < 1) {
                throw new IOException("Invalid file format");
            }

            splitLine = line.trim().split(",", -1);
            System.out.println(Arrays.toString(splitLine));
            for (int i = 0; i < splitLine.length; i++) {
                if (splitLine[i].equals("1")) {
                    curCount = (int) tempCount.get(i);
                    curCount++;
                    tempCount.set(i, curCount);
                    count = (int) candidateVotes.get(i).get(1);
                    count++;
                    candidateVotes.get(i).set(1, count);
                }
                // If splitLine[i] is not an empty string, that means an invalid
                // file was passed in
                else if (!splitLine[i].equals("")) {
                    throw new IOException("Invalid file format");
                }
            }
        }

        // Gathers the votes from candidateVotes and place in partyVotes
        for (int i = 0; i < candidateVotes.size(); i++) {
            String candidateName = (String) candidateVotes.get(i).get(0);
            putVotesInPartyVotes(partyVotes, candidateVotes, partyCandidates, candidateName, tempCount, i);
        }
    }

    // public static void main(String[] args) throws IOException {
    //     ExtractDataMV test;
    //     ArrayList<ArrayList<Object>> partyVotes = new ArrayList<>();
    //     ArrayList<ArrayList<Object>> candidateVotes = new ArrayList<>();
    //     ArrayList<BufferedReader> validFile;
    //     HashMap<String, ArrayList<String>> partyCandidates;
    //     partyVotes = new ArrayList<ArrayList<Object>>();
    //     candidateVotes = new ArrayList<ArrayList<Object>>();

    //     // Test 3.a correct formatting
    //     validFile = new ArrayList<BufferedReader>(Arrays.asList(new BufferedReader(new FileReader(new File("src/test/java/InputFiles/MVBallotTest01.txt")))));
    //     test = new ExtractDataMV(validFile, "MV");
    //     test.validFile = validFile.get(0);
    //     partyCandidates = test.formatPartyInformation(partyVotes, candidateVotes, false);
    //     test.formatBallotInformation(partyVotes, candidateVotes, partyCandidates, 3);
    //     ArrayList<ArrayList<Object>> expectedPartyVotes = new ArrayList<>();
    //     ArrayList<ArrayList<Object>> expectedCandidateVotes = new ArrayList<>();

    //     expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Pike", 4)));
    //     expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Foster", 3)));
    //     expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Deutsch", 4)));
    //     expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Borg", 4)));
    //     expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Jones", 4)));
    //     expectedCandidateVotes.add(new ArrayList<>(Arrays.asList("Smith", 2)));

    //     expectedPartyVotes.add(new ArrayList<>(Arrays.asList("D", 7)));
    //     expectedPartyVotes.add(new ArrayList<>(Arrays.asList("R", 12)));
    //     expectedPartyVotes.add(new ArrayList<>(Arrays.asList("I", 2)));
    // }
}
