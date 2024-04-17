
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
 * This class is used to test the ExtractDataCPL class
 * 
 * @author Derrick Dischinger
 */
public class ExtractDataMPOTest {
    ExtractDataMPO test;
    // ExtractDataCPL test02;
    // ExtractDataCPL test03;
    ArrayList<BufferedReader> validFile;
    HashMap<String, ArrayList<String>> partyCandidates;
    ArrayList<ArrayList<Object>> partyVotes;
    ArrayList<ArrayList<Object>> candidateVotes;
    
    @Before
    public void setUp() throws IOException {
        test = new ExtractDataMPO(validFile, "MPO");
    }

    @Test
    public void testVerifyLineIsDigit() {
        // Test 1.a
        String line = "";
        assertEquals(false, test.verifyLineIsDigit(line));

        // Test 1.b
        line = "1";
        assertEquals(true, test.verifyLineIsDigit(line));

        // Test 1.c
        line = " 1";
        assertEquals(true, test.verifyLineIsDigit(line));

        // Test 1.d
        line = "abc";
        assertEquals(false, test.verifyLineIsDigit(line));

        // Test 1.e
        line = "12b";
        assertEquals(false, test.verifyLineIsDigit(line));
    }

    

}