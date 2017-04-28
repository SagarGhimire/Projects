/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg0.pkg1knapsack.problem;

import java.util.Arrays;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author s525189
 */
public class Problem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int W = 50;
        int n = 1000000;

        Random a = new Random();
        Item[] s = new Item[n];
        for (int i = 0; i < s.length; i++) {
            Item e = new Item();
            e.weight = a.nextInt(50) + 1;

            e.value = a.nextInt(50);
            s[i] = e;
        }
        int g = greedyHeuristicWeight(W, s);
        int q = greedyHeuristicValue(W, s);

        int w = dynamic(W, s);

        //Quality for the solution. i.e. greedy/dynamic. Run this function through the loop too.
        float u = (float) g / w;
        System.out.println("The ratio of greedy to dynamic is :" + u);

        float i = (float) q / w;
        System.out.println("The ratio of greedy value to dynamic is: " + i);

        ///Pass it on the loop.
        greedytime(W, n, 500);
        System.out.println("Optimal solution" + "\n GreedyWeight" + g + "\nGreedyValue " + q + "\n Dyanmic " + w);

    }

    public static int greedyHeuristicWeight(int W, Item[] wt) {//sort by wt // big thita complexity is nlgn
        int totalWeight = 0;
        int totalValue = 0;
        int sumOfWeight = 0;
        sortGreedyWeight(wt); //ig thita complexity is nlgn

        for (int i = 0; i < wt.length; i++) {
            if (wt[i].weight < W - sumOfWeight) {
                sumOfWeight = sumOfWeight + wt[i].weight;
                totalValue += wt[i].value;
            } else {
                break;
            }
        }
        return totalValue;
    }

    public static int greedyHeuristicValue(int W, Item[] val) {//sort by wt //big-thita is nlgn
        int totalWeight = 0;
        int totalValue = 0;
        int sumOfWeight = 0;
        sortGreedyValue(val); //nlgn 

        for (int i = 0; i < val.length; i++) {
            if (val[i].weight < W - sumOfWeight) {
                sumOfWeight = sumOfWeight + val[i].weight;
                totalValue += val[i].value;
            } else {
                break;
            }

        }
        return totalValue;
    }

    public static void sortGreedyWeight(Item[] itemArray) {
        Arrays.sort(itemArray, new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                return i1.weight - i2.weight;
            }
        });

    }

    public static void sortGreedyValue(Item[] itemArray) {
        Arrays.sort(itemArray, new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                return i2.value - i1.value;
            }
        });

    }

    public static int dynamic(int W, Item[] item) { //compexity is : nW
        int n = item.length;
        int[][] dy = new int[n][W + 1];
        for (int i = 0; i < n; i++) {
            dy[i][0] = 0;
        }
        for (int j = 1; j < W + 1; j++) {
            if (item[0].weight <= j) {
                dy[0][j] = item[0].value;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < W + 1; j++) {
                if (item[i].weight < j) {
                    dy[i][j] = max(dy[i - 1][j], item[i].value + dy[i][j - item[i].weight]);
                } else {
                    dy[i][j] = dy[i - 1][j];
                }
            }

        }
        return dy[n - 1][W];
    }


    public static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public static void greedytime(int W, int n, int iters) {
        int greedytime = 0;
        int greedytime1 = 0;
        int dpTime = 0;
        long total = 0;
        long start, end;

        Random rand = new Random();
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            Item item = new Item();
            item.weight = rand.nextInt(W);
            item.value = rand.nextInt(500);
            items[i] = item;
        }
        start = System.nanoTime();
        int greedy = greedyHeuristicValue(W, items);
        end = System.nanoTime();
        greedytime += (end - start) / (float) iters;
        System.out.println("The time for the greedy by value is " + greedytime);

        start = System.nanoTime();
        int greedyweight = greedyHeuristicWeight(W, items);

        end = System.nanoTime();
        greedytime1 += (end - start) / (float) iters;
        System.out.println("The time for the greedy by weight is " + greedytime1);

        start = System.nanoTime();
        int dptime = dynamic(W, items);
        end = System.nanoTime();
        dpTime += (end - start) / (float) iters;
        System.out.println("The time for the dynamic programming is " + dpTime);

        

    }

}
