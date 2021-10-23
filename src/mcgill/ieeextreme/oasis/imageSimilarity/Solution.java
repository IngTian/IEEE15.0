package mcgill.ieeextreme.oasis.imageSimilarity;

//<<<<<<< Updated upstream
//=======
////import com.sun.java.swing.plaf.windows.WindowsTabbedPaneUI;
//
//>>>>>>> Stashed changes
import java.util.*;
import java.io.*;

public class Solution {

    public static final int[] POSSIBLE_ROTATES = {0, 1, 2, 3};
    public static final int[] POSSIBLE_FLIPS = {0, 1};

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

    static class PossibleStateContainer {
        ArrayList<int[][]> p, q;

        public PossibleStateContainer() {
            this.p = new ArrayList<>();
            this.q = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        // write your code here
        Solution s = new Solution();
<<<<<<< HEAD
        ArrayList<TestCase> testCases = s.readTestCases();

        for(TestCase testCase : testCases){
            PossibleStateContainer container = new PossibleStateContainer();
            HashSet<String> containedNodes = new HashSet<>();
            containedNodes.add(s.getStringRepresentationOfABoard(testCase.boardA));
            s.searchPossibleStates(testCase.boardA, containedNodes, container);
//            container.p -> list of images
//            container.q -> list of images
//            testCase.boardB
            ArrayList<int[][]> images = new ArrayList<>();
            boolean a = images.addAll(container.p) && images.addAll(container.q);
            int max_ = 0;
            System.out.println("image_size = "+ images.size());
            for(int i = 0; i <images.size(); i++ ){
//                System.out.println(Arrays.deepToString(images.get(i)));
//                System.out.println("________________________");
//                System.out.println(Arrays.deepToString(testCase.boardB));
//                System.out.println("___________________________");
                int m1 = images.get(i).length;
                int n1 = images.get(i)[0].length;
                int m2 = testCase.boardB
                        .length;
                int n2 = testCase.boardB[0].length;
//                max = Math.max()
                int cur = s.process(images.get(i), m1, n1, testCase.boardB, m2, n2);
                max_ = Math.max(max_, cur);
            }
            System.out.println(max_);
        }

//        int[][] fakeInput = {
//                {0, 1, 1},
//                {1, 0, 1},
//                {0, 0, 0},
//                {1, 1, 1}
//        };
//        PossibleStateContainer container = new PossibleStateContainer();
//        HashSet<String> containedNodes = new HashSet<>();
//        containedNodes.add(s.getStringRepresentationOfABoard(fakeInput));
//        s.searchPossibleStates(fakeInput, containedNodes, container);
//
//        System.out.println("FFFFFF");



    }

    /**
     * Encode the board into a String.
     * @param board The board.
     * @return A String.
     */
    public int calculate_overlap(int[][] mat1, int left, int right, int up, int down, int[][] mat2) {
        int count = 0;
        for (int i = up; i <= down; i++) {
            for (int j = left; j <= right; j++) {
                if (mat1[i-up][j-left] == mat2[i][j] && mat1[i-up][j-left] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
    //    public int getNewline(int[][] mat1,int [][]  mat2, int new_index, int mat1wid){
//
//        return 1;
//    }
    public int[][] increase_dim(int m1, int n1, int m2, int n2, int[][]mat2){
        int m = m2+m1*2;

        int n = n2 + n1*2;
//        System.out.println(m);
//        System.out.println(n);
        int[][] res = new int[m][n];
        for (int i = m1; i < m1+m2; i++){
            for (int j = n1; j < n1+n2; j++){
                res[i][j] = mat2[i-m1][j-n1];
            }
        }
//        System.out.println(Arrays.deepToString(res));
        return res;
    }

    public int process(int[][]mat1, int m1, int n1, int [][] mat2, int m2, int n2){
        int[][] new_mat2 = increase_dim(m1, n1, m2, n2, mat2);

//        System.out.println("case 4");
        return calculate_max(mat1, m1, n1, new_mat2, m2 + m1*2, n2 + n1*2);
    }


//        if (m1 >= m2 && n1 >= n2){
////            System.out.println("case 1");
//            return calculate_max(mat2, m2, n2, mat1, m1, n1);
//        }
//        else if (m1 < m2 && n1 > n2){
////            System.out.println("case 2");
//            int[][] new_mat2 =  increase_dim(m1, n1, m2, n2, mat2);
//            int res1 =  calculate_max(mat1, m1, n1, new_mat2, m2, n1);
//
//            int[][] new_mat1 = increase_dim(m2, n2, m1, n1, mat1);
//            int res2 = calculate_max(mat2, m2, n2, new_mat1, m2, n1);
//            return Math.max(res2, res1);
////            return res1;
//        }
//        else if (m1 > m2 && n1 < n2){
////            System.out.println("case 3");
//            int[][] new_mat2 =  increase_dim(m1, n1, m2, n2, mat2);
//            int res1 =  calculate_max(mat1, m1, n1, new_mat2, m1, n2);
//
//            int[][] new_mat1 = increase_dim(m2, n2, m1, n1, mat1);
//            int res2 = calculate_max(mat2, m2, n2, new_mat1, m1, n2);
//            return Math.max(res2, res1);
////            return res1;
//        }
////        System.out.println("case 4");
//        return calculate_max(mat1, m1, n1, mat2, m2, n2);
//    }
    public int calculate_max(int[][] mat1 ,int m1, int n1, int [][] mat2, int m2, int n2){

        int left = 0;
        int right = left+n1-1;
        int up = 0;
        int down  = up + m1-1;
        // window = up, down, left, right
        int cur_max = 0;
//        int cur_res = calculate_overlap(mat1,left, right,up, down,mat2 );
        int cur_res = 0;
//        System.out.println(cur_res);
        cur_max = cur_res;
//        left++;
//        right++;
        while(down < m2){
            while(right < n2){
//
                cur_res = calculate_overlap(mat1,left, right,up, down,mat2 );
                cur_max = Math.max(cur_max, cur_res);
                left++;
                right++;
            }

            up++;
            down++;
            left = 0;
            right = left+n1-1;
        }


        return cur_max;
    }
//    public int calculate_overlap(int[][] mat1, int left, int right, int up, int down, int[][] mat2) {
//        int count = 0;
//        for (int i = up; i <= down; i++) {
//            for (int j = left; j <= right; j++) {
//                if (mat1[i][j] == mat2[i][j] && mat1[i][j] == 1) {
//                    count++;
//                }
//            }
//        }
//        return count;
//    }
//    //    public int getNewline(int[][] mat1,int [][]  mat2, int new_index, int mat1wid){
////
////        return 1;
////    }
//    public int calculate_max(int[][] mat1 ,int m1, int n1, int [][]  mat2, int m2, int n2){
//        // mat1 a, b,  mat b, c, d
//        //    n
////        [[1,2, 3, 4],
////        [5, 6, 7, 8],  m
////        [9, 10, 11, 12]]
//
//        //       [[1,2, 3, 4, 5],
////        [5, 6, 7, 8, 9],
////        [9, 10, 11, 12, 13],
////        [1,53, ,53 ,25, 35]]
//        int small_m = Math.min(m1, m2);
//        int small_n = Math.min(n1, n2);
//        int left = 0;
//        int right = left+small_n-1;
//        int up = 0;
//        int down  = up + small_m-1;
//        // window = up, down, left, right
//        int cur_max = 0;
//        int cur_res = calculate_overlap(mat1,left, right,up, down,mat2 );
//        cur_max = cur_res;
//        left++;
//        right++;
////        ArrayList
//        while (down < m2) {
//            while (right < n2) {
////                int cur_left = left;
//                for(int i = up; i <= down; i++){
//                    if (mat1[i][right] == mat2[i][right] && mat1[i][right] == 1){
//                        cur_res++;
//                    }
//                    if (mat1[i][left] ==mat2[i][left] && mat1[i][left] == 1){
//                        cur_res--;
//                    }
//                }
//                cur_max = Math.max(cur_max, cur_res);
////              int cur_res = calculate_overlap(mat1, left, left+mat1len-1,mat2 );
//
//                left++;
//                right++;
//            }
//            up++;
//            down ++;
//            left = 0;
//            right = left+small_n-1;
//            cur_res = calculate_overlap(mat1, left, right,up, down,mat2 );
//            cur_max = Math.max(cur_max, cur_res);
//            left ++;
//            right++;
//
//        }
//        return cur_max;
//    }

    public String getStringRepresentationOfABoard(int[][] board) {
        int numberOfColumns = board[0].length;
        StringBuilder builder = new StringBuilder();
        for (int[] row : board)
            for (int c = 0; c < numberOfColumns; c++)
                builder.append(row[c]);
        return builder.toString();
    }

    /**
     * Flip the board.
     * direction == 0 => Do nothing
     * direction == 1 => Clockwise 90
     * direction == 2 => Clockwise 180
     * direction == 3 => Clockwise 270
     * @param input The input board.
     * @param direction The direction to flip.
     * @return The flipped board.
     */
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

    /**
     * Flip the board.
     * direction == 0 => Horizontal Flip
     * direction == 1 => Vertical Flip
     * @param input The input board.
     * @param direction The direction to flip.
     * @return The rotated board.
     */
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
                    result[row][col] = input[col][numberOfColumns - row - 1];
        }

        return result;
    }

    /**
     * Search for all possible states,
     * given rotation and flips.
     * @param input The input board.
     * @param visitedStates The visited states.
     * @param results The results.
     */
    public void searchPossibleStates(int[][] input, Set<String> visitedStates, PossibleStateContainer results) {
        int numberOfRows = input.length, numberOfColumns = input[0].length;
        if (numberOfRows <= numberOfColumns) results.p.add(input);
        else results.q.add(input);

        // Search for all possible rotations.
        for (int rotate : POSSIBLE_ROTATES) {
            int[][] newState = this.rotate(input, rotate);
            String rep = this.getStringRepresentationOfABoard(newState);
            if (!visitedStates.contains(rep)) {
                visitedStates.add(rep);
                searchPossibleStates(newState, visitedStates, results);
            }
        }

        // Search for all possible rotations.
        for (int flip: POSSIBLE_FLIPS) {
            int[][] newState = this.flip(input, flip);
            String rep = this.getStringRepresentationOfABoard(newState);
            if (!visitedStates.contains(rep)) {
                visitedStates.add(rep);
                searchPossibleStates(newState, visitedStates, results);
            }
        }
    }

    /**
=======
        int[][] fakeInput = {
                {0, 1, 1},
                {1, 0, 1},
                {0, 0, 0},
                {1, 1, 1}
        };
        PossibleStateContainer container = new PossibleStateContainer();
        HashSet<String> containedNodes = new HashSet<>();
        containedNodes.add(s.getStringRepresentationOfABoard(fakeInput));
        s.searchPossibleStates(fakeInput, containedNodes, container);
        System.out.println("FFFFFF");
    }

    /**
     * Encode the board into a String.
     * @param board The board.
     * @return A String.
     */
    public String getStringRepresentationOfABoard(int[][] board) {
        int numberOfColumns = board[0].length;
        StringBuilder builder = new StringBuilder();
        for (int[] row : board)
            for (int c = 0; c < numberOfColumns; c++)
                builder.append(row[c]);
        return builder.toString();
    }

    /**
     * Flip the board.
     * direction == 0 => Do nothing
     * direction == 1 => Clockwise 90
     * direction == 2 => Clockwise 180
     * direction == 3 => Clockwise 270
     * @param input The input board.
     * @param direction The direction to flip.
     * @return The flipped board.
     */
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

    /**
     * Flip the board.
     * direction == 0 => Horizontal Flip
     * direction == 1 => Vertical Flip
     * @param input The input board.
     * @param direction The direction to flip.
     * @return The rotated board.
     */
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
                    result[row][col] = input[col][numberOfColumns - row - 1];
        }

        return result;
    }

    /**
     * Search for all possible states,
     * given rotation and flips.
     * @param input The input board.
     * @param visitedStates The visited states.
     * @param results The results.
     */
    public void searchPossibleStates(int[][] input, Set<String> visitedStates, PossibleStateContainer results) {
        int numberOfRows = input.length, numberOfColumns = input[0].length;
        if (numberOfRows < numberOfColumns) results.p.add(input);
        else results.q.add(input);

        // Search for all possible rotations.
        for (int rotate : POSSIBLE_ROTATES) {
            int[][] newState = this.rotate(input, rotate);
            String rep = this.getStringRepresentationOfABoard(newState);
            if (!visitedStates.contains(rep)) {
                visitedStates.add(rep);
                searchPossibleStates(newState, visitedStates, results);
            }
        }

        // Search for all possible rotations.
        for (int flip: POSSIBLE_FLIPS) {
            int[][] newState = this.flip(input, flip);
            String rep = this.getStringRepresentationOfABoard(newState);
            if (!visitedStates.contains(rep)) {
                visitedStates.add(rep);
                searchPossibleStates(newState, visitedStates, results);
            }
        }
    }

    /**
>>>>>>> d4329593990d29049c5087448bc24692d7f01ac4
     * Essentially read the input from stdin.
     * @return An array of test cases.
     */
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
