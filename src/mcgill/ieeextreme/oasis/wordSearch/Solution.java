package mcgill.ieeextreme.oasis.wordSearch;

import java.util.*;

public class Solution {

    private static final int[] Y_STEPS = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] X_STEPS = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boardHeight = scanner.nextInt(), boardWidth = scanner.nextInt(), numberOfWords = scanner.nextInt();
        char[][] board = new char[boardHeight][boardWidth];
        for (int r = 0; r < boardHeight; r++)
            board[r] = scanner.next().toCharArray();
        for (int wordCount = 0; wordCount < numberOfWords; wordCount++) {
            String word = scanner.next();
            boolean found = false;
            for (int r = 0; r < boardHeight && !found; r++)
                for (int c = 0; c < boardWidth && !found; c++) {
                    int[] tempResult = searchWord(board, word, r, c);
                    if (tempResult[0] >= 0 && tempResult[1] >= 0) {
                        System.out.println(r + " " + c + " " + tempResult[0] + " " + tempResult[1]);
                        found = true;
                    }
                }
            if (!found) System.out.println(-1);
        }
    }

    public static int[] searchWord(char[][] board, String word, int r, int c) {

        // Not found.
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || word.charAt(0) != board[r][c])
            return new int[]{-1, -1};

        // Searching in eight directions.
        for (int i = 0; i < 8; i++) {
            int[] tempResult = gradientSearch(X_STEPS[i], Y_STEPS[i], board, r, c, word);
            if (tempResult[0] >= 0 && tempResult[1] >= 0)
                return tempResult;
        }

        return new int[]{-1, -1};
    }

    public static int[] gradientSearch(int xStep, int yStep, char[][] board, int r, int c, String word) {
        int wordLength = word.length();
        for (int step = 0; step < wordLength; step++) {
            int dx = step * xStep, dy = step * yStep, newR = r + dy, newC = c + dx;
            if (!isInBoard(board, newR, newC) || board[newR][newC] != word.charAt(step))
                return new int[]{-1, -1};

            // Correctly found.
            if (step == wordLength - 1)
                return new int[]{newR, newC};
        }
        return new int[]{-1, -1};
    }

    public static boolean isInBoard(char[][] board, int r, int c) {
        return r >= 0 && r < board.length && c >= 0 && c < board[0].length;
    }

}
