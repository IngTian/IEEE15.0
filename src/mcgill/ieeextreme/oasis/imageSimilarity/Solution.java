package mcgill.ieeextreme.oasis.imageSimilarity;

import java.util.*;
import java.io.*;

public class Solution {

    class TestCase {
        int[][] boardA;
        int[][] boardB;

        void setCase(char[][] input1, char[][] input2) {
            int area1 = input1.length * input1[0].length;
            int area2 = input2.length * input2[0].length;
            if (area1 > area2) {
                this.setBoard(input1, 'A');
                this.setBoard(input2, 'B');
            } else {
                this.setBoard(input2, 'A');
                this.setBoard(input1, 'B');
            }
        }

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
        Solution s = new Solution();
        s.readTestCases();
        System.out.println("FFFFFF");
    }

    public String getStringRepresentationOfABoard(int[][] board) {
        int numberOfColumns = board[0].length;
        StringBuilder builder = new StringBuilder();
        for (int[] row : board)
            for (int c = 0; c < numberOfColumns; c++)
                builder.append(row[c]);
        return builder.toString();
    }

    public int[][] flip(int[][] input, int direction) {
        int numberOfRows = input.length, numberOfColumns = input[0].length;
        int[][] result = new int[numberOfRows][numberOfColumns];
        if (direction == 0)
            // Horizontal Flip
            for (int row = 0; row < numberOfRows; row++)
                for (int col = 0; col < numberOfColumns; col++)
                    result[row][col] = input[row][numberOfColumns - col - 1];
        else {
            // Vertical Flip
            for (int col = 0; col < numberOfColumns; col++)
                for (int row = 0; row < numberOfRows; row++)
                    result[row][col] = input[numberOfRows - row - 1][col];
        }
        return result;
    }

    public int[][] rotate(int[][] input, int direction) {
        int numberOfRows = input.length, numberOfColumns = input[0].length;
        int[][] result;

        if (direction == 0)
            return input.clone();
        else if (direction == 1) {
            // Clockwise 90 degrees.
            result = new int[numberOfColumns][numberOfRows];
            for (int row = 0; row < numberOfColumns; row++)
                for (int col = 0; col < numberOfRows; col++)
                    result[row][col] = input[numberOfRows - col - 1][row];
        } else if (direction == 2) {
            // Clockwise 180 degrees.
            result = new int[numberOfRows][numberOfColumns];
            for (int row = 0; row < numberOfRows; row++)
                for (int col = 0; col < numberOfColumns; col++)
                    result[row][col] = input[numberOfRows - row - 1][numberOfColumns - col - 1];
        } else {
            // Clockwise 270 degrees.
            result = new int[numberOfColumns][numberOfRows];
            for (int row = 0; row < numberOfColumns; row++)
                for (int col = 0; col < numberOfRows; col++)
                    result[row][col] = input[col][numberOfRows - row - 1];
        }

        return result;
    }

    public ArrayList<TestCase> readTestCases() {
        Scanner scanner = new Scanner(System.in);

        int numberOfTestCases = scanner.nextInt();
        ArrayList<TestCase> result = new ArrayList<>();

        for (int testCaseIndex = 0; testCaseIndex < numberOfTestCases; testCaseIndex++) {
            TestCase c = new TestCase();
            char[][] inputOne = null, inputTwo = null;

            // Prepare the first board.
            int numberOfRows = scanner.nextInt(), numberOfColumns = scanner.nextInt();
            inputOne = new char[numberOfRows][numberOfColumns];
            for (int row = 0; row < numberOfRows; row++)
                inputOne[row] = scanner.next().toCharArray();

            // Prepare the second board.
            numberOfRows = scanner.nextInt();
            numberOfColumns = scanner.nextInt();
            inputTwo = new char[numberOfRows][numberOfColumns];
            for (int row = 0; row < numberOfRows; row++)
                inputTwo[row] = scanner.next().toCharArray();

            c.setCase(inputOne, inputTwo);
            result.add(c);
        }

        return result;
    }
}
