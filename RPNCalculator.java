/**
 * @author: Yuan Jing Vincent Yan
 * @email: kaikulimu@yahoo.com.hk
 */

import java.util.Scanner;

/**
   This is a basic RPN calculator that supports integer operands as well as the
   integer operators "+", "-", "*", and "/".
*/
public final class RPNCalculator {
    private static boolean exit;
    private static Stack<Integer> mem;
    private static int input;
    private static int op1;
    private static int op2;
    private static boolean validOp;

    private RPNCalculator() {}

    /** This is the main method than runs the RPN calculator.
      @param args command-line arguments. None in this case
    */
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        exit = false;
        mem = new ArrayStack<Integer>();

        while (!exit) {
            System.out.print(">");
            operate(kb.next());
        }
    }

    /** Perform an operation based on the user input.
        @param s the user input
    */
    public static void operate(String s) {
        switch(s) {
            case "!":
                exit = true;
                break;
            case "?":
                System.out.println(mem);
                break;
            case ".":
                try {
                    System.out.println(mem.top());
                    mem.pop();
                } catch (EmptyStackException e) {
                    System.err.println("? There is no element in the stack.");
                }
                break;
            case "+":
                add();
                break;
            case "-":
                subtract();
                break;
            case "*":
                multiply();
                break;
            case "/":
                divide();
                break;
            default:
                try {
                    input = Integer.parseInt(s);
                    mem.push(input);
                } catch (NumberFormatException e) {
                    System.err.println("? Invalid calculator operation. "
                        + "Please enter again:");
                }
        }
    }

    private static void popInts() {
        validOp = true;
        try {
            op1 = mem.top();
            mem.pop();
            try {
                op2 = mem.top();
                mem.pop();
            } catch (EmptyStackException e) {
                System.err.println("? Not enough integer operands "
                    + "to perform calculation.");
                mem.push(op1);
                validOp = false;
            }
        } catch (EmptyStackException e) {
            System.err.println("? Not enough integer operands to "
                + "perform calculation.");
            validOp = false;
        }
    }

    private static void add() {
        popInts();
        if (validOp) {
            mem.push(op2 + op1);
        }
    }

    private static void subtract() {
        popInts();
        if (validOp) {
            mem.push(op2 - op1);
        }
    }

    private static void multiply() {
        popInts();
        if (validOp) {
            mem.push(op2 * op1);
        }
    }

    private static void divide() {
        popInts();
        if (validOp) {
            try {
                mem.push(op2 / op1);
            } catch (ArithmeticException e) {
                System.err.println("? Division by 0 not allowed.");
            }
        }
    }
}
