package mcgill.ieeextreme.oasis.riggedDice;

import sun.jvm.hotspot.types.JDoubleField;

import java.util.*;

public class Solution {

    public static final double[] FAIR_PROB = {1.0 / 6, 1.0 / 6, 1.0 / 6, 1.0 / 6, 1.0 / 6, 1.0 / 6};
    public static final double[] RIGGED_PROB = {1.0 / 7, 1.0 / 7, 1.0 / 7, 1.0 / 7, 1.0 / 7, 2.0 / 7};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfGames = scanner.nextInt();
        for (int game = 0; game < numberOfGames; game++) {
            int numberOfRounds = scanner.nextInt(), aliceScore = 0, bobScore = 0;
            int[] dice1 = new int[6], dice2 = new int[6];
            boolean a1InAlice = true;
            for (int round = 0; round < numberOfRounds; round++) {
                int aliceToss = scanner.nextInt(), bobToss = scanner.nextInt();

                // Keep track of the toss.
                if (a1InAlice) {
                    dice1[aliceToss - 1] += 1;
                    dice2[bobToss - 1] += 1;
                } else {
                    dice1[bobToss - 1] += 1;
                    dice2[aliceToss - 1] += 1;
                }

                // Keep track of the total score.
                aliceScore += aliceToss;
                bobScore += bobToss;
                if (aliceScore != bobScore) a1InAlice = !a1InAlice;
            }

            // Scenario 1 - Dice 1 is rigged.
            double probabilityOfScenario1 = calculateLogProbability(dice1, RIGGED_PROB) + calculateLogProbability(dice2, FAIR_PROB);

            // Scenario 2 - Dice 2 is rigged.
            double probabilityOfScenario2 = calculateLogProbability(dice2, RIGGED_PROB) + calculateLogProbability(dice1, FAIR_PROB);

            if (probabilityOfScenario1 > probabilityOfScenario2)
                System.out.println(1);
            else
                System.out.println(2);
        }
    }

    public static double calculateLogProbability(int[] tosses, double[] probDistribution){
        double result = 0;
        for (int i = 0; i < 6; i++)
            result += tosses[i] * Math.log(probDistribution[i]);
        return result;
    }

}
