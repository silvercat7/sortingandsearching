import java.util.ArrayList;
import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] test1 = {5, 2, 5, 5, 3, 3, 3, 2, 2, 2, 6, 3, 3, 5};
        int[] test2 = {7, 3, 4, 2, 6, 9, 1, 2};
        int[] test3 = {9, 8, 7, 6, 5, 4, 3, 2};
        int[] test4 = {3};
        int[] test5 = {};
//        String[] test1 = {"latte", "<3", "latte", "latte", "cat", "cat", "cat", "<3", "<3", "<3", "luna<3", "cat", "cat", "latte"};
//        String[] test2 = {"kitties", "cat", "luna", "<3", "cat <3", "latte", "luna", "cat", "<3"};
//        String[] test3 = {"kitty baby", "smallest", "kitties", "cat <3", "latte", "luna", "cat", "<3"};
//        String[] test4 = {"cat"};
//        String[] test5 = {};
        ArrayList<int[]> tests = new ArrayList<>();
        tests.add(test1);
        tests.add(test2);
        tests.add(test3);
        tests.add(test4);
        tests.add(test5);
        for( int i = 0; i < tests.size(); i++) {
            int[] test = tests.get(i);
            System.out.println("test " + (i + 1));
            System.out.println("before: " + Arrays.toString(test));
            doubleSelectionSort(test);
            System.out.println("after: " + Arrays.toString(test));
            System.out.println("-------------------------");
        }
    }

    public static void bubbleSort(int[] array) {
        boolean sorting = true;
        while (sorting) {
            sorting = false;
            for (int index = 0; index < array.length - 1; index++) {
                if (array[index] > array[index + 1]) {
                    swap(index, index + 1, array);
                    sorting = true;
                }
            }
        }
    }

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            int minIndex = i;
            for (int index = i; index < array.length; index++) {
                if (array[index] < min) {
                    min = array[index];
                    minIndex = index;
                }
            }
            swap(i, minIndex, array);
        }
    }

    public static void selectionSort(String[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = array[i].length();
            int minIndex = i;
            for (int index = i; index < array.length; index++) {
                if (array[index].length() < min) {
                    min = array[index].length();
                    minIndex = index;
                }
            }
            swap(i, minIndex, array);
        }
    }

    public static void doubleSelectionSort(int[] array) {
        for (int i = 0; i < array.length - i; i++) {
            int min = array[i];
            int minIndex = i;
            int max = array[i];
            int maxIndex = i;
            for (int index = i; index < array.length - i; index++) {
                if (array[index] <= min) {
                    min = array[index];
                    minIndex = index;
                } else if (array[index] >= max) {
                    max = array[index];
                    maxIndex = index;
                }
            }
            doubleSwap(i, minIndex, array.length - i - 1, maxIndex, array);
        }
    }

    public static void insertionSort(int[] array) {
        for (int index = 1; index < array.length; index++) {
            int value = array[index];
            int place = index;
            array[place] = array[place - 1];
            while (place > 0 && value < array[place - 1]) {
                array[place] = array[place - 1];
                place--;
            }
            array[place] = value;
        }
    }

    public static void swap(int a, int b, int[] array) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void swap(int a, int b, String[] array) {
        String temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void doubleSwap(int a, int b, int c, int d, int[] array) {
        int temp = array[a];
        int temp2 = array[c];
        array[a] = array[b];
        array[c] = array[d];
        array[b] = temp;
        array[d] = temp2;
    }
}
