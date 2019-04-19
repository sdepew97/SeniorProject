package com.example.apphomepages.DynamicProgramming.Algorithms;

import com.example.apphomepages.DynamicProgramming.HelperMethods.DynamicProgrammingHelperMethods;
import com.example.apphomepages.General.DataTypes.LevenshteinReturnType;

import java.util.ArrayList;

public class DynamicProgrammingAlgorithms
{
    //Used notes from Professor Kumar's CS325 Computational Linguistic's Class also used https://www.youtube.com/watch?v=Thv3TfsZVpw and https://www.geeksforgeeks.org/edit-distance-dp-5/
    /*
        Computes the Levenshtein distance from target to source. Assume that insertions, deletions cost 1 and (actual) substitutions cost 2.
    */
    public static ArrayList<LevenshteinReturnType> levenshteinDist(String target, String source)
    {
        ArrayList<LevenshteinReturnType> frames = new ArrayList<>();

        int n = target.trim().length();
        int m = source.trim().length();

        int[][] distance = new int[n + 1][m + 1];

        for (int i = 0; i < n + 1; i++)
        {
            for (int j = 0; j < m + 1; j++)
            {
                distance[i][j] = 0;
            }
        }

        //Initial frame
        frames.add(new LevenshteinReturnType(DynamicProgrammingHelperMethods.copy2DArray(distance, n + 1, m + 1), false, -1, -1));

        for (int k = 1; k < n + 1; k++)
        {
            distance[k][0] = distance[k - 1][0] + insertCost(target.charAt(k - 1));
            frames.add(new LevenshteinReturnType(DynamicProgrammingHelperMethods.copy2DArray(distance, n + 1, m + 1), false, k, 0));
        }

        for (int l = 1; l < m + 1; l++)
        {
            distance[0][l] = distance[0][l - 1] + deleteCost(source.charAt(l - 1));
            frames.add(new LevenshteinReturnType(DynamicProgrammingHelperMethods.copy2DArray(distance, n + 1, m + 1), false, 0, l));
        }

        for (int o = 1; o < n + 1; o++)
        {
            for (int p = 1; p < m + 1; p++)
            {
                frames.add(new LevenshteinReturnType(DynamicProgrammingHelperMethods.copy2DArray(distance, n + 1, m + 1), true, o, p));
                distance[o][p] = Math.min(Math.min(distance[o - 1][p] + insertCost(target.charAt(o - 1)), distance[o][p - 1] + deleteCost(source.charAt(p - 1))), distance[o - 1][p - 1] + substCost(source.charAt(p - 1), target.charAt(o - 1)));

                frames.add(new LevenshteinReturnType(DynamicProgrammingHelperMethods.copy2DArray(distance, n + 1, m + 1), false, o, p));
            }
        }

        return frames;
    }

    private static int insertCost(char c)
    {
        return 1;
    }

    private static int deleteCost(char c)
    {
        return 1;
    }

    private static int substCost(char x, char y)
    {
        if (x == y)
        {
            return 0;
        } else
        {
            return 2;
        }
    }
}
