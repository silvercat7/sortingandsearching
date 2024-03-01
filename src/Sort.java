import java.util.ArrayList;
import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] test1 = {5, 2, 5, 5, 3, 3, 3, 2, 2, 2, 6, 3, 3, 5};
        int[] test2 = {7, 3, 4, 2, 6, 9, 1, 2};
        int[] test3 = {9, 8, 7, 6, 5, 4, 3, 2};
        int[] test4 = {3};
        int[] test5 = {};
        ArrayList<int[]> tests = new ArrayList<>();
        tests.add(test1);
        tests.add(test2);
        tests.add(test3);
        tests.add(test4);
        tests.add(test5);
        for( int i = 0; i < tests.size(); i++) {
            int[] test = tests.get(i);
            System.out.println("test " + i);
            System.out.println("before: " + Arrays.toString(test));

            System.out.println("after: " + Arrays.toString(test));
            System.out.println("================================");
        }
    }
}
