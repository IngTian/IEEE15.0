package mcgill.ieeextreme.oasis.languageLearning;

import java.util.*;
import java.io.*;

public class Solution {

    private int K, N;
    private String[] words;

    public static void main(String[] args) {

    }

    private void readInput(){
        Scanner scanner = new Scanner(System.in);
        this.N = scanner.nextInt();
        this.K = scanner.nextInt();
        this.words = new String[this.N];

        for (int wordIndex = 0; wordIndex < this.N; wordIndex++)
            this.words[wordIndex] = scanner.next();
    }
}
