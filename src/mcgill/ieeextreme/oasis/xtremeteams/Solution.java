package mcgill.ieeextreme.oasis.xtremeteams;

import java.math.BigInteger;

public class Solution {

    public static void main(String[] args) {

    }

    public static BigInteger search(
            int currentStudentIndex,
            int studentSearchStartIndex,
            int totalNumberOfStudents,
            int[] students,
            int currentAnswer
    ) {
        BigInteger result = new BigInteger("0");

        if (currentStudentIndex == 3) {
            // The 3rd student.
            int answer = 0;
            for (int idx = studentSearchStartIndex; idx < totalNumberOfStudents; idx++)
                if (~(students[idx] | currentAnswer) == 0) answer++;
            return new BigInteger(String.valueOf(answer));
        } else {
            // 1st or 2nd student
            int lastChoice = -1;
            BigInteger lastIncrement = new BigInteger("0");

            for (int idx = studentSearchStartIndex; idx < totalNumberOfStudents; idx++) {
                int choice = students[idx];
                if (choice == lastChoice) {
                    result = result.add(lastIncrement);
                    continue;
                }

                if (~(choice | currentAnswer) == 0) {
                    int remainingStudents = totalNumberOfStudents - idx - 1;
                    if (currentStudentIndex == 1)
                        lastIncrement = new BigInteger(String.valueOf(remainingStudents)).multiply(new BigInteger(String.valueOf(remainingStudents - 1)));
                    else
                        lastIncrement = new BigInteger(String.valueOf(remainingStudents));

                    result = result.add(lastIncrement);
                } else {
                    lastIncrement = search(currentStudentIndex + 1, idx + 1, totalNumberOfStudents, students, choice | currentAnswer);
                    result = result.add(lastIncrement);
                }

                lastChoice = choice;
            }
        }

        return result;
    }

}
