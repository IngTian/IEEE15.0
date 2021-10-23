package mcgill.ieeextreme.oasis.imageSimilarity;

import java.util.Arrays;

public class test {
    public static void main(String[] args){
//        int [][] mat3 = new int[10][10];
//        [
//        [1, 0, 1, 0, 0, 0],
//        [0, 1, 1, 1, 0, 0],
//        [0, 1, 1, 1, 0, 0],
//        [0, 1, 1, 1, 0, 0],
//        [0, 1, 1, 1, 0, 0]]
        // m1 <= m2, n1 <= n2
//        int [][] mat1 = {
//                {1, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 1},
//                {0, 0, 1, 1, 1}
//        };
//        int [][] mat2 = {
//                {0, 0, 1, 0, 0},
//                {1, 1, 1, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 1, 1},
//                {0, 0, 0, 1, 0}
//
//
//
//        };
        int [][] mat1 = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 1},
        };
        int [][] mat2 = {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0},



        };
//        int [][] mat1 = {
//                {0, 1, 0, 0, 0},
//                {0, 1, 0, 0, 0},
//                {0, 1, 1, 1, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0}
//
//        };
//        int [][] mat2 = {
//                {0, 0, 0},
//                {1, 1, 1},
//                {0, 0, 1}
//        };

//        int [][] mat2 = {
//                {0, 1, 1, 1, 2, 3},
//                {0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0},
//                {1, 1, 1, 1, 1, 1},
//                {0, 0, 0, 0, 0, 0},
//        };
        int m1 = mat1.length; // 4
        int n1 = mat1[0].length; //6
        int m2 = mat2.length; // 5
        int n2 = mat2[0].length; //4
        //
//        k =
        test t= new test();
        int k = t.process(mat1, m1, n1, mat2, m2, n2);
//        System.out.println(Arrays.deepToString(mat3));
        System.out.println(k);
    }

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

//        if (m1 >= m2 && n1 >= n2){
//            System.out.println("case 1");
//            return calculate_max(mat2, m2, n2, mat1, m1, n1);
//        }
//        else if (m1 < m2 && n1 > n2){
//            System.out.println("case 2");
//            int[][] new_mat2 =  increase_dim(m1, n1, m2, n2, mat2);
//            int res1 =  calculate_max(mat1, m1, n1, new_mat2, m2, n1);
//
//            int[][] new_mat1 = increase_dim(m2, n2, m1, n1, mat1);
//            int res2 = calculate_max(mat2, m2, n2, new_mat1, m2, n1);
//            return Math.max(res2, res1);
////            return res1;
//        }
//        else if (m1 > m2 && n1 < n2){
//            System.out.println("case 3");
//            int[][] new_mat2 =  increase_dim(m1, n1, m2, n2, mat2);
//            int res1 =  calculate_max(mat1, m1, n1, new_mat2, m1, n2);
//
//            int[][] new_mat1 = increase_dim(m2, n2, m1, n1, mat1);
//            int res2 = calculate_max(mat2, m2, n2, new_mat1, m1, n2);
//            return Math.max(res2, res1);
////            return res1;
//        }
        int[][] new_mat2 = increase_dim(m1, n1, m2, n2, mat2);

//        System.out.println("case 4");
        return calculate_max(mat1, m1, n1, new_mat2, m2 + m1*2, n2 + n1*2);
    }
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
}
