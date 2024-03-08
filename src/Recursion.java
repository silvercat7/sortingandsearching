public class Recursion {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(fibonacci(i));
        }
        for (int i = 1; i <= 10; i++) {
            System.out.println(factorial(i));
        }
        System.out.println("(()): " + nested("(())"));
        System.out.println(": " + nested(""));
        System.out.println("(((()))): " + nested("(((())))"));
        System.out.println("(((~))): " + nested("(((~)))"));
        System.out.println("((): " + nested("(()"));
        System.out.println("()(): " + nested("()()"));
        System.out.println("hi(())ih: " + nested("hi(())ih"));
    }

    public static long fibonacci(long n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static long factorial(long n) {
        if (n == 1) {
            System.out.print("1 = ");
            return 1;
        } else {
            System.out.print(n + " * ");
            return n * factorial(n - 1);
        }
    }

    public static boolean nested(String input) {
        if (input.equals("") || input.equals("()")) {
            return true;
        } else if (input.length() == 1) {
            return false;
        } else {
            if (input.charAt(0) == '(' && input.charAt(input.length() - 1) == ')') {
                return nested(input.substring(1, input.length() - 1));
            } else {
                return false;
            }
        }
    }
}