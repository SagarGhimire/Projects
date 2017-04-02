/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg0.pkg1knapsack.problem;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author s525189
 */
public class Problem
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int W = 500;
        for (int n = 1; n <= 10000; n *= 2)
        {

            if (n != 0)
            {
                greedytime(W, n, 100);
            }
        }
    }

    public static int greedyHeuristicWeight(int W, Item[] wt)
    {//sort by wt // big thita complexity is nlgn
        int totalWeight = 0;
        int totalValue = 0;
        int sumOfWeight = 0;
        sortGreedyWeight(wt); //ig thita complexity is nlgn

        for (int i = 0; i < wt.length; i++)
        {
            if (wt[i].weight < W - sumOfWeight)
            {
                sumOfWeight = sumOfWeight + wt[i].weight;
                totalValue += wt[i].value;
            } else
            {
                break;
            }
        }
        return totalValue;
    }

    public static int greedyHeuristicValue(int W, Item[] val)
    {//sort by wt //big-thita is nlgn
        int totalWeight = 0;
        int totalValue = 0;
        int sumOfWeight = 0;
        sortGreedyValue(val); //nlgn 

        for (int i = 0; i < val.length; i++)
        {
            if (val[i].weight < W - sumOfWeight)
            {
                sumOfWeight = sumOfWeight + val[i].weight;
                totalValue += val[i].value;
            } else
            {
                break;
            }

        }
        return totalValue;
    }

    public static void sortGreedyWeight(Item[] itemArray)
    {
        Arrays.sort(itemArray, new Comparator<Item>()
        {
            @Override
            public int compare(Item i1, Item i2)
            {
                return i1.weight - i2.weight;
            }
        });

    }

    public static void sortGreedyValue(Item[] itemArray)
    {
        Arrays.sort(itemArray, new Comparator<Item>()
        {
            @Override
            public int compare(Item i1, Item i2)
            {
                return i2.value - i1.value;
            }
        });

    }

    public static int dynamic(int W, Item[] item)
    { //compexity is : nW
        int n = item.length;
        int[][] dy = new int[n][W + 1];
        for (int i = 0; i < n; i++)
        {
            dy[i][0] = 0;
        }
        for (int j = 1; j < W + 1; j++)
        {
            if (item[0].weight <= j)
            {
                dy[0][j] = item[0].value;
            }
        }
        for (int i = 1; i < n; i++)
        {
            for (int j = 1; j < W + 1; j++)
            {
                if (item[i].weight < j)
                {
                    dy[i][j] = max(dy[i - 1][j], item[i].value + dy[i][j - item[i].weight]);
                } else
                {
                    dy[i][j] = dy[i - 1][j];
                }
            }

        }
        return dy[n - 1][W];
    }

    public static int max(int a, int b)
    {
        if (a > b)
        {
            return a;
        } else
        {
            return b;
        }
    }

    public static void greedytime(int W, int n, int iters)
    {
        int greedytime = 0;
        int greedytime1 = 0;
        int dpTime = 0;
        float avgQualityValue = 0;
        float avgQualityWeight = 0;
        long total = 0;
        long start, end;

        Random rand = new Random();

        for (int x = 0; x < iters; x++)
        {
            Item[] items = new Item[n];
            for (int i = 0; i < n; i++)
            {
                Item item = new Item();
                item.weight = rand.nextInt(W);
                item.value = rand.nextInt(500);
                items[i] = item;
            }

            start = System.nanoTime();
            int greedyValue = greedyHeuristicValue(W, items);
            end = System.nanoTime();
            greedytime += (end - start) / (float) iters;

            start = System.nanoTime();
            int greedyWeight = greedyHeuristicWeight(W, items);
            end = System.nanoTime();
            greedytime1 += (end - start) / (float) iters;

            start = System.nanoTime();
            int dpQuality = dynamic(W, items);
            end = System.nanoTime();
            dpTime += (end - start) / (float) iters;

            avgQualityValue += ((float) greedyValue / (float) dpQuality) / ((float) iters);
            avgQualityWeight += ((float) greedyWeight / (float) dpQuality) / ((float) iters);

        }

        System.out.println("Greedy Value Time: " + greedytime + " Greedy Weight Time: " + greedytime1 + " Dynamic Time: " + dpTime);
        System.out.println("Average Quality for Greedy Value: " + avgQualityValue);
        System.out.println("Average Quality for Greedy Weight: " + avgQualityWeight);
        System.out.println("Length: " + n + " --------------------------");
    }

}
