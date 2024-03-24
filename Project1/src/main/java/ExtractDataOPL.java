import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used to extract data from the file for the OPL election
 * 
 * @author Bethany Freeman
 */
public class ExtractDataOPL extends ExtractData {

    /**
     * This creates an object of the ExtractDataOPL class
     *
     * @param validFile this the bufferedReader used to parse through the given
     *                  file
     * @param header    this is the header depicting which type of election it
     *                  is. This is found in the Main class and passed in
     * @throws IOException will happen if there is an error in reading the file
     */
    ExtractDataOPL(BufferedReader validFile, String header) {
        this.validFile = validFile;
        this.header = header;
    }

    /**
     * This method will read as many lines as there are number of parties in the
     * file, the first word
     * in the line will be used as the key in the HashMap and the rest beyond the
     * first , will be
     * added to the Arraylist of string, which represents the list of candidates for
     * a party
     * 
     * @param numParties     this represents the number of parties that are listed
     *                       in
     *                       the given file
     * @param partyVotes     this is an ArrayList<ArrayList<Object>> which contains
     *                       inner mappings of a party name and the number of
     *                       corresponding votes
     * @param candidateVotes this is an ArrayList<ArrayList<Object>> whih contains
     *                       inner mappings of a candidate name and the number of
     *                       corresponding votes
     * @return HashMap<String, ArrayList < String>> that represents a key value of a
     *         party name
     *         to a list of candidate names
     * @throws IOException if there is an error while reading the validFile
     */
    @Override
    protected HashMap<String, ArrayList<String>> formatPartyInformation(int numParties,
            ArrayList<ArrayList<Object>> partyVotes, ArrayList<ArrayList<Object>> candidateVotes) throws IOException {
        HashMap<String, ArrayList<String>> partyCandidates = new HashMap<>();
        String line;
        String[] splitLine;
        String partyName;
        String candidateName;
        ArrayList<Object> partyInner;
        ArrayList<Object> candidateInner;

        for (int i = 0; i < numParties; i++) {
            try {
                // reads a file and splits the words into a string[] and also trims the
                // whitespace
                line = validFile.readLine();
                splitLine = line.trim().split(",");
                partyName = splitLine[0];
                candidateName = splitLine[1];

                try {
                    // Add another candidate to a party already in the HashMap
                    ArrayList<String> tempList = partyCandidates.get(partyName);
                    tempList.add(candidateName);

                    partyCandidates.put(partyName, tempList);

                } catch (NullPointerException e) {
                    // Add new party with candidate to HashMap
                    ArrayList<String> tempList = new ArrayList<>();
                    tempList.add(candidateName);

                    partyCandidates.put(partyName, tempList);

                    // Add party to partyVotes
                    partyInner = new ArrayList<>();
                    partyInner.add(partyName);
                    partyInner.add(0);
                    partyVotes.add(partyInner);
                }

                // Add candidate to candidateVotes
                candidateInner = new ArrayList<>();
                candidateInner.add(candidateName);
                candidateInner.add(0);
                candidateVotes.add(candidateInner);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IOException("Candidates list is empty");
            }

        }

        return partyCandidates;
    }

    /**
     * this will format the ballots to update the number of votes in partyVotes, and
     * candidateVotes in the OPL election
     *
     * @param partyVotes      this is an ArrayList<ArrayList<Object>> which contains
     *                        inner mappings of a party name and the number of
     *                        corresponding
     *                        votes
     * @param candidateVotes  this is an ArrayList<ArrayList<Object>> whih contains
     *                        inner mappings of a candidate name and the number of
     *                        corresponding votes
     * @param partyCandidates
     * @throws IOException
     * @throws Exception
     */
    @Override
    protected void formatBallotInformation(ArrayList<ArrayList<Object>> partyVotes,
            ArrayList<ArrayList<Object>> candidateVotes, HashMap<String, ArrayList<String>> partyCandidates)
            throws IOException {
        String line;
        char[] splitLine;
        int index = -1;
        int count = 0;

        // Check for the EOF
        while ((line = validFile.readLine()) != null) {
            line.trim();
            splitLine = line.toCharArray();

            if (line.indexOf('1') == -1 || line.indexOf(',') == -1) {
                throw new IOException("File format is not in the correct format");
            }

            // Gets index in splitLine and adds 1 vote to it
            for (int i = 0; i < splitLine.length; i++) {
                if (splitLine[i] == '1') {
                    index = i;
                    count = (int) candidateVotes.get(index).get(1);
                    count += 1;
                    candidateVotes.get(index).set(1, count);

                }
            }

        }

        // Gathers the votes from candidateVotes and place in partyVotes
        for (int i = 0; i < candidateVotes.size(); i++) {
            String candidateName = (String) candidateVotes.get(i).get(0);
            putVotesInPartyVotes(partyVotes, candidateVotes, partyCandidates, candidateName);
        }
    }

    /**
     * This will get the party associated with the candidate name
     * It then gets the votes associated with the candidate name
     * Using this information it places the votes in the location of the party in
     * partyVotes
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
     * @param candidateName   this is the name of the current candidate whose votes
     *                        are
     *                        being added to partyVotes
     */
    private void putVotesInPartyVotes(ArrayList<ArrayList<Object>> partyVotes,
            ArrayList<ArrayList<Object>> candidateVotes, HashMap<String, ArrayList<String>> partyCandidates,
            String candidateName) {
        String returnKey = "";
        for (String key : partyCandidates.keySet()) {
            ArrayList<String> values = partyCandidates.get(key);
            if (values.contains(candidateName)) {
                returnKey = key;
                break;
            }
        }

        int votes = 0;
        for (ArrayList<Object> pairC : candidateVotes) {
            String candidate = (String) pairC.get(0);
            if (candidate.equals(candidateName)) {
                votes = (int) pairC.get(1);
                break;
            }
        }

        for (ArrayList<Object> pairP : partyVotes) {
            String party = (String) pairP.get(0);
            if (party.equals(returnKey)) {
                int currentVotes = (int) pairP.get(1);
                votes += currentVotes;
                pairP.set(1, votes);
                break;
            }
        }
    }
}
