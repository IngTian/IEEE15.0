package mcgill.ieeextreme.oasis.languageLearning;

import java.lang.reflect.Array;
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
        long[] dp = new long[this.N], preSum = new long[this.N];
        HashMap<String, ArrayList<Integer>> wordPositionMap = new HashMap<>();

        for (int i = 0; i < this.N; i++) {
            // Using the sum we have up till i - K - 1
            // minus all counts of the same word appear before.
            dp[i] = ((i >= K + 1 ? preSum[i - K - 1]: 0) + 1) % MOD;
            if (wordPositionMap.containsKey(this.words[i]))
                for (int position : wordPositionMap.get(this.words[i]))
                    if (position <= i - K - 1)
                        dp[i] = (dp[i] - dp[position]) % MOD;
                    else
                        break;

            // Record the previous locations of each word.
            if (wordPositionMap.containsKey(this.words[i]))
                wordPositionMap.get(this.words[i]).add(i);
            else {
                ArrayList<Integer> newList = new ArrayList<>();
                newList.add(i);
                wordPositionMap.put(this.words[i], newList);
            }
            preSum[i] = (i == 0 ? dp[i] : preSum[i - 1] + dp[i]) % MOD;
        }


        // Count the total number of unique words.
        long counts = 0;
        for (String word : wordPositionMap.keySet()) {
            ArrayList<Integer> wordPositions = wordPositionMap.get(word);
            counts = (counts + dp[wordPositions.get(wordPositions.size() - 1)]) % MOD;
        }

        return (int)counts % MOD;
    }
}
