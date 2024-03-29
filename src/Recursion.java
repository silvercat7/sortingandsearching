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
//        int[] arr = {2, 4, 8};
//        System.out.println(groupSum(0, arr, 10));
//        System.out.println(groupSum(0, arr, 14));
//        System.out.println(groupSum(0, arr, 9));
        int[] array = {13, 92, 12, 304, 4, 48, 66, 724, 93, 104, 34, 5, 22, 96, 89, 12, 83, 29, 68, 7, 104};
        System.out.println(findLargest(array, 0, 20));
    }

    public static long fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static long tripleFibonacci(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        } else {
            return tripleFibonacci(n - 1) + tripleFibonacci(n - 2) + tripleFibonacci(n - 3);
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

    public static String nestedStars1(String input) {
        if (input.length() <= 1) {
            return input;
        } else {
            return input.charAt(0) + "*" + nestedStars1(input.substring(1));
        }
    }

    public static String nestedStars2(String input) {
        if (input.length() <= 1) {
            return input;
        } else {
            return nestedStars2(input.substring(0, input.length()/2)) + "*" + nestedStars2(input.substring(input.length()/2));
        }
    }

    public static int countX(String input) {
        if (!input.contains("x")) {
            return 0;
        } else {
            return countX(input.substring(input.indexOf("x") + 1)) + 1;
        }
    }

    public static boolean groupSum(int start, int[] input, int target) {
        if (start >= input.length) {
            return target == 0;
        } else {
            return groupSum(start + 1, input, target - input[start]) || groupSum(start + 1, input, target);
        }
    }

    public static int findLargest(int[] array, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            return array[leftIndex];
        }
        int mid = (leftIndex + rightIndex)/2;
        int left = findLargest(array, leftIndex, mid);
        int right = findLargest(array, mid + 1, rightIndex);
        return Math.max(left, right);
    }
}