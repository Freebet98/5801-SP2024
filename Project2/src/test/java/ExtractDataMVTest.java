import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
    * This class is used to test the ExtractDataMV class
    * 
    * @author Rock Zgutowicz
    * @Preconditions:
    *                 The ExtractDataMV class must be created
    *                 The abstract ExtractData class be created
    *                 The FileData class must be created
    *                 All test files must be created and properly formatted
*/
public class ExtractDataMVTest {
    ExtractDataMV test01;
    ArrayList<BufferedReader> validFile;
    HashMap<String, ArrayList<String>> partyCandidates;
    ArrayList<ArrayList<Object>> partyVotes;
    ArrayList<ArrayList<Object>> candidateVotes;

    @Before
    public void setUp() throws IOException {
        test01 = new ExtractDataMV(validFile, "MV");
    }

    @Test
    public void testVerifyLineIsDigit() {
        // Test 1.a
        String line = "";
        assertEquals(false, test01.verifyLineIsDigit(line));

        // Test 1.b
        line = "1";
        assertEquals(true, test01.verifyLineIsDigit(line));

        // Test 1.c
        line = " 1";
        assertEquals(true, test01.verifyLineIsDigit(line));

        // Test 1.d
        line = "abc";
        assertEquals(false, test01.verifyLineIsDigit(line));

        // Test 1.e
        line = "12b";
        assertEquals(false, test01.verifyLineIsDigit(line));
    }
}
