/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication13;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 *
 * @author s525189
 */
public class JavaApplication13Test {
    
    public JavaApplication13Test() {
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

    /**
     * Test of main method, of class JavaApplication13.
     */
    
    static boolean isSorted(ArrayList<Integer>L)
    {
        for (int i=0; i<L.size()-1; i++)
        {
            if (L.get(i) > L.get(i+1))
                return false;
        }
        return true;
    }
    
    @Test
    public void testInsertionSort()
    {
        System.out.println("insertionSort");
        for (int i=0; i<100; i++)
            assert(isSorted(JavaApplication13.insertionSort(JavaApplication13.randoList(i))));
    }
    
    @Test
    public void testMergeSort()
    {
        System.out.println("mergeSort");
        for (int i=0; i<100; i++)
            assert(isSorted(JavaApplication13.mergeSort(JavaApplication13.randoList(i))));
    }
    
    @Test
    public void testSelectAlgo(){
        System.out.println("SelectAlgo");
        ArrayList<Integer> a=  new ArrayList<Integer>();
        a.add(175);
        a.add(120);
        a.add(200);
        a.add(400);
        a.add(43);
        a.add(759);
        assertEquals(43, JavaApplication13.SelectAlgo(a, 0));
    }
    @Test
    public void testbuiltInSort(){
          System.out.println("builtinSort");
          ArrayList<Integer> a=  new ArrayList<Integer>();
        a.add(17);
        a.add(40);
        a.add(23);
        a.add(14);
        a.add(75);
        assertEquals(14, JavaApplication13.builtInSort(a, 0));
    }
       
    }




