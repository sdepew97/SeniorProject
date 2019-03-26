package com.example.apphomepages.DynamicProgramming.Algorithms;

import com.example.apphomepages.DynamicProgramming.HelperMethods.DynamicProgrammingHelperMethods;
import com.example.apphomepages.General.DataTypes.NQueensReturnType;

import java.util.ArrayList;

public class DynamicProgrammingAlgorithms
{
    //Used https://en.wikipedia.org/wiki/Eight_queens_puzzle, https://haseebq.com/n-queens-visualizer/, and https://www.geeksforgeeks.org/java-program-for-n-queen-problem-backtracking-3/ for creating this visualization
    public static ArrayList<NQueensReturnType> solveNQ(boolean[][] board)
    {
        //The place where all frames can be stored
        ArrayList<NQueensReturnType> accumulator = new ArrayList<>();

        boolean valid = solveNQUtil(accumulator, board, 0, board.length);

        if (!valid)
        {
            return null;
        } else
        {
            return accumulator;
        }
    }

    /* A utility function to check if a queen can
       be placed on board[row][col]. Note that this
       function is called when "col" queens are already
       placed in columns from 0 to col -1. So we need
       to check only left side for attacking queens */
    private static boolean isSafe(ArrayList<NQueensReturnType> accumulator, boolean board[][], int row, int col, int N)
    {
        int i, j;

        /* Check this row on left side */
        for (i = 0; i < col; i++)
        {
            if (board[row][i])
            {
                //accumulator.add(new NQueensReturnType(DynamicProgrammingHelperMethods.copy2DArray(board, N), true, row, i));
                return false;
            }
        }

        /* Check upper diagonal on left side */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
        {
            if (board[i][j])
            {
                //accumulator.add(new NQueensReturnType(DynamicProgrammingHelperMethods.copy2DArray(board, N), true, i, j));
                return false;
            }
        }

        /* Check lower diagonal on left side */
        for (i = row, j = col; j >= 0 && i < N; i++, j--)
        {
            if (board[i][j])
            {
                //accumulator.add(new NQueensReturnType(DynamicProgrammingHelperMethods.copy2DArray(board, N), true, i, j));
                return false;
            }
        }

        return true;
    }

    /* A recursive utility function to solve N
       Queen problem */
    private static boolean solveNQUtil(ArrayList<NQueensReturnType> accumulator, boolean board[][], int col, int N)
    {
        /* base case: If all queens are placed
           then return true */
        if (col >= N)
        {
            return true;
        }

        /* Consider this column and try placing
           this queen in all rows one by one */
        for (int i = 0; i < N; i++)
        {
            /* Check if the queen can be placed on
               board[i][col] */
            accumulator.add(new NQueensReturnType(DynamicProgrammingHelperMethods.copy2DArray(board, N), true, i, col));
            if (isSafe(accumulator, board, i, col, N))
            {
                /* Place this queen in board[i][col] */
                board[i][col] = true;

                accumulator.add(new NQueensReturnType(DynamicProgrammingHelperMethods.copy2DArray(board, N), false, -1, -1));

                /* recur to place rest of the queens */
                if (solveNQUtil(accumulator, board, col + 1, N))
                {
                    return true;
                }

                /* If placing queen in board[i][col]
                   doesn't lead to a solution then
                   remove queen from board[i][col] */
                board[i][col] = false; // BACKTRACK

                accumulator.add(new NQueensReturnType(DynamicProgrammingHelperMethods.copy2DArray(board, N), false, -1, -1));
            }
        }

        /* If the queen can not be placed in any row in
           this column col, then return false */
        return false;
    }

}
