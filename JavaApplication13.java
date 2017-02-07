/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication13;

/**
 *
 * @author s525189
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class JavaApplication13 {

 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter the size");
        int size  = input.nextInt();
        
        //int sizes[] = {1, 50, 100, 500, 1000, 5000, 10000};
        System.out.println("\t\tInsertion\tSelectAlgo\t Merge\t\t  BuiltIn");
        //for (int size : sizes) {
            //System.out.println(size + "\t\t" + timeInsertion(size)+"\t\t" +timeSelectAlgo(size)
            //        + "\t\t" + timeMerge(size) + "\t\t" + timeBuiltInSort(size));
            System.out.printf("%d\t\t%.10f    %.10f     %.10f     %.10f\n", size, timeInsertion(size), timeSelectAlgo(size), timeMerge(size), timeBuiltInSort(size));
        
    }

    //insertion sort
    public static ArrayList<Integer> insertionSort(ArrayList<Integer> L) {
        for (int i = 0; i < L.size(); i++) { //iterating the list
            int j = i;
            while (j > 0 && L.get(j - 1) > L.get(j)) { //condition to swap
                swapInds(L, j, j - 1);

                j--; //decrease the index
            }
        }
        return L;
    }

    private static void swapInds(ArrayList<Integer> L, int i, int j) {
        Integer t = L.get(i);
        L.set(i, L.get(j));
        L.set(j, t);
    }

    public static ArrayList<Integer> mergeSort(ArrayList<Integer> L) {
        if (L.size() <= 1) {
            return L;
        }
        ArrayList<Integer> A = mergeSort(new ArrayList(L.subList(0, L.size() / 2))); //assigning the half of the list
        ArrayList<Integer> B = mergeSort(new ArrayList(L.subList(L.size() / 2, L.size()))); //assigning the rest half of the list
        return merge(A, B);//calling the merge function
    }

    private static ArrayList<Integer> merge(ArrayList<Integer> A, ArrayList<Integer> B) {
        int iA = 0;
        int iB = 0;
        ArrayList<Integer> C = new ArrayList<>();

        while (iA < A.size() && iB < B.size()) { //iterating until the size of the list

            if (A.get(iA) < B.get(iB)) { //condition to check the smallest element from the two list
                C.add(A.get(iA)); //adding the smallest element to the new list
                iA++; //increasing the index of the first list A

            } else {
                C.add(B.get(iB)); //adding the smallest element to the new list C
                iB++; //increasing the index of the list B
            }

        }
        while (iA < A.size()) {//add the value until the index is less than the length of array
            C.add(A.get(iA));
            iA++;
        }
        while (iB < B.size()) { //add the value until the index is less than the array
            C.add(B.get(iB));
            iB++;
        }
        return C;
    }

    public static int SelectAlgo(ArrayList<Integer> L, int k) {
        int a = L.size();
        int b = a / 2;
        ArrayList<Integer> Algo = new ArrayList<>();
        ArrayList<Integer> Algo1 = new ArrayList<>();
        ArrayList<Integer> Algo2 = new ArrayList<>();
        int c = L.get(b);
        for (int i = 0; i < L.size(); i++) {
            if (L.get(i) < c) {
                Algo.add(L.get(i));
            } else if (L.get(i) > c) {
                Algo1.add(L.get(i));

            } else if (L.get(i)==c) {
                Algo2.add(L.get(i));
            }
        }
        if (k < Algo.size()) {
            return SelectAlgo(Algo, k);
        } else if (k < (Algo.size() + Algo2.size())) {
            return L.get(b);
        } else {
            return SelectAlgo(Algo1, k - (Algo.size() + Algo2.size()));
        }
    }

    public static int builtInSort(ArrayList<Integer> L,int k) {
        Collections.sort(L);
        return L.get(k);
    }

    public static ArrayList<Integer> randoList(int size) {
        ArrayList<Integer> C = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            C.add((int) (Math.random() * size * 2));
        }
        return C;
    }

    public static double timeInsertion(int size) {
        double avg = 0;
        for (int i = 0; i < 30; i++) {
            ArrayList<Integer> l = randoList(size);
            long start = System.nanoTime();
            insertionSort(l);
            long end = System.nanoTime();
            avg += (end - start) / 1000000000.0;
        }
        return avg;
    }

    public static double timeMerge(int size) {
        double avg = 0;
        for (int i = 0; i < 30; i++) {
            ArrayList<Integer> l = randoList(size);
            long start = System.nanoTime();
            mergeSort(l);
            long end = System.nanoTime();
            avg += (end - start) / 1000000000.0;
        }
        return avg;
    }

    public static double timeSelectAlgo(int size) {
        double avg = 0;
        for (int i = 0; i < 30; i++) {
            ArrayList<Integer> l = randoList(size);
            long start = System.nanoTime();
            SelectAlgo(l, l.size() / 2);
            long end = System.nanoTime();
            avg += (end - start) / 1000000000.0;
        }
        return avg;
    }

    public static double timeBuiltInSort(int size) {
        double avg = 0;
        for (int i = 0; i < 30; i++) {
            ArrayList<Integer> l = randoList(size);
            long start = System.nanoTime();
            builtInSort(l, l.size() / 2);
            long end = System.nanoTime();
            avg += (end - start) / 1000000000.0;
        }
        return avg;
    }
}
