package mcgill.ieeextreme.oasis.ieeeWinner;

import java.util.Locale;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        String[] names = {
                "Team 1",
                "Knapsackers@UNT",
                "MoraSeekers",
                "SurpriseTeam",
                "CuSAT",
                "DongskarPedongi",
                "cofrades",
                "viRUs",
                "TeamName",
                "TeamEPFL1",
                "whatevs",
                "WildCornAncestors",
                "TheCornInTheFields",
                "Aurora"
        };
        for (String str: names)
            System.out.println("Name: " + str + " HashCode: " + str.toLowerCase().hashCode() % 15);
    }

}
