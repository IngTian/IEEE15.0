package mcgill.ieeextreme.oasis.expressionEvaluation;// Don't place your source in a package

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.*;
import java.math.BigInteger;
import java.util.*;

class Main {
    public static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfExpressions = scanner.nextInt();
        for (int testCaseNumber = 0; testCaseNumber < numberOfExpressions; testCaseNumber++) {
            String expression = scanner.next();
            try {
                System.out.println(evaluate(expression));
            } catch (Exception e) {
                // Do nothing
            }
        }
    }

    public static int evaluate(String expression) throws Exception {
        char[] tokens = expression.toCharArray();

        Stack<Integer> values = new Stack<Integer>();

        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++) {

            if (tokens[i] == ' ') continue;

            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuffer buffer = new StringBuffer();

                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') buffer.append(tokens[i++]);
                values.push(Integer.parseInt(buffer.toString()));

                i--;
            } else if (tokens[i] == '(')
                ops.push(tokens[i]);
            else if (tokens[i] == ')') {
                if (i == 0 || tokens[i - 1] == '(') {
                    System.out.println("invalid");
                    throw new Exception("Wrong Argument!");
                }

                while (!ops.empty() && ops.peek() != '(') values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                if (ops.empty()) {
                    System.out.println("invalid");
                    throw new Exception("Wrong Argument!");
                }
                ops.pop();
            }
            // Current token is an operator.
            else if (isOperator(tokens, i)) {
                if (i - 1 < 0 || i + 1 >= tokens.length || isOperator(tokens, i - 1) || isOperator(tokens, i + 1) || tokens[i - 1] == '(' || tokens[i + 1] == ')') {
                    System.out.println("invalid");
                    throw new Exception("Wrong Argument!");
                }

                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                ops.push(tokens[i]);
            }
        }

        // Entire expression has been
        // parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty()) values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        // Top of 'values' contains
        // result, return it
        return values.pop();
    }

    public static boolean isDigit(char[] arr, int idx) {
        return idx >= 0 && idx < arr.length && arr[idx] >= '0' && arr[idx] <= '9';
    }

    public static boolean isOperator(char[] arr, int idx) {
        return idx >= 0 && idx < arr.length &&
                (arr[idx] == '+' || arr[idx] == '-' || arr[idx] == '*' || arr[idx] == '/');
    }

    // Returns true if 'op2' has higher
    // or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    // A utility method to apply an
    // operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+':
                return (a + b) % MOD;
            case '-':
                return (a - b + MOD) % MOD;
            case '*': {
                BigInteger aB = new BigInteger(String.valueOf(a));
                BigInteger bB = new BigInteger(String.valueOf(b));
                BigInteger mod = new BigInteger(String.valueOf(MOD));
                BigInteger product = aB.multiply(bB).mod(mod);
                return product.intValue();
            }
            case '/':
                if (b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
}
