package mcgill.ieeextreme.oasis.expressionEvaluation;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
public class Solution{
//    class TestCase{
//        Stack<Integer> stack = new Stack<>();
//    }
    public static void main(String[] args){
//        String p = "*+2";
        Solution k = new Solution();
//        k.eval(p.toCharArray());
        Scanner s = new Scanner(System.in);
        int nums = s.nextInt();
//        System.out.println(nums);
        for (int i =0; i < nums; i++){
            char[] arr = s.next().toCharArray();
//            ;
//            String s1 = s.nextLine()
//            System.out.println("_______");
//            System.out.println(s1);
            k.eval(arr);
        }
//        System.out.println(s.nextLine());
//        char[] arr = s.next().toCharArray();
//        k.eval(arr);
    }

    public static boolean hasPrecedence(
            char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') &&
                (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
    public int eval(char[] tokens){
        // Stack for numbers: 'values'
        Stack<Integer> digit = new
                Stack<Integer>();
        int count = 0;
        // Stack for Operators: 'ops'
        Stack<Character> operator = new
                Stack<Character>();
        for(int i = 0; i < tokens.length; i++){
            if (tokens[i] == ' ')
                continue;
            else if (tokens[i] == '('){
                operator.push(tokens[i]);
                count+= 1;
            }
            else if (tokens[i] >= '0' && tokens[i] <= '9'){
                int cur_val = 0;
                while(i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9'){
                    cur_val *= 10;
                    int k = tokens[i] - '0';
                    cur_val += k;
                    digit.push(cur_val);
                    i++;
                }
                i--;
            }
            else if (tokens[i] == ')') {
                count -= 1;
                if (count < 0) {
                    System.out.println("invalid");
                    return 0;
                }
                if (i == 0 || tokens[i-1] == '('){
                    System.out.println("invalid");
                    return 0;
                }
                while (operator.peek() != '(')
                    digit.push(applyOp(operator.pop(),
                            digit.pop(),
                            digit.pop()));
                operator.pop();
            }
            else if (tokens[i] == '+' ||
                    tokens[i] == '-' ||
                    tokens[i] == '*' ||
                    tokens[i] == '/')
            {
                // While top of 'ops' has same
                // or greater precedence to current
                // token, which is an operator.
                // Apply operator on top of 'ops'
                // to top two elements in values stack
                if (i==0){
                    System.out.println("invalid");
                    return 0;
                }
                while (!operator.empty() &&
                        hasPrecedence(tokens[i],
                                operator.peek()))
                    digit.push(applyOp(operator.pop(),
                            digit.pop(),
                            digit.pop()));

                // Push current token to 'ops'.
                operator.push(tokens[i]);
            }
        }
        if (count != 0){
            System.out.println("invalid");
            return 0;
        }
        while (!operator.empty())
            digit.push(applyOp(operator.pop(),
                    digit.pop(),
                    digit.pop()));

        // Top of 'values' contains
        // result, return it
        System.out.println(digit.pop());
        return 0;
    }

    public static int applyOp(char op,
                              int b, int a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    System.out.println("invalid");
                    return 0;
                }
                return a / b;
        }
        return 0;
    }
}
