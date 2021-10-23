package mcgill.ieeextreme.oasis.languageLearning;

import java.util.*;

public class Solution {

    private int K, N;
    private String[] words;
    private static final int MOD = 1000000007;
    private Scanner scanner;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = scanner.nextInt();

        for (int testCaseNum = 0; testCaseNum < numberOfTestCases; testCaseNum++) {
            Solution solver = new Solution();
            solver.scanner = scanner;
            solver.readInput();
            System.out.println(solver.computeTotalUniqueSentences());
        }
    }

    public void readInput() {
        this.N = scanner.nextInt();
        this.K = scanner.nextInt();
        this.words = new String[this.N];

        for (int wordIndex = 0; wordIndex < this.N; wordIndex++)
            this.words[wordIndex] = scanner.next();
    }

    public int computeTotalUniqueSentences() {
        long[] dp = new long[this.N];
        for (int i = 0; i < this.N; i++)
            dp[i] = 1;

        long sumTotal = 0;

        for (int i = 0; i < this.N; i++)
            // Starting from i-K-1, going backwards.
            for (int j = i - K - 1; j >= 0; j--)
                if (!this.words[j].equals(this.words[i])) {
                    dp[i] += dp[j];
                    dp[i] %= Solution.MOD;
                }

        // Count the total number of unique words.
        long sum = 0;
        HashSet<String> visitedWords = new HashSet<>();
        for (int i = this.N - 1; i >= 0; i--)
            if (!visitedWords.contains(this.words[i])) {
                sum += dp[i];
                sum %= Solution.MOD;
                visitedWords.add(this.words[i]);
            }
        return (int) sum % Solution.MOD;
    }
}
