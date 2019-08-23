/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kevin
 */
public class CustomListTest {

    public CustomListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void listCanBeAdded() {
        CustomList<Integer> list = new CustomList<>();
        assertEquals(0, list.length());
        list.add(1);
        assertEquals(1, list.length());
    }

    @Test
    public void listCanBeAccessed() {
        CustomList<Integer> list = new CustomList<>();
        list.add(1001);
        int fromList = list.get(0);
        assertEquals(1001, fromList);
    }

    @Test
    public void listWorksWithStrings() {
        CustomList<String> list = new CustomList<>();
        list.add("test");
        String fromList = list.get(0);
        assertEquals("test", fromList);
    }

    @Test
    public void listGrowsDynamically() {
        CustomList<Integer> list = new CustomList<>();
        assertEquals(20, list.getAbsoluteSize());
        for (int i = 0; i < 30; i++) {
            list.add(i);
        }
        assertEquals(40, list.getAbsoluteSize());
        
        for (int i = 0; i < 30; i++) {
            int temp = list.get(i);
            assertEquals(i, temp);
        }
    }
    @Test
    public void removingLast() {
        CustomList<Integer> list = new CustomList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        int last = list.get(list.length() - 1);
        assertEquals(40, last);
        
        list.removeLast();
        
        last = list.get(list.length() - 1);
        assertEquals(30, last);
    }
}
