public class Search {
    public static int sequentialIndexOf(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int binaryIndexOf(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right)/2;
            if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int recursiveBinaryIndexOf(int[] array, int target, int left, int right) {
        int mid = (left + right) / 2;
        if (right < left) {
            return -1;
        } else if (array[mid] == target) {
            return mid;
        } else if (array[mid] < target) {
            return recursiveBinaryIndexOf(array, target, mid + 1, right);
        } else {
            return recursiveBinaryIndexOf(array, target, left, mid - 1);
        }
    }
}