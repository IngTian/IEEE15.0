package mcgill.ieeextreme.oasis.imageSimilarity;

import java.util.*;
import java.io.*;

public class Solution {

    class TestCase {
        int[][] boardA;
        int[][] boardB;

        void setBoard(char[][] input, char boardName) {
            int numberOfRows = input.length, numberOfColumns = input[0].length;
            int[][] result = new int[numberOfRows][numberOfColumns];
            for (int row = 0; row < numberOfRows; row++)
                for (int column = 0; column < numberOfColumns; column++)
                    result[row][column] = input[row][column] == '#' ? 1 : 0;

            if (boardName == 'A')
                this.boardA = result;
            else
                this.boardB = result;
        }
    }

    public static void main(String[] args) {
        // write your code here
    }

    private ArrayList<TestCase> readTestCases() {
        Scanner scanner = new Scanner(System.in);

        int numberOfTestCases = scanner.nextInt();
        ArrayList<int[]> result = new ArrayList<>(numberOfTestCases);

        for (int testCaseIndex = 0; testCaseIndex < numberOfTestCases; testCaseIndex++) {
            int numberOfRows = scanner.nextInt(), numberOfColumns = scanner.nextInt();
            int[][] currentBoard
        }
    }
}
