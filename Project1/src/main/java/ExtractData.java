import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @brief This class is used to extract data from the file
 * 
 * @author Bethany Freeman
 */
abstract public class ExtractData {
    protected ArrayList<BufferedReader> validFiles;
    protected BufferedReader validFile;
    protected FileData fileData;
    protected String header;
    protected ArrayList<ArrayList<Object>> partyVotes = new ArrayList<>();
    protected ArrayList<ArrayList<Object>> candidateVotes = new ArrayList<>();

    /**
     * This is used to extract data from Multiple Files- works with CPL and OPL,
     * assumes all checks done within functions called work
     * 
     * This is an overloaded function
     * 
     * @return fileData, this is a FileData object containing the information from
     *         the extracted file
     * 
     * @throws IOException
     */
    protected FileData extractFromFile() throws IOException {
        // represents the string obtained from the BufferedReader while reading a line
        String line;
        validFile = validFiles.get(0);

        // Read header
        validFile.readLine();

        // Looks at the second line of the file, if it's an integer, numSeats is set to
        // it
        line = validFile.readLine();
        if (!verifyLineIsDigit(line)) {
            throw new IOException("Not enough digits");
        }
        int numSeats = Integer.parseInt(line);

        // Looks at the third line of the file, if it's an integer, numBallots is set to
        // it
        line = validFile.readLine();
        if (!verifyLineIsDigit(line)) {
            throw new IOException("Not enough digits");
        }
        int numBallots = Integer.parseInt(line);

        // Looks at the fourth line of the file, if it's an integer, numParties is set
        // to it
        line = validFile.readLine();
        if (!verifyLineIsDigit(line)) {
            throw new IOException("Not enough digits");
        }
        int numParties = Integer.parseInt(line);

        // Initialize partyCandidates with formatPartyInformation
        HashMap<String, ArrayList<String>> partyCandidates;
        partyCandidates = formatPartyInformation(numParties, partyVotes, candidateVotes);

        // Deals with multiple files
        for (int i = 0; i < validFiles.size(); i++) {
            validFile = validFiles.get(i);
            if (i != 0) {
                for (int k = 0; k < (4 + numParties); k++) {
                    if (k == 2) {
                        line = validFile.readLine();
                        if (!verifyLineIsDigit(line)) {
                            throw new IOException("Not enough digits");
                        }
                        numBallots += Integer.parseInt(line);
                    } else {
                        line = validFile.readLine();
                    }
                }
            }
            formatBallotInformation(partyVotes, candidateVotes, partyCandidates, numSeats);
        }

        fileData = new FileData(header, numSeats, numBallots, numParties, partyCandidates, partyVotes, candidateVotes);
        return fileData;
    }

    /**
     * This is used to extract data from Multiple Files- works with MPO and MV,
     * assumes all checks done within functions called work
     * 
     * this is an overloaded function
     * 
     * @param flag represents the boolean flag indicator that you should use the
     *             extractFromFile for the MPO or MV files
     * 
     * @return fileData, this is a FileData object containing the information from
     *         the extracted file
     * 
     * @throws IOException
     */
    protected FileData extractFromFile(boolean flag) throws IOException {
        String line;
        validFile = validFiles.get(0);

        // Get header
        line = validFile.readLine();

        // Get number of seats
        line = validFile.readLine();
        if (!verifyLineIsDigit(line)) {
            throw new IOException("Not enough digits");
        }

        // Line is confirmed to be a digit, after the check
        int numSeats = Integer.parseInt(line);

        // Get number of candidates
        line = validFile.readLine();
        if (!verifyLineIsDigit(line)) {
            throw new IOException("Not enough digits");
        }
        int numCandidates = Integer.parseInt(line);

        HashMap<String, ArrayList<String>> partyCandidates;
        partyCandidates = formatPartyInformation(partyVotes, candidateVotes, true);

        // Get number of ballots
        line = validFile.readLine();
        if (!verifyLineIsDigit(line)) {
            throw new IOException("Not enough digits");
        }
        int numBallots = Integer.parseInt(line);

        // Deals with multiple files
        for (int i = 0; i < validFiles.size(); i++) {
            validFile = validFiles.get(i);
            if (i != 0) {
                for (int k = 0; k < 5; k++) {
                    if (k == 4) {
                        line = validFile.readLine();
                        if (!verifyLineIsDigit(line)) {
                            throw new IOException("Not enough digits");
                        }
                        numBallots += Integer.parseInt(line);
                    } else {
                        line = validFile.readLine();
                    }
                }
            }
            formatBallotInformation(partyVotes, candidateVotes, partyCandidates, numSeats);
        }

        fileData = new FileData(header, numSeats, numBallots, numCandidates, partyCandidates, partyVotes,
                candidateVotes);
        return fileData;
    }

    /**
     * This will look through the given string, if it finds a character that is not
     * a digit a message will
     * be given to the user and the program will close with a system.exit()
     * 
     * @param line represents a string of the line from the file. This line should
     *             only be digits
     */
    protected boolean verifyLineIsDigit(String line) {
        // If line is empty or null, return false
        if (line == "" || line == null) {
            return false;
        }
        line = line.trim();

        // Goes through every character in line, looks to see if it's a digit
        for (int i = 0; i < line.length(); i++) {
            if (!Character.isDigit(line.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method will read as many lines as there are number of parties in the
     * file, the first word in the line will be used as the key in the HashMap and
     * the rest beyond the first , will be added to the Arraylist of string, which
     * represents the list of candidates for a party
     *
     * This is an overloaded function: CPL and OPL files
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
    protected HashMap<String, ArrayList<String>> formatPartyInformation(int numParties,
            ArrayList<ArrayList<Object>> partyVotes,
            ArrayList<ArrayList<Object>> candidateVotes)
            throws IOException {
        HashMap<String, ArrayList<String>> partyCandidates = new HashMap<>();
        String line;
        String[] splitLine;
        String partyName;
        ArrayList<Object> partyInner;
        ArrayList<Object> candidateInner;
        ArrayList<String> candidates;

        /*
         * This for loop reads a single line from validFile and uses the information
         * in this string to get the matching of a party to its candidates list
         * as well as initialize partyVotes and candidateVotes with the names and
         * the number 0
         */
        for (int i = 0; i < numParties; i++) {
            // reads a file and splits the words into a string[] and also trims the
            // whitespace
            line = validFile.readLine();
            splitLine = line.trim().split(",");
            partyName = splitLine[0];

            // Gets the candidates from splitLine and adds them to the candidate
            // arraylist, also adds them to candidateInner which is used for
            // candidateVotes
            candidates = new ArrayList<>();
            for (int k = 1; k < splitLine.length; k++) {
                candidates.add(splitLine[k]);

                // Initialize candidateVotes with an arrayList of candidateName and 0
                candidateInner = new ArrayList<>();
                candidateInner.add(splitLine[k]);
                candidateInner.add(0);
                candidateVotes.add(candidateInner);
            }

            // Initialize partyVotes with an arraylist of partyName and 0
            partyInner = new ArrayList<>();
            partyInner.add(partyName);
            partyInner.add(0);
            partyVotes.add(partyInner);

            partyCandidates.put(partyName, candidates);
        }

        return partyCandidates;
    }

    /**
     * This method will read one line in the file, it will seperate that one line
     * into a formatted string array, where the first of two indecies is the name of
     * the candidate and the second of the two indicies is the party of the
     * candidate
     * 
     * This is an overloaded function: MPO and MV files
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
     * @param flag           this is a boolean flag retrieved from extractFromFile
     *                       to indicate that you are formatting party information
     *                       from an MPO or an MV file
     * @return HashMap<String, ArrayList < String>> that represents a key value of a
     *         party name
     *         to a list of candidate names
     * @throws IOException if there is an error while reading the validFile
     */
    protected HashMap<String, ArrayList<String>> formatPartyInformation(ArrayList<ArrayList<Object>> partyVotes,
            ArrayList<ArrayList<Object>> candidateVotes, boolean flag) throws IOException {
        String line = validFile.readLine();
        String[] splitLine = line.trim().split(",");
        HashMap<String, ArrayList<String>> partyCandidates = new HashMap<>();

        String temp;

        // Clean the brackets out of the splitString array
        for (int i = 0; i < splitLine.length; i++) {
            temp = splitLine[i].trim();
            if ((i % 2) == 0) {
                splitLine[i] = temp.substring(1);
            } else if ((i % 2) == 1) {
                splitLine[i] = temp.substring(0, temp.length() - 1);
            }
        }

        /*
         * This for loop looks at two indexes of splitLine and adds them to
         * partyCandidates and partyVotes as well as candidateVotes.
         */
        for (int i = 0; i < splitLine.length - 1; i = i + 2) {
            String candidate = splitLine[i];
            String party = splitLine[i + 1];

            // Check if partyCandidates contains party. If not, add it.
            if (!partyCandidates.containsKey(party)) {
                ArrayList<Object> partyInner = new ArrayList<>();
                partyInner.add(party);
                partyInner.add(0);
                partyVotes.add(partyInner);

                ArrayList<Object> candidateInner = new ArrayList<>();
                candidateInner.add(candidate);
                candidateInner.add(0);
                candidateVotes.add(candidateInner);

                ArrayList<String> inner = new ArrayList<>();
                inner.add(candidate);
                partyCandidates.put(party, inner);
            } else {
                ArrayList<Object> candidateInner = new ArrayList<>();
                candidateInner.add(candidate);
                candidateInner.add(0);
                candidateVotes.add(candidateInner);

                ArrayList<String> inner = partyCandidates.get(party);
                inner.add(candidate);
                partyCandidates.put(party, inner);
            }
        }
        return partyCandidates;
    }

    /**
     * This will get the party associated with the candidate name
     * It then gets the votes associated with the candidate name
     * Using this information it places the votes in the location of the party in
     * partyVotes
     * 
     * Used in OPL, MPO and MV Elections
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
     * @param tempCount       this is an ArrayList<Integer> which contains the
     *                        number of votes in the current file that was read
     * @param index           this is the index of the current candidate's votes in
     *                        tempCount
     */
    protected void putVotesInPartyVotes(ArrayList<ArrayList<Object>> partyVotes,
            ArrayList<ArrayList<Object>> candidateVotes, HashMap<String, ArrayList<String>> partyCandidates,
            String candidateName, ArrayList<Integer> tempCount, int index) {
        String returnKey = "";
        // get the party associated with the candidate
        for (String key : partyCandidates.keySet()) {
            ArrayList<String> values = partyCandidates.get(key);
            if (values.contains(candidateName)) {
                returnKey = key;
                break;
            }
        }

        int votes = tempCount.get(index);

        // update the number of votes in partyVotes
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

    /**
     * This is an abstract method that will be initialized in ExtractDataOPL and
     * ExtractDataCPL. When initialized, this method will format the ballots to
     * update the number of votes in partyVotes and in OPL will also update the
     * number of votes in candidateVotes
     *
     * @param partyVotes      is a mapping of multiple party names to their
     *                        corresponding
     *                        number of votes
     * @param candidateVotes  is a mapping of multiple candidate names to their
     *                        corresponing number of votes
     * @param partyCandidates
     * @throws Exception
     */
    abstract protected void formatBallotInformation(ArrayList<ArrayList<Object>> partyVotes,
            ArrayList<ArrayList<Object>> candidateVotes, HashMap<String, ArrayList<String>> partyCandidates, int numSeats)
            throws IOException;

}
