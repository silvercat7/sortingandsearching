import java.util.Arrays;

public class Test {
    private static final int NUM_ITERATIONS = 50;
    private static final int[] SIZES_TO_TEST = {10, 100, 500, 1000, 2000, 4000, 6000, 8000, 10000};

    public static void main(String[] args) {
        long insertionTotalTime;
        long selectionTotalTime;
        System.out.println("array size, insertion sort (in milliseconds), selection sort (in milliseconds)");
        for (int arraySize : SIZES_TO_TEST) {
            insertionTotalTime = 0;
            selectionTotalTime = 0;
            for (int i = 0; i < NUM_ITERATIONS; i++) {
                int[] baseArray = createRandomArray(arraySize);
                int[] copy = Arrays.copyOf(baseArray, baseArray.length);
                long startTime = System.currentTimeMillis();
                Sort.insertionSort(copy);
                long endTime = System.currentTimeMillis();
                insertionTotalTime += (endTime - startTime);
                copy = Arrays.copyOf(baseArray, baseArray.length);
                startTime = System.currentTimeMillis();
                Sort.selectionSort(copy);
                endTime = System.currentTimeMillis();
                selectionTotalTime += (endTime - startTime);
            }
            System.out.println(arraySize + ", " + (double) insertionTotalTime/NUM_ITERATIONS + ", " + (double) selectionTotalTime/NUM_ITERATIONS);
        }
    }

    public static int[] createRandomArray(int arraySize) {
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = (int) (Math.random() * arraySize) + 1;
        }
        return array;
    }
}
