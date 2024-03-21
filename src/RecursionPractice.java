public class RecursionPractice {
    public static void main(String[] args) {

    }

    public static int countX(String input) {
        if (!input.contains("x")) {
            return 0;
        } else {
            return countX(input.substring(input.indexOf("x") + 1)) + 1;
        }
    }

    public static String removeX(String input) {
        if (!input.contains("x")) {
            return input;
        } else {
            int index = input.indexOf("x");
            return removeX(input.substring(0, index) + input.substring(index + 1));
        }
    }

    public static boolean isPalindrome(String input) {
        if (input.equals("") || input.length() == 1) {
            return true;
        } else if (input.substring(0, 1).equals(input.substring(input.length() - 1))) {
            return isPalindrome(input.substring(1, input.length() - 1));
        } else {
            return false;
        }
    }

    public static int minIndex(int[] input, int start, int end) {
        if (start == end) {
            return start;
        }
        int mid = (start + end)/2;
        int leftIndex = minIndex(input, start, mid);
        int rightIndex = minIndex(input, mid + 1, end);
        return Math.min(input[leftIndex], input[rightIndex]);
    }

    public static String removeDoubles(String input) {
        if (input.length() <= 1) {
            return input;
        } else {
            if (input.charAt(0) == input.charAt(1)) {
                return removeDoubles(input.substring(1));
            }
        }
        return "-1";
    }
}