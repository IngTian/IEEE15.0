package mcgill.ieeextreme.oasis.spiedRedBlue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Solution {

    enum Country {
        RED, BLUE
    }

    class Spy {
        String name;
        Spy immediateSupervisor;
        Country country;

        public Spy(String name, Country country, Spy supervisor) {
            this.name = name;
            this.country = country;
            this.immediateSupervisor = supervisor;
        }
    }

    public Spy headOfR, headOfB;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = scanner.nextInt();
        for (int i = 0; i < numberOfTestCases; i++) {
            Solution solver = new Solution();

            // Initialization
            int numberOfRedSpies = scanner.nextInt(), numberOfBlueSpies = scanner.nextInt(), numberOfEvents = scanner.nextInt();
            HashMap<String, Spy> spyMap = new HashMap<>();
            solver.initializeSpies(Country.RED, numberOfRedSpies, spyMap);
            solver.initializeSpies(Country.BLUE, numberOfBlueSpies, spyMap);
            for (int j = 2; j <= numberOfRedSpies; j++)
                spyMap.get("R" + j).immediateSupervisor = spyMap.get(scanner.next());
            for (int j = 2; j <= numberOfBlueSpies; j++)
                spyMap.get("B" + j).immediateSupervisor = spyMap.get(scanner.next());
            solver.headOfB = spyMap.get("B1");
            solver.headOfR = spyMap.get("R1");

            // Process Events
            for (int e = 0; e < numberOfEvents; e++) {
                String typeOfEvent = scanner.next();
                switch (typeOfEvent) {
                    case "w": {
                        solver.competeTwoSpies(spyMap.get(scanner.next()), spyMap.get(scanner.next()));
                        break;
                    }
                    case "c": {
                        solver.crossOver(spyMap.get(scanner.next()), spyMap.get(scanner.next()));
                        break;
                    }
                }
            }
        }
    }

    public void initializeSpies(Country country, int numberOfSpies, HashMap<String, Spy> spyMap) {
        for (int i = 1; i <= numberOfSpies; i++) {
            String name = country == Country.RED ? "R" + i : "B" + i;
            spyMap.put(name, new Spy(name, country, null));
        }
    }

    public void crossOver(Spy x, Spy newImmediateSupervisor) {
        x.country = newImmediateSupervisor.country;
        x.immediateSupervisor = newImmediateSupervisor;
    }

    public void competeTwoSpies(Spy a, Spy b) {
        int aScore = this.getIterations(a, a.country == Country.RED ? headOfR : headOfB);
        int bScore = this.getIterations(b, b.country == Country.RED ? headOfR : headOfB);

        if (aScore == -1 && bScore == -1)
            System.out.println("NONE");
        else if (aScore == bScore && a.country != b.country)
            System.out.println("TIE " + aScore);
        else if(aScore == -1)
            System.out.println(b.country == Country.RED ? "RED " + bScore : "BLUE " + bScore);
        else if(bScore == -1)
            System.out.println(a.country == Country.RED ? "RED " + aScore : "BLUE " + aScore);
        else if (aScore < bScore)
            System.out.println(a.country == Country.RED ? "RED " + aScore : "BLUE " + aScore);
        else
            System.out.println(b.country == Country.RED ? "RED " + bScore : "BLUE " + bScore);
    }

    public int getIterations(Spy s, Spy target) {

        int numberOfIterations = 0;
        Spy pointer = s, fastPointer = s;

        while (true) {
            pointer = pointer.immediateSupervisor;
            numberOfIterations++;

            if (pointer == target)
                return numberOfIterations;

            for (int i = 0; i < 2; i++) if (fastPointer != null) fastPointer = fastPointer.immediateSupervisor;

            if (fastPointer == pointer) return -1;
        }
    }

}
