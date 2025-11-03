import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.*;
import java.io.*;
import java.net.*;
import java.time.*;

public class Lab11_Tests {
    /*
        Complete the test case below that checks to see that threads A and B have both contributed 100 entries respectively
        to the shared ArrayList when they have both finished running.
    */
    @Test
    public void test1() {

        new Lab11_Thread("RESET", 0).setData(new ArrayList<>());

        Lab11_Thread threadA = new Lab11_Thread("A1", 100);
        Lab11_Thread threadB = new Lab11_Thread("B1", 100);

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (Exception e){
            e.printStackTrace();
        }

        // verify exactly 100 items from each thread
        ArrayList<String> data = threadA.getData();
        assertEquals(200, data.size());

        int aCount = 0, bCount = 0;
        for (String s : data) {
            if (s.startsWith("A1 ")) {
            aCount++;}
            if (s.startsWith("B1 ")) {
                bCount++;}
        }
        assertEquals(100, aCount);
        assertEquals(100, bCount);
    }

    @Test
    public void test2() {

        new Lab11_Thread("RESET", 0).setData(new ArrayList<>());

        Lab11_Thread threadA = new Lab11_Thread("A2", 500);
        Lab11_Thread threadB = new Lab11_Thread("B2", 500);

        threadA.start();
        threadB.start();
        try {
            Thread.sleep(500); 
        } catch (Exception e){
            e.printStackTrace();
        }

        // after ~500ms (each loop sleeps 50ms), expect at least ~10 total appends
        int sizeAfterHalfSecond = threadA.getData().size();
        assertTrue("Expected at least 10 items after ~500ms", sizeAfterHalfSecond >= 10);

        // join so these long threads don't affect other tests
        try {
            threadA.join();
            threadB.join();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void test3() {

        new Lab11_Thread("RESET", 0).setData(new ArrayList<>());

        Lab11_Thread threadA = new Lab11_Thread("A3", 10);
        Lab11_Thread threadB = new Lab11_Thread("B3", 10);

        threadA.start();
        
        try {
            threadA.join();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        threadB.start();
        try {
            threadB.join();
        } catch (Exception e){
            e.printStackTrace();
        }

        ArrayList<String> data = threadA.getData();
        // total must be 20 (10 from A, 10 from B)
        assertEquals(20, data.size());

        // first 10 must be A's entries, next 10 must be B's entries
        for (int i = 0; i < 10; i++) {
            assertTrue("Index " + i + " should be from A3", data.get(i).startsWith("A3 "));
        }
        for (int i = 10; i < 20; i++) {
            assertTrue("Index " + i + " should be from B3", data.get(i).startsWith("B3 "));
        }
    }
}