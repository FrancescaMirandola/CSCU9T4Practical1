/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stir.cscu9t4practical1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author saemundur
 */
public class TrainingRecordTest {
    public TrainingRecordTest() {
    }

//    @BeforeAll
//    public void setUpClass() {
//    }
//
//    @AfterAll
//    public void tearDownClass() {
//    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addEntry method, of class TrainingRecord.
     * You might want to extend this test when you implement the other
     * Entry types
     */
    @Test
    public void testAddEntry() {
        System.out.println("addEntry");
        Entry a = new Entry("Alice", 1, 2, 2003, 0, 16, 7, 3);
        Entry b = new SprintEntry("", 5, 5,2021,0,30,50,0.3f,5,3 );
        Entry c = new SwimEntry("Paola", 30, 10,2020, 3,0,0,5,"pool");
        Entry d = new CycleEntry("Tom", 23,3,2020,2,30,30,35,"asphalt", "moderate");
        TrainingRecord instance = new TrainingRecord();
        instance.addEntry(a);
        instance.addEntry(b);
        instance.addEntry(c);
        instance.addEntry(d);
        assertEquals(4,instance.getNumberOfEntries());
        //Entry e = new SwimEntry("Alice", 1, 2, 2003, 6, 6, 7, 3,"pool");
        //instance.addEntry(e);
        //assertEquals(4,instance.getNumberOfEntries());


    }

    /**
     * Test of addEntry with a repeat
     * Adding another entry for the same person on the same day should fail
     */
    @Test
    public void testAddEntryUnique() {
        System.out.println("addEntry -- fail");
        Entry a = new Entry("Alice", 1, 2, 2003, 0, 16, 7, 3);
        Entry b = new Entry("Alice", 1, 2, 2003, 0, 16, 7, 3);
        TrainingRecord instance = new TrainingRecord();
        instance.addEntry(a);
        instance.addEntry(b);
        assertEquals(1, instance.getNumberOfEntries());
        // You might also consider using assertThrows() and let
        // TrainingRecord instance take care of when you're trying to add
        // a none-unique entry
    }

    /**
     * Test of lookupEntry method, of class TrainingRecord.
     */
    @Test
    public void testLookupEntry() {
        System.out.println("lookupEntry");
        TrainingRecord instance = new TrainingRecord();
        String expResult = "No entries found";
       // Entry a = new Entry("Alice", 1, 2, 2003, 0, 16, 7, 3);
       // Entry b = new Entry("Bob", 1, 2, 2003, 0, 14, 15, 3);
        //Entry c1 = new Entry("Claire", 7, 3, 2010, 0, 26, 20, 7);
        //Entry c2 = new Entry("Claire", 11, 3, 2010, 0, 24, 55, 7);
        Entry a = new Entry("Alice", 1, 2, 2003, 0, 16, 7, 3);
        Entry b = new SprintEntry("Emma", 23, 3,2020,0,30,50,0.3f,5,3 );
        Entry c1 = new SwimEntry("Paola", 30, 10,2020, 3,0,0,5,"pool");
        Entry c2 = new CycleEntry("Ector", 23,3,2020,2,30,30,35,"asphalt","moderate");
        instance.addEntry(a);
        instance.addEntry(b);
        instance.addEntry(c1);
        instance.addEntry(c2);
//        int d = 7;
//        int m = 3;
//        int y = 2010;
        int d = 23;
        int m = 3;
        int y = 2020;

        String result = instance.lookupEntry(d, m, y);
        assertNotEquals(expResult, result, "expecting to find the entry");
        result = instance.lookupEntry(1, 2, 1999);
        assertEquals(expResult, result, "expecting to not find the entry");
    }

    /**
     * Test of getNumberOfEntries, of class TrainingRecord
     */
    @Test
    public void testGetNumberOfEntries() {
        System.out.println("GetNumberOfEntries");
        TrainingRecord instance = new TrainingRecord();
        Entry a = new Entry("Alice", 1, 2, 2003, 0, 16, 7, 3);
        Entry b = new Entry("Bob", 1, 2, 2003, 0, 14, 15, 3);
        Entry c1 = new Entry("Claire", 7, 3, 2010, 0, 26, 20, 7);
        Entry c2 = new Entry("Claire", 11, 3, 2010, 0, 24, 55, 7);
        assertEquals(0, instance.getNumberOfEntries());
        instance.addEntry(a);
        assertEquals(1, instance.getNumberOfEntries());
        instance.addEntry(b);
        assertEquals(2, instance.getNumberOfEntries());
        instance.addEntry(c1);
        assertEquals(3,instance.getNumberOfEntries());
        instance.addEntry(c2);
        assertEquals(4, instance.getNumberOfEntries());
        Entry d = new Entry("Alice", 1, 2, 2003, 0, 16, 7, 3);
        Entry e = new SprintEntry("Emma", 5, 5,2021,0,30,50,0.3f,5,3 );
        Entry f = new SwimEntry("Paola", 30, 10,2020, 3,0,0,5,"pool");
        Entry g = new CycleEntry("Ector", 23,3,2020,2,30,30,35,"asphalt","slow");
        instance.addEntry(d);
        instance.addEntry(e);
        instance.addEntry(f);
        instance.addEntry(g);
        assertEquals(8, instance.getNumberOfEntries());
    }

    /**
     * Test of yet to be implemented lookupEntries, of class TrainingRecord
     * Implement the method and then remove the "fail" line below and
     * un-comment call to the method and the assertion line
     */
    @Test
    public void testFindAllByDate() {
        System.out.println("FindAllByDate");
        String expectResultsNone = "Sorry couldn't find anything for this date";
        String expectResults = "Alice ran 3.0 km in 0:16:7 on 1/2/2003\n" +
                                "Bob ran 3.0 km in 0:14:15 on 1/2/2003\n";
        TrainingRecord instance = new TrainingRecord();
        Entry a = new Entry("Alice", 1, 2, 2003, 0, 16, 7, 3);
        Entry b = new Entry("Bob", 1, 2, 2003, 0, 14, 15, 3);
        instance.addEntry(a);
        instance.addEntry(b);
        int d = 1;
        int m = 2;
        int y = 2003;
        //un-comment the lines below when you've implemented the method
       String resultSuccess = instance.findAllByDate(d,m,y);
       String resultNone = instance.findAllByDate(d,m,1999);
       //assertEquals( expectResultsNone, resultNone);
       assertEquals(expectResults,resultSuccess);
    }
    @Test
    public  void testDeleteEntry(){
        System.out.println("DeleteEntry");
        String expectResultsNone = "Sorry couldn't delete record.";
        TrainingRecord instance = new TrainingRecord();
        Entry a = new Entry("Alice", 1, 2, 2003, 0, 16, 7, 3);
        Entry b = new Entry("Bob", 1, 2, 2003, 0, 14, 15, 3);
        Entry c = new Entry("Robert", 5, 2, 2021, 0, 16, 7, 3);
        Entry d = new SprintEntry("Emma", 5, 5,2021,0,30,50,0.3f,5,3 );
        Entry e = new SwimEntry("Paola", 30, 10,2020, 3,0,0,5,"pool");
        instance.addEntry(a);
        instance.addEntry(b);
        instance.addEntry(c);
        instance.addEntry(d);
        instance.addEntry(e);
        instance.deleteEntry("Emma",5,5,2021);
        assertEquals(4,instance.getNumberOfEntries());
        instance.deleteEntry("Bob",1,2,2003);
        assertEquals(3,instance.getNumberOfEntries());
        instance.deleteEntry("Alice",1,2,2003);
        instance.deleteEntry("Paola",30,10,2020);
        assertEquals(1,instance.getNumberOfEntries());

    }
}
