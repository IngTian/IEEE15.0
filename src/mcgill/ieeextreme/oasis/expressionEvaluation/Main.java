package mcgill.ieeextreme.oasis.expressionEvaluation;// Don't place your source in a package
import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.BigInteger;
// import
// Please name your class Main
// package mcgill.ieeextreme.oasis.expressionEvaluation;
class Main{
    //    class TestCase{
//        Stack<Integer> stack = new Stack<>();
//    }
    public static void main(String[] args){
        // String p = "*+2";
        Main k = new Main();
        // k.eval(p.toCharArray());
        Scanner s = new Scanner(System.in);
        int nums = s.nextInt();
//        System.out.println(nums);
        for (int i =0; i < nums; i++){
            char[] arr = s.next().trim().toCharArray();
//            ;
//            String s1 = s.nextLine()
            // System.out.println("_______");
//            System.out.println(s1);
            k.eval(arr);
        }
        // System.out.println(s.nextLine());
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
    public void eval(char[] tokens){
        // Stack for numbers: 'values'
        Stack<Integer> digit = new
                Stack<Integer>();
        int count = 0;
        // Stack for Operators: 'ops'
        Stack<Character> operator = new
                Stack<Character>();
        if (tokens[tokens.length-1] == '+'||
                tokens[tokens.length-1] == '-' ||
                tokens[tokens.length-1] == '*' ||
                tokens[tokens.length-1] == '/'){
            System.out.println("invalid");
            return;
        }
        for(int i = 0; i < tokens.length; i++){
            // if (tokens[i] == ' ')
            //     continue;
            if (tokens[i] == '('){
                operator.push(tokens[i]);
                count+= 1;
            }
            else if (tokens[i] >= '0' && tokens[i] <= '9'){
                int cur_val = 0;
                while(i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9'){
                    cur_val *= 10 ;
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
                    return;
                }
                if (i == 0 ){
                    System.out.println("invalid");
                    return;
                }
                if (i >0 && tokens[i-1] == '('){
                    System.out.println("invalid");
                    return;
                }
                while (operator.peek() != '(')
                    digit.push(applyOp(operator.pop(),digit.pop(),digit.pop()));

                if (operator.size() == 0) {
                    System.out.println("invalid");
                    return;
                }
                operator.pop();
            }
            else if (tokens[i] == '+' ||
                    tokens[i] == '-' ||
                    tokens[i] == '*' ||
                    tokens[i] == '/')
            {

                if (i==0){
                    System.out.println("invalid");
                    return;
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
            else{
                System.out.println("invaild");
                return;
            }
        }
        if (count != 0){
            System.out.println("invalid");
            return;
        }
        if (operator.size()>digit.size()-1){
            System.out.println("invalid");
            return;
        }
        while (!operator.empty())
            digit.push(applyOp(operator.pop(),
                    digit.pop(),
                    digit.pop()));

        // Top of 'values' contains
        // result, return it
        int res = digit.pop();

        // res = (res + 1000000007 ) %  1000000007;

        System.out.println(res);
        return;
    }

    public static int applyOp(char op,
                              int b, int a)
    {
        switch (op)
        {
            case '+':
                return (a + b);
            case '-':
//                System.out.println("a _ b _ op" +op + " " + a + " " + b);
                return a - b;
            case '*': {
                // BigInteger bigA = new BigInteger(String.valueOf(a));
                // BigInteger bigB = new BigInteger(String.valueOf(b));
                // BigInteger mod = new BigInteger(String.valueOf(1000000007));
                // BigInteger result = bigA.multiply(bigB).mod(mod);
                // return result.intValue();
                return a * b;
            }
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
