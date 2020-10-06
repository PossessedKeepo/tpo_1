package edu.tpo.l1.p2;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class HashTableTest {

    protected HashTable<Integer> table;
    protected static HashMap<String, String> testLog;

    // read 'HashTableTestLog.txt' in order to get log for tests below
    @BeforeClass
    public static void beforeClass(){
        testLog = new HashMap<>();

        ClassLoader classLoader = HashTableTest.class.getClassLoader();
        try(InputStream inputStream = classLoader.getResourceAsStream("HashTableTestLog.txt");
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader)) {

            StringBuilder testLogData = new StringBuilder();
            String testName = null;

            String currLine;
            while ( (currLine = reader.readLine()) != null ) {
                if (currLine.contains("TEST")) {
                    if (testName != null) {
                        testLog.put(testName, testLogData.toString());
                        testLogData = new StringBuilder();
                    }
                    testName = (currLine.split(" "))[1];
                } else {
                    testLogData.append(currLine).append("\n");
                }
            }
            testLog.put(testName, testLogData.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void beforeAllTests() {
        table = new HashTable<>();
    }

    @Test
    public void ContainsLog_12NotExists_ShouldProduceCorrectLog(){
        try {
            table.contains(12);
        } catch (NoSuchElementException e) {
            String actualLog = table.getLog();
            assertEquals(testLog.get("contains_12"), actualLog);
        }
    }

    @Test
    public void ContainsLog_44Exists_ShouldProduceCorrectLog(){
        table.add(44);

        table.contains(44);
        String actualLog = table.getLog();

        assertEquals(testLog.get("contains_44"), actualLog);
    }

    @Test
    public void Add_1IsAdded_ShouldProduceCorrectLog() {
        table.add(1);

        String actualLog = table.getLog();

        assertEquals(testLog.get("add_1"), actualLog);
    }

    @Test
    public void Add_SeveralElements_ShouldProduceCorrectLog() {
        table.add(1);
        table.add(2);
        table.add(17);

        String actualLog = table.getLog();

        assertEquals(testLog.get("add_1_2_17"), actualLog);
    }

    @Test
    public void Add_MultipleElements_ShouldProduceCorrectLog() {
        for (int i = 0; i < 37; i++) {
            table.add(i);
        }

        String actualLog = table.getLog();

        assertEquals(testLog.get("add_multiple"), actualLog);
    }

    @Test
    public void Remove_RemoveOneElement_ShouldProduceCorrectLog() {
        table.add(3);

        table.remove(3);
        String actualLog = table.getLog();

        assertEquals(testLog.get("remove_3"), actualLog);
    }

    @Test
    public void Remove_RemoveOneElementFromChain_ShouldProduceCorrectLog() {
        table.add(0);
        table.add(16);

        table.remove(16);
        String actualLog = table.getLog();

        assertEquals(testLog.get("remove_0_16"), actualLog);
    }





    @Test(timeout = 1000, expected = NoSuchElementException.class)
    public void Contains_13NotExists_ShouldThrowNoSuchElementException() {
        table.contains(13);
    }



    @Test
    public void Add_1IsAddedSeveralTimes_ShouldCreateOneEntry() {
        table.add(1);
        table.add(1);
        table.add(1);

        assertEquals(1, table.getActualCapacity());
    }

    @Test
    public void Add_AddTwoValuesInOneBucket_ShouldCreateTwoEntries() {
        table.add(0);
        table.add(16);

        assertEquals(2, table.getActualCapacity());
    }

    @Test(timeout = 1000)
    public void Add_More16ElementsAdded_InnerArrayWasExtended() {
        for (int i = 0; i < 33; i++) {
            table.add(i);
        }

        int capacity = table.getCapacity();

        assertEquals(64, capacity);
    }

    @Test(timeout = 1000, expected = NoSuchElementException.class)
    public void Remove_12Removed_ShouldThrowNoSuchElementException() {
        table.add(12);

        table.remove(12);

        table.contains(12);
    }

}
